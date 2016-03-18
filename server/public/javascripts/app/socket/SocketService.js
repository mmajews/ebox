/**
 * Created by konradmarzec on 18.03.2016.
 */
'use strict';

ebox.factory('SocketService', function() {
    var socket = io.connect();
    return {
        up: function(eventName, callback) {

        },

        down: function(eventName, callback) {

        }
    }
});