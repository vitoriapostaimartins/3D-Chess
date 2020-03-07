package sample;

import javafx.scene.paint.Color;

public class Player {
    private Color colour;
    private boolean isTurn;

    //creates a new player with the specified colour
    public Player(Color c) {
        colour = c;
    }

    //returns player's color
    public Color getColour() {
        return colour;
    }

    //sets turn of this player
    public void setTurn(boolean t) {
        isTurn = t;
    }

    //returns turn of this player
    public boolean getTurn() {
        return isTurn;
    }
}
