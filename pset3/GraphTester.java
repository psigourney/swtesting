package pset3;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class GraphTester {

    /* Test Set for addEdge()  */
    /*
        tae00 - neg size graph
        tae0 - valid 0,1 edge
        tae1 - zero size graph
        tae2 - single node, single edge, valid
        tae3 - invalid target (neg)
        tae4 - invalid source (neg)
        tae5 - invalid source (neg), invalid target (neg)
        tae6 - 3 nodes, 5 edges, all valid
     */

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
    /*
        tr00 - sources null
        tr01 - targets null
        tr02 - both null

        tr0 - empty graph, empty sources, empty targets
        tr1 - invalid target (low)
        tr2 - invalid target (high)
        tr3 - invalid source (low)
        tr4 - invalid source (high)
        tr5 - no source to target
        tr6 - yes source to target
        tr7 - large graph, no route to one target
        tr8 - large graph, routes to all targets
     */

    @Test
    public void tr00(){
        Graph g = new Graph(0);

        Set<Integer> sources = null;
        Set<Integer> targets = new HashSet<Integer>();

        Exception reachExcept = null;
        try{ g.reachable(sources,targets); }
        catch(IllegalArgumentException e) { reachExcept = e; }
        assertTrue( reachExcept.getClass() == IllegalArgumentException.class );
    }

    @Test
    public void tr01(){
        Graph g = new Graph(0);

        Set<Integer> sources = new HashSet<Integer>();
        Set<Integer> targets = null;

        Exception reachExcept = null;
        try{ g.reachable(sources,targets); }
        catch(IllegalArgumentException e) { reachExcept = e; }
        assertTrue( reachExcept.getClass() == IllegalArgumentException.class );
    }

    @Test
    public void tr02(){
        Graph g = new Graph(0);

        Set<Integer> sources = null;
        Set<Integer> targets = null;

        Exception reachExcept = null;
        try{ g.reachable(sources,targets); }
        catch(IllegalArgumentException e) { reachExcept = e; }
        assertTrue( reachExcept.getClass() == IllegalArgumentException.class );
    }


    @Test
    public void tr0(){
        Graph g = new Graph(0);

        Set<Integer> sources = new HashSet<Integer>();
        Set<Integer> targets = new HashSet<Integer>();

        assertTrue(g.reachable(sources, targets));
    }

    @Test
    public void tr1(){
        Graph g = new Graph(1);

        g.addEdge(0,0);

        Set<Integer> sources = new HashSet<Integer>();
        Set<Integer> targets = new HashSet<Integer>();

        targets.add(-1);

        assertTrue(!g.reachable(sources, targets));
    }

    @Test
    public void tr2(){
        Graph g = new Graph(1);

        g.addEdge(0,0);

        Set<Integer> sources = new HashSet<Integer>();
        Set<Integer> targets = new HashSet<Integer>();

        targets.add(2);

        assertTrue(!g.reachable(sources, targets));
    }

    @Test
    public void tr3(){
        Graph g = new Graph(1);

        g.addEdge(0,0);

        Set<Integer> sources = new HashSet<Integer>();
        Set<Integer> targets = new HashSet<Integer>();

        sources.add(-1);
        targets.add(0);

        assertTrue(!g.reachable(sources, targets));
    }

    @Test
    public void tr4(){
        Graph g = new Graph(1);

        g.addEdge(0,0);

        Set<Integer> sources = new HashSet<Integer>();
        Set<Integer> targets = new HashSet<Integer>();

        sources.add(2);
        targets.add(0);

        assertTrue(!g.reachable(sources, targets));
    }

    @Test
    public void tr5(){
        Graph g = new Graph(1);

        g.addEdge(0,0);

        Set<Integer> sources = new HashSet<Integer>();
        Set<Integer> targets = new HashSet<Integer>();

        targets.add(0);

        assertTrue(!g.reachable(sources, targets));
    }

    @Test
    public void tr6(){
        Graph g = new Graph(1);

        g.addEdge(0,0);

        Set<Integer> sources = new HashSet<Integer>();
        Set<Integer> targets = new HashSet<Integer>();

        sources.add(0);
        targets.add(0);

        assertTrue(g.reachable(sources, targets));
    }

    @Test
    public void tr7(){
        Graph g = new Graph(6);
        g.addEdge(0,2);
        g.addEdge(2,1);
        g.addEdge(1,4);
        g.addEdge(2,4);
        g.addEdge(3,4);
        g.addEdge(3,5);

        Set<Integer> sources = new HashSet<Integer>();
        Set<Integer> targets = new HashSet<Integer>();

        sources.add(0);
        targets.add(1);
        targets.add(2);
        targets.add(4);
        targets.add(5);

        assertTrue(!g.reachable(sources, targets));
    }

    @Test
    public void tr8(){
        Graph g = new Graph(6);
        g.addEdge(0,2);
        g.addEdge(2,1);
        g.addEdge(1,4);
        g.addEdge(2,4);
        g.addEdge(3,4);
        g.addEdge(3,5);

        Set<Integer> sources = new HashSet<Integer>();
        Set<Integer> targets = new HashSet<Integer>();

        sources.add(0);
        sources.add(3);
        targets.add(1);
        targets.add(2);
        targets.add(4);
        targets.add(5);

        assertTrue(g.reachable(sources, targets));
    }
}
