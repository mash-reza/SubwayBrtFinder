package com.example.subwayfinder.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.subwayfinder.R;
import com.example.subwayfinder.Util.Utilies;
import com.example.subwayfinder.database.Brt;
import com.example.subwayfinder.database.Metro;
import com.jsibbold.zoomage.ZoomageView;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Menu extends AppCompatActivity {
    private static final String TAG = "Menu";
    private static final int LOCATION_REQEST_CODE = 10;
    private static final int WIFI_REQEST_CODE = 20;
    private static final int INTERNET_REQEST_CODE = 30;
    private double latitude;
    private double longitude;
    private double stationLatitude;
    private double stationLongitude;
    //    private boolean isLocationOn = false;
    private boolean isLatLonLoaded = false;
    private LocationManager locationManager;
    private FusedLocationProviderClient locationProviderClient;

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

    enum StationType {
        METRO, BUS
    }

    private int line = -1;
    private boolean isSpinnerItemSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);

                }
            }, Looper.getMainLooper());
        }
    }

    private void checkPermission() {
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQEST_CODE);
        }
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_WIFI_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_WIFI_STATE}, WIFI_REQEST_CODE);
        }
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_WIFI_STATE}, INTERNET_REQEST_CODE);
        }
    }

    public void onClick(View view) throws IOException {
        switch (view.getId()) {
            case R.id.menuBrtImageButton:
                showStationDialog(StationType.BUS);
                break;
            case R.id.menuBrtMapImageButton:
                menuMapZoomageView.setImageResource(R.drawable.brt_map);
                viewStateChanger();
                break;
            case R.id.menuMetroImageButton:
                showStationDialog(StationType.METRO);
                break;
            case R.id.menuMetroMapImageButton:
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

    private void showStationDialog(StationType type) {
        line = -1;
        isSpinnerItemSelected = true;
        AlertDialog dialog = new AlertDialog.Builder(this).setCancelable(false).create();
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_station, null);
        dialog.setView(dialogView);
        Spinner spinner = dialogView.findViewById(R.id.dialogStationSpinner);
        Button cancel = dialogView.findViewById(R.id.dialogStationCancelButton);
        Button find = dialogView.findViewById(R.id.dialogStationFindButton);
        TextView stationName = dialogView.findViewById(R.id.dialogStationNameTextView);
        RadioGroup radioGroup = dialogView.findViewById(R.id.dialogRadioGroup);
        spinner.setOnTouchListener((v, event) -> true);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.dialogAllStationsRadioButton:
                    spinner.setOnTouchListener((v, event) -> true);
                    line = -1;
                    break;
                case R.id.dialogLineRadioButton:
                    spinner.setOnTouchListener((v, event) -> false);
                    if (type == StationType.BUS) {
                        if (spinner.getSelectedItemPosition() < 5)
                            line = spinner.getSelectedItemPosition() + 1;
                        else if ((spinner.getSelectedItemPosition() == 5))
                            line = spinner.getSelectedItemPosition() + 2;
                        else if ((spinner.getSelectedItemPosition() > 5))
                            line = spinner.getSelectedItemPosition() + 3;
                    } else line = spinner.getSelectedItemPosition() + 1;
                    break;
            }
        });
        switch (type) {
            case BUS:
                ArrayAdapter<CharSequence> brtAdapter = ArrayAdapter.createFromResource(this, R.array.brt_lines, android.R.layout.simple_spinner_item);
                brtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(brtAdapter);
                break;
            case METRO:
                ArrayAdapter<CharSequence> metroAdapter = ArrayAdapter.createFromResource(this, R.array.metro_lines, android.R.layout.simple_spinner_item);
                metroAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(metroAdapter);
                break;
        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (isSpinnerItemSelected) {
                    isSpinnerItemSelected = false;
                } else {
                    if (type == StationType.BUS) {
                        if (parent.getSelectedItemPosition() < 5)
                            line = parent.getSelectedItemPosition() + 1;
                        else if ((parent.getSelectedItemPosition() == 5))
                            line = parent.getSelectedItemPosition() + 2;
                        else if ((parent.getSelectedItemPosition() > 5))
                            line = parent.getSelectedItemPosition() + 3;
                    } else line = parent.getSelectedItemPosition() + 1;
                    Log.i(TAG, "onItemSelected: " + line);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        cancel.setOnClickListener(v -> dialog.cancel());
        find.setOnClickListener(v -> AsyncTask.execute(() -> {
            switch (type) {
                case METRO:
                    if (line == -1) {
                        Metro station = Utilies.getInstance(getApplicationContext()).getNearestMetroStation(35.7543188, 51.0620597);
                        stationName.setText(station.getName());
                        Log.i(TAG, station.toString());
                    } else {
                        Metro station = Utilies.getInstance(getApplicationContext()).getNearestMetroStation(35.7543188, 51.0620597, line);
                        stationName.setText(station.getName());
                        Log.i(TAG, station.toString());
                    }
                    break;
                case BUS:
                    if (line == -1) {
                        Brt station = Utilies.getInstance(getApplicationContext()).getNearestBrtStation(35.7543188, 51.0620597);
                        stationName.setText(station.getName());
                        Log.i(TAG, station.toString());
                    } else {
                        Brt station = Utilies.getInstance(getApplicationContext()).getNearestBrtStation(35.7543188, 51.0620597, line);
                        stationName.setText(station.getName());
                        Log.i(TAG, station.toString());
                    }
                    break;
            }
        }));
        stationName.setOnClickListener(v -> {
            if (isLatLonLoaded) {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + stationLatitude + "," + stationLongitude+"("+stationName.getText()+")");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        });
        dialog.show();
    }
}
