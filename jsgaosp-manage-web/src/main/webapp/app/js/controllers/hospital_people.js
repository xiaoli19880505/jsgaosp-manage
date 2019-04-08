'use strict';

/* Controllers */
  // hospital_people controller
app.controller('HospitalPeopleController', ['$scope', '$http', '$state', function($scope, $http, $state) {
	$scope.peoples=null;
	$http.get('json/people.json').success(function(data){
		$scope.peoples=data.peoples;
	});
	
	$scope.deletePeo=function(name){
		$("td:contains('"+name+"')").parent("tr").remove();
	}
	
	$scope.updatePeo=function(name){
		
	}
	
	
	
 }])
;

 app.controller('ModalHospitalInstanceCtrl', ['$scope', '$modalInstance', 'items', function($scope, $modalInstance, items) {
    $scope.items = items;
    $scope.selected = {
      item: $scope.items[0]
    };

    $scope.ok = function () {
      $modalInstance.close($scope.selected.item);
    };
    
	$scope.add=function(){
		console.log($scope.name);
    }
    $scope.cancel = function () {
      $modalInstance.dismiss('cancel');
    };
  }])
  ; 
  app.controller('ModalHospitalCtrl', ['$scope', '$modal', '$log', function($scope, $modal, $log) {
    $scope.items = ['item1','item2','item3'];
    $scope.add=function(){
		console.log($scope.name);
    }
    $scope.open = function (size) {
      var modalInstance = $modal.open({
        templateUrl: 'xxx',
        scope:$scope,
        //controller: 'ModalHospitalInstanceCtrl',
        size: size,
        resolve: {
          items: function () {
            return $scope.items;
          }
        }
      });

      modalInstance.result.then(function (selectedItem) {
        $scope.selected = selectedItem;
        $("table").append("<tr><td>"+$scope.name+"</td><td>"+$scope.age+"</td><td>"+$scope.phone+"</td><td>"+$scope.job+"</td><td>增加的</td></tr>");
      }, function () {
        $log.info('Modal dismissed at: ' + new Date());
      });
    };
  }])
  ; 