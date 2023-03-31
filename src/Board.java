import java.util.Random;


public class Board {
    private Tile[][] grid;
    private int nrOfBombs;
    private int nrOfMiniBombs;
    private int nrOfRows;
    private int nrOfColumns;

    //arrays to check the 8 neighbouring tiles
    private int[] distanceX = {-1, 0, 1};
    private int[] distanceY = {-1, 0, 1};

    //constructor with customize option
    public Board(int nrOfBombs, int height, int length){
        this.nrOfBombs=nrOfBombs;
        this.nrOfRows =height;
        this.nrOfColumns =length;
        this.nrOfMiniBombs = nrOfBombs/4;

        grid = new Tile[height][length];
        for (int i=0; i<height; i++){
            for (int j=0; j<length; j++){
                grid[i][j]=new Tile();
            }
        }
    }

    //constructor with level
    public Board(String level) {
        if (level.equals("medium")) {
            nrOfBombs = 40;
            nrOfMiniBombs = 10;
            nrOfColumns = 16;
            nrOfRows = 16;
            grid = new Tile[16][16];
            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < 16; j++) {
                    grid[i][j] = new Tile();
                }
            }
        }
        else if (level.equals("hard")) {
            nrOfBombs = 99;
            nrOfMiniBombs = 25;
            nrOfColumns = 30;
            nrOfRows = 16;
            grid = new Tile[16][30];
            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < 30; j++) {
                    grid[i][j] = new Tile();
                }
            }
        }
        else {
            nrOfBombs = 10;
            nrOfMiniBombs = 3;
            nrOfColumns = 8;
            nrOfRows = 8;
            grid = new Tile[8][8];
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    grid[i][j] = new Tile();
                }
            }
        }
    }

    public Tile[][] getGrid() {
        return grid;
    }

    public int getNrOfRows() {
        return nrOfRows;
    }

    public int getNrOfColumns() {
        return nrOfColumns;
    }

    //reset covers all tiles, clears (mini)mine tiles, allocates (mini)mines again, sets the number tiles, clicks the tile according to user input
    public void reset(int selectedRow, int selectedColumn) {
        for (int row = 0; row< nrOfRows; row++) {
            for (int column = 0; column < nrOfColumns; column++) {
                grid[row][column].setTileStatus("covered");
                grid[row][column].setDoesNotHaveMine();
                grid[row][column].setDoesNotHaveMiniMine();
            }
        }
        allocateMiniBombs();
        allocateBombs(selectedRow, selectedColumn);
        setNeighbouringMineCounter();
        clickTile(selectedRow,selectedColumn);

    }

    //returns false when clicked on a mine, else returns true
    //uncovers clicked tile
    //if tile is empty, trigger the recursive function
    public boolean clickTile(int row, int column){
        //mine tile
        if (grid[row-1][column-1].getHasMine()) {
            grid[row - 1][column - 1].setTileStatus("uncovered");
            return false;
        }
        //mini mine tile
        else if (grid[row-1][column-1].getHasMiniMine()) {
            grid[row - 1][column - 1].setTileStatus("uncovered");
            return true;
        }
        //0 tile
        //call recursiveOpen
        else if (grid[row-1][column-1].getNeighbourMineCount() == 0) {
            grid[row - 1][column - 1].setTileStatus("uncovered");
            recursiveOpen(row-1, column-1);
            return true;}
        //number tile
        else {
            grid[row - 1][column - 1].setTileStatus("uncovered");
            return true;
        }
    }

    //
    public void recursiveOpen(int row, int column) {
        for (int x: distanceX) {
                for (int y : distanceY) {
                    //check if we are inside the board
                    if ((row+y>=0 && row+y<nrOfColumns) && (column+x>=0 && column+x<nrOfColumns)) {
                        //check if we are not working with the clicked tile, but its neighbours
                        if (x!=0 || y!=0) {
                            //check that not a (mini)mine
                            if (!grid[row+y][column+x].getHasMine()) {
                                if (!grid[row+y][column+x].getHasMiniMine()) {
                                    if (!grid[row+y][column+x].getTileStatus().equals("uncovered") && !grid[row+y][column+x].getTileStatus().equals("flagged")) {
                                        //if numbered tile, uncovers it
                                        if (grid[row + y][column + x].getNeighbourMineCount() != 0) {
                                            grid[row + y][column + x].setTileStatus("uncovered");
                                        }
                                        // if empty tile, triggers the recursive open again
                                        else if (grid[row + y][column + x].getNeighbourMineCount() == 0) {
                                            grid[row + y][column + x].setTileStatus("uncovered");
                                            recursiveOpen(row+y,column+x);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
    }

    public void setFlag(int row, int column){
        grid[row-1][column-1].setTileStatus("flagged");
    }
    public void removeFlag(int row, int column){
        grid[row-1][column-1].setTileStatus("covered");
    }

    //allocates the mines, if tile already has (mini)mine i-counter decreased and sets mine tile to covered
    public void allocateBombs(int selectedRow, int selectedColumn){
        Random rand = new Random();
        for (int i=0; i<nrOfBombs;i++) {
            int row = rand.nextInt(nrOfRows);
            int column = rand.nextInt(nrOfColumns);
            if (grid[row][column].getHasMine() || grid[row][column].getHasMiniMine()) {
                i--;
            }
            else if (row==selectedRow-1 && column==selectedColumn-1) {
                i--;
            }
            else {
                grid[row][column].setHasMine();
                grid[row][column].setTileStatus("covered");
            }
        }
    }

    //allocates the mini mines, if tile already has mini mine i-counter decreased and sets mini mine tile to covered
    public void allocateMiniBombs(){
        Random rand = new Random();
        for (int i=0; i<nrOfMiniBombs;i++) {
            int row = rand.nextInt(nrOfRows);
            int column = rand.nextInt(nrOfColumns);
            if (grid[row][column].getHasMiniMine()) {
                i--;
            }
            else {
                grid[row][column].setHasMiniMine();
                grid[row][column].setTileStatus("covered");
            }
        }
    }

    //sets how many mines are in the neighbourhood of the tile and covers the tile
    public void setNeighbouringMineCounter() {
        for (int row = 0; row<nrOfRows; row++) {
            for (int column = 0; column<nrOfColumns; column++) {
                int mineCounter = 0;
                for (int y : distanceY) {
                    for (int x : distanceX) {
                        if (row+y>=0 && row+y<nrOfColumns && column+x>=0 && column+x<nrOfRows) {
                            if  (x!=0 || y!=0) {
                                if (grid[row+y][column+x].getHasMine()) {
                                    mineCounter++;
                                }
                            }
                        }
                    }
                }
                grid[row][column].setNeighbourMineCount(mineCounter);
                grid[row][column].setTileStatus("covered");
            }
        }
    }

    //covers all
    public void setAllCovered() {
        for (int row=0;row<nrOfRows;row++) {
            for (int column=0; column<nrOfColumns;column++) {
                 grid[row][column].setTileStatus("covered");

            }
        }
    }

    //uncovers all
    public void setAllBombsUncovered() {
        for (int row=0;row<nrOfRows;row++) {
            for (int column=0; column<nrOfColumns;column++) {
                if( grid[row][column].getHasMine()){
                    grid[row][column].setTileStatus("uncovered");
                }
            }
        }
    }

    //checks if any flags set
    public boolean checkForFlags(){
        for (int row=0;row<nrOfRows;row++) {
            for (int column=0; column<nrOfColumns;column++) {
                if( grid[row][column].getTileStatus().equals("flagged")){
                    return true;
                }
            }
        }
        return false;
    }

    //counts how many tiles were uncovered
    public int  countingOpenCells(){
        int count=0;
        for (int row=0;row<nrOfRows;row++) {
            for (int column=0; column<nrOfColumns;column++) {
                if( grid[row][column].getTileStatus().equals("uncovered")){
                    count=count+1;
                }
            }
        }
        return count;
    }

    //
    public boolean checkBoard(){
        for (int row=0;row<nrOfRows;row++) {
            for (int column=0; column<nrOfColumns;column++) {
             if (this.countingOpenCells()==(nrOfColumns*nrOfRows)-nrOfBombs){
                 return true;
                    }
                }
            }
        return false;
    }

    //checks how many mini mines were uncovered to calculate the score
    public int checkedMiniMine(){
        int count=0;
        for (int row=0;row<nrOfRows;row++) {
            for (int column = 0; column < nrOfColumns; column++) {
                if (grid[row][column].getHasMiniMine()) {
                    if (grid[row][column].getTileStatus().equals("uncovered")) {
                        count = count + 1;
                    }
                }
            }
        }
        return count;
    }


}