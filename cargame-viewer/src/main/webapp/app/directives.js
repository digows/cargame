'use strict';

angular.module('cargame.directives', []).directive('appVersion', function() {
	return function(scope, elm, attrs) {
		elm.text("1.0");
	};
}).directive('speedway', function() {
	return {
		template : '<canvas/>',
		restrict : 'E',
		replace : true,
		link : function(scope, element, attrs) {
			
			var canvas = element[0];
			var context2D = canvas.getContext("2d"); 
			
			// watch the expression, and update the UI on change.
			scope.$watch(scope.cars, function(cars) {
				if ( cars == null ) return;
				updateModel(cars);
			});
			
			function updateModel(cars) {
				if ( cars == null ) return;
				
				for ( var i = 0; i<cars.length; i++ ) {
					createOrUpdateCar(cars[i])
				}
			}
			updateModel(scope.cars);
			
			function createOrUpdateCar(car) {
				
				var imageId = Math.floor(Math.random() * (16 - 1) + 1);
				var image = new Image();
				image.src = "static/images/"+imageId+".png";
				image.onload = function() {
					// save the current co-ordinate system 
					// before we screw with it
					context2D.save(); 

					// move to the middle of where we want to draw our image
					context2D.translate(car.x, car.y);

					// rotate around that point, converting our 
					// angle from degrees to radians 
					context2D.rotate(car.angle * (Math.PI/180));

					// draw it up and to the left by half the width
					// and height of the image 
					//img,x,y
					context2D.drawImage(image, -(image.width/2), -(image.height/2), 10, 20);

					// and restore the co-ords to how they were when we began
					context2D.restore(); 
				};
			}
		}
	}
});
