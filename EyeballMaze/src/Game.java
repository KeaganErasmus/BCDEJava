import java.util.ArrayList;
import java.util.Arrays;

public class Game implements ILevelHolder {
    public int levelWidth;
    private int levelHeight;
    private int levelCount;

    ArrayList<Level> level = new ArrayList<Level>();
    @Override
    public void addLevel(int height, int width) {
        Level levels = new Level();
        levelWidth = width;
        levelHeight = height;
        levelCount += 1;

        levels.allMyLevels.add(level);
    }

    @Override
    public int getLevelWidth() {
        return levelWidth;
    }

    @Override
    public int getLevelHeight() {
        return levelHeight;
    }

    @Override
    public void setLevel(int levelNumber) {
        // Throws an exception when you set a level that doesn't exist
        if(levelNumber > level.size()){
           throw new IllegalArgumentException();
        }
    }

    @Override
    public int getLevelCount() {
//        System.out.println(Arrays.toString(levels));
        return levelCount;
    }

    /*
    * GOAL
    */

}
