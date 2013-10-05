// Models

var Game = Backbone.Model.extend({
  url: "http://localhost/TermsWithChums/src/webapp/assets/test_new_model.json"
});




//Views
var GameView = Backbone.View.extend({

  render: function(){
    var rendered = "<h1>" + "Current turn: " + this.model.get( 'current_turn' ) + "</h1>";
    $(this.el).html( rendered );
  }
});


//App
/*
var App = new (Backbone.Router.extend({

  routes: {"": "index"},

  initialize: function(){
    this.game = new Game();
    this.game.fetch();
    this.gameView = new GameView(this.game);
    //this.gameView.render();
    $('#main').append(this.gameView.el);
  },

  start: function(){
    Backbone.history.start({pushState: true});
  },

  index: function(){
    this.gameView.render();
  }


}));


$(function(){ App.start() });
*/


var game = new Game();
var gameView = new GameView({model: game});
game.fetch({
  success: function(){
    gameView.render();
    $('#main').html(gameView.el);
  }
});



