import java.io.BufferedReader;
import java.sql.Array;
import java.sql.SQLOutput;
import java.util.Scanner;

public class UserInput {
    private GameStatus gameStatus;
    private GameVisual gameVisual;
    private Board newBoard;

    public UserInput(){

    }

    //user input using scanner
    public String userInput(){
        Scanner sc=new Scanner(System.in);
        String userInput=sc.nextLine();
        return userInput;
    }


    //Scanner
    public static void main(String[] args) {
        UserInput user = new UserInput();

        //Customizing or choosing game level
        System.out.println("\n Customize(yes or no): \t");
        String decide= user.userInput();
        Board newBoard;
        if (decide.equals("yes")){
            System.out.println("\n Set (nr of bombs,row,column): \t");
            String setCustomize = user.userInput();
            String[] strArr0 = setCustomize.split("\s+");
            int nrOfBombs= Integer.parseInt(strArr0[0]);
            int row=Integer.parseInt(strArr0[1]);
            int column=Integer.parseInt(strArr0[2]);
            newBoard= new Board(nrOfBombs,row,column);
        }
        else{

        System.out.println("ENTER LEVEL(easy, medium, hard): ");
        String level = user.userInput();
         newBoard = new Board(level); }

        GameVisual newGameVisual = new GameVisual(newBoard);
        newGameVisual.printGameBoard(newBoard);
        long start = System.nanoTime();


        //Start of the game
        System.out.print("\n ENTER CELL COORDINATES (row column):\t");
        String xy = user.userInput();
        String[] strArr = xy.split("\s+");



        newBoard.allocateMiniBombs();
        newBoard.allocateBombs(Integer.parseInt(strArr[0]), Integer.parseInt(strArr[1]));
        newBoard.setNeighbouringMineCounter();
        newBoard.clickTile(Integer.parseInt(strArr[0]), Integer.parseInt(strArr[1]));
        newGameVisual.printGameBoard(newBoard);

        //game continues till a bomb is uncovered

        while (newBoard.clickTile(Integer.parseInt(strArr[0]), Integer.parseInt(strArr[1]))) {
            //Set flag or no
            System.out.print("\n SET FLAG (yes or no):\t");
            String flag = user.userInput();
            if (flag.equals("yes")) {
                System.out.print("\n ENTER FLAG CELL COORDINATES (row column):\t");
                xy=user.userInput();
                String[] strArr1 = xy.split("\s+");
                newBoard.setFlag(Integer.parseInt(strArr1[0]), Integer.parseInt(strArr1[1]));
                newGameVisual.printGameBoard(newBoard);

            } else if(flag.equals("no")) {
                System.out.print("\n ENTER CELL COORDINATES (row column): \t");
                xy = user.userInput();
                String[] strArr1 = xy.split("\s+");
                newBoard.clickTile(Integer.parseInt(strArr1[0]), Integer.parseInt(strArr1[1]));

                 //Checking if a bomb is open, leading to lose the game

                if (newBoard.clickTile(Integer.parseInt(strArr1[0]), Integer.parseInt(strArr1[1])) == false) {
                    newBoard.setAllBombsUncovered();
                    newGameVisual.printGameBoard(newBoard);
                    System.out.print("\n YOU LOST \t");
                    //RESET GAME OR NO
                    System.out.print("\n RESET GAME? (yes or no) \t");
                    String setReset = user.userInput();
                    if (setReset.equals("yes")) {
                        newBoard.setAllCovered();
                        newGameVisual.printGameBoard(newBoard);
                        System.out.print("\n ENTER CELL COORDINATES (row column): \t");
                        xy = user.userInput();
                        String[] strArr2 = xy.split("\s+");
                        newBoard.allocateMiniBombs();
                        newBoard.allocateBombs(Integer.parseInt(strArr2[0]), Integer.parseInt(strArr2[1]));
                        newBoard.setNeighbouringMineCounter();
                        newBoard.clickTile(Integer.parseInt(strArr2[0]), Integer.parseInt(strArr2[1]));
                    } else {
                        System.out.print("\n END GAME \t");
                        break;
                    }

                }
                if (newBoard.checkBoard()){
                    newGameVisual.printGameBoard(newBoard);
                    System.out.print("\n YOU WON \t");
                    //score allocation
                    int miniMinesOpen = newBoard.checkedMiniMine();
                    long end = System.nanoTime();
                    long elapsedTime = (end - start)/60000000;
                    int score = (int)(1000 - elapsedTime/10 - miniMinesOpen);
                    System.out.print("\n SCORE: " + score + "\t");
                    break;
                }
                newGameVisual.printGameBoard(newBoard);
            }
            //Removing previous flags
            if (newBoard.checkForFlags()){
                System.out.print("\n REMOVE FLAG (yes or no):\t");
                String removeFlag = user.userInput();

                if (removeFlag.equals("yes")){{
                    System.out.print("\n REMOVE FLAG CELL COORDINATES (row column):\t");
                    xy=user.userInput();
                    String[] strArr1 = xy.split("\s+");
                    newBoard.removeFlag(Integer.parseInt(strArr1[0]), Integer.parseInt(strArr1[1]));
                    newGameVisual.printGameBoard(newBoard);
                }}
            }
        }


    }}

