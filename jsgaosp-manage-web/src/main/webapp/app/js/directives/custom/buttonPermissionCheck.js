/**
 * 标准指标树
 * <st-targettree></st-targettree>
 */
angular
    .module('app')
    .directive(
        'buttonpermissionCheck',
        [
            'GG',
            'res',
            '$modal', '$timeout', '$sessionStorage',
            function (GG, res, $modal, $timeout, $sessionStorage) {

                function check(a) {
                    if (a.permissioncode && GG.roleOp.indexOf(a.permissioncode) == -1) {
                        return false;
                    } else {
                        return true;
                    }
                }

                return {
                    restrict: 'A',
                    scope: {
                        permissioncode: '='
                    },
                    replace: true,
                    compile: function (e, a) {
                        if (!check(a)) {
                            e.css("display", "none");
                        }
                    }
                }
            }]);