package Client3.balls;

import javafx.scene.shape.Circle;

public abstract class Ball {
    private Circle circle;
    private Double radius;

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }
}
