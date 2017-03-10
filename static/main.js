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

$(document).ready(function() {
    $('.rack-cell').keydown(function(e) {
        if (e.keyCode >= 65 && e.keyCode <= 90) {

        }
        else {
            e.preventDefault();
        }
    });
});

})();