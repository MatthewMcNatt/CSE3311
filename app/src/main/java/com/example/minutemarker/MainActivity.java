package com.example.minutemarker;
/*
Last Modified Matthew McNatt: 2/17
*/
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    //text array and the arraylist adapter
    //The adapter pipes the strings into the listview format
    static ArrayList<String> notes = new ArrayList<>();
    static ArrayAdapter arrayAdapter;

    @Override
    //This is component instantiates the menu
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    //This is the event handler for the menu, my understanding is that new
    //items can be added by adding a new entry into menu xml file( located res/menu)
    // and adding the corresponding lines in nCreateOptionsMenu and here
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId()==R.id.add_note){
            Intent intent = new Intent(getApplicationContext(), EditNoteActivity.class);

            startActivity(intent);
            return true;
        }
        if(item.getItemId()==R.id.settings){
            Intent intent = new Intent(getApplicationContext(), SettingActivity.class);

            startActivity(intent);
            return true;
        }
        return false;
    }

    @Override
    //Code called at startup of app
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //The list of notes
        ListView listview = (ListView) findViewById(R.id.listView);

        //IMPORTANT: this is the code that loads notes FROM shared preferences
        //it fills set, and if set is not null, it adds a single example note.
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.minutemarker;", Context.MODE_PRIVATE);
        HashSet<String> set = (HashSet<String>) sharedPreferences.getStringSet("notes", null);
        if(set == null || set.isEmpty()){
            notes.add("DAY\n");
            notes.add("MONTH\n");
        }else{
            notes= new ArrayList(set);
        }


        //This creates and links the adapter to the notes string data
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, notes );

        //hooks the adapter up to listview
        listview.setAdapter(arrayAdapter);


        //Navigation: on note being clicked. Sends noteId to note edit screen
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //screen to go to
                Intent intent = new Intent(getApplicationContext(), EditNoteActivity.class);
                //send noteId
                intent.putExtra("noteId", i);
                startActivity(intent);
            }
        });

        //This is an event handler, on holding down click on a message, it pops a dialogue to delete it
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                //needed or app will crash
                int itemToDelete = i;

                //evil android.dialoguebuilder syntax
                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Are you sure?")
                        .setMessage("Delete this message?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //THIS IS THE LOGIC THAT DELETES FROM NOTES LOCAL INSTANCE
                                notes.remove(itemToDelete);
                                arrayAdapter.notifyDataSetChanged();

                                //THIS IS THE LOGIC THAT SAVES IT. see on text change in EditNoteActivity for explanation
                                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.minutemarker;", Context.MODE_PRIVATE);
                                HashSet<String> set = new HashSet(MainActivity.notes);
                                sharedPreferences.edit().putStringSet("notes", set).apply();
                                //end of deleting logic
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();

                return true;
            }
        });

    }
}