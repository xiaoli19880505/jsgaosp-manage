/**
 *<h-nav>
 *
 *</h-nav>
 */
angular.module('app').directive('hNav', ['hUtil', '$state',
    function (hUtil) {
        return {
            restrict: 'E',
            scope: {},
            replace: true,
            transclude: true,
            template: '<div style="height: 100%;padding: 2px;"><div ng-style="ns" style="height: 100%" ng-click="select()" ng-transclude> </div> </div>',
            controller: function ($scope, $element, $attrs) {
                $scope.date = $attrs['date'];
                $scope.isTimeNav = true;
                $scope.isDeptNav = true;
                if ($attrs['isTimeNav']) {
                    $scope.isTimeNav = hUtil.toJSON($attrs['isTimeNav']);
                }
            },
            link: function (scope, element, attrs) {
                scope.unselected = {"border": "1px solid #DCDBDB", "backgroundColor": "#FFFFFF", "color": "#DAD7CE"};
                scope.selected = {"border": "1px solid #FDB330", "backgroundColor": "#FDB330", "color": "#FFFFFF"};
                scope.ns = scope.unselected;

                scope.select = function () {
                    scope.ns = scope.selected;
                    scope.$emit("p-filter-refresh", {time: scope.date});
                };
                //默认选中01昨日
                if (scope.date == '01'||scope.date == '04') {
                    scope.ns = scope.selected;
                }

                var filterDestroy = scope.$on('filter-refresh', function (event, filter) {
                    if (filter && filter.time) {
                        if (scope.date != filter.time) {
                            scope.ns = scope.unselected;
                        } else {
                            scope.ns = scope.selected;
                        }
                    }
                });

                element.on('$destroy', function () {
                    filterDestroy();
                })
            }
        }
    }
]);