import javax.swing.*;
import java.awt.*;

public class GameOver extends JPanel {
    private ImageIcon space,gameover;
    public GameOver(int x, int y, int width, int height){
        this.setBounds(x, y, width, height);
        this.setDoubleBuffered(true);
        this.space = new ImageIcon("sky.jpeg");
        this.gameover=new ImageIcon("gameover.png");
    }
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        this.space.paintIcon(this, graphics, 0, 0);
        this.gameover.paintIcon(this, graphics, (Constants.WINDOW_WIDTH-gameover.getIconWidth())/2, (Constants.WINDOW_HEIGHT-gameover.getIconHeight())/2);
    }


}
