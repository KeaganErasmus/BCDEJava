import java.util.ArrayList;

public class Game implements ILevelHolder, IGoalHolder,ISquareHolder {
    public int levelWidth;
    private int levelHeight;
    private int levelCount;
    public ArrayList<Level> allMyLevels = new ArrayList<>();

//    goal
    private int goalCount;
    public int goalRow;
    public int goalCol;
    public ArrayList<Goal> allMyGoals = new ArrayList<>();

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

    /*
     * Squares
     */
    public int squareRow;
    public int squareCol;
    public Color color;
    public Shape shape;
    public Square square;

    public ArrayList<Square> allMySquares = new ArrayList<>();

    @Override
    public void addSquare(Square square, int row, int column) {
        squareRow = row;
        squareCol = column;
        shape = getShapeAt(squareRow, squareCol);

        BlankSquare();

        this.allMySquares.add(new Square(squareRow, squareCol, color, shape));
    }

    @Override
    public Color getColorAt(int row, int column) {
        return null;
    }

    @Override
    public Shape getShapeAt(int row, int column) {
        if(row == squareRow && column == squareCol){
            return shape;
        }
        return null;
    }

    public Shape BlankSquare(){
        return Shape.BLANK;
    }
}
