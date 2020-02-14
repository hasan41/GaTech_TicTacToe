import java.util.Scanner;
import java.util.Random;
public class TicTacToe {

    // Place your methods here
 
 public static void main(String[] args) {
   Scanner sc = new Scanner(System.in);
   Board board = new Board();
   boolean tictacend = false;
   if(getNumberPlayers(sc) == 1) {
    playComputerAI(board, sc, 0,"1", "2");
   }
   else {
     playPlayer(board, sc, "1", "2");
   }
   
   while(!tictacend) {
      switch(board.getGameState()) {
         case ONGOING:
           break;
         case PLAYER1_WIN:
           System.out.println("Player 1, 'X' won!");
           tictacend = true;
           break;
         case PLAYER2_WIN: 
           if(getNumberPlayers(sc) == 0) {
               System.out.println("Player 1, "  + 'X' + " won! ");
         }
           else {
               System.out.println("The Computer won... Your too dumb ");
         }
           tictacend = true;
           break;
         case TIE:
           System.out.println("Game is Draw");
           tictacend = true;
           break;
      }
   }
 }
 
 private static void playPlayer(Board board, Scanner sc, String p1, String p2) {
   System.out.println(board);
   int trackplayer = 0;
   Location location;
   int row;
   int column;
   while(board.getGameState() == GameState.ONGOING) {
     if(trackplayer % 2 == 0) {
       location = getInput(p1, sc);
       row = location.getRow();
       column = location.getColumn();
       if(board.locatelocation(row, column)) {
          board.find(location, 'X');
          System.out.println(board); //prints the board after the if statement is true
          trackplayer = trackplayer + 1;
       }
     }
     else {
          if(board.getGameState() == GameState.ONGOING) {
           location = getInput(p2,sc);
            row = location.getRow();
            column = location.getColumn();
            if(board.locatelocation(row, column)) {
              board.find(location, 'O');
              System.out.println(board);
              trackplayer = trackplayer + 1;
            }
          }
     }
   }
 }
 
 private static void playComputerAI(Board board, Scanner sc, int playnum, String p1, String p2) {
    System.out.println(board);
    int row, column;
    Location location, computer;
    int checkplayer = 0;
    Random ran  = new Random();
    while(board.getGameState() == GameState.ONGOING) {
      if(checkplayer % 2 == 0) {
        location = getInput(p1,sc);
        row = location.getRow();
        column = location.getColumn();
        if(board.locatelocation(row, column)) {
          board.find(location,'X');
          System.out.println(board);
          checkplayer = checkplayer + 1; //increment player every time after its true 
        }
      }
      else {
       computer = new Location(ran.nextInt(3), ran.nextInt(3));
       int comprow = computer.getRow();
       int compcolumn = computer.getColumn();
       if(board.locatelocation(comprow, compcolumn)) {
          System.out.println("Computer's turn ...");
          board.find(computer, 'O');
          System.out.println(board);
          checkplayer = checkplayer + 1;
       }
     }
    }
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



