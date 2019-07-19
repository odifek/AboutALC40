package com.techbeloved.aboutalc40;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button aboutAlcButton = findViewById(R.id.button_about_alc);
        Button myProfileButton = findViewById(R.id.button_my_profile);

        // Set on click listeners
        aboutAlcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent alcIntent = new Intent(MainActivity.this, AboutActivity.class);
                alcIntent.putExtra(AboutActivity.EXTRA_URL, "https://andela.com/alc");
                startActivity(alcIntent);
            }
        });

        myProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profileIntent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(profileIntent);
            }
        });

        setTitle(R.string.alc_with_google);

    }
}
