/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import java.util.*;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteVerticesGraph<L> implements Graph<L> {
    
    private final List<Vertex<L>> vertices = new ArrayList<>();
    
    // Abstraction function:
    //   use String to represent vertices
    //   use Vertex class to represent source vertices and target vertices and their weight

    // Representation invariant:
    //   the number of source edges must be the same with the target edges
    //   the weight must be positive
    //   The num of vertices and edges should :edges num <= vertices num*(vertices num -1)

    // Safety from rep exposure:
    //   make the rep be private and final
    //   return defensive copies of mutable fields
    
    // constructor
    public ConcreteVerticesGraph() {

    }

    // TODO checkRep
    public void checkRep() {
        for (Vertex<L> vertex : vertices) {
            assert (vertex.getName() != null);
            Map<L, Integer> sources = vertex.getSources();
            for (Map.Entry<L, Integer> entry : sources.entrySet()) {
                assert (entry.getKey() != null);
                assert (entry.getValue() > 0);
            }
            Map<L , Integer> targets = vertex.getTargets();
            for (Map.Entry<L, Integer> entry : targets.entrySet()) {
                assert (entry.getKey() != null);
                assert (entry.getValue() > 0);
            }
        }
        int Edgenum = 0;
        for (Vertex<L> vertex : vertices) {
            Map<L,Integer> sources = vertex.getSources();
            Edgenum +=sources.size();
        }
        int Vertexnum = vertices.size();
        assert (Edgenum<=Vertexnum*(Vertexnum-1));
    }

    @Override public boolean add(L vertex) {
        for(Vertex<L> v : vertices) {
            if(vertex.equals(v.getName()))
                return false;
        }
        Vertex<L> newVertex = new Vertex<L>(vertex);
        vertices.add(newVertex);
        checkRep();
        return true;
    }
    
    @Override public int set(L source, L target, int weight) {
        int box=0;
        this.add(source);
        this.add(target);
        for(Vertex<L> l:vertices) {
            if(l.getName().equals(source)) {
                box=l.addTarget(target,weight);
            }
            if(l.getName().equals(target)) {
                box=l.addSource(source,weight);
            }
        }
        checkRep();
        return box;
    }
    
    @Override public boolean remove(L vertex) {
        Iterator<Vertex<L>> it=vertices.iterator();
        while(it.hasNext()) {
            Vertex<L> c=it.next();
            if(c.getName().equals(vertex)) {
                it.remove();
                checkRep();
                return true;
            }
            else {
                if (c.getSources().containsKey(vertex)) {
                    c.removesource(vertex);
                }
                if (c.getTargets().containsKey(vertex)) {
                    c.removetarget(vertex);
                }
            }
        }
        checkRep();
        return false;
    }
    
    @Override public Set<L> vertices() {
        try {
            Set<L> a = new HashSet<>();
            L box = null;
            for(Vertex<L> d:vertices)
            {
                box=d.getName();
                a.add(box);
            }
            return a;
        } catch (Exception e){
            throw new RuntimeException("not implemented");
        }
    }
    
    @Override public Map<L, Integer> sources(L target) {
        Map<L,Integer> Box=new HashMap<>();
        for(Vertex<L> f:vertices) {
            if(f.getName().equals(target)) {
                Box=f.getSources();
                break;
            }
        }
        Map<L,Integer> tmp=new HashMap<>(Box);
        checkRep();
        return tmp;
    }
    
    @Override public Map<L, Integer> targets(L source) {
        Map<L,Integer> box=new HashMap<>();
        for(Vertex<L> ff:vertices) {
            if(ff.getName().equals(source)) {
                box=ff.getTargets();
                break;
            }
        }
        Map<L,Integer> tmp=new HashMap<>(box);
        checkRep();
        return tmp;
    }
    
    // TODO toString()
    @Override
    public String toString(){
        if(vertices.isEmpty()){
            return "This is an empty Graph";
        }else{
            String GraphString = "";
            for (Vertex<L> a : vertices) {
                GraphString = GraphString.concat(a.toString() + "\n");
            }
            return GraphString;
        }
    }
}

