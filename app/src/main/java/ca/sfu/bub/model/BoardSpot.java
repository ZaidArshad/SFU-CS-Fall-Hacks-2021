package ca.sfu.bub.model;

import android.widget.ImageView;

public class BoardSpot {
    private Boolean isOccupied;
    private Piece currentPiece;
    private ImageView image;
    private Integer row;
    private Integer col;

    public BoardSpot(Integer col, Integer row, ImageView image) {
        isOccupied = false;
        currentPiece = null;
        this.row = row;
        this.col = col;
        this.image = image;
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

    public void setImage(ImageView image) {
        this.image = image;
    }

    public ImageView getImage() {
        return image;
    }
}
