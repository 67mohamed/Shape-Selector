package com.graphioradesign.shapeselector;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.List;


public class GameOver extends ActionBarActivity {

    SharedPreferences prefs;
    int experienceScore;
    String message ;

    GameScreen gameScreen = new GameScreen();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_game_over);
        TextView scoreTextView = (TextView) this.findViewById(R.id.scoreTextView);
        TextView highScoreTextView = (TextView) this.findViewById(R.id.highScoreTextView);
        TextView gameOverMessage = (TextView) this.findViewById(R.id.scoreMessageView);
        TextView experienceTextView = (TextView) this.findViewById(R.id.xpTextView);
        gameOverMessage.setText(gameScreen.getGameOverText());
        //setting preferences
        prefs = getSharedPreferences("myPrefsKey", 0);
        getExperienceScore();
        Editor editor = prefs.edit();
        if(gameScreen.returnScore()>Integer.parseInt(prefs.getString("highScore", "0"))){
            editor.putString("highScore", gameScreen.returnScoreString());


        }
        editor.putInt("XP", experienceScore);
        editor.commit();
        String colorArray[] ={"#3498db","#1abc9c","#2ecc71","#9b59b6","#f1c40f","#e67e22","#e74c3c"};
        String colorSmallArray[] = {"#2980b9","#16a085","#27ae60","#8e44ad","#f39c12","#d35400","#c0392b"};
        int x = random(0,6);
        int color = Color.parseColor(colorArray[x]);
        int colorSmall = Color.parseColor(colorSmallArray[x]);
        RelativeLayout rel = (RelativeLayout) findViewById(R.id.GameOverLayout);
        rel.setBackgroundColor(color);
        Button but = (Button) findViewById(R.id.restartGame);
        but.setBackgroundColor(colorSmall);
        scoreTextView.setText("Your Score: " + gameScreen.returnScoreString());
        highScoreTextView.setText("High Score  "+ prefs.getString("highScore", "0"));
        experienceTextView.setText("XP                "+ Integer.toString(prefs.getInt("XP",0)));
        message = "I just played Minute Dash on The Shape Challenge and got a score of "+ gameScreen.returnScoreString()+". High Score: "
                +prefs.getString("highScore", "0")+". Experience Points: "+Integer.toString(prefs.getInt("XP",0))+". Can you beat that?";
        gameScreen.stopTimer();
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_over, menu);
        return true;
    }

    public int random(int min, int max){
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }

    public void onRestartClick(View v){
        gameScreen.updateScore(0);
        Intent intent = new Intent(this, MainScreen.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        gameScreen.updateScore(0);
    }



    public void onSMSClick(View v){
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:"));  // This ensures only SMS apps respond
        intent.putExtra("sms_body", message);
        startActivity(intent);

    }
    public void onFacebookClick(View v){

        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(Intent.createChooser(shareIntent, "Share Your Score"));

    }

    public void getExperienceScore(){
        prefs = getSharedPreferences("myPrefsKey", 0);

        experienceScore = prefs.getInt("XP",0)+gameScreen.returnScore();
    }



    public void onTwitterClick(View v){
        // Create intent using ACTION_VIEW and a normal Twitter url:
        String tweetUrl =
                "https://twitter.com/intent/tweet?text="+message;
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(tweetUrl));

// Narrow down to official Twitter app, if available:
        List<ResolveInfo> matches = getPackageManager().queryIntentActivities(intent, 0);
        for (ResolveInfo info : matches) {
            if (info.activityInfo.packageName.toLowerCase().startsWith("com.twitter")) {
                intent.setPackage(info.activityInfo.packageName);
            }
        }

        startActivity(intent);
    }

    /*
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
    */

}
