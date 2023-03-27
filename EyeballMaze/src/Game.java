import java.util.ArrayList;

public class Game implements ILevelHolder, IGoalHolder,ISquareHolder, IEyeballHolder {
    protected int levelWidth;
    protected int levelHeight;
    protected int levelCount;

    protected Level currentLevel;
    public ArrayList<Level> allMyLevels = new ArrayList<>();

//    goal
    protected int goalCount;
    protected int goalRow;
    protected int goalCol;

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

    /*
    * Movement
    */

    public boolean validMovement(int row, int col){
        Color nextColor = getColorAt(row, col);
        Shape nextShape = getShapeAt(row, col);
        for (Square square: this.currentLevel.allMySquares) {
            return (square.color == nextColor || square.shape == nextShape);
        }
        return false;
    }

    public boolean legalMove(int row, int col){
        if (eyeball.direction == Direction.DOWN){
            return false;
        }
        return true;
    }

    public boolean canMoveTo(int row, int col){
        return validMovement(row, col);
    }

    public Message MessageIfMovingTo(int row, int col){
        if(validMovement(row, col)){
            return Message.OK;
        }
        return Message.DIFFERENT_SHAPE_OR_COLOR;
    }

    public Boolean isDirectionOK(int row, int col){
        if(legalMove(row, col)){
         return validMovement(row,col);
        }
        return false;
    }

    public Message checkDirectionMessage(int row, int col){
        if(validMovement(row, col)){
            switch (eyeball.direction){
                case UP, LEFT, RIGHT -> {
                    return Message.OK;
                }
                case DOWN -> {
                    return Message.BACKWARDS_MOVE;
                }
            }
        }
        return Message.MOVING_DIAGONALLY;
    }

    public boolean hasBlankFreePathTo(int row, int col){
        if (validMovement(row, col)){
            return theSquare.color == Color.BLANK;
        }
        return false;
    }

    public Message checkMessageForBlankOnPathTo(int row, int col){
        if(validMovement(row, col)){
            if(theSquare.color == Color.BLANK){
                return Message.MOVING_OVER_BLANK;
            }
        }
        return null;
    }

    public void moveTo(int row, int col){
        if(validMovement(row, col) && legalMove(row,col)){
            if (eyeball.getRow() > row){
                eyeball.direction = Direction.UP;
            }
            eyeball.row = row;
            eyeball.col = col;
        }
    }
}
