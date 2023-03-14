public class Move implements IMoving {

    @Override
    public boolean canMoveTo(int destinationRow, int destinationColumn) {
        return false;
    }

    @Override
    public Message MessageIfMovingTo(int destinationRow, int destinationColumn) {
        return null;
    }

    @Override
    public boolean isDirectionOK(int destinationRow, int destinationColumn) {
        return false;
    }

    @Override
    public Message checkDirectionMessage(int destinationRow, int destinationColumn) {
        return null;
    }

    @Override
    public boolean hasBlankFreePathTo(int destinationRow, int destinationColumn) {
        return false;
    }

    @Override
    public Message checkMessageForBlankOnPathTo(int destinationRow, int destinationColumn) {
        return null;
    }

    @Override
    public void moveTo(int destinationRow, int destinationColumn) {

    }
}
