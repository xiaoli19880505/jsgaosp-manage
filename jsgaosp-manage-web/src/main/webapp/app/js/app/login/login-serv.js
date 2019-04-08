angular.module('app').factory('loginServ', ['res', 'GG', '$http',
    function (res, GG, $http) {
        var serv = {
            queryRoles: function (email, pwd) {
                return $http({
                    method: 'get',
                    url: GG.BASE + '/auth/roles',
                    params: {
                    	email: email,
                        password: pwd
                    }
                });
            },
            login: function (user) {
                return $http.post('/global/in', {
                    email: user.email,
                    password: user.password
                })
            }
        };
        return serv;
    }
]);