/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph<String>();
    }
    
    /*
     * Testing ConcreteVerticesGraph...
     */

    // Testing strategy for ConcreteVerticesGraph.toString()
    //   TODO
    
    // TODO tests for ConcreteVerticesGraph.toString()
    @Test
    public void testToString(){
        Graph<String> a = emptyInstance();
        assertEquals(0,a.set("First","Second",9));
        assertEquals(0,a.set("First","Third",8));
        assertEquals(0,a.set("First","Fourth",7));
        String goal = "Vertex:First sources:{} target:{Second=9, Third=8, Fourth=7}\n"
                +"Vertex:Second sources:{First=9} target:{}\n"
                +"Vertex:Third sources:{First=8} target:{}\n"
                +"Vertex:Fourth sources:{First=7} target:{}\n";
        assertEquals(goal,a.toString());
    }
    /*
     * Testing Vertex...
     */

    // Testing strategy for Vertex
    //   TODO
    @Test
    public void testgetName(){
        Vertex<String> vertex = new Vertex<String>("a");
        assertEquals("a", vertex.getName());
    }

    @Test
    public void testgetsources() {
        Vertex<String> vertex = new Vertex<String>("a");
        vertex.addSource("b", 5);
        vertex.addSource("c", 3);
        Map<String,Integer> map=new HashMap<String,Integer>();
        map.put("b",5);
        map.put("c",3);
        assertEquals(map, vertex.getSources());
    }

    @Test
    public void testgettargets() {
        Vertex<String> vertex = new Vertex<String>("a");
        vertex.addTarget("b", 5);
        vertex.addTarget("c", 3);
        Map<String,Integer> map=new HashMap<String,Integer>();
        map.put("b",5);
        map.put("c",3);
        assertEquals(map, vertex.getTargets());
    }

    @Test
    public void testAddsource() {
        Vertex<String> vertex = new Vertex<String>("a");
        assertEquals(0, vertex.addSource("b", 5));
        assertEquals(0, vertex.addSource("c", 3));
        assertEquals(5, vertex.addSource("b", 4));
        assertEquals(4, vertex.addSource("b", 0));
//        assertEquals(-1, vertex.addSource("b", -1));
    }

    @Test
    public void testAddtarget() {
        Vertex<String> vertex = new Vertex<String>("a");
        assertEquals(0, vertex.addTarget("b", 5));
        assertEquals(0, vertex.addTarget("c", 3));
        assertEquals(5, vertex.addTarget("b", 4));
        assertEquals(4, vertex.addTarget("b", 0));
//        assertEquals(-1, vertex.addTarget("b", -1));
    }

    @Test
    public void testRemovesource() {
        Vertex<String> vertex = new Vertex<String>("a");
        vertex.addSource("b", 5);
        vertex.addSource("c", 3);
        assertEquals(0, vertex.removesource("d"));
        assertEquals(5, vertex.removesource("b"));
    }

    @Test
    public void testRemovetarget() {
        Vertex<String> vertex = new Vertex<String>("a");
        vertex.addTarget("b", 5);
        vertex.addTarget("c", 3);
        assertEquals(0, vertex.removetarget("d"));
        assertEquals(5, vertex.removetarget("b"));
    }
    // TODO tests for operations of Vertex
    
}
