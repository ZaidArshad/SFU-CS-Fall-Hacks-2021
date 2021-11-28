package ca.sfu.bub.model;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.widget.ImageView;

public class BoardSpot {
    private Boolean isOccupied;
    private Piece currentPiece;
    private final ImageView image;
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

    public void setCurrentPiece(Piece currentPiece, Bitmap image, int turn) {
        this.currentPiece = currentPiece;
        isOccupied = true;
        this.image.setImageBitmap(image);
        if (turn == GameManager.PLAYER_1) this.image.setColorFilter(Color.argb(80, 255, 0, 0));
        if (turn == GameManager.PLAYER_2) this.image.setColorFilter(Color.argb(80, 0, 0, 255));

    }

    public ImageView getImage() {
        return image;
    }
}
