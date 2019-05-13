/**
 * 标准指标树
 * <st-targettree></st-targettree>
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
                    // template:function (e, a) {
                    //    return check(a);
                    // },

                    compile: function (e, a) {
                        if (!check(a)) {
                            e.remove();
                        }
                    }
                }
            }]);