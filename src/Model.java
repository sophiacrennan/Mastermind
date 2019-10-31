import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a game of Mastermind and all its operations.
 */
public class Model implements IModel{

  private int sequenceLength;
  private int guessesAllowed;
  private int colorLength;
  private boolean duplicates;

  private List<String> correctSequence;
  private List<String> guessedSequence;
  private final String[] colorList = {"Red", "Orange", "Yellow", "Green", "Cyan",
          "Blue", "Pink", "Magenta", "Black"};

  private List<String> colors;

  private boolean gameOver;
  private int exactMatches;
  private int inexactMatches;


  /**
   * This constructor takes in no parameters and generates a new game of Mastermind with the
   * default settings (sequence length of 4, 10 total guesses allowed, 6 colors to guess from,
   * and duplicates are allowed)
   */
  public Model() {
    this.sequenceLength = 4;
    this.guessesAllowed = 10;
    this.colorLength = 6;
    this.duplicates = true;

    this.colors = new ArrayList<String>();
    this.generateColorList();

    this.correctSequence = new ArrayList<String>();
    this.generateSequence();

    this.guessedSequence = new ArrayList<String>();
  }

  /**
   * This constructor creates a new game of Mastermind with the default settings from the default
   * constructor except this one sets the sequence length to the given input.
   *
   * @param sequenceLength the length the sequence is to be
   * @throws IllegalArgumentException if the number given is not a positive number
   */
  public Model(int sequenceLength) throws IllegalArgumentException {
    if (sequenceLength <= 0) {
      throw new IllegalArgumentException("The length of the sequence must be a positive number");
    }
    this.sequenceLength = sequenceLength;
    this.guessesAllowed = 10;
    this.colorLength = 6;
    this.duplicates = true;

    this.colors = new ArrayList<String>();
    this.generateColorList();

    this.correctSequence = new ArrayList<String>();
    this.generateSequence();

    this.guessedSequence = new ArrayList<String>();


  }

  /**
   * This constructor creates a new game of Mastermind with the default settings from the default
   * constructor except uses the given boolean for the duplicates field.
   *
   * @param duplicates a boolean of whether the game should allow a color to be used more than
   *                   once in the correct sequence.
   */
  public Model(boolean duplicates) {
    this.sequenceLength = 4;
    this.guessesAllowed = 10;
    this.colorLength = 6;
    this.duplicates = duplicates;

    this.colors = new ArrayList<String>();
    this.generateColorList();

    this.correctSequence = new ArrayList<String>();
    this.generateSequence();

    this.guessedSequence = new ArrayList<String>();
  }

  /**
   * This constructor creates a new game of Mastermind using the default settings from the
   * default constructor except uses the given integer for the correct sequence length and the
   * given boolean for the duplicates field.
   *
   * @param sequenceLength the length of the correct sequence
   * @param duplicates a boolean of whether the game should allow a color to be used more than
   *                   once in the correct sequence
   * @throws IllegalArgumentException if the given length is not a positive number
   */
  public Model(int sequenceLength, boolean duplicates) throws IllegalArgumentException{
    if (sequenceLength <= 0) {
      throw new IllegalArgumentException("The length of the sequence must be a positive number");
    }
    this.sequenceLength = 4;
    this.guessesAllowed = 10;
    this.colorLength = 6;
    this.duplicates = duplicates;

    this.colors = new ArrayList<String>();
    this.generateColorList();

    this.correctSequence = new ArrayList<String>();
    this.generateSequence();

    this.guessedSequence = new ArrayList<String>();

  }

  /**
   * This constructor creates a game of Mastermind with the given parameters.
   *
   * @param sequenceLength the length of the correct sequence
   * @param guessesAllowed the number of guesses allowed
   * @param colorLength the number of colors to be guessed from
   * @param duplicates a boolean of whether the game should allow a color to be used more than
   *                   once in the correct sequence
   * @throws IllegalArgumentException if any of the given integers are not positive numbers
   */
  public Model(int sequenceLength, int guessesAllowed, int colorLength, boolean duplicates) throws IllegalArgumentException {
    if (sequenceLength <= 0 || guessesAllowed <= 0 || colorLength <= 0) {
      throw new IllegalArgumentException("All given numbers must be positive.");
    }
    if (duplicates == false && sequenceLength > colorLength) {
      throw new IllegalArgumentException("If duplicates are disallowed, the length of the " +
              "sequence to be guessed must be less than or equal to the length of the color list.");
    }

    this.sequenceLength = sequenceLength;
    this.guessesAllowed = guessesAllowed;
    this.colorLength = colorLength;
    this.duplicates = duplicates;

    this.colors = new ArrayList<String>();
    this.generateColorList();

    this.correctSequence = new ArrayList<String>();
    this.generateSequence();

    this.guessedSequence = new ArrayList<String>();
  }

