package Client1.balls;

import Client1.app.Main;
import Client1.net.SocketClient;
import com.sun.javafx.geom.Vec2d;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import Client1.physics.*;

public class Puck {
    private Double radius;
    private Circle circle;
    private Vector2d velocity = new Vector2d(0d, 0d);
    private SocketClient client;
    public Puck(Double radius) {
        this.radius = radius;
        this.circle = new Circle(radius, Color.WHITE);
        this.circle.setCenterX(Main.frameWidth/2);
        this.circle.setCenterY(Main.frameHeight/2);
    }


    public Vector2d getVelocity() {
        return velocity;
    }

    public void move() {
        this.circle.setCenterY(this.circle.getCenterY() + (this.velocity.getY() * 10) * Main.speedLoss);
        this.circle.setCenterX(this.circle.getCenterX() + (this.velocity.getX() * 10)* Main.speedLoss);
//        String location ="puck " +  this.circle.getCenterX() + " " + this.circle.getCenterY();
//        client.sendMessage(location);
//        String puckVelocity = "puck " + velocity.getX() + " " + velocity.getY() + " "  + this.circle.getCenterX() + " " + this.circle.getCenterY();
//        client.sendMessage(puckVelocity);
    }

    public void setVelocity(Vector2d velocity) {
        this.velocity = velocity;
//        String puckVelocity = "puck " + velocity.getX() + " " + velocity.getY() + " "  + this.circle.getCenterX() + " " + this.circle.getCenterY();
//        client.sendMessage(puckVelocity);
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

    public SocketClient getClient() {
        return client;
    }

    public void setClient(SocketClient client) {
        this.client = client;

    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }
}
