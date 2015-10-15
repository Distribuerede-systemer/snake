// Imports:
import java.util.*;

// Class:
public class Algorithm {

  /**
   * This is the basic algorithm for running a game of snake between a host and an opponent.
   * The code is purposely written in a verbose manner so as to make it easily understandable.
   *
   * IMPROVEMENTS COMING UP:
   *
   * Actually, this is just a set of dots that move around in a coordinate system. Their trails
   * missing from the code. This is coming right up!
   *
   * Notes:
   *
   * 1) In order to make the host and opponent crash at the same time, use these controls:
   *
   * -- String hostControls = "ddssaaww";
   * -- String opponentControls = "ddwwaass";
   *
   */
  public static void main(String[] args) {

    // Dummy variables that should be replaced with data from the host:
    String hostControls = "ddssaawwdddddd";
    String opponentControls = "ddwwaass";
    String turn = Math.round(Math.random() * 1) == 0 ? "host" : "opponent";

    // Hosting party:
    System.out.println("\n### GAME: host is " + turn + "! ###\n");

    // Split each controls string into a character array:
    char[] hostControlCharacters = hostControls.toCharArray();
    char[] opponentControlCharacters = opponentControls.toCharArray();

    // The game variables for the host:
    boolean hostDidCrash = false;
    int hostScore = 0;
    int hostKills = 0;
    int hostX = 0;
    int hostY = 1;

    // The game variables for the opponent:
    boolean opponentDidCrash = false;
    int opponentScore = 0;
    int opponentX = 0;
    int opponentY = -1;
    int opponentKills = 0;

    // Playing field options:
    int boundary = 4;

    // Total amount of moves:
    int movesCount = hostControls.length() < opponentControls.length() ? opponentControls.length() : hostControls.length();

    // Arrays of host and opponent moves:
    int[][] hostMoves = new int[movesCount][];
    int[][] opponentMoves = new int[movesCount][];

    // Loop through the host (leader) moves:
    System.out.println("Looping through host moves:\n");

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
    System.out.println("\nLooping through opponent moves:\n");

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

      // Kills:
      if (hostMove != null && opponentMove != null && hostMove[0] == opponentMove[0] && hostMove[1] == opponentMove[1]) {
        if (turn.equals("host")) {
          hostKills++;

          opponentDidCrash = true;
        } else {
          opponentKills++;

          hostDidCrash = true;
        }

        System.out.println("Collision at [" + hostMove[0] + ", " + hostMove[1] + "]!\tHost " + hostKills + " - Opponent " + opponentKills);
      }

      // Host move:
      if (hostMove != null) {

        // Check if the host move results in a wall crash:
        if (hostMove[0] > boundary || hostMove[1] > boundary) {
          hostDidCrash = true;
        }

        // Check if the host move is not null and if the host did not previously crash:
        if (!hostDidCrash) {
          hostScore++;
        }

      }

      // Opponent move:
      if (opponentMove != null) {

        // Check if the opponent move results in a wall crash:
        if (opponentMove[0] > boundary || opponentMove[1] > boundary) {
          opponentDidCrash = true;
        }

        // Check if the opponent move is not null and if the opponent did not previously crash:
        if (!opponentDidCrash) {
          opponentScore++;
        }

      }

    }

    System.out.println("hostScore: " + hostScore);
    System.out.println("opponentScore: " + opponentScore);

  }

}
