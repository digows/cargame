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
			
			// watch the expression, and update the UI on change.
			scope.$watch(scope.cars, function(cars) {
				if ( cars == null ) return;
				updateModel(cars);
			});
			
			function updateModel(cars) {
				if ( cars == null ) return;
				
				for ( var i = 0; i<cars.length; i++ ) {
					//console.log(cars[i]);
				}
			}
			updateModel(scope.cars);
		}
	}
});
