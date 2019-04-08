'use strict';

/* Filters */
//need load the moment.js to use this filter. 
angular.module('app')
.filter('fromNow', function () {
	return function (date) {
		return moment(date).fromNow();
	}
});

angular.module('app')
.filter('pwd', function () {
	return function (input) {
		var result = input.substr(0, 2);
		for (var i = 0; i < input.length - 2; i++)
			result += "*";
		return result;
	}
});

angular.module('app')
.filter('linkFilter', function () {
	return function (input) {
		if (input != null) {
			var result = [];
			for (var key in input) {
				var name = input[key].name;
				var value = input[key].value;
				if (name.match('linkurl')) {
					result.push(input[key]);
				}
			}
			return result;
		}
		return input;
	}
});

angular.module('app').filter('to_trusted', ['$sce', function ($sce) {
	return function (text) {
	    return $sce.trustAsHtml(text);
	};
}]);
