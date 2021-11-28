package ca.sfu.bub.model;

public class Player {

    private final Piece[] pieces;

    public Player(int playerNumber){
        pieces = new Piece[6];
        for(int i=0; i<6; i+=2){
            this.pieces[i] = new Piece(i/2, playerNumber);
            this.pieces[i+1] = new Piece(i/2, playerNumber);
        }
    }

    public Piece[] getPieces() {
        return this.pieces;
    }

    public Piece getPiece(int position) {
        return pieces[position];
    }
}
