package pset1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EqualsTester {
    //P1: For any non-null reference value x, x.equals(null) should return false.
    @Test
    public void t1_1(){
        Object obj = new Object();
        assertFalse(obj.equals(null));
    }

    @Test
    public void t1_2(){
        C cee = new C(0);
        assertFalse(cee.equals(null));
    }

    @Test
    public void t1_3(){
        D dee = new D(0, 0);
        assertFalse(dee.equals(null));
    }


    //P2: It is reflexive: for any non-null reference value x, x.equals(x) should return true.
    @Test
    public void t2_1(){
        Object obj = new Object();
        assertTrue(obj.equals(obj));
    }

    @Test
    public void t2_2(){
        C cee = new C(0);
        assertTrue(cee.equals(cee));
    }

    @Test
    public void t2_3(){
        D dee = new D(0, 0);
        assertTrue(dee.equals(dee));
    }

    //P3: It is symmetric: for any non-null reference values x and y, x.equals(y) should return true
    //  iff: y.equals(x) returns true.

    @Test
    public void t3_1(){
        Object objA = new Object();
        Object objB = objA;
        if (objA.equals(objB)) assertTrue(objB.equals(objA));
    }

    @Test
    public void t3_2(){
        C cee = new C(0);
        Object obj = cee;
        if (obj.equals(cee)) assertTrue(cee.equals(obj));
    }

    @Test
    public void t3_3(){
        D dee = new D(0, 0);
        Object obj = dee;

        if (obj.equals(dee)) assertTrue(dee.equals(obj));
    }

    @Test
    public void t3_4(){
        C cee = new C(0);
        Object obj = cee;
        if (cee.equals(obj)) assertTrue(obj.equals(cee));
    }

    @Test
    public void t3_5(){
        C cee = new C(0);
        C cee2 = cee;
        if (cee.equals(cee2)) assertTrue(cee2.equals(cee));
    }

    @Test
    public void t3_6(){
        D dee = new D(0, 0);
        C cee = dee;

        if (cee.equals(dee)) assertTrue(dee.equals(cee));
    }

    @Test
    public void t3_7(){
        D dee = new D(0, 0);
        Object obj = dee;
        if (dee.equals(obj)) assertTrue(obj.equals(dee));
    }

    @Test
    public void t3_8(){
        D dee = new D(0, 0);
        C cee = dee;
        if (dee.equals(cee)) assertTrue(cee.equals(dee));
    }

    @Test
    public void t3_9(){
        D dee = new D(0, 0);
        D dee2 = dee;

        if (dee.equals(dee2)) assertTrue(dee2.equals(dee));
    }



    //P4: It is transitive: for any non-null reference values x,y,z:
    //  if x.equals(y) is true
    //  and y.equals(z) is true
    //  then x.equals(z) must return true.
    @Test
    public void t4(){
        //Not Implemented
    }
}
