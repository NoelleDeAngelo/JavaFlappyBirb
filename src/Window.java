import javax.swing.JFrame;



public class Window extends JFrame {

  public Window() {
    super("Flappy Birb");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(360, 640);
    setLocationRelativeTo(null);
    setResizable(false);
    add(new Game());
    //pack();
    setVisible(true);
  }

}

