import java.awt.*;

public class CustomRectangle {
    private int x;
    private int y;
    private int width;
    private int height;
    private Color color;

    public CustomRectangle(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public void paint (Graphics graphics) {
        graphics.setColor(this.color);
        graphics.fillRect(
                this.x,
                (int) this.y,
                this.width,
                this.height
        );
    }

    public void moveRight () {
        this.x++;
    }

    public void moveLeft () {
        this.x--;
    }

    public void moveUp () {
        this.y = y - 20;

    }

    public void moveDown () {
        this.y++;
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getBottom () {
        return this.y + this.height;
    }

}
