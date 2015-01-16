package namir.dicegame;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    ImageView diceImage1;
    ImageView diceImage2;
    ImageView diceImage3;
    ImageView diceImage4;
    boolean firstImgPressed     = false;
    boolean secondImgPressed    = false;
    boolean thirdImgPressed     = false;
    boolean fourthImgPressed    = false;
    int     pressCounter        = 0;
    int     luckyLook           = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayInf();

        //access firstImage
        diceImage1 = (ImageView) findViewById(R.id.img_first);
        diceImage1.setOnClickListener(this);
        diceImage2 = (ImageView) findViewById(R.id.img_second);
        diceImage2.setOnClickListener(this);
        diceImage3 = (ImageView) findViewById(R.id.img_third);
        diceImage3.setOnClickListener(this);
        diceImage4 = (ImageView) findViewById(R.id.img_fourth);
        diceImage4.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        if(pressCounter < 3) {
            switch (v.getId()) {
                case R.id.img_first:
                    if(!firstImgPressed) {
                        luckyLook += changeImage(diceImage1);
                        pressCounter++;
                    }
                    firstImgPressed = true;
                    break;
                case R.id.img_second:
                    if(!secondImgPressed) {
                        luckyLook += changeImage(diceImage2);
                        pressCounter++;
                    }
                    secondImgPressed = true;
                    break;
                case R.id.img_third:
                    if(!thirdImgPressed) {
                        luckyLook += changeImage(diceImage3);
                        pressCounter++;
                    }
                    thirdImgPressed = true;
                    break;
                case R.id.img_fourth:
                    if(!fourthImgPressed) {
                        luckyLook += changeImage(diceImage4);
                        pressCounter++;
                    }
                    fourthImgPressed = true;
                    break;
            }
        }

        if(pressCounter == 2){
            displayResult();
        }
    }

    public void displayResult(){
        String message = null;
        if (luckyLook < 4 )
            message = "extremely shity";
        else if(luckyLook < 6)
            message = "poop";
        else if(luckyLook < 8)
            message = "not bad";
        else if(luckyLook < 10)
            message = "good";
        else if(luckyLook < 12)
            message = "bitch ur lucky";
        else if(luckyLook == 12)
            message = "damn that's awesome go grab a lottery ticket";

        AlertDialog.Builder end = new AlertDialog.Builder(this);
        end.setTitle("well");
        end.setMessage("your total is: " + luckyLook + "\n"
        + "your luck is: " + message );

        end.setPositiveButton("Restart",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                refreshApp();
            }
        });

        end.show();
    }

    public void refreshApp(){
        diceImage1.setImageDrawable(getResources().getDrawable(R.drawable.dice));
        diceImage2.setImageDrawable(getResources().getDrawable(R.drawable.dice));
        diceImage3.setImageDrawable(getResources().getDrawable(R.drawable.dice));
        diceImage4.setImageDrawable(getResources().getDrawable(R.drawable.dice));

        firstImgPressed     = false;
        secondImgPressed    = false;
        thirdImgPressed     = false;
        fourthImgPressed    = false;
        pressCounter        = 0;
        luckyLook           = 0;
    }

    public void displayInf(){
        AlertDialog.Builder info    = new AlertDialog.Builder(this);
        TextView dialogText         = new TextView(this);
        dialogText.setText("What a nice way to test your luck. The rules are simple, " +
                "you get to roll two dice, the bigger the total is the more lucky you are. "
                + "Enjoy");
        dialogText.setGravity(Gravity.CENTER_HORIZONTAL);
        dialogText.setTextSize(20);
        dialogText.setPadding(20, 10, 20, 10);
        info.setTitle("Hello")
        .setView(dialogText)
        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        info.show();
    }

    public int changeImage(ImageView img){
        //generate random number
        Random rand         = new Random();
        int randNum         = rand.nextInt((6 - 1) + 1) + 1;
        String newImgName   = null;
        int nb              = 0;

        switch (randNum){
            case 1:
                newImgName  = "first";
                nb          = 1;
                break;
            case 2:
                newImgName  = "second";
                nb          = 2;
                break;
            case 3:
                newImgName  = "third";
                nb          = 3;
                break;
            case 4:
                newImgName  = "fourth";
                nb          = 4;
                break;
            case 5:
                newImgName  = "fifth";
                nb          = 5;
                break;
            case 6:
                newImgName  = "sixth";
                nb          = 6;
                break;
        }

        img.setImageResource(getImageId(this, newImgName));
        return nb;
    }

    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }
}

