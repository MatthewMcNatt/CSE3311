package com.example.minutemarker;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

/**
 * Matthew McNatt: 2/27/2023
 * The Testing cases for the que word checker
 * The que word checker is merely the detection,
 * not the associated response
 */
public class QueCheckTest {
    @Test
    public void QueDetectorDetects() {
        QueDetectorInitializer init = new QueDetectorInitializer();
        QueDetector q = init.loadImplementationQues();

        //should be null
        Que test = q.CheckForTriggers("try1");
        assert(test == null);

        test = q.CheckForTriggers("alarm ");
        assert(test.getTitle().equals("alarm"));
        System.out.println( test.getTitle()+ " detected");

        test = q.CheckForTriggers(" for ");
        assert(test.getTitle().equals("stopwatch"));
        System.out.println( test.getTitle()+ " detected");

        test = q.CheckForTriggers(" FOR ");
        assert(test.getTitle().equals("stopwatch"));
        System.out.println( test.getTitle()+ " detected");

        test = q.CheckForTriggers("");
        assert(test==null);

        test = q.CheckForTriggers("a ");
        assert(test==null);

        test = q.CheckForTriggers("I will not fire ");
        assert(test==null);

        test = q.CheckForTriggers("The game is on");
        assert(test==null);

        test = q.CheckForTriggers("The game is oN ");
        assert(test.getTitle().equals("alarm"));
        System.out.println( test.getTitle()+ " detected");

        test = q.CheckForTriggers("The game is oN....");
        assert(test==null);

        test = q.CheckForTriggers("The game is!oN?");
        assert(test.getTitle().equals("alarm"));
        System.out.println( test.getTitle()+ " detected");
    }
    @Test
    public void TriggerAddTest(){
        QueDetectorInitializer init = new QueDetectorInitializer();
        QueDetector q = init.loadImplementationQues();
        ArrayList<String> silverware= new ArrayList<String>();
        silverware.add("spoon");
        silverware.add("fork");
        silverware.add("knife");
        silverware.add("plate");
        q.setQueTriggers("alarm", silverware);
        for(String s : q.GetQue("alarm").getTriggers())
            System.out.println(s);
        boolean iFailed = false;
        try{
            //this will throw exception because stopwatch has a for word
            silverware.add("for");
            q.setQueTriggers("alarm", silverware);
        }catch(Exception e){
            iFailed = true;
        }
        assert(iFailed==true);

    }
}