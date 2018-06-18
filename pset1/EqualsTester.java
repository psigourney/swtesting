package pset1;

public class C{
    int f;

    public C(int f){
        this.f = f;
    }

    @Override
    public boolean equals(Object o){
        //Assume this is implemented for you
        return true;
    }

    @Override
    public int hashCode(){
        //Assume this is implemented for you
        return 0;
    }
}

public class D extends C{
    int g;

    public D(int f, int g){
        super(f);
        this.g = g;
    }

    @Override
    public boolean equals(Object o){
        //Assume this is implemented for you
        return true;
    }

    @Override
    public int hashCode(){
        //Assume this is implemented for you
        return 0;
    }
}

public class EqualsTester {
    //P1: For any non-null reference value x, x.equals(null) should return false.

    //P2: It is reflexive: for any non-null reference value x, x.equals(x) should return true.

    //P3: It is symmetric: for any non-null refernece values x and y, x.equals(y) should return true
    //  iff: y.equals(x) returns true.

    //P4: It is transitive: for any non-null reference values x,y,z:
    //  if x.equals(y) is true
    //  and y.equals(z) is true
    //  then x.equals(z) must return true.

}
