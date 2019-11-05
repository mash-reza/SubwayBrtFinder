package com.example.subwayfinder.view;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.subwayfinder.R;
import com.jsibbold.zoomage.ZoomageView;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Menu extends AppCompatActivity {
    private static final String TAG = "Menu";

    @BindView(R.id.menuBrtImageButton)
    ImageButton menuBrtImageButton;
    @BindView(R.id.menuBrtMapImageButton)
    ImageButton menuBrtMapImageButton;
    @BindView(R.id.menuMetroImageButton)
    ImageButton menuMetroImageButton;
    @BindView(R.id.menuMetroMapImageButton)
    ImageButton menuMetroMapImageButton;
    @BindView(R.id.menuMapZoomageView)
    ZoomageView menuMapZoomageView;

    private boolean isMapImageShowing = false;
    private boolean isBackDoublePressed = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);
    }

    public void onClick(View view) throws IOException {
        switch (view.getId()) {
            case R.id.menuBrtImageButton:

                break;
            case R.id.menuBrtMapImageButton:
//                Glide.with(this).load(brtMapDrawable).into(menuMapZoomageView);
                menuMapZoomageView.setImageResource(R.drawable.brt_map);
                viewStateChanger();
                break;
            case R.id.menuMetroImageButton:

                break;
            case R.id.menuMetroMapImageButton:
//                Glide.with(this).load(metroMapDrawable).into(menuMapZoomageView);
                menuMapZoomageView.setImageResource(R.drawable.subway_map);
                viewStateChanger();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (isMapImageShowing) {
            //gone map and show buttons
            menuMapZoomageView.setVisibility(View.GONE);
            menuBrtImageButton.setVisibility(View.VISIBLE);
            menuBrtMapImageButton.setVisibility(View.VISIBLE);
            menuMetroImageButton.setVisibility(View.VISIBLE);
            menuMetroMapImageButton.setVisibility(View.VISIBLE);
            isMapImageShowing = false;
        } else {
            if (isBackDoublePressed) {
                finish();
            } else {
                isBackDoublePressed = true;
                Toast.makeText(this, R.string.back_pressed_message, Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(() -> isBackDoublePressed = false, 1500);
            }
        }
    }

    private void viewStateChanger() {
        if (isMapImageShowing) {
            //gone map and show buttons
            menuMapZoomageView.setVisibility(View.GONE);
            menuBrtImageButton.setVisibility(View.VISIBLE);
            menuBrtMapImageButton.setVisibility(View.VISIBLE);
            menuMetroImageButton.setVisibility(View.VISIBLE);
            menuMetroMapImageButton.setVisibility(View.VISIBLE);
            isMapImageShowing = false;
        } else {
            //gone buttons and show map
            menuBrtImageButton.setVisibility(View.GONE);
            menuBrtMapImageButton.setVisibility(View.GONE);
            menuMetroImageButton.setVisibility(View.GONE);
            menuMetroMapImageButton.setVisibility(View.GONE);
            menuMapZoomageView.setVisibility(View.VISIBLE);
            isMapImageShowing = true;
        }

    }
}
