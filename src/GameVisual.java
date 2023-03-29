public class GameVisual {

    protected GameVisual(Board board) {

    }

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
        for (int l = 1; l<= board.getNrOfColumns(); l++) {
            if (l<10) {
                System.out.print(l+"  ");
                if (l==3) {
                    System.out.print("\u2009");
                }
                if (l==4) {
                    System.out.print("\u2009");
                }
                if (l==5) {
                    System.out.print("\u2009");
                }
                if (l==6) {
                    System.out.print("\u2009");
                }
                if (l==8) {
                    System.out.print("\u2009");
                }
            }
            else {
                System.out.print(l +" ");
                if (l==11) {
                    System.out.print("\u2009");
                }
                if (l==13) {
                    System.out.print("\u2009");
                }
                if (l==14) {
                    System.out.print("\u2009");
                }
                if (l==15) {
                    System.out.print("\u2009");
                }
                if (l==16) {
                    System.out.print("\u2009");
                }
                if (l==17) {
                    System.out.print("\u2009");
                }
                if (l==18) {
                    System.out.print("\u2009");
                }
                if (l==19) {
                    System.out.print("\u2009");
                }
                if (l==21) {
                    System.out.print("\u2009");
                }
                if (l==22) {
                    System.out.print("\u2009");
                }
                if (l==23) {
                    System.out.print("\u2009");
                }
                if (l==25) {
                    System.out.print("\u2009");
                }
                if (l==26) {
                    System.out.print("\u2009");
                }
                if (l==27) {
                    System.out.print("\u2009");
                }
                if (l==28) {
                    System.out.print("\u2009");
                }
                if (l==29) {
                    System.out.print("\u2009");
                }
            }
        }
    }

//    public static void main(String[] args) {
//        Board minefield = new Board("easy");
//       // System.out.println(minefield.getGrid());
//        GameVisual minesweeper = new GameVisual(minefield);
//        minefield.clickTile(6,6);
//        minefield.allocateBombs(6,6);
//        minefield.setNeighbouringMineCounter();
//        minesweeper.printGameBoard(minefield);
//        System.out.println("");
//        System.out.println("");
//        minefield.clickTile(2,6);
//        minesweeper.printGameBoard(minefield);
//        System.out.println();
//        System.out.println();
//        minefield.clickTile(3,6);
//        minesweeper.printGameBoard(minefield);
//        System.out.println();
//        System.out.println();
//        minefield.clickTile(4,6);
//        minesweeper.printGameBoard(minefield);
//        System.out.println();
//        System.out.println();
//        minefield.clickTile(5,6);
//        minesweeper.printGameBoard(minefield);
//        System.out.println();
//        System.out.println();
//        minefield.clickTile(7,6);
//        minesweeper.printGameBoard(minefield);
//
//
//
//    }
}
