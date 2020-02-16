package com.johnmelodyme.gpstracker;
/**
 * @Copyright : Copyright © 2020 John Melody Melissa
 * ********************************************************
 * THIS APPLICATION IS MEANT FOR EDUCATIONAL PURPOSES ONLY
 * IS NOT INTENT TO SOMETHING SINISTER OR MALICIOUS, I WILL
 * NOT RESPONSIBLE IF MISUSE OF THIS TECHNOLOGY OR THE
 * ABUSIVE OF USAGE OF THIS PROGRAMME.
 * ********************************************************
 * @AUTHOR: John Melody Melissa
 * @REFERENCE: https://github.com/johnmelodyme/GPSTRACKER
 * This application inspired by @TanSinDee, My Baby.
 */

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import com.johnmelodyme.gpstracker.service.TrackingService;

public class TrackerActivity extends AppCompatActivity {
    private static final String TAG = "GPSTRACKER";
    private static final int PERMISSIONS_REQUEST = 100;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, TrackerActivity.TAG.toLowerCase() + TrackerActivity.class.getName() + "is Starting...");
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        // TODO Check whether GPS tracking is enabled
        if (locationManager != null &&
                !locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            finish();
        }

        // TODO Check whether this app has access to the location permission:
        int permission;
        permission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);

        // TODO If the location permission has been granted, then start the TrackingService:
        if (permission == PackageManager.PERMISSION_GRANTED) {
            startTrackingService();
        } else {
            Log.d(TAG, "onCreate: ");
        }
}

    // TODO TrackingService Invocation
    private void startTrackingService() {
        Intent Service;
        Service = new Intent(TrackerActivity.this, TrackingService.class);
        startService(Service);
    }
}