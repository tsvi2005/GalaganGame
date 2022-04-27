import javax.swing.*;
import java.awt.*;

public class Alien {

    private int x;
    private int y;
    private int height;
    private int width;
    private ImageIcon picture;
    private boolean alive;
    private char direction;

    public Alien(ImageIcon picture,int x,int y, char RIGHT) {
        this.x = x;
        this.y = y;
        this.picture = picture;
        this.height=picture.getIconHeight();
        this.width=picture.getIconWidth();
        this.alive=true;
        this.direction = RIGHT;
    }
    public void paint(Graphics graphics){
        if (this.alive){
            graphics.fillRect(this.x,this.y,this.width,this.height);
        }
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public ImageIcon getPicture() {
        return picture;
    }

    public void setPicture(ImageIcon picture) {
        this.picture = picture;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }
    public void shot(){
        Bullet bullet =new Bullet(this.getX(),this.getY());
    }
    public void moveDown(){
        this.y+=30;
    }

}
