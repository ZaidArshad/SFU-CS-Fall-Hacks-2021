package ca.sfu.bub.model;

import android.content.Context;
import android.graphics.Bitmap;

public class GameManager {
    private static GameManager instance;
    private Board board;
    private Player player1;
    private Player player2;
    private int currentTurn;
    public static final int PLAYER_1 = 1;
    public static final int PLAYER_2 = 2;
    public static final int NO_WINNER = 0;
    private Bitmap imageOne;
    private Bitmap imageTwo;
    private Bitmap imageThree;

    private GameManager(Context context) {
        this.board = new Board();
        this.player1 = new Player(PLAYER_1);
        this.player2 = new Player(PLAYER_2);
    }

    public static GameManager getInstance(Context context) {
        if (instance == null) {
            instance = new GameManager(context);
        }
        return instance;
    }

    public Board getBoard() {
        return board;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    private int getCurrentTurn() {
        return currentTurn;
    }

    private boolean placePiece(int col, int row, Piece piece) {
        boolean eaten = board.placePiece(col,row,piece);
        if (piece.getPlayer() == PLAYER_1) currentTurn = PLAYER_2;
        else currentTurn = PLAYER_1;
        return eaten;
    }

    /**
     * Gets the winner of the game if there is one
     * @return
     *      PLAYER_1 (1) if player 1 wins
     *      PLAYER_2 (2) if player 2 wins
     *      NO_WINNER (0) if no one has won yet
     */
    public int getWinner() {
        return board.checkWinner();
    }

    public void setImageOne(Bitmap imageOne) {
        this.imageOne = imageOne;
    }

    public void setImageTwo(Bitmap imageTwo) {
        this.imageTwo = imageTwo;
    }

    public void setImageThree(Bitmap imageThree) {
        this.imageThree = imageThree;
    }
}
