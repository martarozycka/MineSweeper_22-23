import java.util.ArrayList;
import java.util.Random;

public class Board {
    private Tile[][] grid;
    private int nrOfBombs;
    private int nrOfRows;
    private int nrOfColumns;

    private int[] distanceX = {-1, 0, 1};
    private int[] distanceY = {-1, 0, 1};

    public Board(int nrOfBombs,int height,int length){
        this.nrOfBombs=nrOfBombs;
        this.nrOfRows =height;
        this.nrOfColumns =length;
        grid = new Tile[height][length];
        for (int i=0; i<height; i++){
            for (int j=0; j<length; j++){
                grid[i][j]=new Tile();
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


    public void reset(){
        for (int row = 0; row< nrOfRows; row++) {
            for (int column = 0; column < nrOfColumns; column++) {
                grid[row][column].setTileStatus("covered");
            }
        }
    }

    /* don't need this, just generate the board after first click
    public void moveBomb(int row, int column){
    }
     */

    public boolean clickTile(int row, int column){
        if (grid[row-1][column-1].getHasMine()) {
            System.out.println("You lost");
            return false;
        }
        //still implement
        else if (grid[row-1][column-1].getNeighbourMineCount() == 0) {
            grid[row - 1][column - 1].setTileStatus("uncovered");
            return true;}
        else {
            grid[row - 1][column - 1].setTileStatus("uncovered");
            return true;
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
        for (int i=0; i<nrOfBombs;i++) {
            Random rand = new Random();
            int row = rand.nextInt(nrOfRows);
            int column = rand.nextInt(nrOfColumns);

            while (tileNrs.contains(String.valueOf(row+column))) {
                row= rand.nextInt(nrOfRows);
                while (tileNrs.contains(String.valueOf(row+column))) {
                    column = rand.nextInt(nrOfColumns);
                }
            }
            tileNrs.add(String.valueOf(row + column));
            grid[row][column].setHasMine();
            //only to make them visible for now
            grid[row][column].setTileStatus("uncovered");
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
            }
        }
    }

    //for testing set all tiles uncovered
    public void setAllUncovered() {
        for (int row=0;row<nrOfRows;row++) {
            for (int column=0; column<nrOfColumns;column++) {
                grid[row][column].setTileStatus("uncovered");
            }
        }
    }
/*
    public void populateBoard() {
        Tile tile = new Tile();
        for (int i = 0; i< nrOfRows; i++) {
            for (int j = 0; j< nrOfColumns; j++) {
                grid[i][j+1] = tile;
            }
        }
    }

 */
    //recursive function????
}
