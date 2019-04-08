angular.module('app').directive('echart', ['$compile', '$parse', '$timeout', '$q',
    function ($compile, $parse, $timeout, $q) {
        return {
            restrict: 'AC',
            compile: function (element, attributes) {
                var option = '',
                    expsJSON,
                    opt = attributes['opt'],
                    exps = attributes['exps'],
                    content = element.contents();
                //非"{}"
                if (exps.length > 2) {
                    expsJSON = JSON.parse(exps);
                    for (var key in expsJSON) {
                        var exp = expsJSON[key];
                        switch (exp.way) {
                            case 'manual':
                                var reg = new RegExp("('|\"){{[^}]*" + key + "[^{]*}}('|\")", "gi");
                                //var reg = new RegExp("{{[^}]*" + key + "[^{]*}}", "gi");
                                if (exp.value.indexOf('[') < 0) {
                                    opt = opt.replace(reg, '"' + exp.value + '"');
                                } else {
                                    opt = opt.replace(reg, exp.value);
                                }
                                option = opt;
                                break;
                            case 'script':
                                exp.value = $http({
                                    method: 'get',
                                    url: ''
                                });
                                break;
                            case 'url':
                                exp.value = $http({
                                    method: 'get',
                                    url: ''
                                });
                                break;
                            default:
                                break;
                        }
                    }
                } else {
                    option = opt;
                }
                var linkFun = function (scope, el, attr) {
                    var cp = function (val) {
                        //替换 html
                        //替换 option
                        //$timeout(function() {
                        //var toJSON = function(str) {
                        //	return (new Function("return " + str))();
                        //};
                        //echarts.init(el[0]).setOption(toJSON(option));
                        //}, 0);
                    };
                    if (expsJSON) {
                        for (var key in expsJSON) {
                            var reg = new RegExp("('|\"){{[^}]*" + key + "[^{]*}}('|\")", "gi");
                            var exp = expsJSON[key];
                            switch (exp.way) {
                                case 'manual':
                                    $timeout(function () {
                                        var toJSON = function (str) {
                                            return (new Function("return " + str))();
                                        };
                                        console.log('option', option);
                                        echarts.init(el[0]).setOption(toJSON(option));
                                        //echarts.init(el[0]).setOption(option);
                                    }, 0);
//									cp(exp.value);
                                    break;
                                case 'script':
                                    exp.value.then(function (data) {
                                        cp(data);
                                    });
                                    break;
                                case 'url':
                                    exp.value.then(function (data) {
                                        cp(data);
                                    });
                                    break;
                                default:
                                    break;
                            }
                        }
                    } else {
                        cp(opt);
                    }
                };
                return linkFun;
            }
        }
    }
]);