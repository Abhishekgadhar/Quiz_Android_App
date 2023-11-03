package com.learnoset.onlinequizappadmob;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        final AppCompatButton startQuizBtn = findViewById(R.id.startQuizBtn);
        final AppCompatButton quitBtn = findViewById(R.id.quitBtn);
        final AdView adView = findViewById(R.id.adView);

        // add below code in your launcher activity
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
            }
        });

        // make request for ad
        final AdRequest adRequest = new AdRequest.Builder().build();

        // load requested ad into AdView
        adView.loadAd(adRequest);

        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Toast.makeText(StartActivity.this, "Loaded", Toast.LENGTH_SHORT).show();
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError adError) {
                Toast.makeText(StartActivity.this, "a"+adError.getCode(), Toast.LENGTH_SHORT).show();
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
                // i dont want anything to happen so i have left this function blank
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
                // i dont want anything to happen so i have left this function blank
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.

            }
        });

        startQuizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // on clicking the start button i will bw moving to the mainActivity page that consists of the questions
                startActivity(new Intent(StartActivity.this, MainActivity.class));
            }
        });

        quitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // close StartActivity finish() in built in funtion from import
                finish();
            }
        });
    }
}