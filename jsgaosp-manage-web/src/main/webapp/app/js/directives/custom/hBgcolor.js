/**
 *
 */
angular.module('app').directive('hBgcolor', ['hUtil',
	function(hUtil) {
		return {
			restrict: 'A',
			controller: function($scope, $element, $attrs) {},
			link: function(scope, el, attr) {
				var color = attr['hBgcolor'];
				el.css('background-color',color);
			}
		}
	}
]);