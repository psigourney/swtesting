package pset3;

import java.util.*;

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

    public boolean reachable(Set<Integer> sources, Set<Integer> targets) {
        if (sources == null || targets == null) throw new IllegalArgumentException();

        //postcondition: returns true if
        //      1) "sources" does not contain an illegal node
        //      2) "targets" dies not contain an illegal node
        //      3) for each node 'm' in set "targets", there is some node 'n' in set "sources" such
        //      that there is a directed path that starts at "n" in "this"; and false otherwise.

        for( Integer targetNode : targets ){
            if(targetNode < 0 || targetNode >= numNodes) return false;
            boolean targetFound = false;

            for( Integer sourceNode : sources ){
                if(sourceNode < 0 || sourceNode >= numNodes) return false;
                //System.out.println("Checking isReachable(" + sourceNode + ", " + targetNode + ")");
                if(isReachable(sourceNode, targetNode)) {
                    //System.out.println("True: isReachable(" + sourceNode + ", " + targetNode + ")");
                    targetFound = true;
                }
            }
            if(!targetFound) return false; //One of the targets can't be reached
        }
        return true;
    }

    //isReachable Code Copied from: https://www.geeksforgeeks.org/find-if-there-is-a-path-between-two-vertices-in-a-given-graph/
    public boolean isReachable(int s, int d)
    {
        if(s==d) return true;

        // Mark all the vertices as not visited(By default set as false)
        boolean visited[] = new boolean[numNodes];

        // Create a queue for BFS
        Queue<Integer> queue = new LinkedList<>();

        // Mark the current node as visited and enqueue it
        visited[s]=true;
        queue.add(s);

        // 'i' will be used to get all adjacent vertices of a vertex
        Iterator<Integer> i;

        while (queue.size()!=0)
        {
            // Dequeue a vertex from queue and print it
            s = queue.poll();

            int n;

            //Create list of adjacent nodes
            LinkedList<Integer> adjList = new LinkedList<>();
            for(int counter=0; counter < numNodes; counter++){
                if(edges[s][counter]) adjList.add(counter);
            }

            i = adjList.listIterator();

            // Get all adjacent vertices of the dequeued vertex s
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it
            while (i.hasNext()){
                n = i.next();

                // If this adjacent node is the destination node,
                // then return true
                if (n==d) return true;

                // Else, continue to do BFS
                if (!visited[n]){
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
        // If BFS is complete without visited d
        return false;
    }


}
