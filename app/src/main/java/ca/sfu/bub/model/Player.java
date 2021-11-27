package ca.sfu.bub.model;

public class Player {
    // fields
    public Piece[] pieces;

    // constructors
    public Player(Piece[] pieces){
        this.pieces = pieces;
    }

    // getters
    public Piece[] getPieces() {
        return this.pieces;
    }

    // setters
    public void setPieces(Piece[] pieces) {
        this.pieces = pieces;
    }
}
