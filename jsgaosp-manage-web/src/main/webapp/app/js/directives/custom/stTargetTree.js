// /**
//  * 标准指标树
//  * <st-targettree></st-targettree>
//  */
// angular
// 		.module('app')
// 		.directive(
// 				'stTargettree',
// 				[
// 						'GG',
// 						'res',
// 						'$modal',
// 						function(GG, res, $modal,ivhTreeviewMgr) {
// 							/** ******modal ********* */
// 							var modal = {
// 								open : function(size) {
// 									var modalInstance = $modal
// 											.open({
// 												templateUrl : 'tpl/sttarget_tree_modal.html',
// 												controller : function($scope,
// 														$modalInstance) {
// 													var tarRes = res(GG.BASE
// 															+ '/standard/target/loadTargetNode');
// 													$scope.stTargetData = [];
// 													$scope.stTarIds = [];
// 													var tree = $scope.my_tree = {};
// 													$scope.loadList = function() {
// 														tarRes.query().$promise
// 																.then(function(
// 																		data) {
// 																	createTree(data);
// 																})
// 													}
// 													$scope.loadList();
//
// 													function createTree(data){
// 														console.log(data);
// 														$('#stTarTree').tree({
// 															data:data,
// 															animate:true,
// 															checkbox:true,
// 															cascadeCheck:false,
// 															id: 'stTarId',
// 															formatter:function(node){
// 																return node.name;
// 															}
// 														});
// 													}
//
//
// 													$scope.ok = function() {
// 														var nodes = $('#stTarTree').tree('getChecked');
// 														var s = [];
// 														var codes = [];
// 														var len = nodes.length;
// 														for(var i=0; i<len; i++){
// 															s.push(nodes[i].stTarId);
// 															codes.push(nodes[i].code);
// 														}
// 														$modalInstance.close([
// 																true,
// 																s.join(","),codes.join(",") ]);
// 													};
//
// 													$scope.cancel = function() {
// 														$modalInstance
// 																.dismiss('cancel');
// 													};
//
// 												},
// 												size : size,
// 												backdrop : 'static',
// 											});
//
// 									return modalInstance.result.then(function(
// 											items) {
// 										if (items[0]) {
// 											return items;
// 										}
// 									}, function() {
// 										return null;
// 									});
// 								}
// 							}
// 							/** ******modal end ********* */
//
// 							return {
// 								restrict : 'EA',
// 								scope : false,
// 								replace:true,
// 								template : '<button class="btn btn-sm btn-primary" style="margin-bottom: -1px!important;">'+
// 		                              '导入'+
// 		                              '</button>',
// 								link : function(scope, el, attr) {
//
// 									el.on('click', function() {
// 										modal.open('sm').then(function(data) {
// 											if (data) {
// 												scope.importIds = data[1];
// 												scope.importCodes = data[2];
// 											}
//
// 										});
// 									});
// 								}
// 							}
// 						} ]);