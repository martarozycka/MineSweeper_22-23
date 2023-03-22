public class Tile {
    private String tileStatus;
    private boolean hasMine;
    private int neighbourMineCount;

    public Tile(){
        tileStatus = "covered";
        neighbourMineCount = 0;
    }

    public String returnValue() {
        String value = "\uD83D\uDD33 ";
        if (tileStatus.equals("flagged")) {
            value = "\uD83D\uDEA9 ";
        }
        else if (tileStatus.equals("uncovered")) {
            if (hasMine) {
                value = "\uD83D\uDCA3 ";
            }
            else {
                value = String.valueOf(neighbourMineCount + "  ");
            }
        }
        return value;
    }

    public String getTileStatus() {
        return tileStatus;
    }

    public void setTileStatus(String tileStatus) {
        this.tileStatus = tileStatus;
    }

    public boolean getHasMine() {
        return hasMine;
    }

    public void setHasMine() {
        this.hasMine = true;
    }

    public int getNeighbourMineCount() {
        return neighbourMineCount;
    }

    public void setNeighbourMineCount(int neighbourMineCount) {
        this.neighbourMineCount = neighbourMineCount;
    }
}
