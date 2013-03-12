'use strict';

// Declare app level module which depends on filters, and services
angular.module('cargame', []).config(
		[ '$routeProvider', function($routeProvider) {
			$routeProvider.when('/', {
				templateUrl : 'app/views/home/home.html',
				controller : HomeController
			});
//			$routeProvider.when('/view/:id', {
//				templateUrl : 'partials/view.html',
//				controller : ViewCtrl
//			});
//			$routeProvider.when('/edit/:id', {
//				templateUrl : 'partials/edit.html',
//				controller : EditCtrl
//			});
			$routeProvider.otherwise({
				redirectTo : '/'
			});
		} ]);