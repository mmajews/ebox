/**
 * Created by konradmarzec on 18.03.2016.
 */
var io = require('./../utils/io').io();

var games = function (socket, gameClient) {
    socket.on('padEvent', function (controllerData) {
        try {
            controllerData = JSON.parse(controllerData);
        } catch(err) {}
        if (controllerData.name == 'orientation') {
            if (controllerData.orientation < -3.5) {
                setTimeout(function() {
                    io.sockets.connected[gameClient.id].emit('left');
                }, 25)

            } else if (controllerData.orientation > 3.5) {
                setTimeout(function() {
                    io.sockets.connected[gameClient.id].emit('right');
                }, 25)
            } else {
                io.sockets.connected[gameClient.id].emit('stop');
            }
        }
        io.sockets.connected[gameClient.id].emit(controllerData.name, controllerData);
    });
};

module.exports = games;