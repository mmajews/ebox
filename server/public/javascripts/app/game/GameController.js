/**
 * Created by konradmarzec on 18.03.2016.
 */
'use strict';

ebox.controller('GameController', function($scope, SocketService) {

    SocketService.on('up', function() {
        console.log('up');
    });

    SocketService.on('onSingleTapConfirmed', function(data) {
        console.log(data);
    });

    SocketService.on('onScroll', function(data) {
        console.log(data);
    });

    SocketService.on('onDoubleTap', function(data) {
        console.log(data);
    });

    $scope.emitStub = function() {
        SocketService.emit('padEvent', { movement: 'onSingleTapConfirmed' });
    }
});