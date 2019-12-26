package Client3.balls;

import Client3.app.Main;
import Client3.net.SocketClient;
import com.sun.javafx.geom.Vec2d;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import Client3.physics.*;

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
//        String location = null;
//        if (SocketClient.responce != null) {
//            location = SocketClient.responce;
//            String parts[] = location.split(" ");
//            if (parts[0].equals("puck")) {
//                Double x = Main.frameWidth - Double.valueOf(parts[1]);
//                Double y = Main.frameHeight - Double.valueOf(parts[2]);
//                this.circle.setCenterX(x);
//                this.circle.setCenterY(y);
//            }
//        }
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

    public SocketClient getClient() {
        return client;
    }

    public void setClient(SocketClient client) {
        this.client = client;
    }
}
