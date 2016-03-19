/**
 * Created by konradmarzec on 19.03.2016.
 */
'use strict';

var service = {
    io: null
};

exports.setIO = function(io) {
    service.io = io;
};

exports.io = function() {
    return service.io;
};