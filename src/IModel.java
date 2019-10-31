import java.util.ArrayList;
import java.util.List;


/**
 * This interface represents the operations offered by the model.
 * One object of the model represents a game of Mastermind.
 */
public interface IModel {


  /**
   * @return the list of colors generated at the beginning of the game.
   */
  List<String> getColors();


  /**
   * @return the sequence generated at the beginning of the game that is to be the correct solution.
   */
  List<String> getCorrectSequence();

  /**
   * Finds how many exact matches there are between the correct sequence and the guessed sequence.
   * An exact match is if the color is the same in the same exact position for both sequences.
   * @return the number of the exact matches
   */
  int getExactMatches();

  /**
   * Finds how many inexact matches there are between the correct sequence and the guessed sequence.
   * An inexact match is if the color is the same but not in the correct position for both
   * sequences.
   * @return the number of inexact matches
   */
  int getInexactMatches();

  /**
   * @return A String of the correct sequence, the guessed sequence, and the exact and inexact
   * matches.
   */
  String getGameState();

  /**
   * @return the number of guesses left in the game
   */
  int getNumGuesses();

  /**
   * A game is over if the game has been won or if there are no more guesses left.
   * @return a boolean of it the game is over
   */
  boolean getGameOver();

  /**
   * Randomly generates the list of color that will be used in the game out of a list of 9 colors.
   * The list is made up of Strings representing the colors.
   */
  void generateColorList();

  /**
   * Randomly generates the correct sequence that will be used in the game. The sequence is made
   * up of a list of Strings representing colors.
   */
  void generateSequence();

  /**
   * Compares the given guessedSequence with the correct Sequence in order to calculate the exact
   * and inexact matches. Also changes the number of guesses left.
   *
   * @param guessedSequence A list of Strings representing colors to be used as the guessed
   *                        sequence.
   */
  void guessSequence(List<String> guessedSequence);

  /**
   * Changes the number of guesses left in the game each time a sequence is guessed. Also checks
   * if the game is over.
   */
  void changeNumGuesses();

}
