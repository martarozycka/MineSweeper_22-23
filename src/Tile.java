public class Tile {
    private TileStatus tileStatus;
    private boolean hasMine;
    private int neighbourMineCount;

    public Tile(){
        tileStatus = TileStatus.valueOf("covered");
        neighbourMineCount = 0;
    }

    public String returnValue() {
        String value = null;
        if (tileStatus.equals("covered")) {
            value = "□";
        }
        else if (tileStatus.equals("flagged")) {
            value = "\uD83C\uDFF3";
        }
        else if (tileStatus.equals("uncovered")) {
            if (hasMine) {
                value = "*";
            }
            else {
                value = String.valueOf(neighbourMineCount);
            }
        }
        return value;
    }

    public TileStatus getTileStatus() {
        return tileStatus;
    }

    public void setTileStatus(TileStatus tileStatus) {
        this.tileStatus = tileStatus;
    }

    public boolean getHasMine() {
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
