package ca.sfu.bub.model;

public class Player {
    // fields
    private Piece[] pieces;

    // constructors
    public Player(int playerNumber){
        pieces = new Piece[6];
        for(int i=0; i<6; i+=2){
            this.pieces[i] = new Piece(i/2, playerNumber);
            this.pieces[i+1] = new Piece(i/2, playerNumber);
        }
    }

    // getters
    public Piece[] getPieces() {
        return this.pieces;
    }
}
