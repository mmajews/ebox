/**
 * Created by konradmarzec on 18.03.2016.
 */
var io = require('./../utils/io').io();

var games = function (socket, gameClient) {
    socket.on('event', function (controllerData) {
        console.log('games/up');
        io.sockets.connected[gameClient.id].emit('up');
        console.log('oddalem');
    });

    socket.on('down', function () {
        console.log('games/down');
        socket.broadcast.emit('games/down');
    });
};

module.exports = games;