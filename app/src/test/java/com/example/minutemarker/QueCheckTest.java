package com.example.minutemarker;

import org.junit.Test;

import static org.junit.Assert.*;

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
}