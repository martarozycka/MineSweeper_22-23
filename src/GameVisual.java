import java.util.ArrayList;

public class GameVisual {

    protected GameVisual(Board board) {
    }

    //prints the board, before each row there's a row index and in the final row there are column indexes
    public void printGameBoard(Board board) {
        Tile[][] grid = board.getGrid();
        int verticalIndex = 1;
        for (int i = 0; i<board.getNrOfRows(); i++) {
            if (verticalIndex<10) {
                System.out.print(verticalIndex + "   ");
            }
            else {
                System.out.print(verticalIndex + "  ");
            }
            verticalIndex++;
            for (int j = 0; j< board.getNrOfColumns(); j++) {
                System.out.print(grid[i][j].returnValue());
            }
            System.out.println();
        }
        System.out.print("     ");
        // column indexes aligned with tiles
        for (int l = 1; l<= board.getNrOfColumns(); l++) {
            if (l<10) {
                System.out.print(l+"  ");
                if ((l>2 && l<7) || l==8) {
                    System.out.print("\u2009");
                }
            }
            else {
                System.out.print(l +" ");
                if (l==11 || (l>12 && l<20) || (l>20 && l<24) || (l>25 && l<30)) {
                    System.out.print("\u2009");
                }
            }
        }
    }

}
