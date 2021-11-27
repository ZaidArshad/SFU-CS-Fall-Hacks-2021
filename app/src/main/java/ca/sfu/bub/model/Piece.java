package ca.sfu.bub.model;

public class Piece {
    // fields
    public int size;
    public int owner;
    public boolean isUsed;
    public int xPos;
    public int yPos;

    // constructor
    public Piece(int size, boolean isUsed, int row, int col, int owner) {
        this.size = size;
        this.isUsed = isUsed;
        this.xPos = row;
        this.yPos = col;
        this.owner = owner;
    }

    // getters
    public int getPlayer() {
        return this.owner;
    }

    public int getSize(){
        return this.size;
    }

    // setters
    public void setUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }

    public void setXPos(boolean isUsed) {
        this.isUsed = isUsed;
    }

    public void setYPos(boolean isUsed) {
        this.isUsed = isUsed;
    }
}
