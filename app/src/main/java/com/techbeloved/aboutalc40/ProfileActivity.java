package com.techbeloved.aboutalc40;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        ImageView profileImage = findViewById(R.id.imageView_profile_picture);

        Glide.with(this)
                .load(R.drawable.my_picture)
                .transform(new CircleCrop())
                .into(profileImage);

        setTitle("My Profile");
    }
}
