/**
 * Created by konradmarzec on 18.03.2016.
 */
var menu = function(socket) {
    socket.on('up', function() {
        console.log('games/up');
        socket.broadcast.emit('games/up');
    });

    socket.on('down', function() {
        console.log('games/down');
        socket.broadcast.emit('games/down');
    });
};

module.exports = menu;