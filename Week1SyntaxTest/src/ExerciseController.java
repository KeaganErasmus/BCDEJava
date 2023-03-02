public abstract class ExerciseController {
    protected IView myView;
    private String data;

    private int amount = 0;
    ExerciseController( IView theView ){
        this.myView = theView;
    }

    abstract protected void doStuff();

    public void checkNumber(int number) {
        amount = number;
        int count = Integer.parseInt(myView.get());
        for(int i = amount; amount < count; amount++){
            if(amount % 2 == 0){
                System.out.println(amount + " EVEN");
            }else {
                System.out.println(amount + " Odd");
            }
        }
    }
    public void go() {
        this.myView.start();
        this.doStuff();
        this.myView.say("Enter a number");
        this.checkNumber(amount);
        this.myView.stop();
    }

}
