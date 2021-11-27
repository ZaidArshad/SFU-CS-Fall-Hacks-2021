package ca.sfu.bub.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;

import ca.sfu.bub.R;

public class WelcomeScreen extends AppCompatActivity {

    private Boolean screenEnded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        if (getSupportActionBar() != null) getSupportActionBar().hide();

        Handler loadingHandler = new Handler();
        loadingHandler.postDelayed(this::startMainMenu, 4000);

    }

    private void startMainMenu() {
        if (!screenEnded) {
            screenEnded = true;
            startActivity(MainActivity.makeIntent(this));
            finish();
        }
    }
}