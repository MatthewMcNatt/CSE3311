package com.example.minutemarker;
/*
Last Modified Matthew McNatt: 2/27
*/

import java.util.ArrayList;

/*
* Que is an object to hold all the trigger words and
* phrases for an action as well as possibly a lambda expression
* that would represent the action. The latter will depend
* highly on implementation.
*/
public class Que {
    //used as identifier
    private String _title;
    private ArrayList<String> _triggers;

    //getters & setters


    public ArrayList<String> getTriggers() {
        return _triggers;
    }

    public String getTitle() {
        return _title;
    }

    public void setTitle(String title) {
        this._title = title;
    }

    //constructors

    //with array list
    public Que(String title, ArrayList<String> triggers){
        if(triggers == null){
            triggers = new ArrayList<String>();
        } else {_triggers = triggers;}

        _title = title;
    }
    //without arraylist
    public Que(String title){
        _triggers = new ArrayList<String>();
        _title = title;
    }

    public void setTriggers(ArrayList<String> t){
        _triggers = t;
    }





}
