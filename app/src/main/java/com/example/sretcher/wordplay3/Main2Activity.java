//*Created by sretcher
package com.example.sretcher.wordplay3;

//Import Statements

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


public class Main2Activity extends AppCompatActivity {

    //Fields

    private Anagram test1;
    private ListView listView;
    private ArrayAdapter<String> itemsAdapter;
    private EditText textfield;
    private Button confirm;

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Created an instance of Anagram class

        test1 = new Anagram();

        //Creates Listview and this is where the words from the Arraylist Combinations will go.
        //We use a ArrayAdapter in order to add the words from the ArrayList

        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(itemsAdapter);

        textfield = (EditText) findViewById(R.id.editText);

        //Button listener that calls setListViewData which will populate the ArrayList of words

        confirm = (Button) findViewById(R.id.button);
        confirm.setOnClickListener(
                new Button.OnClickListener() {

                    public void onClick(View v) {

                        setListViewData();

                    }


                }


        );

    }

    //Clears itemadapter and ArrayList Combinations so that we can check constantly for new
    //words. We then call StringPermutation which will repopulate the ListView with the chosen word
    //We add the words to the adapter, notify android about changes, and then reset the adapter

    public void setListViewData() {

        itemsAdapter.clear();
        test1.getCombinations().clear();
        test1.printStringPermutation(textfield.getText().toString());
        itemsAdapter.addAll(test1.getCombinations());
        itemsAdapter.notifyDataSetChanged();
        listView.setAdapter(itemsAdapter);

    }

    //Creates Menu
//..ll
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }

    //Menu-Intent goes between diffrent screens

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == R.id.TheGuessingGame) {

            Intent myIntent = new Intent(Main2Activity.this, Main3Activity.class);
            startActivity(myIntent);

        }


        return super.onOptionsItemSelected(item);
    }


}
