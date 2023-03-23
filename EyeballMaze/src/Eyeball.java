public class Eyeball {
    public int row;
    public int col;

    public Color color;
    public Shape shape;
    public Direction direction;

    public Eyeball(int row, int col, Direction direction){
        this.row = row;
        this.col = col;
        this.direction = direction;
    }

    public Color getColor(){
        return this.color;
    }
    public Shape getShape(){
        return this.shape;
    }

}
