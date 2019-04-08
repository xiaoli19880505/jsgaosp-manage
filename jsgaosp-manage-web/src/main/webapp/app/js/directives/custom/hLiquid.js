/**
 *
 * <h-liquid val="" realtime="">
 *
 * </h-liquid>
 */
angular.module('app').directive('hLiquid', ['$interval', '$timeout', '$q', 'hUtil', 'GG',
    function ($interval, $timeout, $q, hUtil, GG) {
        return {
            restrict: 'EA',
            scope: {},
            replace: true,
            //template: '<svg></svg>',
            controller: function ($scope, $element, $attrs) {
                var realtime = $attrs['realtime'];
                $scope.id = 'svg' + GG.idx();
                realtime && ($scope.realtime = parseInt(realtime));
                $scope.val = hUtil.toJSON($attrs['val']);
                $scope.opt = hUtil.toJSON($attrs['opt']);
                $element.height($attrs['height']);
                $element[0].id = $scope.id;
                $scope.isTimeNav = true;
                if ($attrs['isTimeNav']) {
                    $scope.isTimeNav = hUtil.toJSON($attrs['isTimeNav']);
                }
            },
            link: function (scope, el, attr) {
                var realtime = scope.realtime,
                    opt = scope.opt,
                    val = scope.val;
                var config = liquidFillGaugeDefaultSettings();
                for (var key in opt) {
                    config[key] = opt[key];
                }
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
                        if (result && result.data && result.data.metrics && result.data.metrics[0] && result.data.metrics[0][0]) {
                            var vv = result.data.metrics[0][0].value;
                            scope.liquid ? scope.liquid.update(vv) :
                                (scope.liquid = loadLiquidFillGauge(scope.id, vv, config));
                        }
                    })
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