console.log("Hello ebay-controller");

var ebayApp = angular.module('ebayApp',[]);
//angular.module('cookies', ['ngCookies']);

ebayApp.controller('ebayController',function($scope,$http,){
    	
	$scope.selectCategory = function(category){
	    //$cookies.put("selectedCategory", category.name )
	    $.cookie('selectedCategory', category.name );
	}
	
    	$scope.populateCategoryPanel = function(){
    	 $.cookie('selectedCategory', null );
    	    $http.get("/categories")
    	    .then(function(response){
    		    $scope.categoryList = response.data
			
    	    });
    	}
    	
    	$scope.populateCategoryPanel();
    	
   	  
});

//angular.element(document).ready(function() {
//    
//	angular.bootstrap(document.getElementById("ebayPanel"), ['ebayApp']);
//	//angular.bootstrap(document.getElementById("newTaskPanel"), ['taskApp']);
//});
