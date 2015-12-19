package com.graphioradesign.shapeselector;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;


public class GameScreen extends ActionBarActivity {
    String mainImage = "ic_launcher";
    static int score = 0;
    int countDownTimerView = 60;
    static CountDownTimer countDown;
    static String gameOverText = "Game Over";
    int one = 1;
    int two = 2;
    int three = 3;
    static int numberOfShapes = 4;
    static String[] imagesArray = {"tealhexagon","redstar","greentriangle","redcircle","greensquare", "bluerectangle", "pinkpentagon", "pinkoval", "yellowcircle"};
    //static Integer[] color = {51, 170, 248, 117, 239, 125, 182, 89, 153};


/*
    @Override
    protected void onStop(){
        super.onStop();
        gameOver();
    }
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game_screen);
        countDownTimerView = 60;
        drawImages();
        final TextView timer = (TextView) this.findViewById(R.id.Timer);
        timer.setText(Integer.toString(countDownTimerView));

         countDown = new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                if(millisUntilFinished%5==0){
                    if(millisUntilFinished>=5){
                        RelativeLayout rl = (RelativeLayout) findViewById(R.id.gameLayout);
                        rl.setBackgroundColor(Color.argb(random(100, 200), random(1, 255), random(1, 255), random(1, 255)));
                        countDownTimerView--;
                        timer.setText(Integer.toString(countDownTimerView));
                    }
                }else{
                    countDownTimerView--;
                    timer.setText(Integer.toString(countDownTimerView));
                }

            }

            public void onFinish() {
                gameOver();
                gameOverText = "Time's Up!";
            }
        }.start();



        //System.out.println(height+" "+width);

    }
    public static void updateScore(int i){
        score = i;
    }
    public void onBackPressed() {

    }
    public static void stopTimer(){
        countDown.cancel();
    }
    public static String getGameOverText(){
        return gameOverText;
    }

    public void updateScoreView(int i){
        TextView  scoreView  = (TextView)  this.findViewById(R.id.Score);
        scoreView.setText(Integer.toString(i));
        scoreView.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        return;
    }

    public void gameOver(){
        Intent intent = new Intent(this, GameOver.class);

        startActivity(intent);
    }

    public static int returnScore(){
        return score;
    }
    public static String returnScoreString() {return Integer.toString(score);}

    public void drawImages(){
        Collections.shuffle(Arrays.asList(imagesArray));
        setImage();
    }
    public void image1click(View v){
        if(one==0){
            score++;
            updateScoreView(score);
            drawImages();
        }else{
            gameOver();
        }


    }

    public  void image2click(View v){
        if(two==0){
            score++;
            updateScoreView(score);
            drawImages();
        }else{
            gameOver();
        }
    }

    public  void image3click(View v){
        if(three==0){
            score++;
            updateScoreView(score);
            drawImages();
        }else{
            gameOver();
        }
    }

    public String  setMainImage(){
        String mainImage = "ic_launcher";
        int selector = 1;
        switch(selector){
            case 1:
                mainImage = "redcircle";
                break;
            case 2:
                mainImage = "greensquare";
                break;
            default:
                mainImage = "ic_launcher";
        }
        return mainImage;
    }

    public String returnImage(int i){
        return imagesArray[i];
    }

    public int randomShape(){
        int min = 0;
        int max = numberOfShapes-1;
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }

    public int random(int min, int max){
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }

    public void setImage(/*int i*/){

        String image;
        int arrayIndex = 0;

        switch (random(1,3)){
            case 1:
                one = 0;
                two = 2;
                three = 3;
                System.out.println(1);
                break;
            case 2:
                two = 0;
                one = 1;
                three = 3;
                System.out.println(2);
                break;
            case 3:
                three = 0;
                one = 1;
                two =2;
                System.out.println(3);
                break;
            default:
                break;

        }
        ImageView mainImage = (ImageView) this.findViewById(R.id.mainImage);
        ImageView image1 = (ImageView) this.findViewById(R.id.image1);
        ImageView image2 = (ImageView) this.findViewById(R.id.image2);
        ImageView image3 = (ImageView) this.findViewById(R.id.image3);


        int resId = getResources().getIdentifier(returnImage(0), "drawable", getPackageName());
        mainImage.setBackgroundResource(resId);

        int resId2 = getResources().getIdentifier(returnImage(one), "drawable", getPackageName());
        image1.setBackgroundResource(resId2);

        int resId3 = getResources().getIdentifier(returnImage(two), "drawable", getPackageName());
        image2.setBackgroundResource(resId3);

        int resId4 = getResources().getIdentifier(returnImage(three), "drawable", getPackageName());
        image3.setBackgroundResource(resId4);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels-100;
        int width = (displaymetrics.widthPixels)-100;
        //mainImage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(height/3,height/3);
        //mainImage.setLayoutParams(layoutParams);
        /*
        RelativeLayout.LayoutParams smallShapes =  new RelativeLayout.LayoutParams(width/3,width/3);
        image1.setLayoutParams(smallShapes);
        image2.setLayoutParams(smallShapes);
        image3.setLayoutParams(smallShapes);
        */
        /*
        switch (i){
            case 0:
                arrayIndex = 0;
                break;
            case 1:
                arrayIndex = one;
                break;
            case 2:
                arrayIndex = two;
                break;
            case 3:
                arrayIndex = three;
                break;
            default:

        }

        return returnImage(arrayIndex);
        */
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_screen, menu);
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
    */
}
