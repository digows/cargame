'use strict';

function SpeedwayController($scope) {

	var LOG = new Log(Log.DEBUG, Log.consoleLogger);
	
	var socket = null;
	
	$scope.cars = new Array();
	
	$scope.connectToServer = function() {
		LOG.debug("connectToServer");
		
		socket = new WebSocket("ws://"+window.document.location.host);
		socket.onopen = onSocketOpen;
		socket.onmessage = onSocketMessage;
		socket.onerror = onSocketError;
		socket.onclose = onSocketClose;
	};
	
	function onSocketOpen(event) { 
		console.log(event);
		console.log('OPEN: ' + socket.readyState); 
	};
	function onSocketMessage(event) {
		console.log(event);
		console.log(event.data);
		
		if ( event.data == null ) return;
		
		var car = $.parseJSON( event.data );
		var currentCars = $scope.cars;
		
		//If enter in the loop, subscribe
		for (var i = 0; i<currentCars.lenght; i++) {
			if ( car.id == currentCars[i].id ) {
				currentCars[i] = car;
				$scope.cars = currentCars;
				return;
			}
		}
		
		//Else
		currentCars.push(car);
		$scope.cars = currentCars;
	};
	function onSocketError(event) {
		console.log(event);
		console.log('ERROR: ' + event.data); 
	};
	function onSocketClose(event) {
		console.log(event);
		console.log('CLOSED: ' + socket.readyState);
	};
	
	$scope.connectToServer();
	
//	$scope.cars = [
//	        {id:"2131-1123-13dfg3411-3131", x:0, y:0, angle:0, playerName:"Player01"},
//	        {id:"2131-1123-13341dfgd1-3131", x:0, y:50, angle:90, playerName:"Player02"},
//	        {id:"2131-112dfgd3-133411-3131", x:100, y:100, angle:90, playerName:"Player03"},
//	        {id:"213gdfg1-1123-133411-3131", x:100, y:100, angle:180, playerName:"Player04"},
//	        {id:"213asd1-1123-133411-3131", x:100, y:200, angle:180, playerName:"Player05"},
//	        {id:"213asd1-1123-133411-3131", x:100, y:200, angle:270, playerName:"Player06"},
//	        {id:"213asd1-1123-133411-3131", x:200, y:200, angle:270, playerName:"Player07"},
//	        {id:"1123-133411-3131", x:400, y:100, angle:0, playerName:"Player08"},
//	    ];
}