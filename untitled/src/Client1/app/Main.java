package Client1.app;

import Client1.balls.MainBall;
import Client1.balls.Puck;
import Client1.gameProcess.CollisionChecking;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    public static Integer frameHeight = 500;
    public static Integer frameWidth = 400;
    public static Double speedLoss = 0.9;

    public static void main(String[] args) {
        launch();

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        MainBall mainBall = new MainBall(25d);
        Puck puck = new Puck(20d);
        mainBall.setPuck(puck);
        Group root = new Group();
        root.getChildren().addAll(mainBall.getCircle(), puck.getCircle());

        CollisionChecking collisionHandling = new CollisionChecking(puck);
        collisionHandling.setMainBall(mainBall);
        collisionHandling.setPuck(puck);
        collisionHandling.start();

        primaryStage.setResizable(true);
        primaryStage.setScene(new Scene(root, frameWidth, frameHeight, Color.GRAY));
        primaryStage.setTitle("1");
        primaryStage.show();
    }
}
