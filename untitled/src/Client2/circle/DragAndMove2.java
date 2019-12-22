package Client2.circle;

import Client2.SocketClient;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class DragAndMove2 extends Application {
    Circle circle_Red, circle_Green;
    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;
    SocketClient client;

    public void setClient(SocketClient client) {
        this.client = client;
    }

    @Override
    public void start(Stage primaryStage) {

//Create Circles
        circle_Red = new Circle(50.0f, Color.RED);
        circle_Red.setCursor(Cursor.HAND);
        circle_Red.setOnMousePressed(circleOnMousePressedEventHandler);
        circle_Red.setOnMouseDragged(circleOnMouseDraggedEventHandler);

        circle_Green = new Circle(50.0f, Color.GREEN);
        circle_Green.setCursor(Cursor.HAND);
        circle_Green.setCenterX(150);
        circle_Green.setCenterY(150);
        circle_Green.setOnMousePressed(circleOnMousePressedEventHandler);
        circle_Green.setOnMouseDragged(circleOnMouseDraggedEventHandler);
        Group root = new Group();
        root.getChildren().addAll(circle_Red, circle_Green);
        primaryStage.setResizable(true);
        primaryStage.setScene(new Scene(root, 400, 350));
        primaryStage.setTitle("2");
        client = new SocketClient();
        client.startConnection("localhost", 6666);
        System.out.println(client);
        primaryStage.show();
    }

    public static void main(String ... args) {
        launch(args);

    }

    EventHandler<MouseEvent> circleOnMousePressedEventHandler =
            new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    orgSceneX = t.getSceneX();
                    orgSceneY = t.getSceneY();
                    orgTranslateX = ((Circle) (t.getSource())).getTranslateX();
                    orgTranslateY = ((Circle) (t.getSource())).getTranslateY();
                    System.out.println("success");
                }
            };

    EventHandler<MouseEvent> circleOnMouseDraggedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    System.out.println("in drag");
                    double offsetX = t.getSceneX() - orgSceneX;
                    double offsetY = t.getSceneY() - orgSceneY;
                    double newTranslateX = orgTranslateX + offsetX;
                    double newTranslateY = orgTranslateY + offsetY;
                    String location = String.valueOf(newTranslateX) + " " + String.valueOf(newTranslateY);
                    client.sendMessage(location);
                    ((Circle) (t.getSource())).setTranslateX(newTranslateX);
                    ((Circle) (t.getSource())).setTranslateY(newTranslateY);
                }
            };
}
