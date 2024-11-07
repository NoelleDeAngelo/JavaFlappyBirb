import javax.swing.ImageIcon;
import java.awt.*;
import java.util.Random;

public class Pipe {
  int locationX = 340 ;
  int locationY;
  Image img;

  public Pipe(String pos, int pervious){
    if (pos == "top") {
      img = new ImageIcon(getClass().getResource("./assets/toppipe.png")).getImage();
      locationY = (int)(Math.random() * (-40 - (-400) + 1)) -400;
    } else if (pos == "bottom") {
      img = new ImageIcon(getClass().getResource("./assets/bottompipe.png")).getImage();
      locationY = pervious + 620;
    }
  }
  public void move() {
    locationX -= 2;
  }


}
