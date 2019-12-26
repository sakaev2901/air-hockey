package Client1.balls;

import Client1.app.Main;
import Client1.handling.EnemyTracker;
import javafx.scene.Cursor;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class EnemyBall extends Ball{
    private Circle circle;
    private Puck puck;
    private Double radius;
    private double oldX, oldY;
    private EnemyTracker enemyTracker;

    public EnemyBall(Double radius) {
        this.radius = radius;
        this.circle = new Circle(this.radius, Color.GREEN);
        this.circle.setCenterX(Main.frameWidth / 2);
        this.circle.setCenterY(radius);
        this.circle.setCursor(Cursor.HAND);
        enemyTracker = new EnemyTracker(this.circle);
    }

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    public Puck getPuck() {
        return puck;
    }

    public void setPuck(Puck puck) {
        this.puck = puck;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }
}
