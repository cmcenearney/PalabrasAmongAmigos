package palabrasamongamigos.core;

/*
class to handle incoming json move requests, or "proposals", eg:
  {
    "id": 123456789,
    "moveString": "a,12,>,word",
    "moveNumber": 2,
    "currentTurn": 0
  }
*/
public class MoveProposal {

    //attributes
    private long id;
    private String moveString;
    private int moveNumber;
    private int currentTurn;

    //constructors
    public MoveProposal() {}

    public MoveProposal(long id, String moveString, int moveNumber, int currentTurn) {
        this.id = id;
        this.moveString = moveString.toUpperCase();
        this.moveNumber = moveNumber;
        this.currentTurn = currentTurn;
    }

    //getters + setters
    public int getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(int currentTurn) {
        this.currentTurn = currentTurn;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMoveString() {
        return moveString;
    }

    public void setMoveString(String moveString) {
        this.moveString = moveString;
    }

    public int getMoveNumber() {
        return moveNumber;
    }

    public void setMoveNumber(int moveNumber) {
        this.moveNumber = moveNumber;
    }

    public Move newMove(GameModel game){
        String[] args = moveString.split(",");
        int row = ( (int) args[0].charAt(0)) - 65;
        int column = Integer.parseInt(args[1]) - 1;
        boolean across = (args[2].equals(">"));
        String word = args[3];
        Player currentPlayer = game.getPlayers().get(game.currentTurn);
        Move move = new Move(game, row, column, word, across, currentPlayer);
        return move;
    }
}
