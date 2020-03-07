package sample;

public class Queen extends Piece {
    public Queen(Player p) {
        super(p);
        character = "Q";
    }


    //possible moves: straight or diagonal (same as rook and bishop combined)
    @Override
    boolean isValidMove(Tile t1, Tile t2) {
        return board.isStraightMovePossible(t1, t2) || board.isDiagonalMovePossible(t1, t2);
    }


}
