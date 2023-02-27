package com.example.minutemarker;

/*
Last Modified Matthew McNatt: 2/27
*/

import java.util.ArrayList;
import java.util.Arrays;
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
        ArrayList<String> d = new ArrayList<String>();
        {
            d.add(" ");
            d.add(".");
            d.add("-");
            d.add("!");
            d.add("?");
            d.add("/");
        }
        QueDetector q = new QueDetector();
        {
            q.AddQue(new Que("alarm", new ArrayList<String>(
                    Arrays.asList(
                            "set", "at", "on", "alarm"
                    )
            )));
            q.AddQue(new Que("stopwatch", new ArrayList<String>(
                    Arrays.asList(
                            "for", "hour", "minute", "stopwatch"
                    )
            )));
        }

        return q;
    }

    public QueDetector testImplementationQues(){
        return null;
    }
}
