package pset3;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class GraphTester {
    @Test
    public void tae0(){
        Graph g = new Graph(2);
        g.addEdge(0,1);
        System.out.println(g);
        assertEquals(g.toString(), "numNodes: 2\nedges: [[false, true], [false, false]]");
    }

    //Create tests for addEdge

    //Create tests for reachable

    @Test
    public void tae99(){
        Graph g = new Graph(5);
        g.addEdge(0,2);
        g.addEdge(2,1);
        g.addEdge(2,4);
        g.addEdge(2,3);
        g.addEdge(1,4);
        g.addEdge(1,2);

        Set<Integer> sources = new HashSet<Integer>();
        Set<Integer> targets = new HashSet<Integer>();

        sources.add(0);
        sources.add(1);
        //sources.add(-1);
        targets.add(0);
        targets.add(1);
        //targets.add(6);
        targets.add(3);
        targets.add(4);

        assertTrue(g.reachable(sources, targets));
    }
}
