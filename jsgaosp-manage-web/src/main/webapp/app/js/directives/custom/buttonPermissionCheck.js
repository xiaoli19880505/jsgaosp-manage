/**
 *按钮权限控制
 */
angular
    .module('app')
    .directive(
        'button',
        [
            'GG',
            'res',
            '$modal', '$timeout', '$sessionStorage',
            function (GG, res, $modal, $timeout, $sessionStorage) {

                function check(a) {
                    if(a.permissioncode ){
                        if (GG.roleOp.indexOf(a.permissioncode) == -1) {
                            return false;
                        } else {
                            return true;
                        }
                    }else{
                        return true;
                    }

                }

                return {
                    restrict: 'E',
                    scope: {
                        permissioncode: '='
                    },
                    replace: true,
                    compile: function (e, a) {
                        if (!check(a)) {
                            e.remove();
                        }
                    }
                }
            }]);