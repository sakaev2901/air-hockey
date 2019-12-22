package Client1;

import Client1.SocketClient;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class DragAndMove extends Application {
    Circle circle_Red, circle_Green;
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
        circle_Red = new Circle(50.0f, Color.RED);
        circle_Red.setCursor(Cursor.HAND);
        circle_Red.setOnMousePressed(circleOnMousePressedEventHandler);
        circle_Red.setOnMouseDragged(circleOnMouseDraggedEventHandler);

        circle_Green = new Circle(50.0f, Color.GREEN);
        circle_Green.setCenterX(150);
        circle_Green.setCenterY(150);
//        Pane pane = new Pane();
//        pane.getChildren().addAll(circle_Red, circle_Green);
        Group root = new Group();
        root.getChildren().addAll(circle_Red, circle_Green);
        primaryStage.setResizable(true);
        primaryStage.setScene(new Scene(root, 400, 350));
        primaryStage.setTitle("1");
        this.client = new SocketClient();
        client.startConnection("localhost", 6666);
        EnemyTracker enemyTracker = new EnemyTracker();
//        enemyTracker.setEnemy(client1Server.getEnemy());
        enemyTracker.setEnemyCircle(circle_Green);
        enemyTracker.start();
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