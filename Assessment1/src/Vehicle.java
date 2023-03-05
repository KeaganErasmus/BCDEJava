public class Vehicle {

    private String brand;
    private String model;
    private int numWheels;
    private boolean isOff = false;

    public String getBrand(String theBrand){
        brand = theBrand;
        System.out.println("Brand: " + brand);
        return brand;
    }

    public String getModel(String theModel){
        model = theModel;
        System.out.println("Model: " + model);
        return model;
    }

    public int getNumWheels(int theNumWheels){
        numWheels = theNumWheels;
        System.out.println("Number of wheels: " + numWheels);
        return numWheels;
    }

    public boolean turnOn(boolean getIsOff){
        isOff = getIsOff;
        if(isOff){
            startEngine();
        }else {
            System.out.println("Engine is on.");
        }

        return isOff;
    }
    private void startEngine(){
        System.out.println("Engine turning on");
    }

}
