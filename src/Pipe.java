import javax.swing.ImageIcon;
import java.awt.*;

public class Pipe {
  int locationX = 340 ;
  int locationY;
  Image img;
  String pos;

  public Pipe(String pos, int pervious) {
    this.pos = pos;
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

  public int getYLoc() {
    if (pos == "top") {
      return 512 + locationY;
    } else if (pos == "bottom") {
      return locationY;
    }
    return 0;
  }

  public String getPos() {
    return this.pos;
  }
}
