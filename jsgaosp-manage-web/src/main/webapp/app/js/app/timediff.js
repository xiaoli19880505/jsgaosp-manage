angular.module('app').factory('timediff', [function() {
	var regx = '(/global/in)|(/global/out)|(.*\.html)|(.*\.png)|(.*\.jpg)|(.*\.map)|(.*\.gif)|(.*\.ico)|(.*\.css)|(.*\.swf)|(.*\.js)|(.*\.pdf)|(.*\.eot)|(.*\.svg)|(.*\.ttf)|(.*\.woff)|(.*\.otf)|(.*\.json)|(.*\.JSON)';
    var diff = {
        request: function(config) {
            config.requestTimestamp = new Date().getTime();
            return config;
        },
        response: function(response) {
        	if(response.config.url.match(regx)){
        		return response;
        	}
        	response.config.responseTimestamp = new Date().getTime();
            var time = response.config.responseTimestamp - response.config.requestTimestamp;
            console.log(response.config.url +':请求消耗了 ' + (time / 1000) + ' seconds.');
            return response;
        }
    };
    return diff;
}]);
angular.module('app').config(['$httpProvider', function($httpProvider) {
    $httpProvider.interceptors.push('timediff');
}]);