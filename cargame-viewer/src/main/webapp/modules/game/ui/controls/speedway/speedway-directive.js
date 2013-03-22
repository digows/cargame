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
			speedwayCanvas.start();
			
			// watch the expression, and update the UI on change.
			scope.$watch(scope.cars, function(cars) {
				if ( cars == null ) return;
				//updateModel(cars);
			});
			
//			function updateModel(cars) {
//				if ( cars == null ) return;
//				
//				for ( var i = 0; i<cars.length; i++ ) {
//					createOrUpdateCar(cars[i]);
//				}
//			}
		}
	};
});