package sample;

import javafx.scene.paint.Color;

abstract class Piece {
    public Player player;
    public String character;
    public Color color;
    public Board board;
    public Board[] boards;

    //creates a new piece
    public Piece(Player p) {
        player = p;
        color = p.getColour();
    }

    //Sets the board the piece is using. Allows visibility of the board to the piece.
    public void setBoard(Board b) {
        board = b;
    }

    //returns player that owns this piece
    public Player getPlayer() {
        return player;
    }

    //returns colour of the piece
    public Color getColor() {
        return color;
    }

    //returns the character that represents this piece
    @Override
    public String toString() {
        return character;
    }

    //returns if move is possible for this piece
    abstract boolean isValidMove(Tile t1, Tile t2);

    //sets the boards to the pieces
    public void setBoards(Board[] b) {
        boards = b;
    }


}
