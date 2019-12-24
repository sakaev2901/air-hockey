package Client3.app;

import Client1.balls.EnemyBall;
import Client1.balls.MainBall;
import Client1.balls.Puck;
import Client1.gameProcess.CollisionChecking;
import Client1.net.SocketClient;
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

        EnemyBall enemyBall = new EnemyBall(25d);


        Group root = new Group();
        root.getChildren().addAll(mainBall.getCircle(), puck.getCircle(), enemyBall.getCircle());

        CollisionChecking collisionChecking = new CollisionChecking(puck);
        collisionChecking.setMainBall(mainBall);
        collisionChecking.setEnemyBall(enemyBall);
        collisionChecking.setPuck(puck);
        collisionChecking.start();

        SocketClient client = new SocketClient();
        client.startConnection("localhost", 6666);
        mainBall.setClient(client);

        primaryStage.setResizable(true);
        primaryStage.setScene(new Scene(root, frameWidth, frameHeight, Color.GRAY));
        primaryStage.setTitle("1");
        primaryStage.show();
    }
}
