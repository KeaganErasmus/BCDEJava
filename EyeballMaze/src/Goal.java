import java.util.ArrayList;

public class Goal {
    public int row;
    public int col;
    private boolean isComplete;
    public ArrayList<Goal> allMyGoals = new ArrayList<Goal>();

    public Goal(int goalRow, int goalCol) {
        this.row = goalRow;
        this.col = goalCol;
    }
}
