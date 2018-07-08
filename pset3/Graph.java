package pset3;

import java.util.Arrays;
import java.util.Set;

public class Graph {
    private int numNodes;
    private boolean[][] edges;

    public Graph(int size){
        numNodes = size;
        edges = new boolean[size][size];
    }

    public String toString(){
        return "numNodes: " + numNodes + "\n" + "edges: " + Arrays.deepToString(edges);
    }

    public boolean equals(Object o){
        if(o.getClass() != Graph.class) return false;
        return toString().equals(o.toString());
    }

    public void addEdge(int from, int to){
        edges[from][to] = true;
    }

    public boolean reachable(Set<Integer> sources, Set<Integer> targets){
        if(sources == null || targets == null) throw new IllegalArgumentException();

        //postcondition: returns true if
        //      1) "sources" does not contain an illegal node
        //      2) "targets" dies not contain an illegal node
        //      3) for each node 'm' in set "targets", there is some node 'n' in set "sources" such
        //      that there is a directed path that starts at "n" in "this"; and false otherwise.


        //Your code goes here (1.3)
        return true;
    }
}
