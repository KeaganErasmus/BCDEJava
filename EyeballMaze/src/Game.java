import java.util.ArrayList;

public class Game implements ILevelHolder, IGoalHolder,ISquareHolder, IEyeballHolder {

//    Level
    protected int levelWidth;
    protected int levelHeight;
    protected int levelCount;

    protected Level currentLevel;
    public ArrayList<Level> allMyLevels = new ArrayList<>();

//    goal
    protected int goalCount;
    protected int goalRow;
    protected int goalCol;
    protected int numGoalsComplete = 0;
    protected boolean goalComplete;

//    Square
    protected Square theSquare;


// Eyeball
    protected int eyeballRow;
    protected int eyeballCol;
    protected Direction eyeballDirection;
    protected  Eyeball eyeball;


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

        if(goalCol > this.currentLevel.width || goalRow > this.currentLevel.height || goalCol < 0 || goalRow < 0){
            throw new IllegalArgumentException();
        }
    }

    @Override
    public int getGoalCount() {
        return this.currentLevel.allMyGoals.size();
    }

    @Override
    public boolean hasGoalAt(int targetRow, int targetColumn) {
        for (Goal goals : this.currentLevel.allMyGoals) {
            if (targetRow == goals.row && targetColumn == goals.col) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getCompletedGoalCount() {
        return numGoalsComplete;
    }

    /*
     * Squares
     */
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
    @Override
    public void addEyeball(int row, int column, Direction direction) {
        this.eyeballRow = row;
        this.eyeballCol = column;
        this.eyeballDirection = direction;

        this.eyeball = new Eyeball(eyeballRow,eyeballCol,eyeballDirection);

        this.currentLevel.allMyEyes.add(eyeball);

        if(eyeball.row > this.currentLevel.height || eyeball.col > this.currentLevel.width || eyeball.row < 0 || eyeball.col < 0){
            throw new IllegalArgumentException("Eyeball placed outside level scope");
        }
    }

    @Override
    public int getEyeballRow() {
        return eyeball.row;
    }

    @Override
    public int getEyeballColumn() {
        return eyeball.col;
    }

    @Override
    public Direction getEyeballDirection() {
        return this.eyeball.direction;
    }

    public Color getEyeballColor(Eyeball eyeball){
        return getColorAt(eyeball.row, eyeball.col);
    }
    public Shape getEyeballShape(Eyeball eyeball){
        return getShapeAt(eyeball.row, eyeball.col);
    }

    /*
    * Movement
    */
    public boolean validMovement(int row, int col){
        Color nextColor = getColorAt(row, col);
        Shape nextShape = getShapeAt(row, col);

        Color eyeballColor = getEyeballColor(eyeball);
        Shape eyeBallShape  = getEyeballShape(eyeball);

        return (eyeballColor == nextColor || eyeBallShape == nextShape);
    }

    public Direction destDirection(int row, int col){
        if(eyeball.row == row){
            if(eyeball.col < col){
                return Direction.RIGHT;
            } else if (eyeball.col > col) {
                return Direction.LEFT;
            }
        }
        if(eyeball.col == col){
            if(eyeball.row < row){
                return Direction.DOWN;
            } else if (eyeball.row > row) {
                return Direction.UP;
            }
        }
        return null;
    }
    public Message legalMove(int row, int col){
        Direction destDirection = destDirection(row, col);
        switch (destDirection){
            case UP -> {
                if(eyeball.direction == Direction.DOWN){
                    return Message.BACKWARDS_MOVE;
                }
            }
            case DOWN -> {
                if(eyeball.direction == Direction.UP){
                    return Message.BACKWARDS_MOVE;
                }
            }
            case LEFT -> {
                if(eyeball.direction == Direction.RIGHT){
                    return Message.BACKWARDS_MOVE;
                }
            }
            case RIGHT -> {
                if(eyeball.direction == Direction.LEFT){
                    return Message.BACKWARDS_MOVE;
                }
            }
            case null -> {
                    return Message.MOVING_DIAGONALLY;
            }
        }
        return Message.OK;
    }

    public boolean canMoveTo(int row, int col){
        return isDirectionOK(row, col) && validMovement(row, col);
    }

    public Message MessageIfMovingTo(int row, int col){
        if(validMovement(row, col)){
            return Message.OK;
        }
        return Message.DIFFERENT_SHAPE_OR_COLOR;
    }

    public Boolean isDirectionOK(int row, int col){
        return legalMove(row, col) == Message.OK;
    }

    public Message checkDirectionMessage(int row, int col){
        return legalMove(row, col);
    }

    public boolean hasBlankFreePathTo(int row, int col){
        if (validMovement(row, col)){
            return theSquare.color == Color.BLANK;
        }
        return false;
    }
    public Message checkMessageForBlankOnPathTo(int row, int col){
        if(validMovement(row, col)){
            if(theSquare.color != Color.BLANK){
                return Message.MOVING_OVER_BLANK;
            }
        }
        return Message.OK;
    }
    public void moveTo(int row, int col){
        int prevEyeballRow = eyeball.row;
        int prevEyeballCol = eyeball.col;
        if(canMoveTo(row, col)){
            eyeball.direction = destDirection(row, col);
            eyeball.row = row;
            eyeball.col = col;
            this.checkIfGoalComplete(row, col);
        }

        if(this.goalComplete){
            for(Square square: this.currentLevel.allMySquares){
                if(square.row == prevEyeballRow && square.col == prevEyeballCol){
                    square.shape = Shape.BLANK;
                    square.color = Color.BLANK;
                }
            }
        }
    }

    public void checkIfGoalComplete(int row, int col){
        for (Goal goal: this.currentLevel.allMyGoals) {
            if(goal.row == row && goal.col == col){
                this.completedGoal();
                this.currentLevel.allMyGoals.remove(goal);
                return;
            }
        }
    }

    public void completedGoal(){
        this.numGoalsComplete++;
        this.goalComplete = true;
    }
}
