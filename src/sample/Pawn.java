package sample;

import javafx.scene.paint.Color;

public class Pawn extends Piece {
    private int moveCount = 0;

    public Pawn(Player p) {
        super(p);
        character = "P";
    }


    //possible moves: 1 or 2 forward if it is first move; 1 forward if it is not
    @Override
    boolean isValidMove(Tile t1, Tile t2) {
        int switcher = 1; //default value
        if (t1.getPiece().getPlayer().getColour() == Color.RED) { //if the pawn in at the bottom
            switcher = -1;
        }
        //if it is pawn's first move and if the move is one or 2 forward
        if (moveCount == 0 && board.isOneOrTwoForwardPossible(t1, t2, switcher)) {
                moveCount++;
                return true;
        }
        //if it is not pawns first move and if the move is one forward
        else if (moveCount != 0 && board.isOneForwardPossible(t1, t2, switcher)){
                moveCount++;
                return true;
        }
        return false;
    }
}
