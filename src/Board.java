import java.util.ArrayList;
import java.util.Random;

public class Board {
    private Tile[][] grid;
    private int nrOfBombs;
    private int nrOfRows;
    private int nrOfColumns;
    private boolean clicked;

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
        clicked = false;
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

    public boolean isClicked() {
        return clicked;
    }

    public void reset(){
        for (int row = 0; row< nrOfRows; row++) {
            for (int column = 0; column <nrOfColumns; column++) {
                grid[row][column].setTileStatus("covered");
            }
        }
        allocateBombs();
    }
    public void moveBomb(){

    }
    public void clickTile(int row, int column){
        grid[row-1][column-1].setTileStatus("uncovered");

    }
    public void setFlag(int row, int column){
        grid[row-1][column-1].setTileStatus("flagged");
    }
    public void removeFlag(int row, int column){
        grid[row-1][column-1].setTileStatus("covered");

    }
    public void allocateBombs(){
        ArrayList<String> tileNrs = new ArrayList<String>();

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

    /*public void setNeighbouringMineCounter() {
        for (int i = 0; i<nrOfRows; i++) {
            for (int j = 0; i<nrOfColumns; j++) {
                int mineCounter = 0;
                if (grid[i+1][j+1])
            }
        }
    }
     */

    public void populateBoard() {
        Tile tile = new Tile();
        for (int i = 0; i< nrOfRows; i++) {
            for (int j = 0; j< nrOfColumns; j++) {
                grid[i][j+1] = tile;
            }
        }
    }
    //recursive function????
}
