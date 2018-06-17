//Patrick Sigourney - pws482

package pset1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SLListAddTester {
    @Test
    public void test0(){
        SLList l = new SLList();
        assertTrue(l.repOk());
        l.add(true);

        assertTrue(l.header != null);
        assertTrue(l.header.elem == true);
        assertTrue(l.header.next == null);
    }

    @Test public void test1(){
        SLList l = new SLList();
        assertTrue(l.repOk());
        l.add(true);
        assertTrue(l.repOk());
        l.add(false);
        assertTrue(l.repOk());

        //'false' node
        assertTrue(l.header.elem == false);
        assertTrue(l.header.next != null);

        //'true' node
        assertTrue(l.header.next.elem == true);
        assertTrue(l.header.next.next == null);
    }
}
