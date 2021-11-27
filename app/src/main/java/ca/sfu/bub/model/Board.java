package ca.sfu.bub.model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Board {
    private BoardSpot[][] boardSpots;
    public static final int PLAYER_1 = 1;
    public static final int PLAYER_2 = 2;
    public static final int NO_WINNER = 0;

    public Board() {
        boardSpots = new BoardSpot[3][3];
        for (int col = 0; col < 3; col++) {
            for (int row = 0; row < 3; row++) {
                boardSpots[col][row] = new BoardSpot();
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
    public boolean placePiece(int col, int row, Piece piece) {
        boolean result = false;
        if (boardSpots[col][row].getOccupied()) result = true;
        boardSpots[col][row].setCurrentPiece(piece);
        return result;
    }

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
