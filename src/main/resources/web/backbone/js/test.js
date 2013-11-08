function submitMove(game){
    responseData = new Object();
    moveString = $('#move_input').val();
    moveProposal = new Object();
    moveProposal.id = game.get('id');
    moveProposal.moveString = moveString;
    moveProposal.moveNumber = game.get('moves').length + 1;
    moveProposal.currentTurn = game.get('currentTurn');
    model = game;
    $.ajax({
        url: "api/game/" + game.get('id') + "/move",
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(moveProposal),
        processData: false,
        dataType: 'json'
    }).done(function( data, status, xhr ) {
        if (xhr.responseJSON.errorMsg != null ){
          alert(xhr.responseJSON.errorMsg);
        } else {
          console.log(data);
          console.log(status);
          console.log(xhr);
        }
        updateGame(game, xhr.responseJSON)
        //fetchGameAndUpdate(model);
      });
};


function updateGame(game, responseData) {
  for (key in responseData) {
    if (key && key != null) {
        game.set(key, responseData.key);
    }
  };

  if (game.get('errorMsg') != null ){
    alert(game.get('errorMsg'));
  };

  currentPlayer = game.get('players')[game.get('currentTurn')];
  board = game.get('board');

  scores = new Palabras.Views.ScoresView({ model: game });
  boardView = new Palabras.Views.BoardView({ model: board });
  currentPlayerView = new Palabras.Views.CurrentPlayerView({ model: game });

  scores.render();
  currentPlayerView.render();
  boardView.render();

  $('#player_move').replaceWith(currentPlayerView.el);
  $('#board').replaceWith(boardView.el);
  $('#scoreboard').replaceWith(scores.el);
  $("#move_input").focus();


};

function copyWithKeys(srcObj, targetObj){
    k = _.keys(srcObj)
    for (var i = 0; i < k.length; i++){
        key = k[i];
        targetObj[key] = srcObj[key];
    }
};

function fetchGame(game){
    var responseData;
    $.ajax({
        url: "api/game/" + game.get('id'),
        type: 'GET'
    }).complete(function( data, status, xhr ) {
        responseData = xhr.responseText;
      });
    return responseData;
};