package com.example.subwayfinder.view;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.bumptech.glide.Glide;
import com.example.subwayfinder.R;
import com.jsibbold.zoomage.ZoomageView;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Menu extends AppCompatActivity {

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
//                Glide.with(this).load(Drawable.createFromStream(getAssets().open("brt_map.jpg"),"")).into(menuMapZoomageView);
                menuMapZoomageView.setImageDrawable(Drawable.createFromStream(getAssets().open("brt_map.jpg"),""));
                viewStateChanger();
                break;
            case R.id.menuMetroImageButton:

                break;
            case R.id.menuMetroMapImageButton:
//                Glide.with(this).load(Drawable.createFromStream(getAssets().open("subway_map.jpg"),"")).into(menuMapZoomageView);
                menuMapZoomageView.setImageDrawable(Drawable.createFromStream(getAssets().open("subway_map.jpg"),""));

                viewStateChanger();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        viewStateChanger();
    }

    private void viewStateChanger(){
        if(isMapImageShowing){
            //gone map and show buttons
            menuMapZoomageView.setVisibility(View.GONE);
            menuBrtImageButton.setVisibility(View.VISIBLE);
            menuBrtMapImageButton.setVisibility(View.VISIBLE);
            menuMetroImageButton.setVisibility(View.VISIBLE);
            menuMetroMapImageButton.setVisibility(View.VISIBLE);
            isMapImageShowing = false;
        }
        else {
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
