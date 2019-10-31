import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ModelTest {

  IModel model = new Model();
  IModel model2 = new Model(false);
  IModel model3 = new Model(5);
  IModel model4 = new Model(5, 5, 5, true);



  List<String> guessedSequence = new ArrayList<>(Arrays.asList("Blue", "Cyan", "Pink", "Purple"));

  @Test
  public void generateSequence() {
    assertEquals("", this.model.getCorrectSequence());
  }

  @Test
  public void generateColorList() {
    assertEquals("", this.model.getColors());
  }

  @Test
  public void guessSequence() {
    this.model.guessSequence(guessedSequence);
    assertEquals("", this.model.getGameState());
  }
}