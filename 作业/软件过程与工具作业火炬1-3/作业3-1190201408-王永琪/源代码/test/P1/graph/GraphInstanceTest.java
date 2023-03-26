/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * Tests for instance methods of Graph.
 * 
 * <p>PS2 instructions: you MUST NOT add constructors, fields, or non-@Test
 * methods to this class, or change the spec of {@link #emptyInstance()}.
 * Your tests MUST only obtain Graph instances by calling emptyInstance().
 * Your tests MUST NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {
    
    // Testing strategy
    //   TODO
    
    /**
     * Overridden by implementation-specific test classes.
     * 
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testInitialVerticesEmpty() {
        // TODO you may use, change, or remove this test
        assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), emptyInstance().vertices());
    }
    
    // TODO other tests for instance methods of Graph

    @Test
    public void testAdd(){
        Graph<String> a = emptyInstance();
        String vertex1 = "First";
        String vertex2 = "Second";
        assertTrue(a.add(vertex1));
        assertFalse(a.add(vertex1));
        assertTrue(a.vertices().contains(vertex1));
        assertFalse(a.vertices().contains(vertex2));
        assertEquals(1,a.vertices().size());
        assertTrue(a.add(vertex2));
        assertTrue(a.vertices().contains(vertex2));
        assertEquals(2,a.vertices().size());
    }

    @Test
    public void testSet(){
        Graph<String> a = emptyInstance();
        assertEquals(0,a.set("First","Second",9));
        assertTrue(a.vertices().contains("First"));
        assertTrue(a.vertices().contains("Second"));
        assertFalse(a.vertices().contains("Third"));
        assertFalse(a.sources("Third").containsKey("First"));
        assertTrue(a.sources("Second").containsKey("First"));
        assertEquals(2,a.vertices().size());
        assertEquals(9,a.set("First","Second",12));
        assertTrue(a.vertices().contains("First"));
        assertTrue(a.vertices().contains("Second"));
        assertFalse(a.vertices().contains("Third"));
        assertFalse(a.sources("Third").containsKey("First"));
        assertTrue(a.sources("Second").containsKey("First"));
        assertEquals(2,a.vertices().size());
        assertEquals(0,a.set("First","Third",9));
        assertTrue(a.vertices().contains("First"));
        assertTrue(a.vertices().contains("Second"));
        assertTrue(a.vertices().contains("Third"));
        assertTrue(a.sources("Third").containsKey("First"));
        assertTrue(a.sources("Second").containsKey("First"));
        assertEquals(3,a.vertices().size());
        assertEquals(9,a.set("First","Third",0));
        assertTrue(a.vertices().contains("First"));
        assertTrue(a.vertices().contains("Second"));
        assertTrue(a.vertices().contains("Third"));
        assertFalse(a.sources("Third").containsKey("First"));
        assertTrue(a.sources("Second").containsKey("First"));
        assertEquals(3,a.vertices().size());
    }

    @Test
    public void testRemove(){
        Graph<String> a = emptyInstance();
        assertEquals(0,a.vertices().size());
        assertEquals(0,a.set("First","Second",9));
        assertTrue(a.sources("Second").containsKey("First"));
        String vertex1 = "First";
        String vertex2 = "Second";
        assertFalse(a.remove("Third"));
        assertTrue(a.vertices().contains(vertex1));
        assertEquals(2,a.vertices().size());
        assertTrue(a.remove(vertex2));
        assertFalse(a.sources("Second").containsKey("First"));
        assertTrue(a.vertices().contains(vertex1));
        assertEquals(1,a.vertices().size());
    }

    @Test
    public void testVertices(){
        Graph<String> a = emptyInstance();
        assertEquals(0,a.vertices().size());
        String vertex1 = "First";
        String vertex2 = "Second";
        a.add(vertex1);
        assertTrue(a.vertices().contains(vertex1));
        assertFalse(a.vertices().contains(vertex2));
        assertEquals(1,a.vertices().size());
        a.add(vertex2);
        assertTrue(a.vertices().contains(vertex2));
        assertEquals(2,a.vertices().size());

    }

    @Test
    public void testSources(){
        Graph<String> a = emptyInstance();
        assertEquals(0,a.set("First","Second",9));
        assertTrue(a.sources("Second").containsKey("First"));
        assertEquals(0,a.set("First","Third",8));
        assertEquals(0,a.set("First","Fourth",7));
        assertTrue(a.add("Fifth"));

        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();
        Map<String, Integer> map3 = new HashMap<>();

        map1.put("First",9);
        map2.put("First",8);
        map3.put("First",7);

        assertEquals(a.sources("Second"),map1);
        assertEquals(a.sources("Third"),map2);
        assertEquals(a.sources("Fourth"),map3);
        assertEquals(a.sources("Fifth"),Collections.emptyMap());

    }

    @Test
    public void testTargets(){
        Graph<String> a = emptyInstance();
        assertEquals(0,a.set("First","Second",9));
        assertTrue(a.targets("First").containsKey("Second"));
        assertEquals(0,a.set("First","Third",8));
        assertEquals(0,a.set("First","Fourth",7));
        assertTrue(a.add("Fifth"));

        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();
        Map<String, Integer> map3 = new HashMap<>();

        map1.put("Second",9);
        map1.put("Third",8);
        map1.put("Fourth",7);


        assertEquals(a.targets("First"),map1);
        assertEquals(a.targets("Second"),map2);
        assertEquals(a.targets("Third"),map3);
        assertEquals(a.targets("Fifth"),Collections.emptyMap());
    }

    
}
