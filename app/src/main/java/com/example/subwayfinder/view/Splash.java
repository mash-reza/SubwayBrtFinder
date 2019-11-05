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
import com.example.subwayfinder.database.Station;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Splash extends AppCompatActivity {
    private static final String TAG = "Splash";

    @BindView(R.id.splashBackgroundImageView)
    ImageView splashBackgroundImageView;

    List<Station> stations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        AsyncTask.execute(() -> {
//            stations.addAll(Database.getInstance(getApplicationContext()).dao().getStations());
//            for (Station station : stations) {
//                Log.i(TAG, "sd"+station.getId() + "\n" + station.getName() + "\n" + station.getLine() + "\n" + station.getLat() + "\n" + station.getLon());
//            }
//        });
//        AsyncTask.execute(() -> {
//        int id = Utilies.getInstance(getApplicationContext()).getNearestStation(35.6916123,51.4227613);
//        Log.i(TAG, "station id: id");
//
//            Log.i(TAG, Room.databaseBuilder(getApplicationContext(), Database.class, "subway").build().dao().getStation(id).toString());
//        });

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
