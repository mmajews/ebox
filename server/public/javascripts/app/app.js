'use strict';

var ebox = angular.module('ebox', ['ui.router']);

ebox.config(function ($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/')

    $stateProvider.state('home', {
            url: '/',
            templateUrl: 'javascripts/app/home/home.html',
            controller: function ($rootScope, $scope, SocketService, $window) {
                $rootScope.hideCursor = false;

                var listener = $rootScope.$on('onScroll', function (ev,data) {
                    var offset = $('#cursor').offset();
                    var newOffset = {top: offset.top - data.distanceY*2*$window.innerHeight, left: offset.left-data.distanceX*2*$window.innerWidth}
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

                $scope.$on("$destroy", function () {
                    listener();
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

    SocketService.on('onScroll', function (data) {
        $rootScope.$emit('onScroll', data);
    });

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



