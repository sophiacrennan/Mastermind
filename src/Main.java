import java.util.Scanner;


/**
 * This is the main class. It allows a user to input what type of Mastermind game they want. The
 * options are to specify the sequence length (with -sequenceLength followed by an Integer), the
 * number of guesses allowed in the game (with -guessesAllowed followed by an Integer), the length
 * of the colors the user is allowed to guess from (with -colorLength followed by an Integer), and
 * if there can be duplicates of a color in the correct sequence (with -duplicates followed by
 * either "true" or "false"). If the user wishes to not specify all the options then default
 * values will be used.
 */
public class Main {
  public static void main(String[] args) {

    String input = "";
    for (int i = 0; i < args.length; i++) {
      input += args[i] + " ";
    }
    Scanner scanner = new Scanner(input);
    String command;
    int sequenceLength = 4;
    int guessesAllowed = 10;
    int colorLength = 6;
    boolean duplicates = true;

    while (scanner.hasNext()) {
      command = scanner.next();
      switch (command) {
        case "-sequenceLength":
          sequenceLength = scanner.nextInt();
          break;
        case "-guessesAllowed":
          guessesAllowed = scanner.nextInt();
          break;
        case "-colorLength":
          colorLength = scanner.nextInt();
          break;
        case "-duplicates":
          duplicates = scanner.nextBoolean();
          break;
      }

      }

    IModel model = new Model(sequenceLength, guessesAllowed, colorLength, duplicates);
    IView view = new View(model);

  }
}
