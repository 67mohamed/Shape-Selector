package com.graphioradesign.shapeselector;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class MainScreen extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        AdView mAdView = (AdView) findViewById(R.id.adView);
        RelativeLayout rel = (RelativeLayout) findViewById(R.id.MainScreenLayout);
        Button but = (Button) findViewById(R.id.button3);
        Button but2 = (Button) findViewById(R.id.instructions);
        Button but3 = (Button) findViewById(R.id.Enter);
        String colorArray[] ={"#3498db","#1abc9c","#2ecc71","#9b59b6","#f1c40f","#e67e22","#e74c3c"};
        String colorSmallArray[] = {"#2980b9","#16a085","#27ae60","#8e44ad","#f39c12","#d35400","#c0392b"};
        int x = random(0,6);
        int color = Color.parseColor(colorArray[x]);
        int colorSmall = Color.parseColor(colorSmallArray[x]);
        but.setBackgroundColor(colorSmall);
        but2.setBackgroundColor(colorSmall);
        but3.setBackgroundColor(colorSmall);
        rel.setBackgroundColor(color);

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    public int random(int min, int max){
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }

    public void onInstructionsClick(View v){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("Instructions");
        builder1.setMessage("Survival mode is a race against the clock. The counter starts at 5, but every shape you get " +
                "increases the counter by 1. Beware, the counter starts to get faster and faster as you go. \n \nMinute Dash is a test of endurance. How many " +
                "shapes can you get in a minute?");
        builder1.setCancelable(true);
        builder1.setNeutralButton("Return",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });


        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    public void onEnterClick(View v) {
        Intent intent = new Intent(this, GameScreen.class);
        startActivity(intent);
    }

    public void onEnter2Click(View v) {
        Intent intent = new Intent(this, GameScreen2.class);
        startActivity(intent);
    }
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_screen, menu);
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
        }

        return super.onOptionsItemSelected(item);
    }
    */
}
