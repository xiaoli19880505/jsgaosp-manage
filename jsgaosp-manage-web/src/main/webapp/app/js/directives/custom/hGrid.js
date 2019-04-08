/**
 *
 * <h-grid name="值2" val="" realtime>
 *
 * </h-grid>
 */
angular.module('app').directive('hGrid', ['$interval', '$timeout', '$q', 'hUtil',
    function ($interval, $timeout, $q, hUtil) {
        return {
            restrict: 'E',
            scope: {},
            replace: true,
            template: '<div></div>',
            controller: function ($scope, $element, $attrs) {
                var realtime = $attrs['realtime'],
                    val = $attrs['val'],
                    height = $attrs['height'];
                realtime && ($scope.realtime = parseInt(realtime));
                $scope.val = hUtil.toJSON(val);
                $element.height(height);
                $scope.isTimeNav = true;
                if ($attrs['isTimeNav']) {
                    $scope.isTimeNav = hUtil.toJSON($attrs['isTimeNav']);
                }
            },
            link: function (scope, el, attr) {
                var realtime = scope.realtime,
                    val = scope.val;

                scope.time = '01';
                scope.filter = [];
                if (val.query && val.query.time) {
                    scope.time = val.query.time;
                }
                if (val.query && val.query.filter) {
                    scope.filter = val.query.filter;
                }
                var render = function () {
                    if (!val)return;
                    val.query = {time: scope.time, filters: angular.toJson(scope.filter)};
                    var promise = hUtil.data(val);
                    if (!promise)return;
                    promise.then(function (result) {
                        if (result && result.data && result.data.metrics && result.data.metrics[0]) {
                            var data = result.data.metrics[0];
                            var color = '',
                                content = '';
                            for (var i = 0; i < data.length; i++) {
                                color = data[i].value == 0 ? 'gray' : '#73B648';
                                content += '<div class="text-center" style="padding-top:4px;color:#fff;float:left;' +
                                    'width:58px;margin:8px;height:38px;line-height:35px;background-color:' + color + '">' + (i + 1) + '</div>';
                            }
                            el.html(content);
                        }
                    });
                };
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
]);