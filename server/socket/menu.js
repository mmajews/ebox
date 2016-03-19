/**
 * Created by konradmarzec on 18.03.2016.
 */
var menu = function(socket) {
    socket.on('move', function() {
        console.log('move');
        socket.broadcast.emit('move')
    })
};

module.exports = menu;