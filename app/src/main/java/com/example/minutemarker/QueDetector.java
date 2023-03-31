package com.example.minutemarker;

/*
Last Modified Matthew McNatt: 2/27
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

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
    private HashSet<String> _delimeters;
    private Map<String, Integer> _triggers;
    private int _triggerSizeMax;

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

    public QueDetector( ArrayList<Que> ques, HashSet<String> delimiters){
        if(delimiters == null) throw new IllegalArgumentException("No null ques");
        if(ques == null) throw new IllegalArgumentException("No null ques");
        _ques = ques;
        _delimeters = delimiters;
        _triggers = new LinkedHashMap<String, Integer>();

        //declare max
        int max = 0;

        //Constructs a hash map using the trigger word as keys,
        //and also identifies length of longest trigger
        for(int i = 0; i < _ques.size(); i++){
            ArrayList<String> queTriggerList= _ques.get(i).getTriggers();
            for(int j = 0; j < queTriggerList.size(); j++){
                _triggers.put(queTriggerList.get(j),i);
                if(queTriggerList.get(j).length() > max)
                    max = queTriggerList.get(j).length();
            }
        }
        _triggerSizeMax = max;

        /*uncomment to see what the data looks like under the hood
        System.out.println("MAX Trigger is :"+_triggerSizeMax);
        System.out.println("THE LOADED QUE TRIGGERS ARE:");
        for (String i : _triggers.keySet()) {
            System.out.println("KEY: " + i + "\nQue: " + _triggers.get(i)+ " which is " + _ques.get(_triggers.get(i)).getTitle());
        }
        */

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
        //will only check on delimiter. Wont detect a trigger until
        //user is finished typing word.
        //empty string catch
        if(input.length()<=1)
            return null;
        String lastChar = String.valueOf(input.charAt(input.length()-1));
        if(!_delimeters.contains(lastChar)) {
            return null;
        }

        //it was a delimiter so we grab the word
        //first get the start index: loop backwards until delimiter
        //start at second to last character
        int start = -1;
        for(int i = input.length()-2; i >= 0 && start == -1; i--){
            if(_delimeters.contains(String.valueOf(input.charAt(i)))){
                start = i+1;
            }
        }
        //if start equals -1 then there was no delimiters found, start at 0
        if(start == -1){
            start = 0;
        }

        //here is the word to search for
        String possibleTrigger = input.substring(start, input.length()-1).toLowerCase();

        //if it exist return it
        if(_triggers.get(possibleTrigger)!=null)
            return _ques.get(_triggers.get(possibleTrigger));

        return null;



    }

    public String getTriggerWord(String input){
        //will only check on delimiter. Wont detect a trigger until
        //user is finished typing word.
        //empty string catch
        if(input.length()<=1)
            return null;
        String lastChar = String.valueOf(input.charAt(input.length()-1));
        if(!_delimeters.contains(lastChar)) {
            return null;
        }

        //it was a delimiter so we grab the word
        //first get the start index: loop backwards until delimiter
        //start at second to last character
        int start = -1;
        for(int i = input.length()-2; i >= 0 && start == -1; i--){
            if(_delimeters.contains(String.valueOf(input.charAt(i)))){
                start = i+1;
            }
        }
        //if start equals -1 then there was no delimiters found, start at 0
        if(start == -1){
            start = 0;
        }

        //here is the word to search for
        return input.substring(start, input.length()-1);
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
