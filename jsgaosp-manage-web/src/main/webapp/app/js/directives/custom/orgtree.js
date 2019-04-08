/**
 * 机构树
 * <orgtree></orgtree>
 */
angular
		.module('app')
		.directive(
				'orgtree',
				[
						'GG',
						'res',
						'$modal',
						function(GG, res, $modal) {
							/** ******tree util********* */
							var resultNode = [];
							var pNode = null;
							var treeUtil = {
								findNode : function(treeData, keyword) {
									resultNode = [];
									treeUtil.searchLabel(treeData, keyword);
									return resultNode;
								},
								searchLabel : function(node, keyword) {
									if (node.label.toString().indexOf(keyword) >= 0) {
										resultNode.push(node);
									} else {
										if (node.children != null) {
											var child_length = node.children.length;
											for (var i = 0; i < child_length; i++) {
												treeUtil.searchLabel(
														node.children[i],
														keyword);
											}
										}
									}
								},
								treeFilter : function(treeData, keyword) {
									if (keyword != "") {
										var result = [];
										var ii=treeData.length;
										for(var i=0;i<ii;i++){
											var nodes = treeUtil.findNode(treeData[i], keyword)[0];
											if(nodes!=null){
												result.push(nodes);
											}
										}
										return result;
									} else {
										return treeData;
									}
								}

							};
							/** ******tree util end********* */
							/** ******modal ********* */
							var modal = {
								open : function(size) {
									var modalInstance = $modal
											.open({
												templateUrl : 'tpl/org_tree_modal.html',
												controller : function($scope,
														$modalInstance) {
													var orgRes = res(GG.BASE
															+ '/hospital/org');
													$scope.orgList = [];
													var tree = $scope.my_tree = {};
													$scope.name = "";
													$scope.loadOrgList = function() {
														$scope.doing_async = true;
														orgRes.query().$promise
																.then(function(
																		data) {
																	var tmp =[{"label":"医疗机构","children":[],"orgId":"-1","pId":"-1","code":""},
																		            {"label":"监管机构","children":[],"orgId":"-1","pId":"-1","code":""}];
																	angular.forEach(data,function(item){
																		if(item.type=="01"){
																			tmp[0]['children'].push(item);
																		}else{
																			tmp[1]['children'].push(item);
																		}
																	})
																	$scope.orgList = tmp;
																	$scope.orgList = treeUtil
																			.treeFilter(
																					tmp,
																					$scope.name);
																	$scope.doing_async = false;
																	// $timeout(function()
																	// {
																	// tree.expand_all();
																	// tree.select_firstChild_branch();
																	// },
																	// 0);
																})
													}

													$scope.getOrg = function(
															branch) {
														$scope.org = branch;
													}

													$scope.loadOrgList();

													$scope.ok = function() {
														$modalInstance.close([
																true,
																$scope.org ]);
													};

													$scope.cancel = function() {
														$modalInstance
																.dismiss('cancel');
													};

													$scope.search = function() {
														$scope.loadOrgList();
													}
												},
												size : size,
												backdrop : 'static',
											});

									return modalInstance.result.then(function(
											items) {
										if (items[0]) {
											return items[1];
										}
									}, function() {
										return null;
									});
								}
							}
							/** ******modal end ********* */

							return {
								restrict : 'EA',
								scope : false,
								replace:true,
								template : '<div class="input-group m-b" ng-show="app.org==null" style="position:relative;">'
										+ '<span class="input-group-btn">'
										+ '<span class="btn btn-sm btn-default" style="background-color:#EDF1F2;">机构</span>'
										+ '</span>'
										+ '<input type="text" class="input-sm form-control" ng-model="org.name" style="background-color:#fff;cursor: pointer;" disabled>'
										+'<a class="caret" style="position: absolute;top:13px;right: 12px;z-index: 6;color:#58666E;"></a>'
										+ '</div>',
								link : function(scope, el, attr) {

									el.on('click', function() {
										modal.open('sm').then(function(data) {
											if (data) {
												scope.org = data;
												scope.IsOrgSelected = true;
												scope.org.name = data.label;
											}

										});
									});
								}
							}
						} ]);