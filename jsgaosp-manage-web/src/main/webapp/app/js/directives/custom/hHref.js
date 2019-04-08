/**
 *
 * <div h-href="19889988">
 *
 * </div>
 */
angular.module('app').directive('hHref', function (hUtil, $state) {
    var view = function (viewId) {
        $state.go('front.appview', {
            viewId: viewId
        });
    };
    return {
        restrict: 'EA',
        scope: {},
        replace: true,
        controller: function ($scope, $element, $attrs) {
            $scope.hrefTo = $attrs.hHref;
        },
        link: function (scope, element, attrs) {
            if(scope.hrefTo) {
                element.on('click', function () {
                    view(scope.hrefTo);
                });
            }
        }
    }

});