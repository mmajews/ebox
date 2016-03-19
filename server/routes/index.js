var express = require('express');
var router = express.Router();
var QRCode = require('qrcode');
var ip;
require('dns').lookup(require('os').hostname(), function (err, addr, fam) {
  ip = addr;
});

/* GET home page. */
router.get('/', function(req, res, next) {
  res.sendfile('public/index.html');
});

router.get('/qrcode', function(req, res, next) {
    console.log(ip);
    QRCode.draw(ip + ':3000', function(err,canvas){
        stream = canvas.pngStream();
        res.setHeader('Content-type', 'image/png');
        stream.pipe(res);
    });

});

module.exports = router;
