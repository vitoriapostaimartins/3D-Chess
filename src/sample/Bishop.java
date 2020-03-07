package sample;


public class Bishop extends Piece {

    public Bishop(Player p) {
        super(p);
        character = "B";

    }


    @Override
    boolean isValidMove(Tile t1, Tile t2) {
        //possible moves: diagonal
        return board.isDiagonalMovePossible(t1, t2);
    }


}
