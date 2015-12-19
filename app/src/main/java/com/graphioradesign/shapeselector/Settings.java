package com.graphioradesign.shapeselector;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.ads.InterstitialAd;


public class Settings extends ActionBarActivity {
    static InterstitialAd interstitial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        // Create the interstitial.
        /*
        interstitial = new InterstitialAd(this);
        interstitial.setAdUnitId("ca-app-pub-1245676020650503/6477508066");

        // Create ad request.
        AdRequest adRequest = new AdRequest.Builder().build();

        // Begin loading your interstitial.
        interstitial.loadAd(adRequest);
        */
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, Settings.class);
            startActivity(intent);
            return true;
        }else if (id == R.id.action_mainMenu){
            Intent intent = new Intent(this, MainScreen.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static void startAd(View v){
        /*if (interstitial.isLoaded()) {
            interstitial.show();
        }
        // Begin listening to interstitial & show ads.
        interstitial.setAdListener(new AdListener(){
            public void onAdLoaded(){
                interstitial.show();
            }
        }); */
    }
}
