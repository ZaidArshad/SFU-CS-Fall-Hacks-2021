package ca.sfu.bub.model;

public class GameManager {
    private static GameManager instance;
    private Board board;
    private Player player1;
    private Player player2;
    private int currentTurn;
    public static final int PLAYER_1 = 1;
    public static final int PLAYER_2 = 2;
    public static final int NO_WINNER = 0;

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    private GameManager() {
        this.board = new Board();
        this.player1 = new Player(PLAYER_1);
        this.player2 = new Player(PLAYER_2);
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
}
