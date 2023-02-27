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
        Que try1 = q.CheckForTriggers("try1");
        assert(try1 == null);

        Que try2 = q.CheckForTriggers("alarm");
        assert(try2.getTitle().equals("alarm"));
        System.out.println( try2.getTitle()+ " detected");

        Que try3 = q.CheckForTriggers("for");
        assert(try3.getTitle().equals("stopwatch"));
        System.out.println( try3.getTitle()+ " detected");

    }
}