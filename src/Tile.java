public class Tile {
    private String tileStatus;
    private boolean hasMine;
    private int neighbourMineCount;
    private boolean hasMiniMine;

    public Tile(){
        tileStatus = "covered";
        neighbourMineCount = 0;
    }

// determines what shown after tile uncovered
    public String returnValue() {
        String value = "\uD83D\uDD33 ";
        if (tileStatus.equals("flagged")) {
            value = "\uD83D\uDEA9 ";
        }
        else if (tileStatus.equals("uncovered")) {
            if (hasMine) {
                value = "\uD83D\uDCA3 ";
            }
            else if (hasMiniMine) {
                value = "\uD83D\uDC94 ";
            }
            else if (neighbourMineCount==1) {
                value = "1️ ";
            }
            else if (neighbourMineCount==2) {
                value = "2️ ";
            }
            else if (neighbourMineCount==3) {
                value = "3️ ";
            }
            else if (neighbourMineCount==4) {
                value = "4️ ";
            }
            else if (neighbourMineCount==5) {
                value = "5️ ";
            }
            else if (neighbourMineCount==6) {
                value = "6️ ";
            }
            else if (neighbourMineCount==7) {
                value = "7️ ";
            }
            else if (neighbourMineCount==8) {
                value = "8️ ";
            }
            else if (neighbourMineCount==0) {
                value = "⬜ ";
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
    public void setDoesNotHaveMine() {
        this.hasMine = false;
    }

    public boolean getHasMiniMine() {
        return hasMiniMine;
    }

    public void setHasMiniMine() {
        this.hasMiniMine = true;
    }

    public void setDoesNotHaveMiniMine() {
        this.hasMiniMine = false;
    }

    public int getNeighbourMineCount() {
        return neighbourMineCount;
    }

    public void setNeighbourMineCount(int neighbourMineCount) {
        this.neighbourMineCount = neighbourMineCount;
    }
}