  public void generateSequence() {

    List<String> sequence = new ArrayList<String>();

    if(duplicates) {

      for (int i = 0; i < this.sequenceLength; i++) {
        int randomColorIndex = (int) (Math.random() * this.colorLength);
        sequence.add(this.colors.get(randomColorIndex));
      }
    } else {
      for (int i = 0; i < this.sequenceLength; i++) {
        int randomColorIndex = (int) (Math.random() * this.colorLength);

        if (!sequence.contains(this.colors.get(randomColorIndex))) {
          sequence.add(this.colors.get(randomColorIndex));
        } else {
          i--;
        }
      }
    }

    correctSequence.addAll(sequence);
  }

  public void generateColorList() {

    List<String> colorsList = new ArrayList<String>();

    for (int i = 0; i < this.colorLength; i++) {
      int randomIndex = (int) (Math.random() * this.colorList.length);

      if (!colorsList.contains(this.colorList[randomIndex])) {
        colorsList.add(this.colorList[randomIndex]);
      } else {
        i--;
      }

    }
    colors.addAll(colorsList);
  }

  @Override
  public void guessSequence(List<String> guessedSequence) {

    this.guessedSequence.clear();
    this.guessedSequence.addAll(guessedSequence);

    System.out.println(this.guessedSequence);
    this.calculateExactMatches();
    this.calculateInexactMatches();
    this.changeNumGuesses();
  }

  /**
   * Finds how many exact matches there are between the correct sequence and the guessed sequence.
   * An exact match is if the color is the same in the same exact position for both sequences.
   */
  private void calculateExactMatches() {
    int matches = 0;
    for (int i = 0; i < this.sequenceLength; i++) {
      if (guessedSequence.get(i).equals(correctSequence.get(i))) {
        matches++;
      }
    }
    exactMatches = matches;

    if (matches == this.sequenceLength) {
      this.gameOver = true;
    }
  }

  /**
   * Finds how many inexact matches there are between the correct sequence and the guessed sequence.
   * An inexact match is if the color is the same but not in the correct position for both
   * sequences.
   */
  private void calculateInexactMatches() {
    int matches = 0;
    List<Integer> matchIndexes = new ArrayList<Integer>();

    for (int i = 0; i < this.sequenceLength; i++) {
      if (!this.correctSequence.get(i).equals(this.guessedSequence.get(i))) {
        for (int j = 0; j < this.sequenceLength; j++) {
          if (this.correctSequence.get(i).equals(this.guessedSequence.get(j))
          && (!this.correctSequence.get(j).equals(this.guessedSequence.get(j)))
                  &&!matchIndexes.contains(j)) {
            matches++;
            matchIndexes.add(j);
          }
        }
      }
    }
    this.inexactMatches = matches;

  }

  public List<String> getColors() {
    return this.colors;
  }

  public List<String> getCorrectSequence() {
    return this.correctSequence;
  }

  public int getExactMatches() {
    return this.exactMatches;
  }

  public int getInexactMatches() {
    return this.inexactMatches;
  }

  public String getGameState() {
    String output = "Correct Sequence: ";

    for(String color : this.correctSequence) {
      output += color + " ";
    }

    output += "\n Guessed Sequence: ";

    for(String color : this.guessedSequence) {
      output += color + " ";
    }

    output += "\n Exact Matches: " + exactMatches + "\n Inexact Matches: " + inexactMatches;


    return output;
  }

  @Override
  public int getNumGuesses() {
    return guessesAllowed;
  }

  @Override
  public void changeNumGuesses() {
    this.guessesAllowed--;
    if (this.guessesAllowed == 0) {
      this.gameOver = true;
    }
  }

  @Override
  public boolean getGameOver() {
    return this.gameOver;
  }

}
