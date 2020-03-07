package sample;


public class King extends Piece {

    public King(Player p) {
        super(p);
        character = "Ki";
    }

    //possible moves: any way as long as its just one tile
    @Override
    boolean isValidMove(Tile t1, Tile t2) {
        return board.isOneTileMovePossible(t1, t2);
    }

}
