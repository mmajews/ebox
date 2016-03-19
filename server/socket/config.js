'use strict';
var gameClient = {
    id: -1
};

var config = function (socket) {
    if ( socket.handshake.query.type == 'gameClient' ) {
        gameClient.id = socket.id;
    }
    var menu = require('./menu')(socket, gameClient);
    var games = require('./games')(socket, gameClient);
};


module.exports = config;