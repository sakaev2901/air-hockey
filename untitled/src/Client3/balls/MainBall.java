package Client3.balls;

import Client1.app.Main;
import Client1.balls.Ball;
import Client1.balls.Puck;
import Client1.net.SocketClient;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class MainBall  extends Ball {
    private Circle circle;
    private Client1.balls.Puck puck;
    private Double radius;
    double oldX, oldY;
    private SocketClient client;

    public MainBall(Double radius) {
        this.radius = radius;
        this.circle = new Circle(this.radius, Color.RED);
        this.circle.setCenterX(Main.frameWidth / 2);
        this.circle.setCenterY(Main.frameHeight - radius);
        this.circle.setCursor(Cursor.HAND);
        this.circle.setOnMousePressed(circleOnMousePressedEventHandler);
        this.circle.setOnMouseDragged(circleOnMouseDraggedEventHandler);
    }

    EventHandler<MouseEvent> circleOnMousePressedEventHandler =
            new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    System.out.println("success");
                }
            };

    EventHandler<MouseEvent> circleOnMouseDraggedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    double x = t.getSceneX();
                    double y = t.getSceneY();
                    if (x > Main.frameWidth - radius) {
                        x = Main.frameWidth - radius;
                    }
                    if (x < radius) {
                        x = radius;
                    }
//                    if (y > Main.frameHeight - radius) {
//                        y = Main.frameHeight - radius;
//                    }
//                    if (y < Main.frameHeight / 2 + radius) {
//                        y = Main.frameHeight / 2 + radius;
//                    }
//                    Double distanceBetweenTwoBalls =Math.sqrt( Math.pow(puck.getCircle().getCenterX() - x, 2)
//                            + Math.pow(puck.getCircle().getCenterY() - y, 2));
//                    if (distanceBetweenTwoBalls <= radius + puck.getRadius()) {
//                        x = oldX;
//                        y = oldY;
//                    }
//                    oldX = x;
//                    oldY = y;
//                    System.out.println("X: " + x + " Y: " + y);
                    ((Circle) (t.getSource())).setCenterY(y);
                    ((Circle) (t.getSource())).setCenterX(x);
                    String location = String.valueOf(x + " " + y);
                    client.sendMessage(location);
                }
            };
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

    public Client1.balls.Puck getPuck() {
        return puck;
    }

    public void setPuck(Puck puck) {
        this.puck = puck;
    }

    public SocketClient getClient() {
        return client;
    }

    public void setClient(SocketClient client) {
        this.client = client;
    }
}
