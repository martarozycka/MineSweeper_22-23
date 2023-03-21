public class Tile {
    private TileStatus tileStatus;
    private boolean hasMine;
    private int neighbourMineCount;

    public Tile(){

        tileStatus = TileStatus.covered;
    }

    public String returnValue() {
        if (hasMine) {
            return "*";
        } else {
            return (String.valueOf(neighbourMineCount));
        }
    }

    public TileStatus getTileStatus() {
        return tileStatus;
    }

    public void setTileStatus(TileStatus tileStatus) {
        this.tileStatus = tileStatus;
    }

    public boolean isHasMine() {
        return hasMine;
    }

    public void setHasMine(boolean hasMine) {
        this.hasMine = hasMine;
    }

    public int getNeighbourMineCount() {
        return neighbourMineCount;
    }

    public void setNeighbourMineCount(int neighbourMineCount) {
        this.neighbourMineCount = neighbourMineCount;
    }
}
