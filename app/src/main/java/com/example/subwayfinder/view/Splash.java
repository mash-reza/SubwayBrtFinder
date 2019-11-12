package com.example.subwayfinder.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.subwayfinder.R;
import com.example.subwayfinder.database.Brt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Splash extends AppCompatActivity {
    private static final String TAG = "Splash";

    @BindView(R.id.splashBackgroundImageView)
    ImageView splashBackgroundImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ButterKnife.bind(this);
        try {
            Glide.with(this).load(Drawable.createFromStream(getAssets().open("splash.png"),"")).into(splashBackgroundImageView);
        }catch (IOException e){
            Log.e(TAG,e.toString());
            e.printStackTrace();
        }

        new Handler().postDelayed(() -> {
            startActivity(new Intent(this, Menu.class));
            finish();
        }, 1000);
    }
}
