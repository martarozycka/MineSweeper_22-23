public class Board {
    private Tile[][] grid;
    private int nrOfBombs;
    private int height;
    private int length;
    private boolean clicked;

    public Board(int nrOfBombs,int height,int length){
        this.nrOfBombs=nrOfBombs;
        this.height=height;
        this.length=length;
        grid = new Tile[height][length];
        clicked = false;
    }

    public Tile[][] getGrid() {
        return grid;
    }

    public int getNrOfBombs() {
        return nrOfBombs;
    }

    public int getHeight() {
        return height;
    }

    public int getLength() {
        return length;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void reset(){
    }
    public void moveBomb(){

    }
    public void clickTile(Tile tile){

    }
    public void setFlag(Tile tile){

    }
    public void removeFlag(){

    }
    public void allocateBombs(){

    }

    public void populateBoard() {
        Tile tile = new Tile();
        for (int i=0; i<height;i++) {
            for (int j=0; j<length;j++) {
                grid[i][j] = tile;
            }
        }
    }
    //recursive function????
}
