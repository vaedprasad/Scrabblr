(function() {

//GLOBAL VARS

var mainState = {
    preload: function() {

    },
    create: function() {
        board.stage.backgroundColor = '#228B22';
    },
    update: function() {

    },
    restartGame: function() {
        this.boardStarted = false;
        board.state.start('main');
    },
}

var board = new Phaser.Game(500, 500, Phaser.AUTO, 'game');

board.state.add('main', mainState);
board.state.start('main');

})();