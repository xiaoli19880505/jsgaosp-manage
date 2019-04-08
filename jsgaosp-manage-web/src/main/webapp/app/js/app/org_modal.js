'use strict';

/* Controllers */
// hospital_people_modal controller

app.controller('OrgTreeModalCtrl', ['$scope', '$modalInstance', 'OrgService','$timeout', function ($scope, $modalInstance, OrgService,$timeout) {

	$scope.orgList = [];
	var tree = $scope.my_tree = {};
	
	$scope.loadOrgList = function() {
		$scope.doing_async = true;
		OrgService.loadOrg().then(function(data) {
			var tmp = [{
				"label":"所有机构",
				"orgId":"-1",
				"pId":"-1",
				"code":"",
				"address":""
			}];
			tmp[0]['children']=data;
			$scope.orgList = tmp;
			$scope.doing_async = false;
			$timeout(function() {
				tree.expand_all();
				tree.select_firstChild_branch();
			}, 0);
		})

	}
	
	$scope.getOrg = function(branch) {
			$scope.org = branch;
		}

	
	$scope.loadOrgList();
	
	$scope.ok = function () {
		$modalInstance.close([true, $scope.org]);
    };
    
    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
}]);
