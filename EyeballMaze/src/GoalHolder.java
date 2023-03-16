import java.lang.reflect.Array;
import java.util.Arrays;

public class GoalHolder implements IGoalHolder{

    private int goalCount;
    private int goalRow;
    private int goalCol;
    private int[] numGoals;
    public boolean[] isGoal = new boolean[goalCount];
    @Override
    public void addGoal(int row, int column) {
        goalRow = row;
        goalCol = column;

        goalCount++;
//        Arrays.fill(numGoals, goalCount);
        Arrays.fill(isGoal, true);
    }

    @Override
    public int getGoalCount() {
        return goalCount;
    }

    @Override
    public boolean hasGoalAt(int targetRow, int targetColumn) {
        if(goalRow == targetRow || goalCol == targetColumn){
            return true;
        }
        return false;
    }

    @Override
    public int getCompletedGoalCount() {
        return 0;
    }
}
