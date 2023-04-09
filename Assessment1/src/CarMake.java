public class CarMake {
    Make make;

    public CarMake(Make make){
        this.make = make;
    }

    public void showCarModels() {
        switch (make) {
            case VOLKSWAGEN -> System.out.println("Golf");
            case MAZDA -> System.out.println("RX-7");
            case HYUNDIA -> System.out.println("Veloster N");
            case NISSAN -> System.out.println("270Z");
            default -> System.out.println("No car models");
        }
        //
    }
}
