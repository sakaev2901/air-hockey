package sample;

import javafx.scene.image.Image;

public class Ball {
    Integer x;
    Integer y;
    Image image;

    public Ball(Integer x, Integer y, Image image) {
        this.x = x;
        this.y = y;
        this.image = image;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public void moveLeft() {
        this.x -= 10;
    }

    public void moveRight() {
        this.x += 10;
    }

    public void moveUp() {
        this.y -= 10;
    }

    public void  moveDown() {
        this.y += 10;
    }
}
