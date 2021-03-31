package com.kaunik.androiddevelopmentbooks;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class PDFOpener extends AppCompatActivity {

    PDFView myPDFViewer;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pdfopener);

        MobileAds.initialize(this,"ca-app-pub-5348647911544430~1560312967" );
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



        myPDFViewer = (PDFView) findViewById(R.id.pdfViewer);

        String getItem = getIntent().getStringExtra("pdfFileName");

        if (getItem.equals("Android Programming - Pushing the Limits")) {

            myPDFViewer.fromAsset("Android Programming - Pushing the Limits.pdf").load();

        }
        if (getItem.equals("Android Programming for Beginners")) {

            myPDFViewer.fromAsset("Android Programming for Beginners.pdf").load();

        }

        if (getItem.equals("HelloAndroid")) {

            myPDFViewer.fromAsset("HelloAndroid.pdf").load();

        }
        if (getItem.equals("Java A Beginner's Guide")) {

            myPDFViewer.fromAsset("Java A Beginner's Guide.pdf").load();

        }
        if (getItem.equals("Learning Java by Building Android Games")) {

            myPDFViewer.fromAsset("Learning Java by Building Android Games.pdf").load();

        }


        if (getItem.equals("android-dev")) {

            myPDFViewer.fromAsset("android-dev.pdf").load();

        }
        if (getItem.equals("Android-Programming-Cookbook")) {

            myPDFViewer.fromAsset("Android-Programming-Cookbook.pdf").load();

        }
        if (getItem.equals("Android_Project_Report_Final")) {

            myPDFViewer.fromAsset("Android_Project_Report_Final.pdf").load();

        }

        if (getItem.equals("JavaAndroidProgramming")) {

            myPDFViewer.fromAsset("JavaAndroidProgramming.pdf").load();

        }

        if (getItem.equals("MobileApplicationDevelopment")) {

            myPDFViewer.fromAsset("MobileApplicationDevelopment.pdf").load();

        }

    }
}
