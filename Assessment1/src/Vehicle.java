public class Vehicle {

    private String brand;
    private String model;
    private static int numWheels;

    public Vehicle(String brand, String model, int numWheels){
        this.brand = brand;
        this.model = model;
        Vehicle.numWheels = numWheels;
    }

    public Vehicle() {

    }

    public String getBrand(){
        System.out.println("Brand: " + brand);
        return brand;
    }

    public String getModel(){
        System.out.println("Model: " + model);
        return model;
    }

    public int getNumWheels(){
        System.out.println("Number of wheels: " + numWheels);
        return numWheels;
    }

    public boolean turnOn(boolean getIsOff){
        if(getIsOff){
            startEngine();
        }else {
            System.out.println("Engine is on.");
        }

        return getIsOff;
    }
    private void startEngine(){
        System.out.println("Engine turning on");
    }

}
