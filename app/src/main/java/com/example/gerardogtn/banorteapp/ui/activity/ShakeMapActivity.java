package com.example.gerardogtn.banorteapp.ui.activity;

import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gerardogtn.banorteapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;




public class ShakeMapActivity extends AppCompatActivity implements OnMapReadyCallback, SensorListener {

    private boolean isShakeActive = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shake_map);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.color_ab)));
        initializeSensor();
        initializeMap();
        initializeButton();
    }

    private void initializeSensor() {
        SensorManager sensorMgr = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorMgr.registerListener(this,
                SensorManager.SENSOR_ACCELEROMETER,
                SensorManager.SENSOR_DELAY_GAME);
    }

    private void initializeMap() {
        MapFragment mMapFragment = MapFragment.newInstance();
        FragmentTransaction fragmentTransaction =
                getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.shake_map_fragment, mMapFragment);
        fragmentTransaction.commit();
        mMapFragment.getMapAsync(this);
    }

    private void initializeButton() {
        this.findViewById(R.id.shake_map_go_hide_shake).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isShakeActive = false;
                ShakeMapActivity.this.findViewById(R.id.shake_map_go_shake_container).setVisibility(View.GONE);
            }
        });

        ((EditText)this.findViewById(R.id.shake_map_monto)).addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (s.length() == 0) ShakeMapActivity.this.findViewById(R.id.shake_map_go_shake_button).setVisibility(View.GONE);
                        else {
                            if (ShakeMapActivity.this.findViewById(R.id.shake_map_go_shake_button).getVisibility() == View.GONE) {
                                ShakeMapActivity.this.findViewById(R.id.shake_map_go_shake_button).setVisibility(View.VISIBLE);
                                ShakeMapActivity.this.findViewById(R.id.shake_map_go_shake_button).startAnimation(
                                        AnimationUtils.loadAnimation(ShakeMapActivity.this, android.R.anim.fade_in));
                            }
                            isShakeActive = true;

                        }
                    }

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                }
        );

    }

    @Override
    public void onMapReady(GoogleMap map) {

        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(19.284583, -99.13753), 18));


        map.addMarker(new MarkerOptions()
                .title("Costco")
                .snippet("Compra de pizza \n $95000 \n 12-09-2015")
                .position(new LatLng(19.2844266, -99.1384883)));


    }


    private static final int SHAKE_THRESHOLD = 600;
    long lastUpdate = 0;
    float last_x = 0, last_y = 0, last_z = 0, x = 0, y = 0, z = 0;

    public void onSensorChanged(int sensor, float[] values) {
        if (sensor == SensorManager.SENSOR_ACCELEROMETER) {
            long curTime = System.currentTimeMillis();
            // only allow one update every 100ms.
            if ((curTime - lastUpdate) > 100) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                x = values[SensorManager.DATA_X];
                y = values[SensorManager.DATA_Y];
                z = values[SensorManager.DATA_Z];

                float speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000;

                if (speed > SHAKE_THRESHOLD && isShakeActive) {
                    onShake();
                }
                last_x = x;
                last_y = y;
                last_z = z;
            }
        }
    }




    private void onShake(){
        isShakeActive = false;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Confirmación: \n $5000 Erick");
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ShakeMapActivity.this, "Transferencia realizada", Toast.LENGTH_SHORT).show();
            }
        });

        builder.create().show();

    }

    @Override
    public void onAccuracyChanged(int sensor, int accuracy) {

    }

}
