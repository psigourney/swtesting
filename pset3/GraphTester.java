package pset3;

import org.junit.jupiter.api.Test;
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

}
