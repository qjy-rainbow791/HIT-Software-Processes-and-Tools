/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest {


    // Testing strategy
    //   TODO
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }


    @Test
    public void testToString() throws IOException {
        final GraphPoet nimoy = new GraphPoet(new File("test/P1/poet/test.txt"));
        assertEquals("graph = \n" +
                "Edge:to->seek:Weight = 1\n" +
                "Edge:seek->out:Weight = 1\n" +
                "Edge:out->new:Weight = 1\n" +
                "Edge:new->life:Weight = 1\n" +
                "Edge:life->and:Weight = 1\n" +
                "Edge:and->new:Weight = 1\n" +
                "Edge:new->civilizations:Weight = 1\n" +
                "Edge:explore->strange:Weight = 1\n" +
                "Edge:strange->new:Weight = 1\n" +
                "Edge:new->worlds:Weight = 1\n" +
                "Edge:seek->to:Weight = 1\n" +
                "Edge:to->explore:Weight = 2\n" +
                "Edge:explore->new:Weight = 1\n" +
                "Edge:new->and:Weight = 1\n" +
                "Edge:and->exciting:Weight = 1\n" +
                "Edge:exciting->synergies!:Weight = 1\n", nimoy.toString());
    }

    // TODO tests
    @Test
    public void test() throws IOException {
        final GraphPoet nimoy = new GraphPoet(new File("test/P1/poet/test.txt"));
        final String input1 = "explore new worlds";
        final String input2 = "Seek to explore new and exciting synergies!";
        assertEquals("explore strange new worlds", nimoy.poem(input1));
        assertEquals("Seek to explore strange new life and exciting synergies!", nimoy.poem(input2));
    }
}
