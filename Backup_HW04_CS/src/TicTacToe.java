 // I worked on the homework assignment alone, using only course materials.
import java.util.Scanner;
import java.util.Random;
public class TicTacToe {

    // Place your methods here
 
 public static void main(String[] args) {
   Scanner scan = new Scanner(System.in);
   Board board = new Board();
   boolean tictacend = false;
   System.out.println("Welcome to Georgia Instititue's original TicTacToe!\n");
   int play = getNumberPlayers(scan);
   if(play == 1) {
    playComputerAI(board, scan,"1");
   }
   else {
     playPlayer(board, scan, "1", "2");
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
           if(play == 1) {
               System.out.println("The Computer won... Your too dumb");
         }
           else {
               System.out.println("Player 2, 'O' won!");
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
 /***
  * 
  * @param board - Prints out the board.toString and other board public methods from the Board.java
  * @param scan - Let user put in the rowsxcols move so that the scanner lets generate
  * @param p1 - String for player 1 moves while the even trackplayer is generated in the if statement 
  * @param p2 - String for player 2 moves while the odd trackplayer is generated in the if statement  
  */
 private static void playPlayer(Board board, Scanner scan, String p1, String p2) {
  System.out.println(board);
   int trackplayer = 0;
   Location location;
   int row;
   int column;
   while(board.getGameState() == GameState.ONGOING) {
     if(trackplayer % 2 == 0) {
       location = getInput(p1, scan);
       row = location.getRow();
       column = location.getColumn();
       if(board.locatelocation(row, column)) {
          board.find(location, 'X');
          System.out.println(board); //prints the board after the if statement is true
          trackplayer = trackplayer + 1; //increments the player's move so the player 2 will be an even number
       }
     }
     else if(trackplayer % 2 != 0) {
          if(board.getGameState() == GameState.ONGOING) {
           location = getInput(p2,scan);
            row = location.getRow();
            column = location.getColumn();
            if(board.locatelocation(row, column)) {
              board.find(location, 'O');
              System.out.println(board);
              trackplayer = trackplayer + 1; //increments the player's move so the player 2 will be odd number
            }
          }
     }
   }
 }
 /***
  * 
  * @param board - prints out the board for the method playComputerAI during running the while loop
  * @param scan - Scanner type being passed to check if
  * @param p1 - Selects player 1 as a string type inside the getInput method passed in the location class when the checkplayer is an even Integer type
  * @param p2 - If the checkplayer is not an even integer then p2 
  */
 private static void playComputerAI(Board board, Scanner scan, String p1) {
    System.out.println(board);
    int row, column;
    Location location, computer;
    int checkplayer = 0;
    Random ran  = new Random();
    while(board.getGameState() == GameState.ONGOING) {
      if(checkplayer % 2 == 0) {
        location = getInput(p1,scan);
        row = location.getRow();
        column = location.getColumn();
        if(board.locatelocation(row, column)) {
          board.find(location,'X');
          System.out.println(board);
          checkplayer = checkplayer + 1; //increment player every time after player 1 took its turn, so player 2 can make its move 
        }
      }
      else if(checkplayer % 2 !=0) {
       computer = new Location(ran.nextInt(3), ran.nextInt(3));
       int comprow = computer.getRow();
       int compcolumn = computer.getColumn();
       if(board.locatelocation(comprow, compcolumn)) {
          System.out.println("The Computer is making a move...");
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