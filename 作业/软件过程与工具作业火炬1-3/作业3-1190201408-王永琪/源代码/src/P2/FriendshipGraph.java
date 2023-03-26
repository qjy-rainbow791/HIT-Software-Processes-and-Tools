package P2;

import P1.graph.Graph;

import java.util.*;

public class FriendshipGraph {
    private final Graph<Person> graph = Graph.empty();

    public FriendshipGraph() {

    }

    public boolean addVertex(Person p)
    {
        return graph.add(p);
    }

    public int addEdge(Person p1,Person p2)
    {
        int lastweight;
        lastweight = graph.set(p1,p2,1);
        return lastweight;
    }

    public int getDistance(Person p1,Person p2)
    {
        if(p1==p2) {
            return 0;
        }
        Map<Person,Integer> theway=new HashMap<>();
        Queue<Person> myqueue=new LinkedList<>();
        myqueue.offer(p1);
        theway.put(p1,0);
        int distance;
        while(!myqueue.isEmpty()) {
            Person top=myqueue.poll();
            distance=theway.get(top);
            Map<Person, Integer> friend=graph.targets(top);
            Set<Person> allfriend=friend.keySet();
            for(Person m:allfriend) {
                if(!theway.containsKey(m)) {
                    theway.put(m,distance+1);
                    myqueue.offer(m);
                    if(m==p2) {
                        return theway.get(p2);
                    }
                }
            }
        }
        return -1;
    }

    public Graph<Person> getGraph(){
        return this.graph;
    }

    public static void main (String[] args)
    {
        FriendshipGraph graph = new FriendshipGraph();
        Person rachel = new Person("Rachel");
        Person ross = new Person("Ross");
        Person ben = new Person("Ben");
        Person kramer = new Person("Kramer");
        graph.addVertex(rachel);
        graph.addVertex(ross);
        graph.addVertex(ben);
        graph.addVertex(kramer);
        graph.addEdge(rachel, ross);
        graph.addEdge(ross, rachel);
        graph.addEdge(ross, ben);
        graph.addEdge(ben, ross);
        System.out.println(graph.getDistance(rachel, ross));
        //should print 1
        System.out.println(graph.getDistance(rachel, ben));
        //should print 2
        System.out.println(graph.getDistance(rachel, rachel));
        //should print 0
        System.out.println(graph.getDistance(rachel, kramer));
        //should print -1
    }

}
