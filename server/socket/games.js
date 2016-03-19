/**
 * Created by konradmarzec on 18.03.2016.
 */
var io = require('./../utils/io').io();

var games = function (socket, gameClient) {
    socket.on('padEvent', function (controllerData) {
        try {
            controllerData = JSON.parse(controllerData);
        } catch(err) {}
        io.sockets.connected[gameClient.id].emit(controllerData.movement, controllerData);
    });
};

module.exports = games;