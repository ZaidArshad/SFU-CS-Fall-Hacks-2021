package ca.sfu.bub.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import ca.sfu.bub.R;

public class GameScreen extends AppCompatActivity {
    private ImageView ivPositions[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        if (getSupportActionBar() != null) getSupportActionBar().hide();
        populatePositions();
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

//        for (ImageView imageView: ivPositions) {
//            imageView.setBackground(AppCompatResources.getDrawable(this, R.drawable.player2_circle));
//        }

    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, GameScreen.class);
    }

}