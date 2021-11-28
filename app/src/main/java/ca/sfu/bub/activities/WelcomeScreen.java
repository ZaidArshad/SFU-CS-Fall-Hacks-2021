package ca.sfu.bub.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AnimationUtils;

import ca.sfu.bub.R;

public class WelcomeScreen extends AppCompatActivity {

    private Boolean screenEnded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        if (getSupportActionBar() != null) getSupportActionBar().hide();

        fadeIn(findViewById(R.id.tvAppTitle));
        fadeIn(findViewById(R.id.tvNames));
        Handler loadingHandler = new Handler();
        loadingHandler.postDelayed(this::startImageSelection, 4000);

    }

    private void startImageSelection() {
        if (!screenEnded) {
            screenEnded = true;
            startActivity(ImageActivity.makeIntent(this));
            finish();
        }
    }

    private void fadeIn(View view) {
        view.setAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in));
        view.animate();
    }
}