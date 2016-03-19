/**
 * Created by konradmarzec on 18.03.2016.
 */
var io = require('./../utils/io').io();

var games = function (socket, gameClient) {
    console.log("costam");
    socket.on('padEvent', function (controllerData) {
        console.log("pad event: " + controllerData);
        try {
            controllerData = JSON.parse(controllerData);
        } catch(err) {}
        io.sockets.connected[gameClient.id].emit(controllerData.name, controllerData);
    });
};

module.exports = games;