import javax.rmi.ssl.SslRMIClientSocketFactory;
import javax.swing.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
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
        for (int i = 0; i < allMyVehicles.length; i++) {
            newAllMyVehicles[i] = allMyVehicles[i];
        }

        for (String v : newAllMyVehicles) {
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
        try {
            FileInputStream inputStream = new FileInputStream("C:/Programming/Java/BCDEJava/Assessment1/src/cars.txt");
            int data;
            while ((data = inputStream.read()) != -1) {
                System.out.println((char) data);
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Tutorial demos
        boolean result = true;
        char capitalC = 'C';
        byte bt = 100;
        short s = 10000;
        int j = 100000;

        // declares an array of integers
        int[] anArray;
        // allocates memory for 10 integers
        anArray = new int[10];
        // initialize first element
        anArray[0] = 100;
        // initialize second element
        anArray[1] = 200;
        System.out.println("Element at index 0: "
                + anArray[0]);
        System.out.println("Element at index 1: "
                + anArray[1]);

        int testscore = 76;
        char grade;

        if (testscore >= 90) {
            grade = 'A';
        } else if (testscore >= 80) {
            grade = 'B';
        } else if (testscore >= 70) {
            grade = 'C';
        } else if (testscore >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }
        System.out.println("Grade = " + grade);

        Bicycle bicycle1 = new Bicycle(2, 10, 2);

        //lambda
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        numbers.add(5);
        numbers.add(9);
        numbers.add(8);
        numbers.add(1);
        Consumer<Integer> method = (n) -> {
            System.out.println(n);
        };
        numbers.forEach(method);

        Path path = Paths.get("C:\\home\\keagan\\hello.txt");
        System.out.format("toString: %s%n", path.toString());
        System.out.format("getFileName: %s%n", path.getFileName());
        System.out.format("getName(0): %s%n", path.getName(0));
        System.out.format("getNameCount: %d%n", path.getNameCount());
        System.out.format("subpath(0,2): %s%n", path.subpath(0, 2));
        System.out.format("getParent: %s%n", path.getParent());
        System.out.format("getRoot: %s%n", path.getRoot());

//        readFirstLineFromFile("C:\\Users\\Keagan\\hello.txt");

        //unboxing
//        System.out.println("\nAutoboxing/Unboxing");
        List<Integer> li = new ArrayList<>();
        for (int k = 1; k < 50; k += 2) {
            li.add(k);
//            System.out.print(li);
        }

        String palindrome = "Dot saw I was Tod";
        StringBuilder sb1 = new StringBuilder(palindrome);
        sb1.reverse();  // reverse it
        System.out.println(sb1);

        int numLetters = 0;
        Day day = Day.WEDNESDAY;
        numLetters = switch (day) {
            case MONDAY, FRIDAY, SUNDAY -> 6;
            case TUESDAY -> 7;
            case THURSDAY, SATURDAY -> 8;
            case WEDNESDAY -> 9;
            default -> throw new IllegalStateException("Invalid day: " + day);
        };
        System.out.println(numLetters);

        //Hard to read variable decleration
//        URL url = new URL("http://www.oracle.com/");
//        URLConnection conn = url.openConnection();
//        Reader reader = new BufferedReader(
//                new InputStreamReader(conn.getInputStream()));

        //Using var to have better readability
        var url = new URL("http://www.oracle.com/");
        var conn = url.openConnection();
        var reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        String NoTextBlockMessage = "'The time has come,' the Walrus said,\n" +
                "'To talk of many things:\n" +
                "Of shoes -- and ships -- and sealing-wax --\n" +
                "Of cabbages -- and kings --\n" +
                "And why the sea is boiling hot --\n" +
                "And whether pigs have wings.'\n";

        // BETTER
        String TextBlockMessage = """
                    'The time has come,' the Walrus said,
                    'To talk of many things:
                    Of shoes -- and ships -- and sealing-wax --
                    Of cabbages -- and kings --
                    And why the sea is boiling hot --
                    And whether pigs have wings.'
                    """;

        System.out.println("No text block message\n"+ NoTextBlockMessage);
        System.out.println("Text block message\n"+TextBlockMessage);

         record Person (String name, String address) {
             public Person{
                 Objects.requireNonNull(name);
                 Objects.requireNonNull(address);
             }
         }

        Pattern p = Pattern.compile(".s");//. represents single character
        Matcher m = p.matcher("as");
        boolean b = m.matches();
    }

    public void print(Object o) {
        if (o instanceof String s){
            System.out.println("This is a String of length " + s.length());
        } else {
            System.out.println("This is not a String");
        }
    }


    //    static void readFirstLineFromFile(String path) throws IOException {
//        try (FileReader fr = new FileReader(path);
//             BufferedReader br = new BufferedReader(fr)) {
//            br.readLine();
//        }
//    }
}