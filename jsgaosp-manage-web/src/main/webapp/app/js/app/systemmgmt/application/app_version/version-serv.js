/**
 * res 为自定义的$resource
 */
angular.module('app').factory('BcSysVersionService',
		[ 'res', 'GG', function(res, GG) {
			var res = res(GG.BASE + '/version/:type');
			var service = {
				listVersion : function(currentPage, keyword) {
					return res.get({
						currentPage : currentPage,
						keyword : keyword,
						type : 'list_versions'
					}).$promise;
				},
				listHisVersion : function(currentPage, sysversions) {
					return res.get({
						currentPage : currentPage,
						Id : sysversions.Id,
						appName : sysversions.appName,
						sysId : sysversions.sysId,
						areaNo : sysversions.areaNo,
						sysType : sysversions.sysType,
						version : sysversions.version,
						type : 'list_his_versions'
					}).$promise;
				},
				updateVersion : function(version) {
					version = version;
					return res.update(version).$promise;
				},
			};
			return service;
		} ]);