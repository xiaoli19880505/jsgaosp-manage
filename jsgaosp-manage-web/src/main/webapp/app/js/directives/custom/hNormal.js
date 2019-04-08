/**
 * name
 * val
 * realtime
 *
 *
 * <h-normal val="{}" realtime="6" >
 *      总收入<h-val/>万元
 *      <h-tb/>
 *      环比<h-hb/>%
 * </h-normal>
 *
 */
angular.module('app').directive('hNormal', ['$interval', '$timeout', '$q', 'hUtil',
    function ($interval, $timeout, $q, hUtil) {
        return {
            restrict: 'E',
            transclude: true,
            scope: true,
            priority: 10,
            template: '<div ng-transclude></div></div>',
            replace: true,
            controller: function ($scope, $element, $attrs) {
                var realtime = $attrs['realtime'];
                realtime && ($scope.realtime = parseInt(realtime));
                $scope.val = hUtil.toJSON($attrs['val']);
                $scope.isTimeNav = true;
                if ($attrs['isTimeNav']) {
                    $scope.isTimeNav = hUtil.toJSON($attrs['isTimeNav']);
                }
            },
            link: function (scope, el, attr) {
                var realtime = scope.realtime;
                var val = scope.val;
                scope.time = '01';
                scope.filter = [];
                if (val&&val.query && val.query.time) {
                    scope.time = val.query.time;
                }
                if (val&&val.query && val.query.filter) {
                    scope.filter = val.query.filter;
                }
                scope.value = 0;
                scope.tb = 0;
                scope.hb = 0;
                var html = angular.element(el)[0].innerHTML;
                var valType = "";
                if (html.indexOf("normal=\"#\"") >= 0) {
                    valType += "HNORMAL";
                }
                //if (html.indexOf("tb=\"#\"") >= 0 && html.indexOf("hb=\"#\"") >= 0) {
                //    valType += "-TB-HB";
                //}
                var render = function () {
                    if (!val)return;
                    val.query = {time: scope.time, filters: angular.toJson(scope.filter), valType: valType};
                    var promise = hUtil.data(val);
                    if (!promise)return;
                    promise.then(function (result) {
                        scope.value = 0;
                        scope.tb = 0;
                        scope.hb = 0;
                        if (result && result.data && result.data.metrics && result.data.metrics[0]) {
                            result.data.metrics[0][1] && (scope.value = result.data.metrics[0][1].value);
                            result.data.metrics[0][2] && (scope.tb = result.data.metrics[0][2].value);
                            result.data.metrics[0][3] && (scope.hb = result.data.metrics[0][3].value);
                        }
                        scope.$emit("normal_refresh", {value: scope.value, tb: scope.tb, hb: scope.hb});
                    });
                };
                scope.$on('normal_refresh', function (event, data) {
                    scope.$broadcast('normal_child_refresh', data);
                });

                render();
                if (realtime > 0) {
                    var interval = $interval(function () {
                        render();
                    }, realtime * 1000);
                }

                var filterDestroy = scope.$on('filter-refresh', function (event, filter) {
                    if (filter && filter.other && scope.filter != filter.other) {
                        scope.filter = filter.other;
                        render();
                    }
                    if (scope.isTimeNav) {
                        if (filter && filter.time && scope.time != filter.time) {
                            scope.time = filter.time;
                            render();
                        }
                    }
                });

                el.on('$destroy', function () {
                    filterDestroy();
                    if (interval) {
                        $interval.cancel(interval);
                    }
                });
            }
        }
    }
]).directive('hVal', ['$interval', '$timeout', '$q', 'hUtil',
    function ($interval, $timeout, $q, hUtil) {
        return {
            restrict: 'E',
            scope: true,
            replace: true,
            template: '<span normal="#"></span>',
            link: function (scope, el, attr) {
                var on = scope.$on('normal_child_refresh', function (event, data) {
                    if (data.value != 0) {
                        el.html(data.value);
                    } else {
                        el.html('<span style="font-size: 12px">暂无数据</span>');
                    }

                });
                el.on('$destroy', function () {
                    on();
                });
            }
        }
    }
]).directive('hTb', ['$interval', '$timeout', '$q', 'hUtil',
    function ($interval, $timeout, $q, hUtil) {
        return {
            restrict: 'E',
            scope: true,
            replace: true,
            template: '<span tb="#"></span>',
            link: function (scope, el, attr) {
                var on = scope.$on('normal_child_refresh', function (event, data) {
                    var value = data.tb;
                    if (value == 0) {
                        el.html('同比&nbsp;<span style="font-size: 12px">暂无数据</span>');
                    } else {
                        var up = '<span class="glyphicon glyphicon-arrow-up"></span>';
                        var down = '<span class="glyphicon glyphicon-arrow-down"></span>';
                        var arrow = value >= 0 ? up : down;
                        var color = value >= 0 ? '#EE4C61' : '#339900';
                        value = value < 0 ? -value : value;
                        el.html('同比&nbsp;<span style="color: ' + color + '">' + arrow + value + '%</span>');
                    }
                });
                el.on('$destroy', function () {
                    on();
                });
            }
        }
    }
]).directive('hHb', ['$interval', '$timeout', '$q', 'hUtil',
    function ($interval, $timeout, $q, hUtil) {
        return {
            restrict: 'E',
            scope: true,
            replace: true,
            template: '<span hb="#"></span>',
            link: function (scope, el, attr) {
                var on = scope.$on('normal_child_refresh', function (event, data) {
                    var value = data.hb;
                    if (value == 0) {
                        el.html('环比&nbsp;<span style="font-size: 12px">暂无数据</span>');
                    } else {
                        var up = '<span class="glyphicon glyphicon-arrow-up"></span>';
                        var down = '<span class="glyphicon glyphicon-arrow-down"></span>';
                        var arrow = value >= 0 ? up : down;
                        var color = value >= 0 ? '#EE4C61' : '#339900';
                        value = value < 0 ? -value : value;
                        el.html('环比&nbsp;<span style="color: ' + color + '">' + arrow + value + '%</span>');
                    }
                });
                el.on('$destroy', function () {
                    on();
                });
            }
        }
    }
]);