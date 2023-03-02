public class Main {
    public static void main(String[] args) {
        IView view = new ConsoleView();
        ExerciseController exerciseController = new ExerciseController(view) {
            @Override
            protected void doStuff() {

            }
        };

        exerciseController.go();
    }
}