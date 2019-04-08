/**
 * 机构树
 * <orgtree-nav></orgtree-nav>
 */
angular.module('app').directive('orgtreeNav', [ 'GG', 'res', function(GG, res) {
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
						treeUtil.searchLabel(node.children[i], keyword);
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
	return {
		restrict : 'EA',
		scope : false,
		replace:true,
		templateUrl : 'tpl/org_tree_nav.html',
		controller : function($scope, $element, $attrs,$timeout) {
			$scope.orgId = null;

			$scope.name = "";
			$scope.search = function() {
				$scope.loadOrg($scope.name);
			};

			$scope.orgList = [];
			$scope.my_tree = {};
			$scope.changeOrg = function(branch) {
				$scope.orgId = branch.orgId;
				$scope.code = branch.code;
			};
			$scope.loadOrg = function() {
				var orgRes = res(GG.BASE + '/hospital/org');
				orgRes.query().$promise.then(function(data) {
					var tmp = [ {
						"label" : "所有机构",
						"orgId" : "-1",
						"pId" : "-1",
						"code" : "",
						"address" : "",
					} ];
					if($scope.app.org==null){
						var childa={"label":"医疗机构","children":[],"orgId":"-1","pId":"-1","code":""};
						var childb={"label":"监管机构","children":[],"orgId":"-1","pId":"-1","code":""};
						tmp[0]['children']=[];
						tmp[0]['children'].push(childa);
						tmp[0]['children'].push(childb);

						angular.forEach(data,function(item){
							if(item.type=="01"){
								tmp[0]['children'][0]['children'].push(item);
							}else{
								tmp[0]['children'][1]['children'].push(item);
							}
						});
					}else{
						tmp[0]['children']=data;
					}
					$scope.orgList = tmp;
					var result=treeUtil.treeFilter(tmp, $scope.name);
					if(result[0]!=null){
						$scope.orgList=result;
					}else{
						$scope.orgList=[];
					}
					$timeout(function() {
						//$scope.my_tree.expand_all();
						$scope.my_tree.select_firstChild_branch();
					});
				});
			};
			$scope.loadOrg();
			
		},
		link : function(scope, el, attr) {
			
		}
	}
} ]);