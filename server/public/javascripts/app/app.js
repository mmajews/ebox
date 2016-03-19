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
    .state('pong', {
        url: '/pong',
        templateUrl: 'javascripts/app/pong/game.html'
    })
});

ebox.run(function ($document, $rootScope, $state) {
    setTimeout(function () {
        $('.overlay').fadeOut();
    }, 1000);

    $document.bind('keydown', function (e) {
        if(e.which == 37){
            $rootScope.emit('keydown:left');
        } else if(e.which == 39){
            $rootScope.emit('keydown:right');
        }
    });

    $document.bind('mousemove', function (e) {
        var x = e.pageX, y = e.pageY;
        $('#cursor').offset({left: x+1, top: y+1});
    });

    $rootScope.goTo = function (state) {
        $state.go(state);
    };
});
