package sample;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Tile {
    public static Tile tile = null;
    public static final int SIZE = 50;
    int[] coord;
    Piece piece = null;
    Color colour;
    Button tileUI;
    Board board;

    //every tile has a position and a colour
    public Tile(Color col, int[] c) {
        colour = col;
        tileUI = new Button();
        tileUI.setMinWidth(SIZE);
        tileUI.setMinHeight(SIZE);
        if (col == Color.BEIGE) {
            tileUI.setStyle("-fx-background-color: Beige;");
        } else {
            tileUI.setStyle("-fx-background-color: Black;");
        }
        tileUI.setFont(new Font(15));
        coord = c;
        ClickHandler click = new ClickHandler();
        tileUI.setOnMouseClicked(click);
    }


    //returns the piece that is on the tile
    public Piece getPiece() {
        return piece;
    }

    //returns the board that the tile's piece is on
    public Board getBoard() {
        return board;
    }

    //sets the board that the tile's piece is on
    public void setBoard(Board b) {
        board = b;
    }

    //places piece on a tile (writes the character on the tile ui)
    public void setPiece(Piece p) {
        if (p == null)
            return;
        piece = p;
        tileUI.setText(p.toString());
        tileUI.setTextFill(p.getColor());
    }


    //removes piece from a tile
    public void removePiece() {
        piece = null;
        tileUI.setText("");
    }

    //returns the buttons (tiles) in order for them to be added to the board ui (gridpane)
    public Button getTileUI() {
        return tileUI;
    }

    //checks if a tile is empty
    public boolean isEmpty() {
        return piece == null;
    }

    //returns row of this tile
    public int getRow() {
        return coord[0];
    }

    //returns column of this tile
    public int getCol() {
        return coord[1];
    }

    //handles the mouse clicks
    public class ClickHandler implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent mouseEvent) {
            System.out.println("red player - beginning handle method: " + board.getPlayers()[0].getTurn());
            System.out.println("blue player - beginning handle method: " + board.getPlayers()[1].getTurn());

            //whenever the person clicks on a tile, it identifies if the tile is empty or not. the second click
            // places the piece from the previously selected tile on the new tile, and calls removePiece() to
            // remove the piece from the previous tile.
            if (tile == null && !Tile.this.isEmpty()) { //check if any tile is selected and if this tile has a piece
                tile = Tile.this; //select tile

            } else {
                //check if any tile is selected or if the selected tile is the one that was clicked
                if (tile == null || tile == Tile.this || !tile.piece.getPlayer().getTurn()) {
                    tile = null;
                    return;
                }
                if (tile.getPiece().isValidMove(tile, Tile.this) && Tile.this.isEmpty()) {
                    Tile.this.setPiece(tile.piece);
                    tile.removePiece();
                    tile = null;
                    board.changeTurns();
                } else {
                    tile = null;
                }

            }

        }
    }

}

