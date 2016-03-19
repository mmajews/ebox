'use strict';

var ebox = angular.module('ebox', ['ui.router']);

ebox.config(function ($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/')

    $stateProvider.state('home', {
        url: '/',
        controller: 'HomeController',
        templateUrl: 'javascripts/app/home/home.html'
    })
    .state('game', {
        url: '/game',
        controller: 'GameController',
        templateUrl: 'javascripts/app/game/game.html'
    })
});

ebox.run(function () {
    setTimeout(function () {
        $('.overlay').fadeOut();
    }, 1000);
});