/**
 * res 为自定义的$resource
 */
angular.module('app').factory('ApproveService',
		[ 'res', 'GG', function(res, GG) {
			var res = res(GG.BASE + '/approves/:type');
			var service = {
				listApproves : function(currentPage, orgNo,keyword) {
					return res.get({
						orgNo : orgNo,
						keyword : keyword,
						currentPage : currentPage,
						type : 'list_approves'
					}).$promise;
				},
				updateApprove : function(approve) {
		            return res.update(approve).$promise;
				}
			
			};
			return service;
		} ]);