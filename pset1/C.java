package pset1;

public class C{
    int f;

    public C(int f){
        this.f = f;
    }

    @Override
    public boolean equals(Object o){
        //Assume this is implemented for you
        if (!(o instanceof C)) return false;
        return this.f == ((C) o).f;
    }

    @Override
    public int hashCode(){
        //Assume this is implemented for you
        return f;
    }
}
