/**
 * Created by konradmarzec on 18.03.2016.
 */
'use strict';

ebox.factory('SocketService', function($rootScope) {
    var socket = io.connect('', { query: 'type=gameClient' });

    return {
        on: function(eventName, callback) {
            socket.on(eventName, function () {
                console.log('powrót');
                var args = arguments;
                $rootScope.$apply(function () {
                    callback.apply(socket, args);
                });
            });
        },

        emit: function(eventName, data) {
            console.log(data);
            socket.emit(eventName, data);
        },

        socket: function() {
            return socket;
        },

        noop: angular.noop
    }
});