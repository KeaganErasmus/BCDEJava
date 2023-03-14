import java.lang.reflect.Array;

public class Main {
    public static void main(String[] args) {

        Array numVehicles[];
        // Classes
        Vehicle golf = new Vehicle();
        golf.getBrand("Volkswagen");
        golf.getModel("Golf");
        golf.getNumWheels(4);
        golf.turnOn(true);
        
        System.out.println();

        Vehicle tricycle = new Vehicle();
        tricycle.getBrand("Mobo Cruiser");
        tricycle.getModel("Pro Adult Tricycle");
        tricycle.getNumWheels(3);
    }
}