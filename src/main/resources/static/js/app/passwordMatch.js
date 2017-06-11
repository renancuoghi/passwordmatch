var app = angular.module('AppPasswordMatch', []);

app.controller('MatchController', ['$scope','$http', function($scope,$http) {
   $scope.data = {
           "hide" : true,
           "password" : "",
           "loading" : false
       };

   /**
    * Object score result from match rest controller
    * {"score": 38,"complexity": "WEAK"}
    */
   $scope.scoreResult = null;

   
   $scope.getLabelColor = function(){
	   
	   if($scope.scoreResult){
		   var complexity = $scope.scoreResult.complexity;
		   if(complexity == "FORTE" || complexity == "MUITO_FORTE"){
			   return "success";
		   }else if(complexity == "BOA"){
			   return "warning";
		   }else{
			   return "danger";
		   }
	   }else{
		   return "default";
	   }
	   
   }

   $scope.match = function(){
       
	    console.log('indo: ' + $scope.data.password);
        var req = {
         method: 'GET',
         url: 'match/check',
         params : {"password" : $scope.data.password}
        };
        $scope.data.loading = true;

       $http(req).then(function(data, status, headers, config) {
            console.log(data.data);
            $scope.data.loading = false;
            $scope.scoreResult = data.data;
      });


   }
}]);