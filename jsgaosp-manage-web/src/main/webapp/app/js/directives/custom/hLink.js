/**
 *
 * <div h-link="19889988">
 *
 * </div>
 */
angular.module('app').directive('hLink', ['hUtil', '$state', function (hUtil, $state) {
    var padding = 25;
    var linkHtml = '<div style="display:table;font-size:20px;color:#C2D5DC;position:absolute;top:0px;right:0px;width:'
        + padding
        + 'px;" >'+
        '<span class="glyphicon glyphicon-chevron-right" style="vertical-align:middle;display:table-cell;"></span>'
        + '</div>';
    var view = function (viewId) {
        $state.go('front.appview', {
            viewId: viewId
        });
    };
    return {
        restrict: 'A',
        priority: 11,
        controller: function ($scope, $element, $attrs) {
            $scope.linkTo = $attrs.hLink;
        },
        compile: function (el, attr) {
            el.parent().css('position', 'relative');
            el.parent().css('left', '0');
            el.parent().css('top', '0');

            return function (scope, el, attr) {
                el.css('padding-right', padding);
                var linkTo = scope.linkTo;
                if (!linkTo)
                    return;
                // link el start
                var linkEl = angular.element(linkHtml);
                linkEl[0].style.height=el[0].style.height;
                el.parent().append(linkEl);
                //el.css('background-color')
                //&& linkEl
                //    .css(
                //    'background-color',
                //    el
                //        .css('background-color'));
                // link el end
                linkEl.on('click', function () {
                    view(linkTo);
                });

            }
        }
    }
}]);
