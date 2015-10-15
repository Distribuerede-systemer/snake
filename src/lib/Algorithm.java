// Imports:
import java.util.*;

// Class:
public class Algorithm {
  public static void main(String[] args) {

    // Dummy variables that should be replaced in a function call:
    String hostControls = "ddssaaww";
    String opponentControls = "ddwwaass";
    String turn = Math.round(Math.random() * 1) == 0 ? "host" : "opponent";

    // Hosting party:
    System.out.println("\n### GAME: host is " + turn + "! ###\n");

    // Split each controls string into a character array:
    char[] hostControlCharacters = hostControls.toCharArray();
    char[] opponentControlCharacters = opponentControls.toCharArray();

    // The host score and position:
    int hostScore = 0;
    int hostX = 0;
    int hostY = 1;

    // The opponent score and position:
    int opponentX = 0;
    int opponentY = -1;
    int opponentScore = 0;

    // Playing field options:
    int size = 9;
    int radius = (size - 1) / 2;

    // Total amount of moves:
    int movesCount = hostControls.length() < opponentControls.length() ? opponentControls.length() : hostControls.length();

    // Arrays of host and opponent moves:
    int[][] hostMoves = new int[movesCount][];
    int[][] opponentMoves = new int[movesCount][];

    // Loop through the host (leader) moves:
    System.out.println("Looping through host control moves:\n");

    for (int i = 0; i < hostControls.length(); i++) {
      char move = hostControlCharacters[i];

      if (move == 'a') {
        hostX--;
      } else if (move == 'd') {
        hostX++;
      } else if (move == 'w') {
        hostY++;
      } else if (move == 's') {
        hostY--;
      }

      hostMoves[i] = new int[]{hostX, hostY};

      System.out.println(Arrays.toString(hostMoves[i]));
    }

    // Loop through the follower (opponent) moves:
    System.out.println("Looping through opponent control moves:\n");

    for (int i = 0; i < opponentControls.length(); i++) {
      char move = opponentControlCharacters[i];

      if (move == 'a') {
        opponentX--;
      } else if (move == 'd') {
        opponentX++;
      } else if (move == 'w') {
        opponentY++;
      } else if (move == 's') {
        opponentY--;
      }

      opponentMoves[i] = new int[]{opponentX, opponentY};

      System.out.println(Arrays.toString(opponentMoves[i]));
    }

    // Loop through both moves to find collisions:
    System.out.println("\nDetecting collisions in favor of " + turn + ":\n");

    for (int i = 0; i < movesCount; i++) {
      int[] hostMove = hostMoves[i];
      int[] opponentMove = opponentMoves[i];

      if (hostMove != null && opponentMove != null && hostMove[0] == opponentMove[0] && hostMove[1] == opponentMove[1]) {
        if (turn.equals("host")) {
          hostScore++;
        } else {
          opponentScore++;
        }

        System.out.println("Collision at [" + hostMove[0] + ", " + hostMove[1] + "]!\tHost " + hostScore + " - Opponent " + opponentScore);
      }
    }

    /*

      TODO:

      1) Setup the playing field using the size int.

    */

  }
}
