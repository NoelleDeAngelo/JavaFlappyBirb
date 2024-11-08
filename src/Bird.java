import javax.swing.ImageIcon;
import java.awt.*;


public class Bird {
  int locationX = 15 ;
  int locationY = 640 / 2;
  double velocity = -4;
  Image img = new ImageIcon(getClass().getResource("./assets/flappybird.png")).getImage();

  public void move() {
    locationY = (int) Math.max(locationY + velocity, 0);
    velocity += .3;
  }

  public void flap() {
    velocity = -4;
  }


//Check if bird collides
  public boolean checkFloorCollision() {
    if (locationY >= 640) {
      return true;
    }
    return false;
  }

  public boolean checkPipeCollision(int pipeTop, int pipeBottom) {
    if (locationY >= pipeTop && locationY <= pipeBottom){
      return true;
    }
      return false;
  }

}
