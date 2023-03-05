public class Main {
    public static void main(String[] args) {

        // Classes
        Vehicle golf = new Vehicle();
        golf.getBrand("Volkswagen");
        golf.getModel("Golf");
        golf.getNumWheels(4);
        golf.turnOn(true);

        System.out.println();

        Vehicle tricycle = new Vehicle();
        golf.getBrand("Mobo Cruiser");
        golf.getModel("Pro Adult Tricycle");
        golf.getNumWheels(3);
    }
}