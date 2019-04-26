/**
 * 机构模态框调用
 */
angular.module('app').service(
		'OrgTreeModalServ',
		[
				'$modal',
				function($modal) {

					var service = {
						open : function(size) {
							var modalInstance = $modal.open({
								templateUrl : 'tpl/org_tree_modal.html',
								controller : 'OrgTreeModalCtrl',
								size : size,
								backdrop : 'static',
							});
							return modalInstance.result.then(
									function(items) {
										if (items[0]) {
											return items;
										}
									}, function() {
										return null;
									});
						}
					}

					return service

				} ]);