package ca.sfu.bub.model;

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
