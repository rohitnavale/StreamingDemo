/**
 * 
 */


(function () {
    'use strict';
var dapp = angular.module("dashbrods_app", ['datatables','ngMaterial']);
      
dapp.controller('dashboard_card_controller',function($scope,$http){
	
	$http.get("data/card.json")
	  .success( function(response) {    
	     //alert(JSON.stringify(response))
	     $scope.dashborddata=response;
    	});
});

dapp.controller('dashboard_usercard_controller',usercard);

function usercard($http,$scope){
	
	
	$http.get("data/user_card_monthount.json")
	  .success( function(response) {    
	     
	     $scope.dashborddata=response;
	     usercarddata(response)
	     
  	});
	
	
function usercarddata(data){
		
	
var datajson={};
datajson.json=data;
var key={};
var  value=[];
key.x='month';
value[0]='count';
key.value=value;
datajson.type="line";


datajson.keys=key;		
var chart = c3.generate({
    bindto: '#usercard',
    size: {width:300, height:150},
    data: datajson,
 
    axis: {
            x: {
               type: 'category',
               tick: {
                  rotate: 10,
                  multiline: false
                },



            }
        
        },


})


}


}

dapp.controller('dashboard_datatable_controller',userchart);

function userchart($http,$scope){
	
	
	$http.get("data/user_count.json")
	  .success( function(response) {    
	     
	     $scope.dashborddata=response;
	     checkfunction(response)
	     
  	});
	
	
function checkfunction(data){
		
	
var datajson={};
datajson.json=data;
var key={};
var  value=[];
key.x='name';
value[0]='count';
key.value=value;
datajson.type="bar";
datajson.keys=key;		
var chart = c3.generate({
    bindto: '#check',
    data: datajson,
    axis: {
            x: {
               type: 'category',
               tick: {
                  rotate: 10,
                  multiline: false
                },



            }
        
        },


})


}


}


dapp.controller('dashboard_location_controller',locationchart);
function locationchart($http,$scope){
	
	$http.get("data/location_count.json")
	  .success( function(response) {    
	     //alert(response)
	     $scope.dashborddata=response;
	     checkfunction1(response)
	     
  	});
	
	
function checkfunction1(data){
		
	
var datajson={};
datajson.json=data;
var key={};
var  value=[];
key.x='country';
value[0]='count';
key.value=value;
datajson.type="line";
datajson.keys=key;		
var chart = c3.generate({
    bindto: '#check1',
    data: datajson,
    axis: {
            x: {
               type: 'category',
               tick: {
                  rotate: 10,
                  multiline: false
                },



            }
        
        },


})


}


}

dapp.controller('dashboard_twitdata_controller',locationchart1);
function locationchart1($http,$scope){
	
	$http.get("data/twitdata.json")
	  .success( function(response) {    
	   // alert(JSON.stringify(response))
	     $scope.dashbordtwidata=response;
	   
	     
  	});
	
	



}
/*function WithAjaxCtrl(DTOptionsBuilder, DTColumnBuilder) {
	var vm = this;
	vm.dtOptions = DTOptionsBuilder.fromSource("data/Ui/datatable_data.json")
	vm.dtOptions.paging=false;
	vm.dtOptions.scrollY="300px";
	vm.dtOptions.scrollCollapse =false;

    	//.withPaginationType('full_numbers');
	vm.dtColumns = [
    	DTColumnBuilder.newColumn('id').withTitle('ID'),
    	DTColumnBuilder.newColumn('firstName').withTitle('First name'),
    	DTColumnBuilder.newColumn('lastName').withTitle('Last name').notVisible()
	];
}*/

})();   