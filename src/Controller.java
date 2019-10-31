//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.List;
//
//public class Controller implements IController {
//
//  private IModel model;
//  private IView view;
//  private List<String> guesses;
//
//  public Controller(IModel model, IView view) {
//    this.model = model;
//    this.view = view;
//  }
//
//  public void run() {
//    this.view.render();
//  }
//
//  @Override
//  public void addGuess(String guess) {
//    this.guesses.add(guess);
//    System.out.println("poop");
//  }
//
//  @Override
//  public void submitGuess() {
//    if (this.guesses.size() == this.model.getCorrectSequence().size()) {
//      this.model.guessSequence(this.guesses);
//      this.guesses.clear();
//      this.model.changeNumGuesses();
//    }
//  }
//
//  @Override
//  public void deleteGuess() {
//    if (this.guesses.size() > 0) {
//      this.guesses.remove(this.guesses.size() - 1);
//    }
//  }
//
//}
