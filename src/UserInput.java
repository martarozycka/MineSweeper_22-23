import java.io.BufferedReader;
import java.util.Scanner;

public class UserInput {
    private GameStatus gameStatus;
    private GameVisual gameVisual;
    private int userInput;
    private Board newBoard;

    public UserInput(){

    }
    public void play(Scanner sc){
       while(sc.hasNextInt()){
           gameStatus.equals(GameStatus.play);
       }
    }




    public static void main(String[] args) {

        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        Scanner sc3=new Scanner(System.in);

        System.out.println("ENTER nrOfBombs, height,length: ");
        int nrBombs=sc1.nextInt();
        int height=sc1.nextInt();
        int length=sc1.nextInt();
        Board newBoard= new Board(nrBombs, height,length);
        GameVisual newGameVisual=new GameVisual(newBoard);
        newGameVisual.printGameBoard(newBoard);

        System.out.println("\n Set Flag (yes or no):\t");
        String flag=sc2.nextLine();
        if (flag.equals("yes")) {
            int x = sc2.nextInt();
            int y = sc2.nextInt();
            newBoard.setFlag(x, y);
            newGameVisual.printGameBoard(newBoard);
        }

        else{
            System.out.println("\n ENTER CELL COORDINATES(x y):\t");
            int x= sc3.nextInt();
            int y= sc3.nextInt();
            newBoard.clickTile(x,y);
            newGameVisual.printGameBoard(newBoard);

            while (newBoard.clickTile(x,y)==true){
                System.out.println("\n Set Flag (yes or no):\t");
                String flag1=sc2.nextLine();
                if (flag.equals("yes")) {
                    int x1 = sc2.nextInt();
                    int y1= sc2.nextInt();
                    newBoard.setFlag(x1, y1);
                    newGameVisual.printGameBoard(newBoard);
                }

                else{
                    System.out.println("\n ENTER CELL COORDINATES(x y):\t");
                    int x1= sc3.nextInt();
                    int y1= sc3.nextInt();
                    newBoard.clickTile(x1,y1);
                    if (newBoard.clickTile(x1,y1)==false){
                        System.out.println("\n YOU LOST \t");}
                    else{
                        newGameVisual.printGameBoard(newBoard);}

                }


            }


        }

    }

}
