public class Manufacturer<T> {

    private T name;

    public void addName(T name){
        this.name = name;
    }

    public T getName(){
        return name;
    }
}
