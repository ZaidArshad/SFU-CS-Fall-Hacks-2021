package ca.sfu.bub.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import ca.sfu.bub.R;
import ca.sfu.bub.model.Board;
import ca.sfu.bub.model.BoardSpot;
import ca.sfu.bub.model.GameManager;
import ca.sfu.bub.model.Piece;
import ca.sfu.bub.model.Player;

public class GameScreen extends AppCompatActivity {
    private ImageView ivPositions[];
    private ImageView playerOnePieces[];
    private ImageView playerTwoPieces[];
    private GameManager gameManager;
    private Bitmap imageOne;
    private Bitmap imageTwo;
    private Bitmap imageThree;
    private Piece chosenPiece;
    private Player playerOne;
    private Player playerTwo;
    private Board gameBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        if (getSupportActionBar() != null) getSupportActionBar().hide();

        gameManager = GameManager.getInstance(this);
        playerOne = gameManager.getPlayer1();
        playerTwo = gameManager.getPlayer2();

        setUpImages();
        populatePositions();
        populatePlayerPieces();
        setOnClickPieces();
        setOnClickBoardSpots();
    }

    private void setOnClickBoardSpots() {
        BoardSpot[][] spots = gameBoard.getBoardSpots();
        for (int col = 0; col < 3; col++) {
            for (int row = 0; row < 3; row++) {
                int finalCol = col;
                int finalRow = row;
                spots[col][row].getImage().setOnClickListener((v) -> {
                    gameBoard.placePiece(finalCol, finalRow, chosenPiece);
                    spots[finalCol][finalRow].getImage().setImageBitmap(gameManager.getImage(chosenPiece.getSize()));
                });
            }
        }
    }

    private void setOnClickPieces() {
        for (int i = 0; i < playerOnePieces.length; i++) {
            int finalI = i;
            playerOnePieces[i].setOnClickListener((v) -> {
                playerOnePieces[finalI].setVisibility(View.INVISIBLE);
                chosenPiece = playerOne.getPiece(finalI);
            });
        }

        for (int i = 0; i < playerTwoPieces.length; i++) {
            int finalI = i;
            playerTwoPieces[i].setOnClickListener((v) -> {
                playerTwoPieces[finalI].setVisibility(View.INVISIBLE);
                chosenPiece = playerTwo.getPiece(finalI);
            });
        }
    }

    private void setUpImages() {
        imageOne = gameManager.getImageOne();
        imageTwo = gameManager.getImageTwo();
        imageThree = gameManager.getImageThree();
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

        playerTwoPieces = new ImageView[] {
                findViewById(R.id.ivPlayer2_S_1),
                findViewById(R.id.ivPlayer2_S_2),
                findViewById(R.id.ivPlayer2_M_1),
                findViewById(R.id.ivPlayer2_M_2),
                findViewById(R.id.ivPlayer2_L_1),
                findViewById(R.id.ivPlayer2_L_2),
        };

        playerTwoPieces[0].setImageBitmap(imageOne);
        playerTwoPieces[1].setImageBitmap(imageOne);
        playerTwoPieces[2].setImageBitmap(imageTwo);
        playerTwoPieces[3].setImageBitmap(imageTwo);
        playerTwoPieces[4].setImageBitmap(imageThree);
        playerTwoPieces[5].setImageBitmap(imageThree);
    }

    private void populatePositions() {
        ivPositions = new ImageView[] {
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

        for (ImageView imageView: ivPositions) {
            imageView.setBackground(AppCompatResources.getDrawable(this, R.drawable.player2_circle));
        }

        gameBoard = new Board(ivPositions);

    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, GameScreen.class);
    }

}