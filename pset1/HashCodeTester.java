package pset1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HashCodeTester {
    //P5: If two objects are equal according to .equals(Obj), then calling .hashcode()  on each of the
    //  two objects should produce the same integer result.

    @Test
    public void t5_1(){
        Object objA = new Object();
        Object objB = objA;
        if (objA.equals(objB)) assertTrue(objA.hashCode() == objB.hashCode());
    }

    @Test
    public void t5_2(){
        C cee = new C(0);
        Object obj = cee;
        if (obj.equals(cee)) assertTrue(obj.hashCode() == cee.hashCode());
    }

    @Test
    public void t5_3(){
        D dee = new D(0, 0);
        Object obj = dee;

        if (obj.equals(dee)) assertTrue(obj.hashCode() == dee.hashCode());
    }

    @Test
    public void t5_4(){
        C cee = new C(0);
        Object obj = cee;
        if (cee.equals(obj)) assertTrue(cee.hashCode() == obj.hashCode());
    }

    @Test
    public void t5_5(){
        C cee = new C(0);
        C cee2 = cee;
        if (cee.equals(cee2)) assertTrue(cee.hashCode() == cee2.hashCode());
    }

    @Test
    public void t5_6(){
        D dee = new D(0, 0);
        C cee = dee;

        if (cee.equals(dee)) assertTrue(cee.hashCode() == dee.hashCode());
    }

    @Test
    public void t5_7(){
        D dee = new D(0, 0);
        Object obj = dee;
        if (dee.equals(obj)) assertTrue(dee.hashCode() == obj.hashCode());
    }

    @Test
    public void t5_8(){
        D dee = new D(0, 0);
        C cee = dee;
        if (dee.equals(cee)) assertTrue(dee.hashCode() == cee.hashCode());
    }

    @Test
    public void t5_9(){
        D dee = new D(0, 0);
        D dee2 = dee;

        if (dee.equals(dee2)) assertTrue(dee.hashCode() == dee2.hashCode());
    }

}
