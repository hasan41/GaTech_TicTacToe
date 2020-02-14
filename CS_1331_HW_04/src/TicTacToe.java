import java.util.Scanner;
import java.util.Random;

public class TicTacToe {
   private static final Random Random = new Random(); //Random instance variable 

    // Place your methods here
    public static void main(String[] args) {
     
     Scanner sc = new Scanner(System.in);
     Board board = new Board();
     Location location = new Location(Random.nextInt(3),Random.nextInt(3));
     char [] b;
     b = new char[] {'X', 'O'};
     String [] pnumber = new String[] {"1", "2"};
     int player = 0;
     boolean tictacend = false;
     int countplayer = getNumberPlayers(sc);
     char pc = 0;
     System.out.println(board);
     while(!tictacend) {
        if(countplayer == 1 && player == 1) {
          do {
            if(countplayer == 1 && player == 1) {
               playComputer(board,location);
            }
            else {
              location = getInput(pnumber[player],sc);
            }
          }while(!board.find(location.getRow(),location.getColumn(),b[player]));
          
          System.out.println(board);
          
     switch(board.getGameState()) {
     case ONGOING:
        break;
     case PLAYER1_WIN:
        System.out.println("Congrats, Player 1 'X' wins! ");
        tictacend = true;
        break;
     case PLAYER2_WIN:
         if(countplayer == 1) {
          System.out.println("Sorry, Computer won! ");
         }
         else if(countplayer == 2) {
          System.out.println("Congrats, Player 2 'O' wins! ");
         }
         tictacend = true;
         break;
     case TIE:
         System.out.println("Game is Draw");
         tictacend = true;
         break;
     }
     player = (player + 1) % 2;
    }
     }
    }
    
    private static void playComputer(Board board, Location location) {
        System.out.println(board);
        int row  = Random.nextInt(3);
        int column  = Random.nextInt(3);
        System.out.println("The computer is making move... ");
        location = new Location(row, column);
    }
    
    private static void playPlayer(Board board,Scanner sc) {
      System.out.print(board);
      Location p1 = new Location(getInput("1", sc));
      
    }
    
//    private static Location Robot(Board board, char[][] b) {
//     int row = 0;
//     int column = 0;
//     int count = 0;
//     boolean a = true;
//     for(row = 0; row < b.length; row++) {
//       for(column = 0; column < b.length; column++) {
//          if(b[row][column] == 0) {
//             count++;
//          }
//          int select = (int) (Math.random() * count);
//          count = 0;
//          for (row = 0; row < b.length; row ++){
//              for (column = 0; column < b.length; column ++){
//                  if (b[row][column] == 0){
//                      if (count == select){
//                         b[row][column] = 2;
//                         System.out.println("The computer is making the move...");
//                       }
//                      count++;
//                      }
//                  }
//              }
//          }
//       }
//     return null;
//     
//   }

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
