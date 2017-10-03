console.log("Hello product-controller");

var productApp = angular.module('productApp',[]);
//angular.module('cookies', ['ngCookies']);
productApp.controller('productController',function($scope,$http){
    	
        $scope.loadProducts = function(){
    	    
    		var category = $.cookie('selectedCategory');
    		if(category!=null){
    		$http.get("/products/"+category)
    		.then(function(response){
    		    $scope.productList = response.data
			
    		});
    	    }
    	}
    	   
    	;
    	$scope.loadProducts();
   	  
});

//angular.element(document).ready(function() {
//    
//	angular.bootstrap(document.getElementById("ebayPanel"), ['ebayApp']);
//	//angular.bootstrap(document.getElementById("newTaskPanel"), ['taskApp']);
//});
