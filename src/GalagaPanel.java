import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.ArrayList;

public class GalagaPanel extends JPanel {
    private boolean inGame = true;
    public int enemyX = 60;
    public int enemyY = 10;
    private ImageIcon space, ship, alien;
    private Ship spaceShip;
    private Alien enemy;
    private ArrayList<Alien> enemies;
    private Bullet bullet;
    private boolean isShoting=false;
    private boolean shot=false;


    public GalagaPanel(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        this.setDoubleBuffered(true);
        this.space = new ImageIcon("sky.jpeg");
        this.ship = new ImageIcon("ship.png");
        this.alien = new ImageIcon("alien.png");
        this.spaceShip = new Ship(Constants.SHIP_WIDTH,Constants.SHIP_HEIGHT,ship);
        this.enemies = new ArrayList<Alien>();
        for (int i = 0; i < Constants.NUM_OF_ALIEN; i++) {
            if (i != 0) {
                if (i % Constants.AMOUNT_OF_ALIEN_IN_LINE == 0) {
                    enemyX = Constants.ENEMY_X;
                    enemyY += Constants.ENEMY_Y;// new row
                } else {
                    enemyX = enemyX + Constants.ENEMY_SPACE;
                }
            }
            Alien temp = new Alien(alien, enemyX, enemyY, Constants.RIGHT);
            this.enemies.add(temp);
        }
        this.mainGameLoop();
    }

    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        this.space.paintIcon(this, graphics, 0, 0);
        for (Alien alien : this.enemies) {
            if (alien.isAlive()) {
                this.alien.paintIcon(this, graphics, alien.getX(), alien.getY());
            }
        }
        if(spaceShip.isAlive()) {
            this.spaceShip.getPicture().paintIcon(this, graphics, spaceShip.getX(), spaceShip.getY());
        }
        if (isShoting && !this.bullet.isHit()) {
            this.bullet.paint(graphics);
            shot=true;
        }

    }

    public void mainGameLoop() {
        new Thread(() -> {
            Random random=new Random();
            this.setFocusable(true);
            this.requestFocus();
            while (true) {
                for (int i = 0; i < this.enemies.size(); i++) {
                    Alien currentAlien = this.enemies.get(i);
                    if (currentAlien.getDirection() == Constants.RIGHT) {
                        currentAlien.setX(currentAlien.getX() - Constants.MOVE_OFFSET);
                        if (currentAlien.getX() <= 0) {
                            currentAlien.setDirection(Constants.LEFT);
                            currentAlien.moveDown();
                        }
                    }else{
                        currentAlien.setX(currentAlien.getX() + Constants.MOVE_OFFSET);
                        if (currentAlien.getX() >= Constants.WINDOW_WIDTH-alien.getIconWidth()) {
                            currentAlien.setDirection(Constants.RIGHT);
                            currentAlien.moveDown();
                        }
                    }
                }

                try {
                    Thread.sleep(Constants.ALIEN_SLEEP_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                repaint();
            }
        }).start();
        new Thread(() -> {
            this.setFocusable(true);
            this.requestFocus();
//            while (this.bullet.getY() > 0) {
            PlayerController playerController = new PlayerController(this);
            this.addKeyListener(playerController);

            try {
                Thread.sleep(Constants.SLEEP_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();

        new Thread(() -> {
            this.setFocusable(true);
            this.requestFocus();
            ArrayList<Bullet> bullets = spaceShip.getBullets();
            spaceShip.shot();
            this.bullet=bullets.get(0);
            while (true) {
                if (isShoting) {
                    this.bullet=bullets.get(bullets.size()-1);
                    this.bullet.moveUp();
                }
                synchronized (enemies) {//Only this works
                    for (Alien alien1 : this.enemies) {
                        Alien currentAlien = alien1;
                        if (this.bullet.checkCollision(currentAlien)) {
                            this.bullet.kill(alien1);
                            currentAlien.setAlive(false);
                            this.enemies.remove(currentAlien);
                            System.out.println("COLLISION!");
                            bullet.setHit(true);
                            spaceShip.bullets.remove(this.bullet);
                            this.bullet.setY(-1);//out of the game
                            isShoting = false;
                            if (this.enemies.size() < 1) {
                                Won won = new Won(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
                                this.add(won);
                                break;
                            }
                            break;
                        }
                        if (this.bullet.getY() < 0) {
                            isShoting = false;
                            shot = false;
                            bullet.setHit(false);
                            spaceShip.bullets.remove(this.bullet);
                        }

                    }
                }
                repaint();
                try {
                    Thread.sleep(Constants.SLEEP_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(()->{
            this.setFocusable(true);
            this.requestFocus();
            while (spaceShip.isAlive()) {
                if(this.enemies.size()>0) {
                    synchronized (enemies) {//Only this works
                        for (Alien alien1 : this.enemies) {
                            Alien currentAlien = alien1;
                            if (this.spaceShip.checkCollision(currentAlien)) {
                                this.spaceShip.setAlive(false);
                                GameOver gameOver = new GameOver(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
                                this.add(gameOver);
                                System.out.println("dead");
                                break;
                            }
                            if (!this.spaceShip.isAlive()) {
                                for (Alien alien2 : this.enemies) {
                                    //Alien currentAlien1 = alien2;
                                    alien2.setAlive(false);

                                    // enemies.remove(currentAlien1);
                                }
                                enemies.clear();
                            }
                        }
                    }
                    try {
                        Thread.sleep(Constants.SLEEP_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    catch (Exception e){

                    }
                }
            }
        }).start();

    }

    public Ship getPlayer() {
        return spaceShip;
    }
    public boolean isShoting() {
        return isShoting;
    }

    public void setShoting(boolean shoting) {
        isShoting = shoting;
    }

    public Bullet getBullet() {
        return bullet;
    }

    public void setBullet(Bullet bullet) {
        this.bullet = bullet;
    }
//    public void addBullets(){
//        this.bullet=new Bullet(spaceShip.getX()+(spaceShip.getWidth()/2),spaceShip.getY()) ;
//        this.bullets.add(this.bullet);
//    }


}

