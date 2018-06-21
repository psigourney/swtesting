package pset1;


public class D extends C{
    int g;

    public D(int f, int g){
        super(f);
        this.g = g;
    }

    @Override
    public boolean equals(Object o){
        //Assume this is implemented for you
        if (!(o instanceof D)) return false;
        return (this.f == ((D) o).f && this.g == ((D) o).g);
    }

    @Override
    public int hashCode(){
        //Assume this is implemented for you
        return f*g;
    }
}