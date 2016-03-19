'use strict';
var gameClient = {
    id: -1
};

var config = function (socket) {
    console.log('dojszlo '+socket.id);
    if ( socket.handshake.query.type == 'gameClient' ) {
        gameClient.id = socket.id;
        console.log('klient gry');
    }
    var menu = require('./menu')(socket, gameClient);
    var games = require('./games')(socket, gameClient);
};


module.exports = config;