angular.module('app').directive('showScroll', function($window) {
    return {
    	restrict : 'EA',
        link : function(scope, elm, attrs, ctrl) {
        	var height = $(window).height() - 330;
        	elm.css("height", height);
        }
    };
});