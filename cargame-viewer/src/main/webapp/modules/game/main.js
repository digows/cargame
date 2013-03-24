'use strict';

/**
 * Declare app level module which depends on filters, and services
 */
angular.module('cargame', [ 'speedway-directive' ]).config(
		[ '$routeProvider', function($routeProvider) {
			$routeProvider.when('/', {
				templateUrl : 'modules/game/ui/views/speedway/canvas.html',
				controller : SpeedwayController
			}).otherwise({
				redirectTo : '/'
			});
		} ]);