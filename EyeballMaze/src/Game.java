public class Game implements ILevelHolder{
    private int levelWidth;
    private int levelHeight;
    private int levelCount;
    public int[][] newLevel;

    @Override
    public void addLevel(int height, int width) {
        levelWidth = width;
        levelHeight = height;
        levelCount += 1;

        for(int row = 0; row < levelHeight; row++){
            for (int col = 0; col < levelWidth; col++){
                newLevel[row][col] = row + col * width;
            }
        }
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
    }

    @Override
    public int getLevelCount() {
        return levelCount;
    }
}
