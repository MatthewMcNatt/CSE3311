package com.example.minutemarker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;

public class EditNoteActivity extends AppCompatActivity {

    //The note to work on
    int noteId;
    Que currentQue = null;
    String currentContext = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        //text editor UI binding(boilerPlate)
        EditText editText = (EditText) findViewById(R.id.editText);


        Intent intent = getIntent();

        //INITIALIZE DETECTOR
        QueDetectorInitializer init = new QueDetectorInitializer();
        QueDetector que_detector = init.loadImplementationQues();

        //this attempts to pull the note selected into the screen context.
        //The minus one is default value, in other words, if there is
        //no not associated with the id, the use case is creating a new note
        //and a new note is created.
        noteId = intent.getIntExtra("noteId", -1);

        if(noteId != -1 ){
            editText.setText(MainActivity.notes.get(noteId));
        }
        else{
            MainActivity.notes.add(" ");
            noteId = MainActivity.notes.size()-1;
            MainActivity.arrayAdapter.notifyDataSetChanged();
        }

        //Event Listeners
        //Two are blank but left intentionally
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Maybe needed later?
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //declare a holder que object
                 if(!currentContext.equalsIgnoreCase(String.valueOf(charSequence))) {
                    Que que = null;

                    //IMPORTANT: this is the heart of the note permanence PER APPLICATION INSTANCE.
                    //Every time text changes, the notes in main activity is updated
                    MainActivity.notes.set(noteId, String.valueOf(charSequence));
                    //this tells the link between UI displaying notes in MAIN MENU  that notes changed
                    MainActivity.arrayAdapter.notifyDataSetChanged();

                    //IMPORTANT: this is the heart of the note permanence ACROSS APPLICATION INSTANCE.
                    //this is basically a file link. This line opens connection
                    SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.minutemarker;", Context.MODE_PRIVATE);
                    //this line creates a hashset which shared prefereneces is capable of storing
                    HashSet<String> set = new HashSet(MainActivity.notes);
                    //This is what writes to the file
                    sharedPreferences.edit().putStringSet("notes", set).apply();


                    //run detection algorithm
                    que = que_detector.CheckForTriggers(String.valueOf(charSequence));
                    //if it returns something, flash dialog
                    currentContext = String.valueOf(charSequence);
                    if (que != null) {
                        currentQue = que;
                        que = null;


                        //String message = "que has been detected " + que.getTitle();
                        String trigger = que_detector.getTriggerWord(String.valueOf(charSequence));
                        System.out.println(trigger);

                        int start = charSequence.toString().indexOf(trigger);
                        int end = start + trigger.length();
                        SpannableString ss = new SpannableString(charSequence);

                        // run what happens when word clicked
                        ClickableSpan clickableSpan = new ClickableSpan() {
                            @Override
                            public void onClick(View view) {

                                Toast.makeText(EditNoteActivity.this, currentQue.getTitle(),
                                        Toast.LENGTH_LONG).show();
                                if(currentQue.getTitle().equalsIgnoreCase("alarm")){
                                    Intent intent = new Intent(getApplicationContext(), Alarm_Activity.class);
                                    startActivity(intent);
                                }

                            }

                            @Override
                            public void updateDrawState(TextPaint ds) {
                                super.updateDrawState(ds);
                                ds.setUnderlineText(true);
                            }
                        };

                        ss.setSpan(clickableSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        editText.setText(ss);
                        editText.setMovementMethod(LinkMovementMethod.getInstance());
                        editText.setSelection(editText.getText().length());
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // didn't use
            }

        });

    }
}