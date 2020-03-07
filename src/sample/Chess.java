package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Chess extends Application {
    //creates the 3 boards
    private Board chessBoard;
    private Board chessBoard2;
    private Board chessBoard3;
    //creates the players
    private Player whitePlayer;
    private Player blackPlayer;
    static final int BOARDWIDTH = 8;
    static final int BOARDHEIGHT = 8;

    //creates a new chess game
    public Chess() {
        chessBoard = new Board(BOARDWIDTH, BOARDHEIGHT, 1);
        chessBoard2 = new Board(BOARDWIDTH, BOARDHEIGHT, 2);
        chessBoard3 = new Board(BOARDWIDTH, BOARDHEIGHT, 3);
        whitePlayer = new Player(Color.RED);
        whitePlayer.setTurn(true);
        blackPlayer = new Player(Color.BLUE);
        blackPlayer.setTurn(false);
        chessBoard.setPlayers(whitePlayer, blackPlayer);
        chessBoard2.setPlayers(whitePlayer, blackPlayer);
        chessBoard3.setPlayers(whitePlayer, blackPlayer);
        setInitialPieces();
        chessBoard.setBorderColor("red");
        chessBoard2.setBorderColor("green");
        chessBoard3.setBorderColor("blue");
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane sp = new GridPane();
        sp.add(chessBoard.getBoardUI(), 0, 0);
        sp.add(chessBoard2.getBoardUI(), 1, 0);
        sp.add(chessBoard3.getBoardUI(), 2, 0);
        Scene scene = new Scene(sp, Tile.SIZE * chessBoard.getWidth() * 3 + (3 * (Board.BORDER + Board.PADDING) + 25),
                (Tile.SIZE * chessBoard.getHeight()) + Board.BORDER + Board.PADDING + 10);
        primaryStage.setTitle("Chess");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    //initializes all the initial pieces and adds them to the tile array from board
    public void setInitialPieces() {
        chessBoard.getTiles()[0][0].setPiece(new Rook(blackPlayer));
        chessBoard.getTiles()[0][1].setPiece(new Knight(blackPlayer));
        chessBoard.getTiles()[0][2].setPiece(new Bishop(blackPlayer));
        chessBoard.getTiles()[0][3].setPiece(new Queen(blackPlayer));
        chessBoard.getTiles()[0][4].setPiece(new King(blackPlayer));
        chessBoard.getTiles()[0][5].setPiece(new Bishop(blackPlayer));
        chessBoard.getTiles()[0][6].setPiece(new Knight(blackPlayer));
        chessBoard.getTiles()[0][7].setPiece(new Rook(blackPlayer));
        chessBoard.getTiles()[7][7].setPiece(new Rook(whitePlayer));
        chessBoard.getTiles()[7][6].setPiece(new Knight(whitePlayer));
        chessBoard.getTiles()[7][5].setPiece(new Bishop(whitePlayer));
        chessBoard.getTiles()[7][4].setPiece(new King(whitePlayer));
        chessBoard.getTiles()[7][3].setPiece(new Queen(whitePlayer));
        chessBoard.getTiles()[7][2].setPiece(new Bishop(whitePlayer));
        chessBoard.getTiles()[7][1].setPiece(new Knight(whitePlayer));
        chessBoard.getTiles()[7][0].setPiece(new Rook(whitePlayer));
        for (int i = 0; i < chessBoard.getWidth(); i++) {
            chessBoard.getTiles()[1][i].setPiece(new Pawn(blackPlayer));
            chessBoard.getTiles()[6][i].setPiece(new Pawn(whitePlayer));
        }

        for (int i = 0; i < chessBoard.getTiles().length; i++) {
            for (int j = 0; j < chessBoard.getTiles()[i].length; j++) {
                if (!chessBoard.getTiles()[i][j].isEmpty()) {
                    chessBoard.getTiles()[i][j].getPiece().setBoard(chessBoard);
                    chessBoard.getTiles()[i][j].getPiece().setBoards(new Board[]{chessBoard, chessBoard2, chessBoard3});
                }

            }
        }


    }

    public static void main(String[] args) {
        launch(args);
    }
}

