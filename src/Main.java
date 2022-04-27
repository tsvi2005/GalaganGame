import javax.swing.*;

public class Main extends JFrame {

    public static void main(String[] args) {
        new Main();
    }

    public Main () {
        GalagaPanel galagaPanel = new GalagaPanel(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        this.add(galagaPanel);
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        this.setVisible(true);



    }



}
