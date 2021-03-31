package com.kaunik.androiddevelopmentbooks;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends AppCompatActivity {

    SearchView searchView;
    ListView pdfListView;
    ArrayAdapter<String> adapter;
    private InterstitialAd mInterstitialAd;



    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to Exit 'Android Development' ?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity.super.onBackPressed();

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        AlertDialog alertDialog=builder.create();
        alertDialog.show();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-5348647911544430/7656389357");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        searchView = (SearchView) findViewById(R.id.searchView);
        pdfListView = (ListView) findViewById(R.id.myPDFList);

        String[] pdfFiles = {"Android Programming - Pushing the Limits", "Android Programming for Beginners","android-dev",
                "Android-Programming-Cookbook","Android_Project_Report_Final",
                 "HelloAndroid","JavaAndroidProgramming",
                "Java A Beginner's Guide",
                "MobileApplicationDevelopment",

             };

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pdfFiles) {


            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView myText = (TextView) view.findViewById(android.R.id.text1);

                return view;
            }
        };

        pdfListView.setAdapter(adapter);
        pdfListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                else {
                    String item = pdfListView.getItemAtPosition(i).toString();
                    Intent start = new Intent(getApplicationContext(), PDFOpener.class);
                    start.putExtra("pdfFileName", item);
                    startActivity(start);
                }
                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        String item = pdfListView.getItemAtPosition(i).toString();
                        Intent start = new Intent(getApplicationContext(), PDFOpener.class);
                        start.putExtra("pdfFileName", item);
                        startActivity(start);
                        mInterstitialAd.loadAd(new AdRequest.Builder().build());
                    }

                });

            }
        });


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);

                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();

        if (id==R.id.about_us){
            Intent i=new Intent(MainActivity.this,About_Us.class);
            startActivity(i);
            return true;
        }
        if (id==R.id.rate_us){
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName());
            Intent i = new Intent(Intent.ACTION_VIEW, uri);
            try {
                startActivity(i);
            } catch (Exception e) {
                Toast.makeText(this, "Unable to open", Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        if(id==R.id.share)
        {
            Intent shareintent=new Intent();
            shareintent.setAction(Intent.ACTION_SEND);
            shareintent.putExtra(Intent.EXTRA_TEXT,"https://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName());
            shareintent.setType("text/plain");
            startActivity(Intent.createChooser(shareintent,"Share Via"));
            return true;
        }
        return true;
    }

}