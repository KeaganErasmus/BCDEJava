import java.util.Arrays;

public class Game implements ILevelHolder{
    private int levelWidth;
    private int levelHeight;
    private int levelCount;
    public int[] level;


    @Override
    public void addLevel(int height, int width) {
        levelWidth = width;
        levelHeight = height;
        levelCount += 1;

        level = new int[this.levelCount];
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
        if(levelNumber > levelCount){
           throw new IllegalArgumentException();
        }
    }

    @Override
    public int getLevelCount() {
        System.out.println(Arrays.toString(level));
        return levelCount;
    }
}
