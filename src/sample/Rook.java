package sample;

public class Rook extends Piece {

    public Rook(Player p) {
        super(p);
        character = "R";
    }


    //possible moves: horizontal or vertical only
    @Override
    boolean isValidMove(Tile t1, Tile t2) {
        return board.isStraightMovePossible(t1, t2);
    }


}
