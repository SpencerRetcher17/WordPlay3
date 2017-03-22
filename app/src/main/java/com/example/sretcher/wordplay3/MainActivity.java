//*Created by sretcher
//the app looks cool man
//It looks like everything is in working order
//I won a round
//i'll try and commit

package com.example.sretcher.wordplay3;

//Import Statements

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    //SplashScreen

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Thread myThread = new Thread() {

            public void run() {

                //Sleeps for 3 seconds, switches to the Guessing game

                try {

                    sleep(3000);
                    Intent myIntent = new Intent(MainActivity.this, Main3Activity.class);
                    startActivity(myIntent);
                    finish();
                }

                //Prints out errors

                catch (InterruptedException e) {

                    e.printStackTrace();


                }


            }

        };

        //Start thread

        myThread.start();


    }
}
