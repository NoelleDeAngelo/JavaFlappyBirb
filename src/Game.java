import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;



public class Game extends JPanel {
  // Images
  Image backgroundImg = new ImageIcon(getClass().getResource("./assets/flappybirdbg.png")).getImage();
  Image birdImg = new ImageIcon(getClass().getResource("./assets/flappybird.png")).getImage();
  Image topPipeImg = new ImageIcon(getClass().getResource("./assets/toppipe.png")).getImage();
  Image bottomPipeImg = new ImageIcon(getClass().getResource("./assets/bottompipe.png")).getImage();
  Image deathbgImg = new ImageIcon(getClass().getResource("./assets/deathbg.png")).getImage();

  // Sprites
  Bird bird = new Bird();
  ArrayList<Pipe> pipesList = new ArrayList<Pipe>();

  // Gameplay
  boolean dead;
  boolean collision = false;


  public Game() {
    setFocusable(true);
    addKeyListener(new KeyListener() {

      @Override
      public void keyTyped(KeyEvent e) {
      }

      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && !dead) {
          bird.flap();
        } else if (dead) {
          loop();
        }
      }

      @Override
      public void keyReleased(KeyEvent e) {
      }

    });
    loop();
  }




//Progress the game
  public void loop() {
    Timer timer = new Timer();
    TimerTask tick = new TimerTask() {    //task to run every frame
      @Override
      public void run() {
        bird.move();
        Iterator<Pipe> iter = pipesList.iterator();
        while (iter.hasNext()) {
          Pipe pipe = iter.next();
          pipe.move();
          if (pipe.locationX < 51 && pipe.locationX >= -49) {
            if (pipe.getPos() == "top") {
              collision = bird.checkPipeCollision(0, pipe.getYLoc());
            } else if (pipe.getPos() == "bottom") {
              collision = bird.checkPipeCollision(pipe.getYLoc(), 640);
            }
            if (collision) {
              break;
            }
          }
          if (pipe.locationX < -64) {
            iter.remove();
          }
        }
        dead = collision || bird.checkFloorCollision();
        repaint();
        if (dead) {
          // add dely to strating new game
          timer.cancel();
          bird = new Bird();
          pipesList.clear();
          collision = false;

        }
      }

    };
    TimerTask addPipe = new TimerTask() {  // task to add pipe
      @Override
      public void run() {
        Pipe topPipe= new Pipe("top", 0);
        Pipe bottomPipe = new Pipe("bottom", topPipe.locationY);
        pipesList.add(topPipe);
        pipesList.add(bottomPipe);
      }
    };
    timer.scheduleAtFixedRate(tick,0, (1000 / 60));
    timer.scheduleAtFixedRate(addPipe, 0, (1700));
  }

//Draw Game
  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    draw(g, dead);
  }

  public void draw(Graphics g, boolean dead) {
    if (dead) {
      g.drawImage(deathbgImg, 0, 0, 360, 640, null);
    } else {
      g.drawImage(backgroundImg, 0, 0, 360, 640, null);
      g.drawImage(bird.img, bird.locationX, bird.locationY, 36, 24, null);
      for (Pipe pipe : pipesList) {
        g.drawImage(pipe.img, pipe.locationX, pipe.locationY, 64, 512, null);
      }
    }
  }


}
