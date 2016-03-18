'use strict';

var eBox = angular.module('ebox', ['ui.router']);

eBox.config(function ($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/')

    $stateProvider.state('home', {
        url: '/',
        controller: 'HomeController',
        templateUrl: 'javascripts/app/home/home.html'
    });
});