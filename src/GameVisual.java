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
        Board minefield = new Board(10, 8, 8);
        GameVisual minesweeper = new GameVisual(minefield);
        minefield.allocateBombs();
        minefield.setFlag(5,5);
        minefield.setFlag(6,6);
        minefield.removeFlag(5,5);
        minefield.clickTile(6,6);
        minefield.clickTile(1,6);
        minefield.clickTile(2,6);
        minefield.clickTile(3,6);
        minefield.clickTile(5,6);
        minefield.clickTile(4,6);
        minefield.clickTile(7,6);
        minefield.reset();


        minesweeper.printGameBoard(minefield);

    }
}
