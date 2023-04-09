import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CarMake volkswagen = new CarMake(Make.VOLKSWAGEN);
        CarMake mazda = new CarMake(Make.MAZDA);
        CarMake hyundia = new CarMake(Make.HYUNDIA);
        CarMake nissan = new CarMake(Make.NISSAN);

        volkswagen.showCarModels();
        mazda.showCarModels();
        hyundia.showCarModels();
        nissan.showCarModels();

        Car myCar = new Car();
        myCar.brand = "Suzuki";
        myCar.model = "Swift sport";

        List<String> cars = Arrays.asList("Volvo", "Nissan", "Mazda");

        Collections.sort(cars, (a, b) -> a.compareTo(b));

        System.out.println(cars + "\n");


        // Classes
        Vehicle golf = new Vehicle("volkswagen", "golf", 4);
        golf.getBrand();
        golf.getModel();
        golf.getNumWheels();
        golf.turnOn(true);

        System.out.println();

        Vehicle tricycle = new Vehicle("Mobo Cruiser", "Pro Adult Tricycle", 3);
        tricycle.getBrand();
        tricycle.getModel();
        tricycle.getNumWheels();

        System.out.println();

        String[] allMyVehicles = {golf.getBrand(), tricycle.getBrand()};
        String[] newAllMyVehicles = new String[allMyVehicles.length + 1];

        System.out.println();

        int i;
        for(i = 0; i < allMyVehicles.length; i++){
            newAllMyVehicles[i] = allMyVehicles[i];
        }

        for (String v:newAllMyVehicles) {
            System.out.println(v);
        }
        System.out.println();

        System.out.println("Inheritance demo:");
        System.out.println(myCar.getBrand());
        System.out.println(myCar.getModel());
        System.out.println();

        Manufacturer<String> manufacturer = new Manufacturer<>();
        manufacturer.addName("Bozo");
        System.out.println(manufacturer.getName());

    }
}