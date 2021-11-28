package ca.sfu.bub.model;

public class Piece {

    private final int size;
    private final int owner;
    private boolean isUsed = false;

    public Piece(int size, int owner) {
        this.size = size;
        this.owner = owner;
    }

    public int getPlayer() {
        return this.owner;
    }

    public int getSize(){
        return this.size;
    }

    public boolean isUsed() {
        return this.isUsed;
    }

    public void setUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }
}
