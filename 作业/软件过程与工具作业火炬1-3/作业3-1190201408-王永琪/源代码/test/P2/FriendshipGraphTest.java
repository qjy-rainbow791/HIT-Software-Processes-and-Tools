package P2;

import org.junit.Test;

import static org.junit.Assert.*;

public class FriendshipGraphTest {

    @Test
    public void addVertex() {
        FriendshipGraph graph = new FriendshipGraph();
        Person a = new Person("zhansan");
        Person b = new Person("lisi");
        graph.addVertex(a);
        assertTrue(graph.getGraph().vertices().contains(a));
        assertFalse(graph.getGraph().vertices().contains(b));
    }

    @Test
    public void addEdge() {
        FriendshipGraph graph = new FriendshipGraph();
        Person a = new Person("zhangsan");
        Person b = new Person("lisi");
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addEdge(a,b);
        graph.addEdge(b,a);
        assertTrue(graph.getGraph().targets(a).containsKey(b));
        assertTrue(graph.getGraph().sources(b).containsKey(a));
    }

    @Test
    public void getDistance() {
        FriendshipGraph graph = new FriendshipGraph();
        Person a = new Person("zhangsan");
        Person b = new Person("lisi");
        Person c = new Person("wangwu");
        Person d = new Person("zhaoliu");
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addVertex(c);
        graph.addVertex(d);
        graph.addEdge(a,b);
        graph.addEdge(b,c);
        graph.addEdge(c,a);
        assertEquals(0,graph.getDistance(a,a));
        assertEquals(0,graph.getDistance(b,b));
        assertEquals(0,graph.getDistance(c,c));
        assertEquals(1,graph.getDistance(a,b));
        assertEquals(1,graph.getDistance(b,c));
        assertEquals(1,graph.getDistance(c,a));
        assertEquals(2,graph.getDistance(a,c));
        assertEquals(2,graph.getDistance(b,a));
        assertEquals(2,graph.getDistance(c,b));
        assertEquals(-1,graph.getDistance(a,d));
        assertEquals(-1,graph.getDistance(b,d));
        assertEquals(-1,graph.getDistance(c,d));
    }
}