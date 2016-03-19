/**
 * Created by konradmarzec on 18.03.2016.
 */
var io = require('./../utils/io').io();

var games = function (socket, gameClient) {
    socket.on('padEvent', function (controllerData) {
        console.log('games/up');
        io.sockets.connected[gameClient.id].emit(controllerData.movement, controllerData);
        console.log('oddalem');
    });
};

module.exports = games;