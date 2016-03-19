/* 
* @author Niklas von Hertzen <niklas at hertzen.com>
* @created 4.1.2012 
* @website http://hertzen.com
 */



GTA.Player = function ( game, x, y, z ) {

    var self = this;
    
    this.position.x = x;
    this.position.y = y;
    this.position.z = z;

    this.registerAnimations( game.spriteNumbers.offset.PED );
    
    this.initPhysics( game );
    
    
    var geom = THREE.GeometryUtils.clone( game.sprites[ this.animationSprites[ 0 ][ 0 ] ].sprite.geometry );
    
    this.sprite = new THREE.Mesh( geom, game.sprites[ this.animationSprites[ 0 ][ 0 ] ].sprite.material );
    
    //this.sprite = THREE.SceneUtils.cloneObject( game.sprites[ this.animationSprites[ 0 ][ 0 ] ].sprite );

    //this.sprite.geometry = THREE.GeometryUtils.clone( this.sprite.geometry );
    this.sprite.geometry.dynamic = true; 
    
    this.spriteAnimator = new GTA.SpriteAnimation( game, this.animationSprites[ 0 ][ 0 ], this.sprite ); 

    this.game =  game;

    this.add( this.sprite );
    this.speed = 700;
    this.rotationSpeed = 0.1;
    
 
    
    this.weapon = 0;
    
    this.lastframe = 0;
    
    this.runningFrames = [];
    this.spriteframe = 0;
    
   

    
    this.domElement = document;

    window.mySocket.on('left', function () {
        console.log('left web')
        self.turnRight = false;
        self.turnLeft = true;
    });

    window.mySocket.on('right', function () {
        console.log('right web')

        self.turnLeft = false;
        self.turnRight = true;
    });

    window.mySocket.on('touchDown', function () {
        console.log('down web')

        self.moveForward = true;
    });

    window.mySocket.on('touchUp', function () {
        console.log('up web')
        self.moveForward = false;
    });

    window.mySocket.on('stop', function () {
        console.log('up web')
        self.turnLeft = false;
        self.turnRight = false;
    });
    
    this.onKeyDown = function ( event ) {
        switch( event.keyCode ) {

            case 38: /*up*/
            case 87: /*W*/
                this.moveForward = true;
                break;

            case 37: /*left*/
            case 65: /*A*/
                this.turnLeft = true;
                break;

            case 40: /*down*/
            case 83: /*S*/
                this.moveBackward = true;
                break;

            case 39: /*right*/
            case 68: /*D*/
                this.turnRight = true;
                break;

            case 82: /*R*/
                this.moveUp = true;
                break;
            case 70: /*F*/
                this.moveDown = true;
                break;

            case 81: /*Q*/
                this.freeze = !this.freeze;
                break;

        }
        
    };
    
    this.onKeyUp = function ( event ) {

        switch( event.keyCode ) {

            case 38: /*up*/
            case 87: /*W*/
                this.moveForward = false;
                break;

            case 37: /*left*/
            case 65: /*A*/
                this.turnLeft = false;
                break;

            case 40: /*down*/
            case 83: /*S*/
                this.moveBackward = false;
                break;

            case 39: /*right*/
            case 68: /*D*/
                this.turnRight = false;
                break;

            case 82: /*R*/
                this.moveUp = false;
                break;
            case 70: /*F*/
                this.moveDown = false;
                break;

        }

    };
    
    
    this.domElement.addEventListener( 'keydown', bind( this, this.onKeyDown ), false );
    this.domElement.addEventListener( 'keyup', bind( this, this.onKeyUp ), false );
    
    
};


function bind( scope, fn ) {

    return function () {

        fn.apply( scope, arguments );

    };

};

GTA.Player.prototype = GTA.Pedestrian.prototype;
GTA.Player.prototype.constructor = GTA.Player;

