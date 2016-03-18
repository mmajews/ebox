/**
 * Created by konradmarzec on 18.03.2016.
 */
'use strict';

ebox.controller('GameController', function($scope, SocketService) {
    SocketService.on('up', function() {
        console.log('up');
    })

    $scope.emit = function() {
        SocketService.emit('up', { message: 'ELDO BYKU' } )
    }
});