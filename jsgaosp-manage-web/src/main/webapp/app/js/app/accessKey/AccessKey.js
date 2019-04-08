'use strict';
app.controller('AccessKeyController',function($http,$scope,$state,$stateParams,$timeout,AccessKeyService,modalServ){

	$scope.totalItems=0;
	$scope.currentPage=1;
	$scope.size=10;
	$scope.query="";
	$scope.accessKeys=[];
	$scope.accessKey={};
	$scope.master=false;

	//初始化数据
	$scope.init = function(){
		AccessKeyService.getAllAccessKey($scope.query,$scope.currentPage,$scope.size).then(function(data){
			$scope.totalItems=data.totalItems;
			$scope.accessKeys=data.content;
		});
	}
	$scope.init();
	//按条件查询客户
	$scope.enter = function(ev){
		if (ev.keyCode !== 13){
			return; 
		}else{
           $scope.Search();			
		} 
	}
	
	$scope.Search = function(){
		$scope.currentPage=1;
		$scope.size=10;
		$scope.init();
	}
	//翻页
	$scope.pageChanged = function(){
		$scope.init();
	}
	//查看秘钥
	$scope.showItem = function(item){
		$scope.accessKey=item;
	}
	//显示创建弹框
	$scope.showCreateModal = function(){
		$('#createAccessKeyModal').modal();
	}
	//创建秘钥
	$scope.createAccessKey = function(){
		$scope.accessKey={};
		$scope.accessKey.name=$scope.name;
		$scope.accessKey.description=$scope.description;
		AccessKeyService.save($scope.accessKey).then(function(data){
			$scope.currentPage=1;
			$scope.size=10;
			$scope.init();
		});
	}
	//全选
	$scope.chooseAll = function(){
		angular.forEach($scope.accessKeys,function(item){
			item.check=$scope.master;
		});
	}
	//删除
	var ids="";
	$scope.delete = function(){
		ids="";
		angular.forEach($scope.accessKeys,function(item){
			if(item.check){
				ids=ids==""?"'"+item.apiKey+"'":ids+",'"+item.apiKey+"'";
			}
		});
		if(ids==""){
			toastr.info('请先选择要删除的数据!');
		}else{
			modalServ.showModal({}, {
				bodyText: '        确定清除该渠道配置信息？     '
			}).then(function () { 
				AccessKeyService.delete("("+ids+")").then(function(data){
					ids="";
					toastr.success('删除成功!');	
					$scope.init();
				});
			});
		}
	}
   //弹出编辑框
	$scope.editItem={};
	$scope.edit = function(){
		var id="";
		var count=0;
		angular.forEach($scope.accessKeys,function(item){
			if(item.check){
				count++;
				$scope.editItem=angular.copy(item);
			}
		});
		if(count!=1){
			toastr.info('请选择一条要编辑的数据!');
		}else{
			$('#editAccessKeyModal').modal();
		}
	}
	
	$scope.showItem = function(item){
		$scope.editItem=angular.copy(item);
		$('#editAccessKeyModal').modal();
	}
	
	//update accessKey
	$scope.updateAccessKey = function(){
		AccessKeyService.update($scope.editItem).then(function(data){
			$scope.init();
		});
	}

});