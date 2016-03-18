/**
 * Created by konradmarzec on 18.03.2016.
 */
'use strict';

ebox.factory('SocketService', function() {
    var socket = io.connect();

    return {
        on: function(eventName, callback) {
            socket.on(eventName, function () {
                var args = arguments;
                $rootScope.$apply(function () {
                    callback.apply(socket, args);
                });
            });
        },

        emit: function(eventName, data, callback) {
            socket.emit(eventName, data, function () {
                var args = arguments;
                $rootScope.$apply(function () {
                    if (callback) {
                        callback.apply(socket, args);
                    }
                });
            })
        }
    }
});