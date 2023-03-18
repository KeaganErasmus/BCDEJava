import java.util.ArrayList;

public class Game implements ILevelHolder, IGoalHolder {
    public int levelWidth;
    private int levelHeight;
    private int levelCount;
    public ArrayList<Level> allMyLevels = new ArrayList<Level>();

//    goal
    private int goalCount;
    private int goalRow;
    private int goalCol;

    public ArrayList<Goal> allMyGoals = new ArrayList<>();

    @Override
    public void addLevel(int height, int width) {
        Level level = new Level();
        levelWidth = width;
        levelHeight = height;
        levelCount += 1;

        allMyLevels.add(level);
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
        if(levelNumber > allMyLevels.size()){
           throw new IllegalArgumentException();
        }
    }

    @Override
    public int getLevelCount() {
        return levelCount;
    }

    /*
     * GOAL
     */

    @Override
    public void addGoal(int row, int column) {
        Goal goal = new Goal();
        goalRow = row;
        goalCol = column;

        this.allMyGoals.add(goal);
        goalCount++;

        if(goalCol > levelWidth || goalRow > levelHeight){
            throw new IllegalArgumentException();
        }
    }

    @Override
    public int getGoalCount() {
        return goalCount;
    }

    @Override
    public boolean hasGoalAt(int targetRow, int targetColumn) {
        if(targetRow == goalRow && targetColumn == goalCol){
            return true;
        }
        return false;
    }

    @Override
    public int getCompletedGoalCount() {
        return 0;
    }
}
