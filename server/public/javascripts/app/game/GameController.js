/**
 * Created by konradmarzec on 18.03.2016.
 */
'use strict';

ebox.controller('GameController', function($scope, SocketService) {

    SocketService.on('up', function() {
        console.log('up');
    });

    $scope.emitStub = function() {
        SocketService.emit('event', { message: 'ELDO BYKU' }, function(response) {
            console.log(response + 'response')
        });
    }
});