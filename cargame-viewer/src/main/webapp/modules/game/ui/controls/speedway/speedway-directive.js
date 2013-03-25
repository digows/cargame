'use strict';

/**
 * 
 */
angular.module('speedway-directive', []).directive('speedway', function() {
    
	return {
		template : '<canvas/>',
		restrict : 'E',
		replace : true,
		link : function(scope, element, attrs) {
			
			var canvas = element[0];
			var speedwayCanvas = new SpeedwayCanvas( canvas );
			
			function updateModel( cars ) {
				if ( cars == null ) return;
				
				speedwayCanvas.clear();
				for ( var i = 0; i<cars.length; i++ ) {
					speedwayCanvas.drawCar(cars[i]);					
				}
			};

			scope.$watch(scope.cars, updateModel);
			
			updateModel(scope.cars);
		}
	};
});