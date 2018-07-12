package pset3;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class GraphTester {

    /* Test Set for addEdge()  */
    @Test
    public void tae00(){
        Graph g = new Graph(-1);
        g.addEdge(0,0);
        System.out.println(g);
        assertEquals(g.toString(), "numNodes: 0\nedges: []");
    }

    @Test
    public void tae0(){
        Graph g = new Graph(2);
        g.addEdge(0,1);
        System.out.println(g);
        assertEquals(g.toString(), "numNodes: 2\nedges: [[false, true], [false, false]]");
    }

    @Test
    public void tae1(){
        Graph g = new Graph(0);
        g.addEdge(0,0);
        System.out.println(g);
        assertEquals(g.toString(), "numNodes: 0\nedges: []");
    }

    @Test
    public void tae2(){
        Graph g = new Graph(1);
        g.addEdge(0,0);
        System.out.println(g);
        assertEquals(g.toString(), "numNodes: 1\nedges: [[true]]");
    }

    @Test
    public void tae3(){
        Graph g = new Graph(1);
        g.addEdge(0,-1);
        System.out.println(g);
        assertEquals(g.toString(), "numNodes: 1\nedges: [[false]]");
    }

    @Test
    public void tae4(){
        Graph g = new Graph(1);
        g.addEdge(-1,0);
        System.out.println(g);
        assertEquals(g.toString(), "numNodes: 1\nedges: [[false]]");
    }

    @Test
    public void tae5(){
        Graph g = new Graph(1);
        g.addEdge(-1,-1);
        System.out.println(g);
        assertEquals(g.toString(), "numNodes: 1\nedges: [[false]]");
    }

    @Test
    public void tae6(){
        Graph g = new Graph(2);
        g.addEdge(0,1);
        System.out.println(g);
        assertEquals(g.toString(), "numNodes: 2\nedges: [[false, true], [false, false]]");
    }

    @Test
    public void tae99(){
        Graph g = new Graph(3);
        g.addEdge(0,0);
        g.addEdge(0,1);
        g.addEdge(0,2);
        g.addEdge(2,0);
        g.addEdge(2,2);

        System.out.println(g);
        assertEquals(g.toString(), "numNodes: 3\nedges: [[true, true, true], [false, false, false], [true, false, true]]");
    }


    /*  Test Set for reachable()  */
    @Test
    public void tr00(){
        Graph g = new Graph(5);
        g.addEdge(0,2);
        g.addEdge(2,1);
        g.addEdge(1,4);
        g.addEdge(2,4);
        g.addEdge(3,4);

        Set<Integer> sources = new HashSet<Integer>();
        Set<Integer> targets = new HashSet<Integer>();

        sources.add(0);
        sources.add(1);
        targets.add(1);
        targets.add(2);
        targets.add(4);

        assertTrue(g.reachable(sources, targets));

    }
}
