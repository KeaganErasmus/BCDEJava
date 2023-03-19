import java.util.ArrayList;

public class Game implements ILevelHolder, IGoalHolder {
    public int levelWidth;
    private int levelHeight;
    private int levelCount;
    public ArrayList<Level> allMyLevels = new ArrayList<>();

//    goal
    private int goalCount;
    private int goalRow;
    private int goalCol;

    boolean[] hasGoals;

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
        goalRow = row;
        goalCol = column;

        Goal goal = new Goal(goalRow, goalCol);
        this.allMyGoals.add(new Goal(goalRow, goalCol));
        goalCount = this.allMyGoals.size();

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
        for (Goal goalEl : allMyGoals) {
            if (targetRow == goalEl.row && targetColumn == goalEl.col) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getCompletedGoalCount() {
        return 0;
    }
}
