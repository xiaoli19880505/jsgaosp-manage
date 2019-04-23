angular.module('app').service('commonServ', [ '$http','$q', function( $http,$q) {
    this.name = "service";
    //根据数据库字段名获取对应的数据字典
    this.getCodeListByColName = function(colName){
        var d = $q.defer();
        $http.get('/common/list_code?codeSortKey='+colName)
            .success(function(response) {
                d.resolve(response);
            })
            .error(function(){
                alert(0)
                d.reject("error");
            });
        return d.promise;
    }

    //获得登录者对应地区的系统列表（地区号在session中获得）
    this.getSysListByArea = function(){
        var d = $q.defer();
        $http.get('/common/list_sys')
            .success(function(response) {
                d.resolve(response);
            })
            .error(function(){
                alert(0)
                d.reject("error");
            });
        return d.promise;
    }

} ]);