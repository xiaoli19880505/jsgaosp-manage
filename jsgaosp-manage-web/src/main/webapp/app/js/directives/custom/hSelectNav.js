/**
 *<h-select-nav>
 *
 *</h-select-nav>
 */
angular.module('app').directive('hSelectNav', ['hUtil',
    function (hUtil) {
        return {
            restrict: 'E',
            scope: {},
            replace: true,
            template: '<div style="height: 100%;width:100%;padding: 5px;">' +
            '<select class="form-control" ng-model="selected" ng-change="select()" ng-options="item as item for item in list"></select>' +
            '</div>',
            controller: function ($scope, $element, $attrs) {
                $scope.val = hUtil.toJSON($attrs['val']);
            },
            link: function (scope, element, attrs) {
                var val = scope.val;
                var promise = hUtil.data(val);
                if (!promise)return;
                promise.then(function (result) {
                    scope.list=["全部"];
                    scope.list=scope.list.concat(result.data.dim);
                    scope.selected=scope.list[0];
                });
                scope.select = function () {
                    var other=[];
                    if(scope.selected=='全部'){
                        other.push({"field":"code","value":"all"});
                    }else{
                        other.push({"field":"code","value":scope.selected});
                    }
                    scope.$emit("p-filter-refresh", {other: angular.toJson(other)});
                };
            }
        }
    }
]);