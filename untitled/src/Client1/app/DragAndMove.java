package Client1.app;

import Client1.EnemyTracker;
import Client1.balls.MainBall;
import Client1.net.SocketClient;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.net.Socket;

public class DragAndMove extends Application {
    Circle circle_Red, circle_Green, circle_Black;
    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;
    SocketClient client;
    static Socket enemy;

    public void setEnemy(Socket enemy) {
        this.enemy = enemy;
    }

    public void setClient(SocketClient client) {
        this.client = client;
    }

    @Override
    public void start(Stage primaryStage) {
        circle_Black = new Circle(25.0f, Color.BLACK);
        circle_Black.setCenterX(200);
        circle_Black.setCenterY(225);
        circle_Black.setCursor(Cursor.HAND);
        circle_Black.setOnMousePressed(circleOnMousePressedEventHandler);
        circle_Black.setOnMouseDragged(circleOnMouseDraggedEventHandler);



        circle_Red = new Circle(25.0f, Color.RED);
        circle_Red.setCenterX(200);
        circle_Red.setCenterY(425);
        circle_Red.setCursor(Cursor.HAND);
        circle_Red.setOnMousePressed(circleOnMousePressedEventHandler);
        circle_Red.setOnMouseDragged(circleOnMouseDraggedEventHandler);

        MainBall mainBall = new MainBall(50d);

        circle_Green = new Circle(25.0f, Color.GREEN);
        circle_Green.setCenterX(200);
        circle_Green.setCenterY(25);
        Group root = new Group();
        root.getChildren().addAll(circle_Red, circle_Green, circle_Black, mainBall.getCircle());
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 400, 450));
        primaryStage.setTitle("1");
        this.client = new SocketClient();
//        client.startConnection("localhost", 6666);
        EnemyTracker enemyTracker = new EnemyTracker();
        enemyTracker.setEnemyCircle(circle_Green);
//        enemyTracker.start();
        primaryStage.show();
    }

    public void startGame(String... args) {
        launch();
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
                    double offsetX = t.getSceneX() - orgSceneX;
                    double offsetY = t.getSceneY() - orgSceneY;
                    double newTranslateX = orgTranslateX + offsetX;
                    double newTranslateY = orgTranslateY + offsetY;
                    ((Circle) (t.getSource())).setTranslateX(newTranslateX);
                    ((Circle) (t.getSource())).setTranslateY(newTranslateY);
                    System.out.println(circle_Red.getCenterX());
                }
            };


}