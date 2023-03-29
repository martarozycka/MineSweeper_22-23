import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.round;

public class Board {
    private Tile[][] grid;
    private int nrOfBombs;
    private int nrOfMiniBombs;
    private int nrOfRows;
    private int nrOfColumns;

    private int[] distanceX = {-1, 0, 1};
    private int[] distanceY = {-1, 0, 1};

    public Board(int nrOfBombs, int height, int length){
        this.nrOfBombs=nrOfBombs;
        this.nrOfRows =height;
        this.nrOfColumns =length;
        this.nrOfMiniBombs = nrOfBombs/4;
        //check if rounds up

        grid = new Tile[height][length];
        for (int i=0; i<height; i++){
            for (int j=0; j<length; j++){
                grid[i][j]=new Tile();
            }
        }
    }

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

    public int getNrOfBombs() {
        return nrOfBombs;
    }

    public int getNrOfRows() {
        return nrOfRows;
    }

    public int getNrOfColumns() {
        return nrOfColumns;
    }

    public void reset(int selectedRow, int selectedColumn) {
        for (int row = 0; row< nrOfRows; row++) {
            for (int column = 0; column < nrOfColumns; column++) {
                grid[row][column].setTileStatus("covered");
                grid[row][column].setDoesNotHaveMine();
                grid[row][column].setDoesNotHaveMiniMine();
            }
        }
        allocateBombs(selectedRow, selectedColumn);
        setNeighbouringMineCounter();
    }

    //returns false when clicked on a mine, else returns true
    public boolean clickTile(int row, int column){
        //mine tile
        if (grid[row-1][column-1].getHasMine()) {
            grid[row - 1][column - 1].setTileStatus("uncovered");
            return false;
        }
        else if (grid[row-1][column-1].getHasMiniMine()) {
            grid[row - 1][column - 1].setTileStatus("uncovered");
            return true;
        }
        //0 tile
        //if we get into next 0, call recursiveOpen
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
//seems like doesn't come back to the loop after getting to a numbered tile in the recursion
    public void recursiveOpen(int row, int column) {
        for (int x: distanceX) {
                for (int y : distanceY) {
                    if ((row+y>=0 && row+y<nrOfColumns) && (column+x>=0 && column+x<nrOfColumns)) {
                        if (x!=0 || y!=0) {
                            if (!grid[row+y][column+x].getHasMine()) {
                                if (!grid[row+y][column+x].getHasMiniMine()) {
                                    if (!grid[row+y][column+x].getTileStatus().equals("uncovered") && !grid[row+y][column+x].getTileStatus().equals("flagged")) {
                                        if (grid[row + y][column + x].getNeighbourMineCount() != 0) {
                                            grid[row + y][column + x].setTileStatus("uncovered");
                                            //System.out.println(row+y + " " + column + x + ", value: " + grid[row + y][column + x].getNeighbourMineCount());
                                        } else if (grid[row + y][column + x].getNeighbourMineCount() == 0) {
                                            grid[row + y][column + x].setTileStatus("uncovered");
                                            //System.out.println(row+y + " " + column+x + ", value: " + grid[row + y][column + x].getNeighbourMineCount());
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

    //add to the arraylist the coordinates of clicked tile
    public void allocateBombs(int selectedRow, int selectedColumn){

        ArrayList<String> tileNrs = new ArrayList<String>();
        tileNrs.add(String.valueOf(selectedRow+selectedColumn));
        Random rand = new Random();
        for (int i=0; i<nrOfBombs;i++) {
            int row = rand.nextInt(nrOfRows);
            int column = rand.nextInt(nrOfColumns);

            while (tileNrs.contains(String.valueOf(row+column))) {
                row = rand.nextInt(nrOfRows);
                while (tileNrs.contains(String.valueOf(row+column))) {
                    column = rand.nextInt(nrOfColumns);
                }
            }

            tileNrs.add(String.valueOf(row + column));
            grid[row][column].setHasMine();
            grid[row][column].setTileStatus("covered");
            //only to make them visible for now
        }
        //allocate mini bombs
        for (int j=0; j<nrOfMiniBombs;j++) {
            //Random rand = new Random();
            int row1 = rand.nextInt(nrOfRows);
            int column1 = rand.nextInt(nrOfColumns);

            while (tileNrs.contains(String.valueOf(row1+column1))) {
                row1= rand.nextInt(nrOfRows);
                while (tileNrs.contains(String.valueOf(row1+column1))) {
                    column1 = rand.nextInt(nrOfColumns);
                }
            }
            tileNrs.add(String.valueOf(row1 + column1));
            grid[row1][column1].setHasMiniMine();
            grid[row1][column1].setTileStatus("covered");
            //only to make them visible for now
        }
    }

// works :)))
    public void setNeighbouringMineCounter() {
        for (int row = 0; row<nrOfRows; row++) {
            for (int column = 0; column<nrOfColumns; column++) {
                int mineCounter = 0;
                for (int y : distanceY) {
                    for (int x : distanceX) {
                        if (row+y>=0 & row+y<nrOfColumns & column+x>=0 & column+x<nrOfRows) {
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

    //for testing set all tiles uncovered
    public void setAllCovered() {
        for (int row=0;row<nrOfRows;row++) {
            for (int column=0; column<nrOfColumns;column++) {
                 grid[row][column].setTileStatus("covered");

    }}}
    public void setAllBombsUncovered() {
        for (int row=0;row<nrOfRows;row++) {
            for (int column=0; column<nrOfColumns;column++) {
                if( grid[row][column].getHasMine()){
                    grid[row][column].setTileStatus("uncovered");
                }
            }
        }}



    public boolean checkForFlags(){
        for (int row=0;row<nrOfRows;row++) {
            for (int column=0; column<nrOfColumns;column++) {
                if( grid[row][column].getTileStatus().equals("flagged")){
                    return true;
                }
            }}
        return false;
    }

    public int  countingOpenCells(){
        int count=0;
        for (int row=0;row<nrOfRows;row++) {
            for (int column=0; column<nrOfColumns;column++) {
                if( grid[row][column].getTileStatus().equals("uncovered")){
                    count=count+1;
                }
            }}
        return count;
    }

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