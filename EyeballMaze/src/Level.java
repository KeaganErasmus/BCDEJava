import java.util.ArrayList;

public class Level {
    public int width;
    public int height;
    public ArrayList<Square> allMySquares = new ArrayList<>();
    public ArrayList<Goal> allMyGoals = new ArrayList<>();


    public Level(int width, int height){
        this.width = width;
        this.height = height;
    }
}
