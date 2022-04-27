import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerController implements KeyListener {

    private GalagaPanel galagaPanel;

    public PlayerController (GalagaPanel galagaPanel) {
        this.galagaPanel =galagaPanel ;
    }

    public void keyTyped(KeyEvent keyEvent) {

    }

    public void keyReleased(KeyEvent keyEvent) {
    }

    public void keyPressed(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (keyCode == keyEvent.VK_RIGHT) {
            galagaPanel.getPlayer().moveRight();
        }
        if (keyCode == keyEvent.VK_LEFT) {
            galagaPanel.getPlayer().moveLeft();
        }
        if (keyCode == keyEvent.VK_UP) {
            galagaPanel.getPlayer().moveUp();
        }
        if (keyCode == keyEvent.VK_DOWN) {
            galagaPanel.getPlayer().moveDown();
        }
        if (keyCode == keyEvent.VK_SPACE) {
            if (!galagaPanel.isShoting()) {
                galagaPanel.getPlayer().shot();
                galagaPanel.setShoting(true);
                galagaPanel.getPlayer().shot();
                System.out.println("done");
            }

        }
    }
}
