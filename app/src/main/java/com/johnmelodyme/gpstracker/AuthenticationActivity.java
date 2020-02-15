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
 * This application inspired by @Cindy, My Baby.
 */

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class AuthenticationActivity extends AppCompatActivity {
    private static final String TAG = "GPSTRACKER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
    }
}
