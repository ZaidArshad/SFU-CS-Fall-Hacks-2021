package ca.sfu.bub.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import ca.sfu.bub.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpImgBtn();
    }

    private void setUpImgBtn() {
        Button imageBtn = findViewById(R.id.imageScreenBtn);
        imageBtn.setOnClickListener((v) -> {
            Intent intent = ImageActivity.makeIntent(this);
            startActivity(intent);
        });
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

}