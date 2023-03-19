import java.util.ArrayList;

public class Level {
    public int width;
    public int height;
    public ArrayList<Level> allMyLevels = new ArrayList<>();

    public Level(int width, int height){
        this.width = width;
        this.height = height;
    }
}
