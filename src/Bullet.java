import java.awt.*;

public class Bullet {
    private int bulletWidth=5;
    private int bulletHeight=7;
    private CustomRectangle bullet;
    private boolean hit;
    private boolean shot;

    public Bullet(int x, int y) {
        this.bullet = new CustomRectangle(
                x,
                y,
                bulletWidth,
                bulletHeight,
                Color.CYAN);
        this.hit=false;
        this.shot=false;

    }
    public void setY(int y){
        this.bullet.setY(y);
    }

    public int getBulletWidth() {
        return bulletWidth;
    }

    public void setBulletWidth(int bulletWidth) {
        this.bulletWidth = bulletWidth;
    }

    public int getBulletHeight() {
        return bulletHeight;
    }

    public void setBulletHeight(int bulletHeight) {
        this.bulletHeight = bulletHeight;
    }

    public CustomRectangle getBullet() {
        return bullet;
    }

    public void setBullet(CustomRectangle bullet) {
        this.bullet = bullet;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public void paint (Graphics graphics) {
        if (!hit) {
            this.bullet.paint(graphics);
        }
    }

    public boolean isShot() {
        return shot;
    }

    public void setShot(boolean shot) {
        this.shot = shot;
    }
    public int getY(){
        return this.bullet.getY();
    }
    public int getX(){
        return this.bullet.getX();
    }

    public void moveUp () {

        this.bullet.moveUp();
        this.shot=true;

    }

    //    public void moveDown(){
//        while (!hit){
//            this.bullet.moveDown();
//
//    }
    public void kill (Alien enemy) {
        if (checkCollision(enemy)){
            this.hit = true;
        }
    }
    public boolean checkCollision (Alien enemy) {
        boolean collision = false;
        Rectangle bullet = new Rectangle(
                this.bullet.getX(),
                this.bullet.getY(),
                this.bullet.getWidth(),
                this.bullet.getHeight()
        );
        Rectangle obstacleRect = new Rectangle(
                enemy.getX(),
                enemy.getY(),
                enemy.getWidth(),
                enemy.getHeight()

        );
        if (bullet.intersects(obstacleRect)) {
            collision = true;
        }
        return collision;
    }

//    public int getY(){
//        return this.bullet.getY();
//    }


}