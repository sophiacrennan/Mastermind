import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

/**
 * This class represents the visual of the Mastermind game.
 */
public class View extends JFrame implements IView, ActionListener {

  private IModel model;

  private List<String> guesses;

  private int totalNumGuesses;

  private String[][] allGuesses;

  private JPanel colorPanel;
  private JPanel guessesPanel;
  private JPanel correctPanel;
  private JPanel buttonPanel;
  private JPanel matchesPanel;

  private JButton submit;
  private JButton delete;
  private Box box;

  public View(IModel model) {

    this.model = model;
    this.guesses = new ArrayList<String>();
    this.totalNumGuesses = this.model.getNumGuesses();
    this.allGuesses =
            new String[this.totalNumGuesses][this.model.getCorrectSequence().size()];
    for (int i = 0; i < this.model.getNumGuesses(); i++) {
      for (int j = 0; j < this.model.getCorrectSequence().size(); j++) {
        this.allGuesses[i][j] = "White";
      }
    }
    this.guessesPanel = new JPanel();
    this.correctPanel = new JPanel();
    this.box = Box.createVerticalBox();

    this.createCorrectPanel();
    this.createButtonPanel();
    this.createMatchesPanel();
    this.createColorPanel();
    this.createGuessesPanel();


    int width = this.model.getCorrectSequence().size();
    if (width < this.model.getColors().size()) {
      width = this.model.getColors().size();
    }
    int height = this.model.getNumGuesses();

    setSize(width * 45, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocation(200, 200);

    add(this.colorPanel, BorderLayout.SOUTH);
    add(this.guessesPanel, BorderLayout.CENTER);

    add(this.matchesPanel, BorderLayout.EAST);

    setVisible(true);

  }

  /**
   * This creates the correct panel which contains the correct sequence which will be revealed
   * when the game is over. Otherwise,
   */
  private void createCorrectPanel() {
    if (this.model.getGameOver()) {
      this.correctPanel.removeAll();
      for (int i = 0; i < this.model.getCorrectSequence().size(); i++) {
        ColorButton circle = new ColorButton(getColor(this.model.getCorrectSequence().get(i)));
        circle.setEnabled(false);
        this.correctPanel.add(circle);
      }
    } else {
      for (int i = 0; i < this.model.getCorrectSequence().size(); i++) {
        ColorButton circle = new ColorButton(Color.DARK_GRAY);
        circle.setEnabled(false);
        this.correctPanel.add(circle);
      }
    }

  }

  /**
   * This creates the guesses panel which contains all the guesses the user makes. A white circle
   * represents all the guesses a user can make. As the user guesses, the circles are filled with
   * the color the user guessed.
   */
  private void createGuessesPanel() {
    Box box = Box.createVerticalBox();
    this.guessesPanel.add(box);
    for (int i = 0; i < this.totalNumGuesses; i++) {
      JPanel guessPanel = new JPanel(new GridLayout());
      for (int j = 0; j < this.model.getCorrectSequence().size(); j++) {
        ColorButton circle = new ColorButton(getColor(this.allGuesses[i][j]));
        circle.setEnabled(false);
        guessPanel.add(circle);
      }
      box.add(guessPanel);
    }
    this.guessesPanel.add(this.buttonPanel);
    this.guessesPanel.add(this.correctPanel);


  }

  /**
   * This creates the Color panel which contains all the colors a user can guess with for the
   * correct sequence.
   */
  private void createColorPanel() {
    this.colorPanel = new JPanel();
    for (String color : this.model.getColors()) {
      ColorButton button = new ColorButton(this.getColor(color));
      this.colorPanel.add(button);
      button.addActionListener(this);
      button.setActionCommand(color);

    }
  }

  /**
   * This creates the button panel which contains the submit button and the delete button.
   */
  private void createButtonPanel() {
    this.buttonPanel = new JPanel();

    this.submit = new JButton("Submit");
    this.submit.setActionCommand("Submit");
    this.submit.addActionListener(this);

    this.delete = new JButton("Delete");
    this.delete.setActionCommand("Delete");
    this.delete.addActionListener(this);

    this.buttonPanel.add(this.submit);
    this.buttonPanel.add(this.delete);

  }

  /**
   * This creates a panel with all the exact and inexact matches.
   */
  private void createMatchesPanel() {
    this.matchesPanel = new JPanel();
    this.matchesPanel.add(this.box);

  }

  /**
   * Allows a user to submit there sequence to be compared with the correct sequence, printing
   * out the exact matches and inexact matches, and ending the game if they are out of guesses
   * Allows the user to delete the last color they added to their sequence
   * Allows a user to click on a color and have it be added to their sequence
   *
   * @param e an ActionEvent
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    if (!this.model.getGameOver()) {
      switch (e.getActionCommand()) {
        case "Submit":
          if (this.guesses.size() == this.model.getCorrectSequence().size()) {
            this.model.guessSequence(this.guesses);

            this.guesses.removeAll(this.guesses);

            String matches = "";
            matches += (Integer.toString(this.model.getExactMatches()));
            matches += (Integer.toString(this.model.getInexactMatches()));

            JLabel matchSet = new JLabel(matches);
            matchSet.setFont(new Font("Serif", Font.PLAIN, 26));
            this.box.add(matchSet);

            setVisible(true);
          }

          if (this.model.getGameOver()) {
            JLabel gameOver;
            if(this.model.getExactMatches() == 4) {
              gameOver = new JLabel("Game Over! You won!");
            } else {
              gameOver = new JLabel("Game Over!");
            }
            gameOver.setFont(new Font("Serif", Font.BOLD, 20));
            this.guessesPanel.add(gameOver);
            this.createCorrectPanel();
            setVisible(true);
          }

          break;
        case "Delete":
          if (this.guesses.size() > 0) {
            this.guesses.remove(this.guesses.size() - 1);


            for (int i = 0; i < this.totalNumGuesses; i++) {
              String previous = "White";
              String current = "";
              for (int j = 0; j < this.model.getCorrectSequence().size(); j++) {
                current = this.allGuesses[i][j];
                if (previous != "White" && current == "White") {
                  this.allGuesses[i][j - 1] = "White";
                } else if (previous != "White" && j == this.model.getCorrectSequence().size() - 1
                        && i == this.totalNumGuesses - this.model.getNumGuesses()) {
                  this.allGuesses[i][j] = "White";
                }
                previous = current;
              }
            }

            this.guessesPanel.removeAll();
            this.createGuessesPanel();
            setVisible(true);

            System.out.println(this.guesses);
          }
          break;
        case "Red":
        case "Yellow":
        case "Green":
        case "Cyan":
        case "Magenta":
        case "Black":
        case "Blue":
        case "Pink":
        case "Orange":
          if (this.guesses.size() < this.model.getCorrectSequence().size()) {
            this.guesses.add(e.getActionCommand());
            for (int i = 0; i < this.totalNumGuesses; i++) {
              for (int j = 0; j < this.model.getCorrectSequence().size(); j++) {
                if (this.allGuesses[i][j] == "White") {
                  this.allGuesses[i][j] = e.getActionCommand();
                  this.guessesPanel.removeAll();
                  this.createGuessesPanel();
                  setVisible(true);
                  return;
                }
              }

            }
          }
      }
    }
  }

  /**
   * Converts a String of a color into an actual color.
   *
   * @param color A String representing a color
   * @return the Color the String represents
   */
  private Color getColor(String color) {
    switch (color) {
      case "Red":
        return Color.RED;
      case "Orange":
        return Color.ORANGE;
      case "Yellow":
        return Color.YELLOW;
      case "Green":
        return Color.GREEN;
      case "Cyan":
        return Color.CYAN;
      case "Blue":
        return Color.BLUE;
      case "Pink":
        return Color.PINK;
      case "Magenta":
        return Color.MAGENTA;
      case "Black":
        return Color.BLACK;
      case "White":
        return Color.WHITE;
    }
    return Color.GRAY;
  }

}
