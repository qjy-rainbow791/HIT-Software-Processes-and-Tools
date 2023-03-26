/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import org.junit.Test;


import static org.junit.Assert.*;

/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph<String>();
    }

    /*
     * Testing ConcreteEdgesGraph...
     */
    // Testing strategy for ConcreteEdgesGraph.toString()
    @Test
    public void testToString(){
        Graph<String> a = emptyInstance();
        assertEquals(0,a.set("First","Second",9));
        String goal = "Edge:First->Second:Weight = 9\n";
        assertEquals(goal,a.toString());
        assertEquals(0,a.set("First","Third",8));
        goal = "Edge:First->Second:Weight = 9\n"
                +"Edge:First->Third:Weight = 8\n";
        assertEquals(goal,a.toString());
        assertEquals(0,a.set("First","Fourth",7));
        goal = "Edge:First->Second:Weight = 9\n"
                +"Edge:First->Third:Weight = 8\n"
                +"Edge:First->Fourth:Weight = 7\n";
        assertEquals(goal,a.toString());
    }
    /*
     * Testing Edge...
     */

    @Test
    public void testEdge(){
        Edge<String> a = new Edge<>("First","Second",5);
        assertEquals("First",a.getSource());
        assertEquals("Second",a.getTarget());
        assertEquals(5,a.getWeight());
        assertEquals("Edge:First->Second:Weight = 5",a.toString());
    };
    // Testing strategy for Edge
    //
    
}
