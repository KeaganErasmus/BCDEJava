import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class TestCompletingGoals {
    Game game;
    LevelDataHandler levelDataHandler;
    record SquareData(Color color, Shape shape, Position position) {};
    SquareData[] levelOneInitData = {
            new SquareData(Color.BLANK, Shape.BLANK, new Position(0, 0)),
            new SquareData(Color.BLANK, Shape.BLANK, new Position(0, 1)),
            new SquareData(Color.RED, Shape.FLOWER, new Position(0, 2)),
            new SquareData(Color.BLANK, Shape.BLANK, new Position(0, 3)),
            new SquareData(Color.BLUE, Shape.CROSS, new Position(1, 0)),
            new SquareData(Color.YELLOW, Shape.FLOWER, new Position(1, 1)),
            new SquareData(Color.YELLOW, Shape.DIAMOND, new Position(1, 2)),
            new SquareData(Color.GREEN, Shape.CROSS, new Position(1, 3)),
            new SquareData(Color.GREEN, Shape.FLOWER, new Position(2, 0)),
            new SquareData(Color.RED, Shape.STAR, new Position(2, 1)),
            new SquareData(Color.GREEN, Shape.STAR, new Position(2, 2)),
            new SquareData(Color.YELLOW, Shape.DIAMOND, new Position(2, 3)),
            new SquareData(Color.RED, Shape.FLOWER, new Position(3, 0)),
            new SquareData(Color.BLUE, Shape.FLOWER, new Position(3, 1)),
            new SquareData(Color.RED, Shape.STAR, new Position(3, 2)),
            new SquareData(Color.GREEN, Shape.FLOWER, new Position(3, 3)),
            new SquareData(Color.BLUE, Shape.STAR, new Position(4, 0)),
            new SquareData(Color.RED, Shape.DIAMOND, new Position(4, 1)),
            new SquareData(Color.BLUE, Shape.FLOWER, new Position(4, 2)),
            new SquareData(Color.BLUE, Shape.DIAMOND, new Position(4, 3)),
            new SquareData(Color.BLANK, Shape.BLANK, new Position(5, 0)),
            new SquareData(Color.BLUE, Shape.DIAMOND, new Position(5, 1)),
            new SquareData(Color.BLANK, Shape.BLANK, new Position(5, 2)),
            new SquareData(Color.BLANK, Shape.BLANK, new Position(5, 3)) };
    Position[] levelOneSolution = {
            new Position(3, 1), new Position(3, 3), new Position(1, 3), new Position(1, 0),
            new Position(4, 0), new Position(4, 2), new Position(0, 2) };
    private class LevelDataHandler {
        Game game;
        public LevelDataHandler(Game game) {
            this.game = game;
        }
        public void createLevel(int height, int width) {
            this.game.addLevel(height, width);
        }
        public void setUpLevel(SquareData[] levelInitData) {
            for (SquareData s : levelInitData) {
                Square square;
                if ((s.color == Color.BLANK) && (s.shape == Shape.BLANK)) {
                    square = new BlankSquare();
                } else {
                    square = new PlayableSquare(s.color, s.shape);
                }
                this.game.addSquare(square, s.position.getRow(), s.position.getColumn());
            }
        }
    }