/**
 * TODO specification
 * Mutable.
 * This class is internal to the rep of ConcreteVerticesGraph.
 * An implementation of vertex
 * Every vertex has the source vertices and the target vertices
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Vertex<L> {
    
    // fields
    private final L name;
    private final Map<L,Integer> sources;
    private final Map<L,Integer> targets;


    // Abstraction function:
    //   name is the label of the vertex
    //   sources contains Edges that directs it
    //   targets contains Edges that it directs others

    // Representation invariant:
    //   name must not be null
    //   the weights of sources and targets must be positive;

    // Safety from rep exposure:
    //   make the rep be private and final
    //   return defensive copies of mutable fields
    
    // constructor
    public Vertex(L name){
        this.name = name;
        this.sources = new HashMap<>();
        this.targets = new HashMap<>();
        checkRep();
    }

    // checkRep
    public void checkRep() {
        assert (name != null);
        for (L key : sources.keySet())
            assert (sources.get(key) > 0);
        for (L key : targets.keySet())
            assert (targets.get(key) > 0);
    }

    // methods
    public L getName(){
        return this.name;
    }

    public Map<L, Integer> getSources() {
        return new HashMap<>(sources);
    }

    public Map<L, Integer> getTargets() {
        return new HashMap<>(targets);
    }

    /**
     * add a new source in this vertex's sources，if weight !=0，add it in sources
     * (if source is exist，renew its weight and return last weight)
     * (if source isn't exist,create new vertex and return 0)
     * if weight = 0，remove last source(isn't exist return 0，is exist return last weight)
     * @param newsource wait to add source
     * @param weight  the length for last vertex to new source
     * @return last weight if not exist return 0
     */
    public int addSource(L newsource,int weight){
        Integer lastweight=0;
        if(weight>0) {
            lastweight=sources.put(newsource,weight);
            if(lastweight==null) {
                lastweight=0;
            }
        }
        if(weight==0){
            lastweight=this.removesource(newsource);
        }
        if(weight<0) {
            throw new RuntimeException("wrong input of weight");
        }
        checkRep();
        return lastweight;
    }

    /**
     * add a new target in this vertex's targets，if weight !=0，add it in targets
     * (if target is exist，renew its weight and return last weight)
     * (if target isn't exist,create new vertex and return 0)
     * if weight = 0，remove last target(isn't exist return 0，is exist return last weight)
     * @param newtarget wait to add target
     * @param weight  the length for last vertex to new target
     * @return last weight if not exist return 0
     */
    public int addTarget(L newtarget,int weight) {
        Integer lastweight=0;
        if(weight>0) {
            lastweight=targets.put(newtarget,weight);
            if(lastweight==null) {
                lastweight=0;
            }
        }
        if(weight==0){
            lastweight=this.removetarget(newtarget);
        }
        if(weight<0) {
            throw new RuntimeException("wrong input of weight");
        }
        checkRep();
        return lastweight;
    }

    /**
     * delete a source in sources，and return last weight
     * @param newsource a source
     * @return  last weight and if it's not exist return 0
     */
    public int removesource(L newsource) {
        Integer newweight=sources.remove(newsource);
        if(newweight==null) {
            return 0;
        }
        else {
            return newweight;
        }
    }

    /**
     * delete a target in targets，and return last weight
     * @param newtarget a target
     * @return  last weight and if it's not exist return 0
     */
    public int removetarget(L newtarget) {
        Integer newweight=targets.remove(newtarget);
        if(newweight==null) {
            return 0;
        }
        else {
            return newweight;
        }
    }

    // toString()
    @Override
    public String toString(){
        checkRep();
        return "Vertex:"+this.name.toString() + " sources:" + this.sources + " target:" + this.targets;
    }
    
}
