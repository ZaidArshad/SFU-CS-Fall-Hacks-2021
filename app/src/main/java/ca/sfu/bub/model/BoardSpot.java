package ca.sfu.bub.model;

public class BoardSpot {
    private Boolean isOccupied;
    private Piece currentPiece;

    public BoardSpot() {
        isOccupied = false;
        currentPiece = null;
    }

    public Boolean getOccupied() {
        return isOccupied;
    }

    public Piece getCurrentPiece() {
        return currentPiece;
    }

    public void setCurrentPiece(Piece currentPiece) {
        this.currentPiece = currentPiece;
        isOccupied = true;
    }
}
