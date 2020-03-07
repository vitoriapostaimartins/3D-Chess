package sample;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Board {
    //set of tiles on the board
    private Tile[][] tiles;
    private int width;
    private int height;
    private Player[] players;
    private String colour;
    static final int PADDING = 2;
    static final int BORDER = 5;
    private int level;


    //creates a new board with a specified width and height
    public Board(int width, int height, int l) {
        this.width = width;
        this.height = height;
        tiles = new Tile[width][height];
        create();
        level = l;

    }

    //return level
    public int getLevel(){
        return level;
    }

    //sets players of the game
    public void setPlayers(Player p1, Player p2) {
        players = new Player[]{p1, p2};
    }

    //returns players that are playing the game
    public Player[] getPlayers() {
        return players;
    }

    //creates all the tiles and adds them to the board
    private void create() {
        Color colour;
        int counter = 0;
        for (int i = 0; i < tiles.length; i++) {
            int devideResult = 0;
            if (i % 2 == 1) {
                devideResult = 1;
            }
            for (int j = 0; j < tiles[i].length; j++) {
                if (counter % 2 == devideResult)
                    colour = Color.BEIGE;

                else
                    colour = Color.BLACK;
                int[] coord = {i, j};
                tiles[i][j] = new Tile(colour, coord);
                tiles[i][j].setBoard(this);
                counter++;
            }
        }
    }

    //sets the colour of the border
    public void setBorderColor(String c) {
        colour = c;
    }

    //returns the gridpane that represents the board. the user interface
    public GridPane getBoardUI() {
        GridPane board = new GridPane();
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                board.add(tiles[i][j].getTileUI(), j, i);
            }
        }
        board.setStyle("-fx-padding: " + PADDING + ";" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: " + BORDER + ";" +
                "-fx-border-color: " + colour + ";");
        return board;
    }


    //returns tile array (tiles on the board)
    public Tile[][] getTiles() {
        return tiles;
    }


    //returns board width
    public int getWidth() {
        return width;

    }

    //returns board height
    public int getHeight() {
        return height;
    }

    //changes the turns of the game
    public void changeTurns() {
        System.out.println("Changing turns");
        players[0].setTurn(!players[0].getTurn());
        players[1].setTurn(!players[1].getTurn());
    }

    //returns if the move is a diagonal path
    private boolean isDiagonal(Tile t1, Tile t2){
        return Math.abs(t2.getRow() - t1.getRow()) == Math.abs(t2.getCol() - t1.getCol());
    }

    //returns the difference between the levels
    private int difBoards(Board b1, Board b2){
        return Math.abs(b1.getLevel() - b2.getLevel());
    }

    //returns the difference between the rows of the two tiles
    private int difRows(Tile t1, Tile t2){
        return Math.abs(t2.getRow() - t1.getRow());
    }

    //returns the difference between the columns of the two tiles
    private int difCols(Tile t1, Tile t2){
        return Math.abs(t2.getCol() - t1.getCol());
    }

    //returns if the diagonal move is possible (considering different boards as well)
    boolean isDiagonalMovePossible(Tile t1, Tile t2){
        // Formula used: |X2-X1|=|Y2-Y1|
        //if it is diagonal
        if (isDiagonal(t1, t2)) {
            int difBoards = difBoards(t1.getBoard(), t2.getBoard());
            int difRows = difRows(t2, t1);
            //if it is the same board
            if (difBoards == 0) {
                return isDiagonalClear(t1, t2);
            } else {
                //if the difference between tiles is 1
                if (difBoards == difRows) {
                    t1.getPiece().setBoard(t2.getBoard());
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    //returns if the one tile move is possible (considering different boards as well)
    public boolean isOneTileMovePossible(Tile t1, Tile t2){
        //Formula used: |X2-X1|<=1 and |Y2-Y1|<=1
        //if it is the same board or the diff between boards is one
        int difBoards = difBoards(t1.getBoard(), t2.getBoard());
        int difRows = difRows(t1, t2);
        int difCols = difCols(t1, t2);
        if(difBoards == 0 || difBoards == 1)
            return difRows <= 1 && difCols <= 1;
        return false;
    }

    //returns if the L move is possible (considering different boards as well)
    public boolean isLMovePossible(Tile t1, Tile t2){
        int difBoards = difBoards(t1.getBoard(), t2.getBoard());
        //Formula used: (|X2-X1|=1 and |Y2-Y1|=2) or (|X2-X1|=2 and |Y2-Y1|=1)
        if (difBoards == 0 || difBoards == 1){
            int difRows = difRows(t1, t2);
            int difCols = difCols(t1, t2);
            return ((difRows == 1 && difCols == 2) || (difRows == 2 && difCols == 1));
        }
        return false;
    }

    //returns if the horizontal or vertical move is possible (considering different boards as well)
    public boolean isStraightMovePossible(Tile t1, Tile t2){
        //Formula used: X2=X1 or Y2=Y1.
        int difRows = difRows(t1, t2);
        int difCols = difCols(t1, t2);
        int difBoards = difBoards(t1.getBoard(), t2.getBoard());
        if (difRows == 0 || difCols == 0) {
            if (difBoards == 0) {
                return isStraightPathClear(t1, t2);
            } else if(difBoards == 1){
                if(difRows == 0){
                    if(difCols == 1){
                        t1.getPiece().setBoard(t2.getBoard());
                        return true;
                    }
                } else{
                    if(difRows == 1){
                        t1.getPiece().setBoard(t2.getBoard());
                        return true;
                    }
                }
                return false;
            } else{
                if((difRows == 0 && difCols == 2) || (difRows == 2)){
                    t1.getPiece().setBoard(t2.getBoard());
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    //returns if the one forward move is possible (considering different boards as well)
    public boolean isOneForwardPossible(Tile t1, Tile t2, int switcher){
        //Formula used: X2=X1 and Y2-Y1=1
        int difCols = difCols(t1, t2);
        int difBoards = difBoards(t1.getBoard(), t2.getBoard());
        if (difCols == 0 && t2.getRow() - t1.getRow() == (switcher)) {
            if (difBoards != 2) {
                t1.getPiece().setBoard(t2.getBoard());
                return true;
            }else {
                return false;
            }
        }
        return false;
    }

    //returns if the one or two forward move is possible (considering different boards as well)
    public boolean isOneOrTwoForwardPossible(Tile t1, Tile t2, int switcher){
        //Formula used: X2=X1 and Y2-Y1=1
        int difCols = difCols(t1, t2);
        int difRows = difRows(t1, t2);
        int difBoards = difBoards(t1.getBoard(), t2.getBoard());
        if (difCols == 0 && (t2.getRow() - t1.getRow() == switcher ||
                t2.getRow() - t1.getRow() == (2 * switcher))) {
            if (difBoards == 0 && isStraightPathClear(t1, t2)) {
                return true;
            } else if (difBoards == difRows) {
                t1.getPiece().setBoard(t2.getBoard());
                return true;
            }
        } else {
            System.out.println("cant move pawn");
        }
        return false;
    }

    //returns true if the diagonal is clear of pieces; otherwise, returns false
    private boolean isDiagonalClear(Tile t1, Tile t2) {
        if(t1.equals(t2))
            return false;
        int switcherRow = 1; //default value
        int switcherCol = 1; //default value

        if (t1.getRow() > t2.getRow()) {
            switcherRow = -1; //if the first tile is below the second one
        }
        if (t1.getCol() > t2.getCol()) {
            switcherCol = -1; //if the first tile is to the right of the second one
        }
        //goes through every tile on the diagonal
        for (int i = 0; i < (Math.sqrt(2 * Math.abs(t2.getRow() - t1.getRow()))); i++) {
            try {
                //if there is a tile that is not empty, returns false
                if (!this.getTiles()[t1.getRow() + (i * switcherRow) + switcherRow][t1.getCol() +
                        (i * switcherCol) + switcherCol].isEmpty()) {
                    return false;
                }
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
        }
        return true;
    }

    //returns true if the straight path is clear of pieces; otherwise, returns false
    private boolean isStraightPathClear(Tile t1, Tile t2) {
        if(t1.equals(t2))
            return false;
        int switcher = 1;
        if (t1.getRow() > t2.getRow() || t1.getCol() > t2.getCol())
            switcher = -1;
        if (t1.getRow() == t2.getRow()) {
            for (int i = 0; i < Math.abs(t2.getCol() - t1.getCol()); i++) {
                try {
                    if (!this.getTiles()[t1.getRow()][t1.getCol() + (i * switcher) + switcher].isEmpty()) {
                        return false;
                    }
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
            }
        } else {
            for (int i = 0; i < Math.abs(t2.getRow() - t1.getRow()); i++) {
                try {
                    if (!this.getTiles()[t1.getRow() + (i * switcher) + switcher][t1.getCol()].isEmpty()) {
                        return false;
                    }
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
            }
        }
        return true;
    }
}

