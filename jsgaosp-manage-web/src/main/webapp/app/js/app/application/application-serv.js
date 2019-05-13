/**
 * res 为自定义的$resource
 */
angular.module('app').factory('ApplicationService',
		[ 'res', 'GG', function(res, GG) {
			var res = res(GG.BASE + '/application/:type');
			var service = {
				listApplications : function(currentPage, orgNo,keyword) {
					return res.get({
						orgNo : orgNo,
						keyword : keyword,
						currentPage : currentPage,
						type : 'list_applications'
					}).$promise;
				},
				addApplication : function(application,org_id) {
					application.org_id= org_id;
	                return res.save(application).$promise;
				},
				rollbackVersion : function(application) {
					return res.get({
						infoId : application.info_id,
						type : 'rollback_version'
					}).$promise;
				},
				listHisVersion : function(currentPage,appli) {
					return res.get({
						appName : appli.app_name,
						orgId : appli.org_id,
						sysType : appli.sys_type,
						currentPage : currentPage,
						type : 'list_version'
					}).$promise;
				},
				updateApplication : function(application) {
	                return res.update(application).$promise;
				},
				disableApplication : function(info_id,org_id,app_id,sys_type,workstatus) {
					return res.get({
						info_id:info_id,
				    	org_id:org_id,
				    	app_id:app_id,
				    	sys_type:sys_type,
						workstatus:workstatus,
						type : 'disable_version'
					}).$promise;
				},
				updateVersionApplication : function(application) {
					application.method = 'updateVersion';
					return res.get({
						application : application,
						type : 'update_version'
					}).$promise;
				},
				deleteApplication : function(id) {
					return res.delete({
	                	id:id
	                }).$promise;
				}
				
			};
			return service;
		} ]);