'use strict';

app.controller('CompaignCompanyController',function($scope,$http,$state,$timeout,CompanyService){
	/*
	 * 初始化
	 */
	$scope.totalItems = 1;
    $scope.currentPage = 1;
	$scope.getCompanyInfoById=function(){
		CompanyService.getCompanyInfo($scope.app.user.companyId).then(function(data){
			var arr = new Array();
			arr.push(data);
			console.log(arr);
			$scope.company=arr;
		})
	}
	$scope.submitForm=function(isValid){
		$scope.submitted = false;
		if(isValid){
			var data = {
				companyId : $scope.app.user.companyId,
				name : $scope.company.name,
				description : $scope.company.description
			};
			CompanyService.saveCompany(data).then(function(data){
				if(data.result){
					toastr.success('保存成功！');
				}
			});
		}else{
			$scope.submitted = true;
		}
	}
	
	$scope.getCompanyInfoById();
})