import javax.swing.*;
import java.awt.*;

public class Won extends JPanel{

    private ImageIcon space,won;


    public Won(int x, int y, int width, int height){
        this.setBounds(x, y, width, height);
        this.setDoubleBuffered(true);
        this.space = new ImageIcon("sky.jpeg");
        this.won=new ImageIcon("won.jpg");

    }
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        this.space.paintIcon(this, graphics, 0, 0);
        this.won.paintIcon(this, graphics, (Constants.WINDOW_WIDTH-won.getIconWidth())/2, (Constants.WINDOW_HEIGHT-won.getIconHeight())/2);

    }
}
