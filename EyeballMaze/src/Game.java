import java.util.ArrayList;

public class Game implements ILevelHolder, IGoalHolder,ISquareHolder {
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

        allMyLevels.add(new Level(levelWidth, levelHeight));
        levelCount = this.allMyLevels.size();
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
        goalRow = row;
        goalCol = column;

        Goal goal = new Goal(goalRow, goalCol);
        this.currentLevel.allMyGoals.add(goal);
        goalCount = this.currentLevel.allMyGoals.size();

        if(goalCol > this.currentLevel.width || goalRow > this.currentLevel.height){
            throw new IllegalArgumentException();
        }
    }

    @Override
    public int getGoalCount() {
        return goalCount;
    }

    @Override
    public boolean hasGoalAt(int targetRow, int targetColumn) {
        for (Goal goalEl : this.currentLevel.allMyGoals) {
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

    /*
     * Squares
     */
    public int squareRow;
    public int squareCol;
    public Color color;
    public Shape shape;
    public Square theSquare;

    @Override
    public void addSquare(Square square, int row, int column) {
        squareRow = row;
        squareCol = column;
        theSquare = square;

        theSquare.row = squareRow;
        theSquare.col = squareCol;


        this.currentLevel.allMySquares.add(theSquare);

        if(squareRow > levelHeight || squareCol > levelWidth){
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Color getColorAt(int row, int column) {
        for(Square squares : this.currentLevel.allMySquares){
            if(theSquare.row == row && theSquare.col == column){
                return color;
            }
        }
        return null;
    }

    @Override
    public Shape getShapeAt(int row, int column) {
        if(row == theSquare.row && column == theSquare.col){
            return shape;
        }
//        System.out.println(allMySquares);
        return null;
    }
}
