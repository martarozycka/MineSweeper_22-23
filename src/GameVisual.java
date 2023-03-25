public class GameVisual {

    protected GameVisual(Board board) {

    }

    public void printGameBoard(Board board) {
        Tile[][] grid = board.getGrid();
        int verticalIndex = 1;
        for (int i = 0; i<board.getNrOfRows(); i++) {
            System.out.print(verticalIndex + "  ");
            verticalIndex++;
            for (int j = 0; j< board.getNrOfColumns(); j++) {
                System.out.print(grid[i][j].returnValue());
            }
            System.out.println();
        }
        System.out.print("    ");
        for (int l = 1; l<= board.getNrOfColumns(); l++) {
            System.out.print(l + "  ");
            ;
        }
    }

    public static void main(String[] args) {
        Board minefield = new Board("easy");
       // System.out.println(minefield.getGrid());
        GameVisual minesweeper = new GameVisual(minefield);
        minefield.clickTile(6,6);
        minefield.allocateBombs(6,6);
        minefield.setNeighbouringMineCounter();
        minesweeper.printGameBoard(minefield);
        System.out.println("");
        System.out.println("");
        minefield.clickTile(2,6);
        minesweeper.printGameBoard(minefield);
        System.out.println();
        System.out.println();
        minefield.clickTile(3,6);
        minesweeper.printGameBoard(minefield);
        System.out.println();
        System.out.println();
        minefield.clickTile(4,6);
        minesweeper.printGameBoard(minefield);
        System.out.println();
        System.out.println();
        minefield.clickTile(5,6);
        minesweeper.printGameBoard(minefield);
        System.out.println();
        System.out.println();
        minefield.clickTile(7,6);
        minesweeper.printGameBoard(minefield);



    }
}
