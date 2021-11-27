package ca.sfu.bub.model;

import android.graphics.Bitmap;

public class Piece {
    // fields
    private final int size;
    private final int owner;
    private boolean isUsed;
    private int xPos;
    private int yPos;

    // constructor
    public Piece(int size, int owner) {
        this.size = size;
        this.owner = owner;
    }

    // getters
    public int getPlayer() {
        return this.owner;
    }

    public int getSize(){
        return this.size;
    }

    public boolean isUsed() {
        return this.isUsed;
    }

    public int getXPos() {
        return this.xPos;
    }

    public int getYPos() {
        return this.yPos;
    }

    // setters
    public void setUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }

    public void setXPos(int row) {
        this.xPos = row;
    }

    public void setYPos(int col) {
        this.yPos = col;
    }
}
