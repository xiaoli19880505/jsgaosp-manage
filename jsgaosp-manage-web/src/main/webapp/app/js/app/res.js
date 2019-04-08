/**
 * 修改默认 update 的 METHOD 为put
 */
angular.module('app').factory('res', [ '$resource', function($resource) {
	return function(url, params, methods) {
		var defaults = {
			update : {
				method : 'put',
				isArray : false
			},
			create : {
				method : 'post'
			}
		};

		methods = angular.extend(defaults, methods);

		var resource = $resource(url, params, methods);

		resource.prototype.$save = function() {
			if (!this.id) {
				return this.$create();
			} else {
				return this.$update();
			}
		};

		return resource;
	};
} ]);