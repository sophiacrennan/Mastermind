import java.awt.*;

import javax.swing.*;


/**
 * This class represents each of the circles shown in the view (the colors the user can guess
 * from and the empty circles where the users guesses will go).
 */
public class ColorButton extends JButton {

  Color color;

  /**
   * Given a color, a circle button will be created with the color as a background.
   * @param color the color of the circle
   */
  public ColorButton(Color color) {
    //super(Integer.toString(index));
    this.color = color;
    setBorderPainted(false);
    setFocusable(false);

    Dimension size = getPreferredSize();
    size.width = Math.max(size.width, size.height);
    size.height = Math.max(size.width, size.height);
    setPreferredSize(size);

    setContentAreaFilled(false);


  }

  protected void paintComponent(Graphics g) {
    g.setColor(this.color);
    g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);
    super.paintComponent(g);
  }
}
