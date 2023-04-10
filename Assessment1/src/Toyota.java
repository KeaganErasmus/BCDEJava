public non-sealed class Toyota extends Vehicle{

    private final String brand;
    public Toyota(String brand){
        this.brand = brand;
    }
    @Override
    public String getBrand(){
        return brand;
    }
}
