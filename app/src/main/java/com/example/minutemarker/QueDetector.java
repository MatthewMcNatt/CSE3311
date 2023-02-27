package com.example.minutemarker;

/*
Last Modified Matthew McNatt: 2/27
*/

import java.util.ArrayList;

/*
* This is the actual detection mechanism.
* It is an aggregate type. It holds ALL of the
* ques the app is meant to recognize.
* It takes in strings, and returns Ques associated
* with those string triggers. Otherwise it returns null
* */
public class QueDetector {

    //fields
    private ArrayList<Que> _ques;
    private ArrayList<String> _delimeters;

    //DANGEROUS: SHOULD ONLY BE USED IN TEST CASES
    public ArrayList<Que> getQues() {
        return _ques;
    }

    //constructors
    public QueDetector(){
        _ques = new ArrayList<Que>();
    }

    public QueDetector( ArrayList<Que> ques){
        if(_ques == null) throw new IllegalArgumentException("No null ques");
        _ques = ques;
    }

    public QueDetector( ArrayList<Que> ques, ArrayList<String> delimiters){
        if(delimiters == null) throw new IllegalArgumentException("No null ques");
        if(ques == null) throw new IllegalArgumentException("No null ques");
        _ques = ques;
        _delimeters = delimiters;
    }

    //Methods
    public void AddQue(Que que){
        if(que == null) throw new IllegalArgumentException("No null ques");
        _ques.add(que);
    }

    public void AddDelimiter(String s){
        if(s.length() == 0) throw new IllegalArgumentException("No empty string delimiters");
        _delimeters.add(s);
    }

    public Que GetQue(int index){
        return _ques.get(index);
    }

    //DETECTOR METHOD
    /*FOR NOW THIS IS A HACK IMPLEMENTATION*/
    /*THIS WILL BE EXTREMELY EFFICIENT SOON*/
    //Takes in string, returns a que if the string
    //matches a trigger of one of the ques
    //WILL RETURN NULL IF NONE FOUND
    public Que CheckForTriggers(String input){

        for(Que q: _ques){
            for( String trigger : q.getTriggers()){
                if (input.contains(trigger)){
                    return q;
                }
            }
        }
        return null;
    }

    //DETECTOR METHOD
    /*FOR NOW THIS IS A HACK IMPLEMENTATION*/
    /*THIS WILL BE EXTREMELY EFFICIENT SOON*/
    //Takes in string, returns true if the string
    //is a registered delimiter
    public boolean CheckIfDelimiter(String input){
        for (String s : _delimeters)
            if(s.equalsIgnoreCase(input))
                return true;

        return false;
    }





}
