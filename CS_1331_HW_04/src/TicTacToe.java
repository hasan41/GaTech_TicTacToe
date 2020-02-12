import java.util.Scanner;
import java.util.Random;

public class TicTacToe {
    // Place your methods here
    public static void main(String[] args) {
//     Scanner sc = new Scanner(System.in);
//     Board board = new Board();
//     int s = getNumberPlayers(sc);
//     while(true) {
//     if(s == 1) {
//         playComputer(board);
//     }
//     else if(s == 2) {
//         playPlayer(board);
//     }
//     }
     Scanner sc = new Scanner(System.in);
     Board board = new Board();
     char[][] b = new char [3][3];
     char pc = 0;
     if(getNumberPlayers(sc) == 1) {
        playComputer(board,b,pc);
     }
//     int i = 0, j = 0;
//     b.location(board, i, j);
//     String s = b.toString();
//     System.out.print(s);
//     
//     if(getNumberPlayers(sc) == 1) {
//        playComputer(b, board, compSymbol);
//     }
//     else {
//         playPlayer(b,board,userSymbol,sc);
//     }
     //b.toString();
    }
    
    public static void playComputer(Board board, char[][] b, char pc) {
        System.out.println(board);
        for(int i = 0; i < b.length; i++) {
           for(int j = 0; j < b[0].length; j++) {
               if(b[i][j] == ' ') {
                  b[i][j] = pc;
                  return;
               }
           }
        }
    }
    
    public static void playPlayer(Board board, char[][] b, char pp, Scanner sc) {
        String s = board.toString();
        String player = " ";
        System.out.println(s);
        int row = sc.nextInt();
        int column = sc.nextInt();
        while(b[row][column] != ' ') {
            getInput(player, sc);
            row = sc.nextInt();
            column = sc.nextInt();
         }
        b[row][column] = pp;
    }
    

    /********************************************************
     *                                                      *
     *      DO NOT MODIFY CODE BELOW THIS BOX               *
     *                                                      *
     ********************************************************/

   /**
     * Gets number of players from command line
     * @param sc        for reading from command-line
     * @return number of players (always 1 or 2)
     */
    private static int getNumberPlayers(Scanner sc) {
        boolean repeatPrompt = true;
        int numPlayers = 0;
        while (repeatPrompt) {
            System.out.print("How many players (1 or 2)? ");
            String input = sc.next();
            try {
                numPlayers = Integer.parseInt(input);
                if (numPlayers == 1 || numPlayers == 2) {
                    repeatPrompt = false;
                } else {
                    System.out.println("Enter 1 or 2 players.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please only enter a number.");
            }
        }
        return numPlayers;
    }

   /**
     * Handles scanner calls and user input
     * @param player    whose turn it is
     * @param sc        for reading from command-line
     * @return int[] holding row, column in that order
     */
    private static Location getInput(String player, Scanner sc) {
        boolean repeatPrompt = true;
        int row = -1;
        int col = -1;
        while (repeatPrompt) {
            System.out.print("Enter desired square for " + player + ": ");
            String input = sc.next();
            input = input.trim();
            String[] splitInput = input.split(",");
            try {
                row = Integer.parseInt(splitInput[0]);
                col = Integer.parseInt(splitInput[1]);
                repeatPrompt = false;
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("Please follow the format 'row,col'; for ex '1,2'");
            }
        }
        Location loc = new Location(row, col);
        return loc;
    }

}