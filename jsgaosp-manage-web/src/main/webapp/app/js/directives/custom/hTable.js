/**
 *
 * <h-table name="值2" header="" val="" realtime>
 *
 * </h-table>
 */
angular.module('app').directive('hTable', ['$interval', '$timeout', '$q', 'hUtil',
    function ($interval, $timeout, $q, hUtil) {
        return {
            restrict: 'E',
            scope: {},
            replace: true,
            template: '<table></table>',
            controller: function ($scope, $element, $attrs) {
                var val = $attrs['val'],
                    header = $attrs['header'],
                    height = $attrs['height'],
                    realtime = $attrs['realtime'];
                $scope.val = hUtil.toJSON(val);
                $scope.header = header.split(',');
                $scope.realtime = realtime;
                //$element.height(height);
                $scope.isTimeNav = true;
                if ($attrs['isTimeNav']) {
                    $scope.isTimeNav = hUtil.toJSON($attrs['isTimeNav']);
                }
            },
            link: function (scope, el, attr) {
                var val = scope.val,
                    header = scope.header,
                    realtime = scope.realtime;

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
                    val.query = {time: scope.time, filters: angular.toJson(scope.filter), valType: "HTABLE"};
                    var promise = hUtil.data(val);
                    if (!promise)return;
                    el.html('<div style="text-align: center;width: 100%;padding-top: 10%"><i class="fa fa-2x fa-spin fa-spinner"></i></div>');
                    promise.then(function (result) {
                        var content = '<thead style="border-bottom:3px solid #2CBFCE;font-weight: bold"><tr>';
                        for (var h = 0; h < header.length; h++) {
                            content += '<td>' + header[h] + '</td>';
                        }
                        content += '</tr></thead>';
                        content += '<tbody class="animated fadeIn">';
                        if (result && result.data && result.data.metrics && result.data.metrics[0] && result.data.metrics[0].length > 0) {
                            var data = result.data.metrics[0];
                            var nodata=0;
                            for (var i = data.length - 1; i >= 0; i--) {
                                var obj = data[i];
                                if(obj) {
                                    content += '<tr>';
                                    for (var j = 0; j < header.length; j++) {
                                        if (obj[j] && obj[j].value != '0' && obj[j].value != '') {
                                            content += '<td>' + obj[j].value + '</td>';
                                        } else {
                                            content += '<td>暂无</td>';
                                        }
                                    }
                                    content += '</tr>';
                                }else{
                                    nodata++;
                                }
                            }
                            if(nodata==data.length){
                                content += "<tr><td colspan='" + header.length + "'><div style='text-align: center;margin-top: 10%'><span>暂无数据</span></div></td></tr>";
                            }
                        } else {
                            content += "<tr><td colspan='" + header.length + "'><div style='text-align: center;margin-top: 10%'><span>暂无数据</span></div></td></tr>";
                        }
                        content += '</tbody>';
                        el.html(content);
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