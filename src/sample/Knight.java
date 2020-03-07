package sample;

public class Knight extends Piece {

    public Knight(Player p) {
        super(p);
        character = "Kn";
    }


    //possible moves: "L" move
    @Override
    boolean isValidMove(Tile t1, Tile t2) {
        return board.isLMovePossible(t1, t2);
    }

}
