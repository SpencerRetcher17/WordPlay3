//*Created by Spencer Retcher
package com.example.sretcher.wordplay3;

//Import Statements

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.R.attr.duration;


public class Main3Activity extends AppCompatActivity {

    //Fields

    private TextView textView; //Shows scrambled word
    private EditText editText; //Your answer
    private Button check;     //Checks your answer
    private String randomWord; //Original Word
    private String scrambledWord; //Scrambled Word
    private Anagram test3;  // Instance of Anagram
    private Button next;   //Resets random word
    private MediaPlayer win; //Win Sound
    private MediaPlayer lose; //Lose Sound
    private Snackbar mySnackBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initialize components by ids, make test3 read the file sent in

        test3 = new Anagram(this, "readwords.txt");
        textView = (TextView) findViewById(R.id.word);
        editText = (EditText) findViewById(R.id.input);

        //Generates random word-sets the textView to the scrambled word

        generateRandomWord();


        check = (Button) findViewById(R.id.check);
        next = (Button) findViewById(R.id.reset);

        //Listener that checks your answer and will play a sound whether you are correct
        //or incorrect. Also changes textView text to correct

        check.setOnClickListener(
                new Button.OnClickListener() {

                    public void onClick(View v) {

                        if (correctAnswer()) {

                            textView.setText("correct");
                            Victory();
                        } else
                            Loser();
                    }


                }


        );

        //Listener that resets the scrambled word
        //Snackbar will show the random word if the reset button is clicked


        next.setOnClickListener(
                new Button.OnClickListener() {

                    public void onClick(View v) {

                        //Creates Snackbar, Length Indefinite means the snackbar will stay forever unless touched by user
                        //Three parameters(view to anchor the snackbar, string, time duration of snackbar)
                        Snackbar bar = Snackbar.make(v, randomWord, Snackbar.LENGTH_INDEFINITE)
                                .setAction("Dismiss", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        //Snackbar by default closes on touch
                                    }
                                });

                        //Shows it

                        bar.show();


                        generateRandomWord();

                    }


                }


        );


    }

    //Generates Random Word while also keeping the Original Word. Sets the textView to scrambled word

    public void generateRandomWord() {


        randomWord = test3.getRandomWord().toLowerCase();
        Log.i("RandomWord", randomWord);
        scrambledWord = test3.scramble(randomWord).toLowerCase();
        Log.i("ScrambledWord", scrambledWord);

        textView.setText(scrambledWord);

    }

    //Checks if user input equals original word

    public boolean correctAnswer() {

        return editText.getText().toString().equals(randomWord.toString());

    }

    //Plays win sound

    public void Victory() {
        win = MediaPlayer.create(this, R.raw.tada);
        win.start();

    }

    //Plays loser sound, handler delays sound for 5 seconds

    public void Loser() {
        lose = MediaPlayer.create(this, R.raw.laughter);
        lose.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                lose.stop();
            }
        }, 5000);

    }

    //Create Menu

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //Hello
    //Menu that switches between screens

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.ListAnagram) {

            Intent myIntent = new Intent(Main3Activity.this, Main2Activity.class);
            startActivity(myIntent);

        }


        return super.onOptionsItemSelected(item);
    }
}

