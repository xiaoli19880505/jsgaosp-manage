/**
 * res 为自定义的$resource
 */
angular.module('app').factory('ApplicationService',
		[ 'res', 'GG', function(res, GG) {
			var res = res(GG.BASE + '/application/:type');
			var service = {
				listApplications : function(currentPage, orgNo) {
					return res.get({
						orgNo : orgNo,
						currentPage : currentPage,
						type : 'list_applications'
					}).$promise;
				},
				addApplication : function(application,org_id) {
					application.org_id= org_id;
	                return res.save(application).$promise;
				},
				updateApplication : function(application) {
	                return res.update(application).$promise;
				},
				deleteApplication : function(id) {
					return res.delete({
	                	id:id
	                }).$promise;
				}
			};
			return service;
		} ]);