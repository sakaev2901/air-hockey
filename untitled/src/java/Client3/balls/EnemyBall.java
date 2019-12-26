package Client3.balls;

import Client3.app.Main;
import Client3.balls.Ball;
import Client3.balls.Puck;
import Client3.handling.EnemyTracker;
import javafx.scene.Cursor;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class EnemyBall extends Ball {
    private Circle circle;
    private Puck puck;
    private Double radius;
    private double oldX, oldY;
    private EnemyTracker enemyTracker;

    public EnemyBall(Double radius, Puck puck) {
        this.radius = radius;
        this.circle = new Circle(this.radius, Color.GREEN);
        this.circle.setCenterX(Main.frameWidth / 2);
        this.circle.setCenterY(radius);
        this.circle.setCursor(Cursor.HAND);
        enemyTracker = new EnemyTracker(this.circle);
        enemyTracker.setPuckCircle(puck.getCircle());
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
