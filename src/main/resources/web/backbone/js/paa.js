var game = (function () {
    var json = null;
    $.ajax({
        'async': false,
        'global': false,
        'url': "http://localhost:8082/twc/",
        //'url': "assets/test3.json",
        'dataType': "json",
        'success': function (data) {
            json = data;
            gameLogic();
        }
    });
    return json;
})();

function fetchGame() {
    var json = null;
    $.ajax({
        async: false,
        global: false,
        url: "http://localhost:8082/twc/",
        data: { move: $("#move").val() },
        //data: { move: "John", location: "Boston" }
        //'url': "assets/test3.json",
        dataType: "json",
        success: function (data) {
            json = data;
            //gameLogic();
        }
    });
    return json;
};

function gameLogic(){
  if (game != null) {
    if (game.tile_bag.tiles.length == 0) {
      gameOver();
    }
    alert(game.tile_bag.tiles.length + " left in the bag");
  }
};

/*
Fisher-Yates shuffle algorithm
http://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle#The_modern_algorithm
*/
function shuffle(arr){
  for (var i=arr.length-1; i > 0; i--){
    j = Math.floor((Math.random()*i)+0);
    tmp = arr[j];
    arr[j] = arr[i];
    arr[i] = tmp;
  }
  return arr;
};

function gameOver(){
  alert("gameover man!");
};

function test(){
  //alert(json.id);
  game = fetchGame();
  redrawTable(game);
  redrawTileRack();
  redrawScoreboard();
};

function redrawTable(game) {
  var tbl = document.createElement("table");
  for (var i=0; i<game.board.spaces.length; i++){
    var tr = document.createElement("tr");
    for (var j=0; j<game.board.spaces[i].length; j++){
      var td = document.createElement("td");
      var space = game.board.spaces[i][j];
      td.className = space.type;
      if (space.occupied) {
        td.innerHTML = space.value;
        td.className += (" occupied");
      }
      tr.appendChild(td);
    }
    tbl.appendChild(tr);
  }
  tbl.setAttribute("id","board");
  $( "#board" ).replaceWith(tbl);
};

function redrawTileRack(tiles) {
  //tiles = tiles || game.players[game.current_turn].tiles;
  if (typeof tiles === "undefined" || tiles === null) { 
    tiles = game.players[game.current_turn].tiles; 
  }
  var tbl = document.createElement("table");
  var tr = document.createElement("tr");
  for (var i=0; i<tiles.length; i++){
    var td = document.createElement("td");
    td.innerHTML = tiles[i].character + "<sub class='tile_num'>" + tiles[i].points + "</sub>";
    tr.appendChild(td);
    }
  tbl.appendChild(tr);
  tbl.setAttribute("id","tile_rack");
  $( "#tile_rack" ).replaceWith(tbl);
  $( "#move_prompt" ).html(game.players[game.current_turn].name + ", it's your move:")
};

function redrawScoreboard(players) {
  //tiles = tiles || game.players[game.current_turn].tiles;
  if (typeof players === "undefined" || players === null) { 
    players = game.players; 
  }
  var tbl = document.createElement("table"); 
  for (var i=0; i<players.length; i++){
    var tr = document.createElement("tr");
    var td = document.createElement("td");
    td.innerHTML = players[i].name;
    var td2 = document.createElement("td");
    td2.innerHTML = players[i].score;
    tr.appendChild(td);tr.appendChild(td2);
    tbl.appendChild(tr);
  }
  tbl.setAttribute("id","score_table");
  $( "#score_table" ).replaceWith(tbl);
};

function shuffleTiles(tiles) {
  redrawTileRack(shuffle(game.players[game.current_turn].tiles));
};