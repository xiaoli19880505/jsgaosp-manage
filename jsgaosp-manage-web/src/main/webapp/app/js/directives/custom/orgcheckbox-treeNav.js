/**
 * 机构树
 * <orgtree-nav></orgtree-nav>
 */
angular.module('app').directive('orgcheckboxtreeNav', [ 'GG', 'res','commonServ', function(GG, res,commonServ) {
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
			if (node.org_name.toString().indexOf(keyword) >= 0) {
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
		templateUrl : 'tpl/checkbox_org_tree.html',
		controller : function($scope, $element, $attrs,$timeout) {
			$scope.orgNo = null;

			$scope.orgNameKey = "";
			$scope.search = function() {
				$scope.loadOrg();
			};

			$scope.orgList = [];
			$scope.my_tree = {};
			$scope.changeOrg = function(branch) {
				$scope.orgNo= branch.org_no;
				$scope.orgName= branch.org_name;
				$scope.code= branch.code;
			};
			$scope.loadOrg = function() {



				commonServ.getOrgTreeByUser().then(function(data) {
					$scope.doing_async = false;
					$scope.orgList = data.data;
					var tmp= data.data;
					var result=treeUtil.treeFilter(tmp, $scope.orgNameKey);
					if(result[0]!=null){
						$scope.orgList=result;
					}else{
						$scope.orgList=[];
					}
					$timeout(function() {
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