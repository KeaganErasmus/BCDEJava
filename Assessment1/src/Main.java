import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Enum types");
        CarMake volkswagen = new CarMake(Make.VOLKSWAGEN);
        CarMake mazda = new CarMake(Make.MAZDA);
        CarMake hyundia = new CarMake(Make.HYUNDIA);
        CarMake nissan = new CarMake(Make.NISSAN);

        volkswagen.showCarModels();
        mazda.showCarModels();
        hyundia.showCarModels();
        nissan.showCarModels();

        Toyota myCar = new Toyota("Toyota");

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

        System.out.println("List");
        for(int i = 0; i < allMyVehicles.length; i++){
            newAllMyVehicles[i] = allMyVehicles[i];
        }

        for (String v:newAllMyVehicles) {
            System.out.println(v);
        }
        System.out.println();

        //Inheritance
        System.out.println("Inheritance demo:");
        System.out.println(myCar.getBrand());
        System.out.println();

        //Generics
        System.out.println("Generics demo");
        Manufacturer<String> manufacturer = new Manufacturer<>();
        manufacturer.addName("Bongo");
        System.out.println(manufacturer.getName());

        System.out.println();

        //AutoBoxing and Unboxing
        System.out.println("Autoboxing example");
        Integer numWheels = 6;
        int i = numWheels; // Unboxing
        System.out.println(i);

        System.out.println();

        //StringBuilder
        System.out.println("StringBuilder demo");
        StringBuilder sb = new StringBuilder();
        sb.append(volkswagen.make);
        sb.append(" ");
        sb.append(myCar.getBrand());
        System.out.println(sb);

        System.out.println();

        // Using a literal string
        String originalMessg = "this is a normal text example\n" +
                "where I can have to do more formatting to get new lines\n";

        // Using a text block
        String message = """
               this is a text block example
               where I can have a long string of text on new lines
               """;

        System.out.println(originalMessg);
        System.out.println(message);

        //File input stream
        try{
            FileInputStream inputStream = new FileInputStream("C:/Programming/Java/BCDEJava/Assessment1/src/cars.txt");
            int data;
            while ((data = inputStream.read()) != -1){
                System.out.println((char)data);
            }
            inputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}