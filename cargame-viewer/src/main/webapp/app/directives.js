'use strict';

angular.module('cargame.directives', []).directive('appVersion', function() {
	return function(scope, elm, attrs) {
		elm.text("1.0");
	};
}).directive('speedwayData', function() {
	return function(scope, element, attrs) {

		console.log(scope);
		console.log(element);
		console.log(attrs);

		// watch the expression, and update the UI on change.
		scope.$watch(attrs.speedwayData, function(value) {
			if (value == null)
				return;
			updateModel(value);
		});

		function updateModel(cars) {
			for ( var car in cars) {

			}
		}

		element.bind('$destroy', function() {
			// $timeout.cancel(timeoutId);
			console.log("destroy");
		});
	}
}).directive('sample', function factory() {
	return {
		priority : 0,
		template : '<div> </div>',
		// templateUrl: 'directive.html',
		replace : false,
		transclude : false,
		restrict : 'A',
		scope : false,
		compile : function compile(tElement, tAttrs, transclude) {
			return {
				pre : function preLink(scope, iElement, iAttrs, controller) {
				},
				post : function postLink(scope, iElement, iAttrs, controller) {
				}
			}
		},
		link : function postLink(scope, element, attrs) {
			console.log(scope);
			console.log(element);
			console.log(attrs);
		}
	};
}).directive('speedway', function() {
	return {
		template : '<canvas/>',
		restrict : 'E',
		link : function(scope, element, attrs) {
			console.log(scope);
			console.log(element);
			console.log(attrs);
			console.log(attrs.data);
		}
	}
});
