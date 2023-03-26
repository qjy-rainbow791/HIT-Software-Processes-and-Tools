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
public class ConcreteEdgesGraph<L> implements Graph<L> {
    
    private final Set<L> vertices = new HashSet<>();
    private final List<Edge<L>> edges = new ArrayList<>();
    
    // Abstraction function:
    //   A Graph
    //   use L to represent vertex
    //   use Edge Class to represent edge

    // Representation invariant:
    //   The num of vertices and edges should :edges num <= vertices num*(vertices num -1)

    // Safety from rep exposure:
    //   make the rep be private and final
    //   return defensive copies of mutable fields
    
    // constructor
    public ConcreteEdgesGraph(){

    }
    // checkRep
    public void checkRep(){
        if(edges.size()>(vertices.size()*(vertices.size()-1))){
            throw new RuntimeException("Wrong num of edge and vertex!");
        }
    }

    @Override public boolean add(L vertex) {
        try{
            if(vertices.contains(vertex)){
                return false;
            }else{
                vertices.add(vertex);
                return true;
            }
        }catch (Exception e){
            throw new RuntimeException("not implemented");
        }
    }
    
    @Override public int set(L source, L target, int weight) {
        try{
            if(weight < 0){
                throw new RuntimeException("Wrong input of weight");
            }else if(weight > 0){
                if (!vertices.contains(source)){
                    this.add(source);
                }
                if (!vertices.contains(target)){
                    this.add(target);
                }
                Iterator<Edge<L>> i = edges.iterator();
                while (i.hasNext()) {
                    Edge<L> edge = i.next();
                    if (edge.getSource().equals(source) && edge.getTarget().equals(target)) {
                        int lastEdgeWeight = edge.getWeight();
                        i.remove();
                        Edge<L> newEdge = new Edge<L> (source, target, weight);
                        edges.add(newEdge);
                        checkRep();
                        return lastEdgeWeight;
                    }
                }
                Edge<L> newEdge = new Edge<L>(source,target,weight);
                edges.add(newEdge);
                checkRep();
                return 0;
            }else{
                Iterator<Edge<L>> i = edges.iterator();
                while (i.hasNext()) {
                    Edge<L> edge = i.next();
                    if (edge.getSource().equals(source) && edge.getTarget().equals(target)) {
                        int lastEdgeWeight = edge.getWeight();
                        i.remove();
                        checkRep();
                        return lastEdgeWeight;
                    }
                }
                checkRep();
                return 0;
            }
        }catch (Exception e){
            throw new RuntimeException("not implemented");
        }
    }
    
    @Override public boolean remove(L vertex) {
        try {
            if (!vertices.contains(vertex)){
                return false;
            }
            edges.removeIf(edge -> edge.getSource().equals(vertex) || edge.getTarget().equals(vertex));
            vertices.remove(vertex);
            checkRep();
            return true;
        } catch (Exception e){
            throw new RuntimeException("not implemented");
        }
    }
    
    @Override public Set<L> vertices() {
        try {
            return Collections.unmodifiableSet(vertices);
        } catch (Exception e){
            throw new RuntimeException("not implemented");
        }
    }
    
    @Override public Map<L, Integer> sources(L target) {
        try {
            Map<L ,Integer> a = new HashMap<>();
            for (Edge<L> edge : edges) {
                if (edge.getTarget().equals(target)) {
                    a.put(edge.getSource(), edge.getWeight());
                }
            }
            return a;
        } catch (Exception e){
            throw new RuntimeException("not implemented");
        }
    }
    
    @Override public Map<L, Integer> targets(L source) {
        try {
            Map<L ,Integer> a = new HashMap<>();
            for (Edge<L> edge : edges) {
                if (edge.getSource().equals(source)) {
                    a.put(edge.getTarget(), edge.getWeight());
                }
            }
            return a;
        } catch (Exception e){
            throw new RuntimeException("not implemented");
        }
    }

    // toString()
    @Override
    public String toString(){
        if(edges.isEmpty()){
            return "This is an empty Graph";
        }else{
            String GraphString = "";
            for (Edge<L> edge : edges) {
                GraphString = GraphString.concat(edge.toString() + "\n");
            }
            return GraphString;
        }
    }
    
}

/**
 * specification
 * Immutable.
 * An Implementation of Edge
 * This class is internal to the rep of ConcreteEdgesGraph.
 * Edge has a positive weight that Type{@code int}
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Edge<L> {
    
    // fields
    private final L source;
    private final L target;
    private final int weight;
    // Abstraction function:
    //   source and target are the start vertex and end vertex of the edge
    //   weight is the value of Edge

    // Representation invariant:
    //   the source and target are not null
    //   the weight is positive

    // Safety from rep exposure:
    //   the rep should be private and final
    //   return defensive copies of mutable fields
    
    // constructor
    public Edge(L source,L target,int weight){
        this.source = source;
        this.target = target;
        this.weight = weight;
        checkRep();
    }

    // checkRep
    public void checkRep()
    {
        if(source==null||target==null||weight<=0)
        {
            throw new RuntimeException("Wrong rep of Edge!");
        }
    }

    // methods
    public L getSource(){
        checkRep();
        return this.source;
    }

    public L getTarget(){
        checkRep();
        return this.target;
    }

    public int getWeight(){
        checkRep();
        return this.weight;
    }

    // toString()
    @Override
    public String toString(){
        checkRep();
        return "Edge:" + this.source.toString() + "->" + this.target.toString() + ":" + "Weight = " + this.weight;
    }
}
