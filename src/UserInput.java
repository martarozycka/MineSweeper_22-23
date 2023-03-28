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

    public String userInput(){
        Scanner sc=new Scanner(System.in);
        String userInput=sc.nextLine();
        return userInput;
    }
    public void lose(){
        System.out.println("YOU LOST");
    }




    public static void main(String[] args) {
        UserInput user = new UserInput();

        System.out.println("ENTER LEVEL(easy, medium, difficult): ");
        String level = user.userInput();
        Board newBoard = new Board(level);
        GameVisual newGameVisual = new GameVisual(newBoard);
        newGameVisual.printGameBoard(newBoard);

        System.out.print("\n ENTER CELL COORDINATES (row column):\t");
        String xy = user.userInput();
        String[] strArr = xy.split("\s+");
        newBoard.allocateBombs(Integer.parseInt(strArr[0]), Integer.parseInt(strArr[1]));
        newBoard.setNeighbouringMineCounter();

        //checking if first click is a bomb
        while(!newBoard.clickTile(Integer.parseInt(strArr[0]), Integer.parseInt(strArr[1]))){
            newBoard.allocateBombs(Integer.parseInt(strArr[0]), Integer.parseInt(strArr[1]));
            newBoard.setNeighbouringMineCounter();
            newBoard.clickTile(Integer.parseInt(strArr[0]), Integer.parseInt(strArr[1]));
        }
        newBoard.clickTile(Integer.parseInt(strArr[0]), Integer.parseInt(strArr[1]));
        newGameVisual.printGameBoard(newBoard);
        //game continues till a bomb is uncovered

        while (newBoard.clickTile(Integer.parseInt(strArr[0]), Integer.parseInt(strArr[1]))) {

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
                if (newBoard.clickTile(Integer.parseInt(strArr1[0]), Integer.parseInt(strArr1[1])) == false) {
                    newBoard.setAllBombsUncovered();
                    newGameVisual.printGameBoard(newBoard);
                    System.out.print("\n YOU LOST \t");
                    //RESET GAME OR NO
                    System.out.print("\n RESET GAME? (yes or no) \t");
                    String setReset = user.userInput();
                    if (setReset.equals("yes")) {
                        newBoard.reset(newBoard);

                    } else {
                        System.out.print("\n END GAME \t");
                        break;
                    }

                }

                newBoard.clickTile(Integer.parseInt(strArr1[0]), Integer.parseInt(strArr1[1]));
                newGameVisual.printGameBoard(newBoard);

            }

            //check if won
            else if (newBoard.checkBoard()==true ){
                System.out.print("\n YOU WON \t");
                break;
            }

//            else if(!flag.equals("yes") || !flag.equals("no")){
//                System.out.print("\n INVALID ANSWER \t");
//            }


            if (newBoard.checkForFlags()){
                System.out.print("\n REMOVE FLAG (yes or no):\t");
                String removeFlag = user.userInput();
//                if(!removeFlag.equals("yes") || !removeFlag.equals("no")){
//                    System.out.print("\n INVALID ANSWER \t");
//                }
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

//invalid answer fro flag and remove flag does not work and won does not work checkkkkk
