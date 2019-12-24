package Client3.balls;

import Client1.app.Main;
import com.sun.javafx.geom.Vec2d;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import Client1.physics.*;

public class Puck {
    private Double radius;
    private Circle circle;
    private Vector2d velocity = new Vector2d(0d, 0d);
    public Puck(Double radius) {
        this.radius = radius;
        this.circle = new Circle(radius, Color.BLACK);
        this.circle.setCenterX(Main.frameWidth/2);
        this.circle.setCenterY(Main.frameHeight/2);
    }


    public Vector2d getVelocity() {
        return velocity;
    }

    public void move() {
        this.circle.setCenterY(this.circle.getCenterY() + (this.velocity.getY() * 10) * Main.speedLoss);
        this.circle.setCenterX(this.circle.getCenterX() + (this.velocity.getX() * 10)* Main.speedLoss);
    }

    public void setVelocity(Vector2d velocity) {
        this.velocity = velocity;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }
}
