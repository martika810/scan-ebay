console.log("Hello tab-controller");
var tabApp = angular.module('tabApp',[]);
tabApp.controller('navTabController',function($scope,$http){
	
	$scope.changeTab = function(event,idTab){
		console.log("Click tab");
		$('.container').addClass('hide');
		$("#"+idTab).removeClass('hide');
	}
	
});

