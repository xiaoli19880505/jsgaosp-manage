'use strict';

app.controller('AdviceController',['$scope','$http','$state','$modal','$timeout','modalServ','AdviceService','$sessionStorage',
	function($scope,$http,$state,$modal,$timeout,modalServ,AdviceService,$sessionStorage){
	$scope.totalItems = 100;
    $scope.currentPage = 1;
    $scope.maxSize = 5;
	
	$scope.$watch('orgNo', function (newVal, oldVal) {
		if ($scope.orgNo != null) {
			console.log($scope.orgNo);
			$scope.loadApplications();
		}
	})
	
	
	$scope.loadAdvice=function(){
		AdviceService.listAdvice($scope.currentPage,$scope.orgNo,$scope.keyword).then(function(res){
            console.log(res)
            $scope.adviceList=res.data.list;
            $scope.totalItems=res.data.totalCount;
            $scope.currentPage=res.data.page;
            $scope.chooseArgs=[];
        })
    }
	
	
	 //搜索
    $scope.searchApp=function(){
        $scope.loadApplications();
    }

    $scope.pageChanged = function () {
        $scope.loadApplications();
    };
	
    
   

}])