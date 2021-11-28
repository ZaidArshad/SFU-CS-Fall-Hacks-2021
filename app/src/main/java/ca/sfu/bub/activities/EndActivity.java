package ca.sfu.bub.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import ca.sfu.bub.R;
import ca.sfu.bub.model.GameManager;

public class EndActivity extends AppCompatActivity {
    public static final int PLAYER_1 = 1;
    public static final int PLAYER_2 = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        setPlayAgainButton();
        setWinner();
        playVictorySong();
    }

    private void playVictorySong() {
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.victory_song);
        mediaPlayer.start();
    }

    private void setPlayAgainButton() {
        Button btnPlayAgain = findViewById(R.id.btnPlayAgain);
        btnPlayAgain.setOnClickListener((v) -> {
            Intent intent = ImageActivity.makeIntent(this);
            startActivity(intent);
            finish();
        });
    }

    private void setWinner() {
        TextView tvWinner = findViewById(R.id.tvWinner);

        GameManager gameManager = GameManager.getInstance(this);
        int winner = gameManager.getGameWinner();
        String strWinner = "";
        if (winner == PLAYER_1) {
            tvWinner.setTextColor(getResources().getColor(R.color.player1));
            strWinner = "Player 1";
        }
        else if (winner == PLAYER_2) {
            tvWinner.setTextColor(getResources().getColor(R.color.player2));
            strWinner = "Player 2";
        }
        else {
            tvWinner.setTextColor(getResources().getColor(R.color.both_players));
            strWinner = "Draw";
        }

        tvWinner.setText(strWinner);
        GameManager.newInstance(this);
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, EndActivity.class);
    }
}