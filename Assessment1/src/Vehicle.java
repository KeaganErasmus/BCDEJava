public class Vehicle implements IVehicle{

    public String brand;
    public String model;
    private static int numWheels;

    public Vehicle(String brand, String model, int numWheels){
        this.brand = brand;
        this.model = model;
        Vehicle.numWheels = numWheels;
    }

    public Vehicle() {

    }

    @Override
    public String getBrand(){
        System.out.println("Brand: " + brand);
        return brand;
    }

    @Override
    public String getModel(){
        System.out.println("Model: " + model);
        return model;
    }

    @Override
    public void getNumWheels(){
        System.out.println("Number of wheels: " + numWheels);

        if(numWheels < 0){
            throw new IllegalArgumentException("A car can not have less than zero wheels");
        }
    }

    @Override
    public void turnOn(boolean getIsOff){
        if(getIsOff){
            startEngine();
        }else {
            System.out.println("Engine is on.");
        }

    }
    private void startEngine(){
        System.out.println("Engine turning on");
    }

}
