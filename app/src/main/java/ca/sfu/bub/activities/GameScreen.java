package ca.sfu.bub.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;
import ca.sfu.bub.R;
import ca.sfu.bub.model.Board;
import ca.sfu.bub.model.BoardSpot;
import ca.sfu.bub.model.GameManager;
import ca.sfu.bub.model.Piece;
import ca.sfu.bub.model.Player;

public class GameScreen extends AppCompatActivity {
    private ImageView[] playerOnePieces;
    private ImageView[] playerTwoPieces;
    private GameManager gameManager;
    private Bitmap imageOne;
    private Bitmap imageTwo;
    private Bitmap imageThree;
    private Piece chosenPiece;
    private Player playerOne;
    private Player playerTwo;
    private Board gameBoard;
    private boolean somethingPicked = false;
    public static final int PLAYER_1 = 1;
    public static final int PLAYER_2 = 2;
    public static final int NO_WINNER = 0;
    private TextView tvPlayerTurn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        if (getSupportActionBar() != null) getSupportActionBar().hide();

        gameManager = GameManager.getInstance(this);
        playerOne = gameManager.getPlayer1();
        playerTwo = gameManager.getPlayer2();
        tvPlayerTurn = findViewById(R.id.tvPlayerTurn);

        setBackground();
        initializeTurn();
        setUpImages();
        populatePositions();
        populatePlayerPieces();
        setOnClickPieces();
        setOnClickBoardSpots();
        checkIfAllPiecesUsed();
    }

    private boolean checkIfAllPiecesUsed() {
        Piece[] arrayOne = playerOne.getPieces();
        Piece[] arrayTwo = playerTwo.getPieces();
        for (int i = 0; i < arrayOne.length; i++) {
            if (!arrayOne[i].isUsed() || !arrayTwo[i].isUsed()) {
                return false;
            }
        }
        return true;
    }

    private void setBackground() {
        ConstraintLayout constraintLayout = findViewById(R.id.mainLayout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();
    }

    @SuppressLint("SetTextI18n")
    private void initializeTurn() {
        Random rand = new Random();
        int result = rand.nextInt(2);

        if (result == 0) {
            gameManager.setCurrentTurn(PLAYER_1);
            showNextTurn();
        }
        else {
            gameManager.setCurrentTurn(PLAYER_2);
            showNextTurn();
        }
    }

    private void setOnClickBoardSpots() {
        BoardSpot[][] spots = gameBoard.getBoardSpots();
        for (int col = 0; col < 3; col++) {
            for (int row = 0; row < 3; row++) {
                int finalCol = col;
                int finalRow = row;
                spots[col][row].getImage().setOnClickListener((v) -> {

                    if (chosenPiece == null) {
                        Toast.makeText(GameScreen.this, "Please choose a piece",Toast.LENGTH_SHORT).show();
                    }
                    else if (!chosenPiece.isUsed()) {
                        //if spot is currently occupied
                        if (spots[finalCol][finalRow].getOccupied()) {
                            //if current piece is smaller than chosen piece
                            if (spots[finalCol][finalRow].getCurrentPiece().getSize() < chosenPiece.getSize()) {
                                gameBoard.placePiece(finalCol, finalRow, chosenPiece, gameManager.getImage(chosenPiece.getSize()), gameManager.getCurrentTurn());
                                chosenPiece.setUsed(true);
                                gameManager.swapTurns();
                                showNextTurn();
                                if (gameBoard.checkWinner() == 1) {
                                    Toast.makeText(GameScreen.this, "Player 1 won!",Toast.LENGTH_SHORT).show();
                                    gameManager.setGameWinner(1);
                                    startActivity(EndActivity.makeIntent(this));
                                    finish();
                                }
                                else if (gameBoard.checkWinner() == 2) {
                                    Toast.makeText(GameScreen.this, "Player 2 won!",Toast.LENGTH_SHORT).show();
                                    gameManager.setGameWinner(2);
                                    startActivity(EndActivity.makeIntent(this));
                                    finish();
                                }
                                else if (gameBoard.checkWinner() == 0 && checkIfAllPiecesUsed()) {
                                    Toast.makeText(GameScreen.this, "It's a draw!",Toast.LENGTH_SHORT).show();
                                    gameManager.setGameWinner(0);
                                    startActivity(EndActivity.makeIntent(this));
                                    finish();
                                }
                            }
                            else {
                                Toast.makeText(GameScreen.this, "Cannot place piece here!",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            gameBoard.placePiece(finalCol, finalRow, chosenPiece, gameManager.getImage(chosenPiece.getSize()), gameManager.getCurrentTurn());
                            chosenPiece.setUsed(true);
                            gameManager.swapTurns();
                            showNextTurn();
                            if (gameBoard.checkWinner() == 1) {
                                Toast.makeText(GameScreen.this, "Player 1 won!",Toast.LENGTH_SHORT).show();
                                gameManager.setGameWinner(1);
                                startActivity(EndActivity.makeIntent(this));
                                finish();
                            }
                            else if (gameBoard.checkWinner() == 2) {
                                Toast.makeText(GameScreen.this, "Player 2 won!",Toast.LENGTH_SHORT).show();
                                gameManager.setGameWinner(2);
                                startActivity(EndActivity.makeIntent(this));
                                finish();
                            }
                            else if (gameBoard.checkWinner() == 0 && checkIfAllPiecesUsed()) {
                                Toast.makeText(GameScreen.this, "It's a draw!",Toast.LENGTH_SHORT).show();
                                gameManager.setGameWinner(0);
                                startActivity(EndActivity.makeIntent(this));
                                finish();
                            }
                        }
                    }
                    else {
                        Toast.makeText(GameScreen.this, "Choose another piece!",Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(GameScreen.this, ""+gameBoard.checkWinner(),Toast.LENGTH_SHORT).show();
                });
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private void showNextTurn() {
        if (gameManager.getCurrentTurn() == PLAYER_1) {
            tvPlayerTurn.setText("Player 1's Turn");
            appearFadeOut(tvPlayerTurn);
        }
        else {
            tvPlayerTurn.setText("Player 2's Turn");
            appearFadeOut(tvPlayerTurn);
        }
    }

    @SuppressLint("SetTextI18n")
    private void setOnClickPieces() {
        for (int i = 0; i < playerOnePieces.length; i++) {
            int finalI = i;
            playerOnePieces[i].setOnClickListener((v) -> {
                if (gameManager.getCurrentTurn() == PLAYER_1) {
                    if (!somethingPicked || chosenPiece.isUsed()) {
                        playerOnePieces[finalI].setVisibility(View.INVISIBLE);
                        chosenPiece = playerOne.getPiece(finalI);
                        somethingPicked = true;
                    }
                }
                else {
                    showNextTurn();
                }
            });
        }

        for (int i = 0; i < playerTwoPieces.length; i++) {
            int finalI = i;
            playerTwoPieces[i].setOnClickListener((v) -> {
                if (gameManager.getCurrentTurn() == PLAYER_2) {
                    if (!somethingPicked || chosenPiece.isUsed()) {
                        playerTwoPieces[finalI].setVisibility(View.INVISIBLE);
                        chosenPiece = playerTwo.getPiece(finalI);
                        somethingPicked = true;
                    }
                }
                else {
                    showNextTurn();
                }
            });
        }
    }

    private void setUpImages() {
        imageOne = gameManager.getImage(0);
        imageTwo = gameManager.getImage(1);
        imageThree = gameManager.getImage(2);
    }

    private void populatePlayerPieces() {
        playerOnePieces = new ImageView[] {
                findViewById(R.id.ivPlayer1_S_1),
                findViewById(R.id.ivPlayer1_S_2),
                findViewById(R.id.ivPlayer1_M_1),
                findViewById(R.id.ivPlayer1_M_2),
                findViewById(R.id.ivPlayer1_B_1),
                findViewById(R.id.ivPlayer1_B_2),
        };

        playerOnePieces[0].setImageBitmap(imageOne);
        playerOnePieces[1].setImageBitmap(imageOne);
        playerOnePieces[2].setImageBitmap(imageTwo);
        playerOnePieces[3].setImageBitmap(imageTwo);
        playerOnePieces[4].setImageBitmap(imageThree);
        playerOnePieces[5].setImageBitmap(imageThree);

        for (ImageView imageView : playerOnePieces) {
            imageView.setColorFilter(Color.argb(80, 255, 0, 0));
        }

        playerTwoPieces = new ImageView[] {
                findViewById(R.id.ivPlayer2_S_1),
                findViewById(R.id.ivPlayer2_S_2),
                findViewById(R.id.ivPlayer2_M_1),
                findViewById(R.id.ivPlayer2_M_2),
                findViewById(R.id.ivPlayer2_L_1),
                findViewById(R.id.ivPlayer2_L_2),
        };

        for (ImageView imageView : playerTwoPieces) {
            imageView.setColorFilter(Color.argb(80, 0, 0, 255));
        }

        playerTwoPieces[0].setImageBitmap(imageOne);
        playerTwoPieces[1].setImageBitmap(imageOne);
        playerTwoPieces[2].setImageBitmap(imageTwo);
        playerTwoPieces[3].setImageBitmap(imageTwo);
        playerTwoPieces[4].setImageBitmap(imageThree);
        playerTwoPieces[5].setImageBitmap(imageThree);
    }

    private void populatePositions() {
        ImageView[] ivPositions = new ImageView[]{
                findViewById(R.id.ivPos1),
                findViewById(R.id.ivPos2),
                findViewById(R.id.ivPos3),
                findViewById(R.id.ivPos4),
                findViewById(R.id.ivPos5),
                findViewById(R.id.ivPos6),
                findViewById(R.id.ivPos7),
                findViewById(R.id.ivPos8),
                findViewById(R.id.ivPos9),
        };

        gameBoard = new Board(ivPositions);

    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, GameScreen.class);
    }

    private void appearFadeOut(View view) {
        view.setVisibility(View.VISIBLE);
        Animation fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.setAnimation(fadeOut);
        view.animate();

    }

}