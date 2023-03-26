/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import P1.graph.Graph;

/**
 * A graph-based poetry generator.
 * 
 * <p>GraphPoet is initialized with a corpus of text, which it uses to derive a
 * word affinity graph.
 * Vertices in the graph are words. Words are defined as non-empty
 * case-insensitive strings of non-space non-newline characters. They are
 * delimited in the corpus by spaces, newlines, or the ends of the file.
 * Edges in the graph count adjacencies: the number of times "w1" is followed by
 * "w2" in the corpus is the weight of the edge from w1 to w2.
 * 
 * <p>For example, given this corpus:
 * <pre>    Hello, HELLO, hello, goodbye!    </pre>
 * <p>the graph would contain two edges:
 * <ul><li> ("hello,") -> ("hello,")   with weight 2
 *     <li> ("hello,") -> ("goodbye!") with weight 1 </ul>
 * <p>where the vertices represent case-insensitive {@code "hello,"} and
 * {@code "goodbye!"}.
 * 
 * <p>Given an input string, GraphPoet generates a poem by attempting to
 * insert a bridge word between every adjacent pair of words in the input.
 * The bridge word between input words "w1" and "w2" will be some "b" such that
 * w1 -> b -> w2 is a two-edge-long path with maximum-weight weight among all
 * the two-edge-long paths from w1 to w2 in the affinity graph.
 * If there are no such paths, no bridge word is inserted.
 * In the output poem, input words retain their original case, while bridge
 * words are lower case. The whitespace between every word in the poem is a
 * single space.
 * 
 * <p>For example, given this corpus:
 * <pre>    This is a test of the Mugar Omni Theater sound system.    </pre>
 * <p>on this input:
 * <pre>    Test the system.    </pre>
 * <p>the output poem would be:
 * <pre>    Test of the system.    </pre>
 * 
 * <p>PS2 instructions: this is a required ADT class, and you MUST NOT weaken
 * the required specifications. However, you MAY strengthen the specifications
 * and you MAY add additional methods.
 * You MUST use Graph in your rep, but otherwise the implementation of this
 * class is up to you.
 */
public class GraphPoet {
    
    private final Graph<String> graph = Graph.empty();
    
    // Abstraction function:
    //   a graph stores the corpus of the text
    //   the vertices in graph are the words in the text, the edges are adjacent
    //   whose weight is the time "w1" is followed by "w2"

    // Representation invariant:
    //   words must be lowercase


    // Safety from rep exposure:
    //   make the rep be private and final
    //   return defensive copies of mutable fields
    
    /**
     * Create a new poet with the graph from corpus (as described above).
     * 
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */
    public GraphPoet(File corpus) throws IOException {
        FileReader reader = new FileReader(corpus);
        try (Scanner sc1 = new Scanner(reader)) {
            while (sc1.hasNextLine()) {  //按行读取字符串
                String line = sc1.nextLine();
                line = line.replace(".", " ");
                String[] everyLine = line.split("\\s+");
                String[] wordsToLowerCase = new String[everyLine.length];
                for(int i = 0; i < everyLine.length; i++) {
                    wordsToLowerCase[i] = everyLine[i].toLowerCase();
                }
                for(int i = 0; i < everyLine.length; i++) {
                    graph.add(wordsToLowerCase[i]);
                    if(i >= 1) {
                        int lastEdgeWeight = graph.set(wordsToLowerCase[i - 1], wordsToLowerCase[i], 1);
                        if (lastEdgeWeight != 0) {
                            graph.set(wordsToLowerCase[i - 1], wordsToLowerCase[i], lastEdgeWeight + 1);
                        }
                    }
                }
            }
        }
        reader.close();
        checkRep();
    }
    
    // checkRep
    public void checkRep() {
        Set<String> vertices = graph.vertices();
        for (String vertex : vertices)
            assert (vertex != null);
    }

    /**
     * Generate a poem.
     * 
     * @param input string from which to create the poem
     * @return poem (as described above)
     */
    public String poem(String input) {
        StringBuilder build=new StringBuilder();
        String[] newinput=input.split(" ");
        List<String> mylist= new ArrayList<>(Arrays.asList(newinput));  //按空格分隔存入list
        Map<String, Integer> sourcemap = new HashMap<>();
        Map<String, Integer> targetmap = new HashMap<>();
        for(int i=0;i<mylist.size()-1;i++) {
            build.append(mylist.get(i)).append(" ");
            String source=mylist.get(i).toLowerCase();
            String target=mylist.get(i+1).toLowerCase();
            targetmap=graph.targets(source);
            sourcemap=graph.sources(target);
            int max=0;
            String word="";  //待加入的点
            for(String a:targetmap.keySet()) {
                if(sourcemap.containsKey(a)&&sourcemap.get(a)+targetmap.get(a)>max) {
                    max=sourcemap.get(a)+targetmap.get(a);
                    word=a;
                }
            }
            if(max>0) {
                build.append(word).append(" ");  //加入word点
            }
        }
        build.append(mylist.get(mylist.size()-1));  //加入并未考虑的最后一个点
        return build.toString();
    }
    
    // toString()
    @Override
    public String toString() {
        return "graph = \n" + graph;
    }
    
}
