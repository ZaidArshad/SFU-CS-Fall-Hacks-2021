package ca.sfu.bub.model;

import android.content.Context;
import android.graphics.Bitmap;

public class GameManager {
    private static GameManager instance;
    private Player player1;
    private Player player2;
    private int currentTurn;
    public static final int PLAYER_1 = 1;
    public static final int PLAYER_2 = 2;
    public static final int NO_WINNER = 0;
    private Bitmap imageOne;
    private Bitmap imageTwo;
    private Bitmap imageThree;

    private Bitmap[] images;

    private GameManager(Context context) {
        this.player1 = new Player(PLAYER_1);
        this.player2 = new Player(PLAYER_2);
    }

    public static GameManager getInstance(Context context) {
        if (instance == null) {
            instance = new GameManager(context);
        }
        return instance;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    private int getCurrentTurn() {
        return currentTurn;
    }

    public Bitmap getImage(int num) {
        return images[num];
    }

    public void setImages(Bitmap[] images) {
        this.images = images;
    }

    //----------------------------------------

    public Bitmap getImageOne() {
        return imageOne;
    }

    public Bitmap getImageTwo() {
        return imageTwo;
    }

    public Bitmap getImageThree() {
        return imageThree;
    }
}
