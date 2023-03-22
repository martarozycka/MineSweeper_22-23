import java.sql.SQLOutput;

public class GameVisual {

    protected GameVisual(Board board) {

    }

    public void printGameBoard(Board board) {
        Tile[][] grid = board.getGrid();
        int verticalIndex = 1;
        for (int i=0; i<board.getHeight(); i++) {
            System.out.println(verticalIndex + "  ");
            verticalIndex++;
            for (int j=0; j< board.getLength();j++) {
                System.out.print(grid[i][j].returnValue());
            }
        }
        for (int l=1; l< board.getLength(); l++) {
            System.out.print(l + " ");
            l++;
        }
    }

    public static void main(String[] args) {
        Board minefield = new Board(10, 8, 8);
        GameVisual minesweeper = new GameVisual(minefield);
        minesweeper.printGameBoard(minefield);
    }
}
