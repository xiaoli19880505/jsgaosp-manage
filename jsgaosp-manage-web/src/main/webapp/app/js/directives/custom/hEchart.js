/**
 *
 * <h-echart name="值2" val="" opt="{}" realtime="">
 *
 * </h-echart>
 */
angular.module('app').directive('hEchart', ['$interval', '$timeout', '$q', 'hUtil',
    function ($interval, $timeout, $q, hUtil) {
        return {
            restrict: 'E',
            scope: {},
            replace: true,
            template: '<div></div>',
            controller: function ($scope, $element, $attrs) {
                var realtime = $attrs['realtime'];
                realtime && ($scope.realtime = parseInt(realtime));
                var historytime = $attrs['historyTime'];
                historytime && ($scope.historytime = parseInt(historytime));
                $scope.date = null;
                $scope.date = $element.parent().parent().parent().parent();
                if ($scope.date && $scope.date[0]) {
                    $scope.date = $scope.date[0].outerHTML.match('date="(0)[0123456789]"');
                    if ($scope.date && $scope.date[0]) {
                        $scope.date = $scope.date[0].substr(6, 2);
                    }
                }
                $scope.val = hUtil.toJSON($attrs['val']);
                $scope.opt = hUtil.toJSON($attrs['opt']);
                $scope.type = $attrs['type'];

                $scope.isTimeNav = true;
                if ($attrs['isTimeNav']) {
                    $scope.isTimeNav = hUtil.toJSON($attrs['isTimeNav']);
                }
            },
            link: function (scope, el, attr) {
                var opt = scope.opt,
                    realtime = scope.realtime,
                    historytime = scope.historytime,
                    val = scope.val,
                    type = scope.type,
                    date = scope.date,
                    echart = scope.echart;

                scope.time = '01';
                scope.filter = [];
                if (val.query && val.query.time) {
                    scope.time = val.query.time;
                }
                if (val.query && val.query.filter) {
                    scope.filter = val.query.filter;
                }
                if (date == '01' || date == '04') {
                    opt.series[0].itemStyle.normal.lineStyle.color = "#FFFFFF";
                }
                var render = function () {
                    if (!val)return;
                    if (historytime > 0) {
                        val.query = {
                            time: scope.time,
                            filters: angular.toJson(scope.filter),
                            valType: "HISTORY",
                            extra: historytime
                        };
                    } else {
                        val.query = {time: scope.time, filters: angular.toJson(scope.filter)};
                    }
                    var promise = hUtil.data(val);
                    if (!promise)return;
                    promise.then(function (result) {
                        var data = result.data;

                        if (data && data.metrics && data.metrics.length > 0) {
                            //metrics排序
                            data.metrics.sort(function (a, b) {
                                if (a[0] && b[0]) {
                                    return Number(a[0].value) < Number(b[0].value) ? 1 : -1
                                }
                            });
                            //echart兼容判断
                            if (opt.yAxis && opt.yAxis[0].data && data.dim) {
                                opt.yAxis[0].data = data.dim.reverse();
                            }
                            if (opt.xAxis && opt.xAxis[0].data && data.dim) {
                                opt.xAxis[0].data = data.dim.reverse();
                            }
                            if (opt.legend && opt.legend.data && opt.legend.data.length == 0 && data.dim) {
                                opt.legend.data = data.dim.reverse();
                            }
                            //echart兼容判断
                            //多series匹配
                            var nodata = 0;
                            for (var idx = 0, len = data.metrics.length; idx < len; idx++) {
                                if (!data.metrics[idx] || data.metrics[idx].length == 0) {
                                    nodata++;
                                }
                                opt.series[idx] && (opt.series[idx].data = data.metrics[idx].reverse());
                            }
                            if (nodata == data.metrics.length) {
                                el.html("<div style='width:100%;height:100%;text-align: center;padding-top:10%'><span>暂无数据</span></div>");
                                return;
                            }
                            $timeout(function () {
                                //if (scope.echart) {
                                //    scope.echart.clear();
                                //    scope.echart.setOption(opt);
                                //} else {
                                scope.echart = null;
                                echart = scope.echart = echarts.init(el[0]);
                                echart.setOption(opt);
                                scope.$on('resize', function () {
                                    echart.resize();
                                });
                                //}
                                //scope.echart.resize();

                            }, 0);
                        } else {
                            el.html("<div style='width:100%;height:100%;text-align: center;padding-top:10%'><span>暂无数据</span></div>");
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
                    }
                    if (scope.isTimeNav) {
                        if (filter && filter.time && scope.time != filter.time) {
                            scope.time = filter.time;
                        }
                    } else {
                        if (filter && filter.time && date) {
                            if (date == filter.time) {
                                opt.series[0].itemStyle.normal.lineStyle.color = "#FFFFFF";
                            } else {
                                opt.series[0].itemStyle.normal.lineStyle.color = "#FDB330";
                            }
                            if (echart) {
                                echart.clear();
                                echart.setOption(opt);
                            }
                        }
                    }
                    render();
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