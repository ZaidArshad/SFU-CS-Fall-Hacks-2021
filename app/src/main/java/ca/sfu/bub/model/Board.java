package ca.sfu.bub.model;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class Board {
    private final BoardSpot[][] boardSpots;
    public static final int PLAYER_1 = 1;
    public static final int PLAYER_2 = 2;
    public static final int NO_WINNER = 0;

    public Board(ImageView[] imageList) {
        int i = 0;
        boardSpots = new BoardSpot[3][3];
        for (int col = 0; col < 3; col++) {
            for (int row = 0; row < 3; row++) {
                boardSpots[col][row] = new BoardSpot(col, row, imageList[i]);
                i++;
            }
        }
    }

    public BoardSpot[][] getBoardSpots() {
        return boardSpots;
    }

    /**
     *
     * @param col col of piece
     * @param row row of piece
     * @param piece Piece to be placed
     * @return If the piece consumes another piece
     */
    public boolean placePiece(int col, int row, Piece piece, Bitmap image, int turn) {
        boolean result = false;
        if (boardSpots[col][row].getOccupied()) {
            result = true;
        }

        boardSpots[col][row].setCurrentPiece(piece, image, turn);

        return result;
    }

    /**
     * Gets the winner of the game if there is one
     * @return
     *      PLAYER_1 (1) if player 1 wins
     *      PLAYER_2 (2) if player 2 wins
     *      NO_WINNER (0) if no one has won yet
     */
    public int checkWinner() {

        // Check for arrangements vertically
        int player1Count = 0;
        int player2Count = 0;
        for (int col = 0; col < 3; col++) {
            int owner = belongToPlayer(boardSpots[col][0]);
            if (owner == PLAYER_1) player1Count++;
            else if (owner == PLAYER_2) player2Count++;
            else if (owner == NO_WINNER) break;
        }
        if (player1Count == 3) return PLAYER_1;
        else if (player2Count == 3) return PLAYER_2;

        // Check for arrangements horizontally
        player1Count = 0;
        player2Count = 0;
        for (int row = 0; row < 3; row++) {
            int owner = belongToPlayer(boardSpots[0][row]);
            if (owner == PLAYER_1) player1Count++;
            else if (owner == PLAYER_2) player2Count++;
            else if (owner == NO_WINNER) break;
        }
        if (player1Count == 3) return PLAYER_1;
        else if (player2Count == 3) return PLAYER_2;

        // Check for arrangement diagonally top left | mid | bot right
        player1Count = 0;
        player2Count = 0;
        for (int row = 0; row < 3; row++) {
            int col = row;
            int owner = belongToPlayer(boardSpots[col][row]);
            if (owner == PLAYER_1) player1Count++;
            else if (owner == PLAYER_2) player2Count++;
            else if (owner == NO_WINNER) break;
        }
        if (player1Count == 3) return PLAYER_1;
        else if (player2Count == 3) return PLAYER_2;

        // Check for arrangement diagonally top right | mid | bot left
        player1Count = 0;
        player2Count = 0;
        for (int row = 0; row < 3; row++) {
            int col = 2-row;
            int owner = belongToPlayer(boardSpots[col][row]);
            if (owner == PLAYER_1) player1Count++;
            else if (owner == PLAYER_2) player2Count++;
            else if (owner == NO_WINNER) break;
        }
        if (player1Count == 3) return PLAYER_1;
        else if (player2Count == 3) return PLAYER_2;

        return NO_WINNER;
    }

    private Integer belongToPlayer(BoardSpot boardSpot) {
        if (boardSpot.getOccupied()) {
            if (boardSpot.getCurrentPiece().getPlayer() == PLAYER_1) {
                return PLAYER_1;
            }
            else if (boardSpot.getCurrentPiece().getPlayer() == PLAYER_2) {
                return PLAYER_2;
            }
        }
        return NO_WINNER;
    }
}
