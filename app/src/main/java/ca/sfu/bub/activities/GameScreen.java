package ca.sfu.bub.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.Objects;

import ca.sfu.bub.R;

public class GameScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        if (getSupportActionBar() != null) getSupportActionBar().hide();
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, GameScreen.class);
    }

}