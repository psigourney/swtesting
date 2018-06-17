//Patrick Sigourney - pws482

package pset1;

import java.util.HashSet;
import java.util.Set;


public class SLList {
    Node header;

    static class Node{
        boolean elem;
        Node next;
    }

    boolean repOk() {
        Set<Node> visited = new HashSet<Node>();
        Node n = header;
        while (n != null) {
            if (!visited.add(n)) {
                return false;
            }
            n = n.next;
        }
        return true;
    }

    void add(boolean e){
        Node myNode = new Node();
        myNode.elem = e;
        myNode.next = header;
        header = myNode;
    }
}