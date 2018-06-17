package pset1;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import pset1.SLList.Node;

import java.util.HashSet;
import java.util.Set;

public class SLListRepOkTester {
    @Test //Empty List
    public void t0(){
        SLList l = new SLList();

        assertTrue(l.repOk());
    }

    @Test //Single Node Passing
    public void t1(){
        SLList l = new SLList();
        Node n = new Node();
        n.next = l.header;
        n.elem = true;
        l.header = n;

        assertTrue(l.repOk());
    }

    @Test //Double Node Passing
    public void t2(){
        SLList l = new SLList();
        Node n = new Node();
        n.next = l.header;
        n.elem = true;
        l.header = n;

        Node m = new Node();
        m.next = l.header;
        m.elem = false;
        l.header = m;

        assertTrue(l.repOk());
    }

    @Test //Single Node Failing
    public void t3(){
        SLList l = new SLList();
        Node n = new Node();

        n.next = n;
        n.elem = true;
        l.header = n;

        assertFalse(l.repOk());
    }

    @Test //Double Node Failing
    public void t4(){
        SLList l = new SLList();
        Node n = new Node();

        n.next = l.header;
        n.elem = true;
        l.header = n;

        Node m = new Node();

        m.next = l.header;
        m.elem = true;
        l.header = m;

        n.next = m;

        assertFalse(l.repOk());
    }
}
