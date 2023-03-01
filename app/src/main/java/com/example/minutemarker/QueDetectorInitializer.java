package com.example.minutemarker;

/*
Last Modified Matthew McNatt: 2/27
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/*
* THIS IS A HACK CLASS AS OF 2/27
* its only purpose is to load the ques and delimiters
* currently implemented by the app. It has two methods,
* one of which is for testing, other for implementation
* just in case its needed
* */
public class QueDetectorInitializer {
    public QueDetectorInitializer(){
        //do nothing
    }

    public QueDetector loadImplementationQues(){
        HashSet<String> d = new HashSet<String>();
        {
            d.add(" ");
            d.add(".");
            d.add("-");
            d.add("!");
            d.add("?");
            d.add("/");
        }

        ArrayList<Que> q = new ArrayList<Que>();
        q.add(new Que("alarm", new ArrayList<String>(
                Arrays.asList(
                        "set", "at", "on", "alarm"
                )
        )));
        q.add(new Que("stopwatch", new ArrayList<String>(
                Arrays.asList(
                        "for", "hour", "minute", "stopwatch"
                )
        )));

        return new QueDetector(q, d);


    }

    public QueDetector testImplementationQues(){
        return null;
    }
}
