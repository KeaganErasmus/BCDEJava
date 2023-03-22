import java.util.ArrayList;

public class Game implements ILevelHolder, IGoalHolder,ISquareHolder, IEyeballHolder {
    public int levelWidth;
    private int levelHeight;
    private int levelCount;

    public Level currentLevel;
    public ArrayList<Level> allMyLevels = new ArrayList<>();

//    goal
    private int goalCount;
    public int goalRow;
    public int goalCol;

    @Override
    public void addLevel(int height, int width) {
        levelWidth = width;
        levelHeight = height;

        this.allMyLevels.add(new Level(levelWidth, levelHeight));
        this.levelCount++;
        this.setLevel(levelCount - 1);
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
        if(levelNumber > levelCount){
           throw new IllegalArgumentException();
        }
        else {
            currentLevel = this.allMyLevels.get(levelNumber);
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
        this.goalRow = row;
        this.goalCol = column;

        currentLevel.allMyGoals.add(new Goal(goalRow, goalCol));
        goalCount = this.currentLevel.allMyGoals.size();

        if(goalCol > this.currentLevel.width || goalRow > this.currentLevel.height || goalCol < 0 || goalRow < 0){
            throw new IllegalArgumentException();
        }
    }

    @Override
    public int getGoalCount() {
        return goalCount;
    }

    @Override
    public boolean hasGoalAt(int targetRow, int targetColumn) {
        for (Goal goals : this.currentLevel.allMyGoals) {
            if (targetRow == goals.row && targetColumn == goals.col) {
                System.out.println(this.currentLevel.allMyGoals);
                return true;
            }
        }
        return false;
    }

    @Override
    public int getCompletedGoalCount() {
        return 0;
    }

    /*
     * Squares
     */
    public Square theSquare;

    @Override
    public void addSquare(Square square, int row, int column) {
        theSquare = square;

        theSquare.row = row;
        theSquare.col = column;

        this.currentLevel.allMySquares.add(theSquare);

        if(row > levelHeight || column > levelWidth || row < 0 || column < 0){
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Color getColorAt(int row, int column) {
        for(Square squares : this.currentLevel.allMySquares){
            if(row == squares.getRow() && column == squares.getCol()){
                return squares.color;
            }
        }
        return null;
    }

    @Override
    public Shape getShapeAt(int row, int column) {
        for (Square squares : this.currentLevel.allMySquares){
            if(row == squares.getRow() && column == squares.getCol()){
                return squares.shape;
            }
        }
        return null;
    }

    /*
    *  Eyeball
    */
    public int eyeballRow;
    public int eyeballCol;

    @Override
    public void addEyeball(int row, int column, Direction direction) {
        eyeballRow = row;
        eyeballCol = column;
    }

    @Override
    public int getEyeballRow() {
        return eyeballRow;
    }

    @Override
    public int getEyeballColumn() {
        return eyeballCol;
    }

    @Override
    public Direction getEyeballDirection() {
        return null;
    }
}
