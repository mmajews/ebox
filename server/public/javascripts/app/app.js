'use strict';

var ebox = angular.module('ebox', ['ui.router']);

ebox.config(function ($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/')

    $stateProvider.state('home', {
            url: '/',
            templateUrl: 'javascripts/app/home/home.html',
            controller: function ($rootScope, SocketService, $window) {
                $rootScope.hideCursor = false;

                SocketService.on('onScroll', function (data) {
                    var offset = $('#cursor').offset();
                    var newOffset = {top: offset.top - data.distanceX*2, left: offset.left-data.distanceY*2}
                    if(newOffset.top < 0){
                        newOffset.top = 0;
                    }
                    if(newOffset.left < 0){
                        newOffset.left = 0;
                    }

                    if(newOffset.top > $window.innerHeight){
                        newOffset.top = $window.innerHeight;
                    }
                    if(newOffset.left > $window.innerWidth){
                        newOffset.left = $window.innerWidth;
                    }

                    $('#cursor').offset(newOffset);
                });

            }
        })
        .state('game', {
            url: '/game',
            controller: 'GameController',
            templateUrl: 'javascripts/app/game/game.html'
        })
        .state('pong', {
            url: '/pong',
            templateUrl: 'javascripts/app/pong/game.html',
            controller: function ($rootScope) {
                $rootScope.hideCursor = true;
            }
        })
        .state('flappyBird', {
            url: '/flappy_bird',
            templateUrl: 'javascripts/app/flappy_bird/game.html'
        })
});

ebox.run(function ($document, $rootScope, $state, SocketService) {

    SocketService.noop();
    setTimeout(function () {
        $('.overlay').fadeOut();
    }, 1000);

    $document.bind('mousemove', function (e) {
        var x = e.pageX, y = e.pageY;
        $('#cursor').offset({left: x + 1, top: y + 1});
    });

    $rootScope.goTo = function (state) {
        $state.go(state);
    };
});
