package Client1.app;

import Client1.balls.Ball;
import Client1.balls.EnemyBall;
import Client1.balls.MainBall;
import Client1.balls.Puck;
import Client1.gameProcess.CollisionChecking;
import Client1.handling.BallsTracker;
import Client1.net.SocketClient;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Main extends Application {
    public static Double frameHeight = 500d;
    public static Double frameWidth = 400d;
    public static Double speedLoss = 0.9;
    public static Double strokeWidth = 10d;
    public static Integer mainScore = 0;
    public static Integer enemyScore = 0;
    public static Text mainText;
    public static Text enemyText;




    @Override
    public void start(Stage primaryStage) throws Exception {

        MainBall mainBall = new MainBall(25d);
        Puck puck = new Puck(20d);
        mainBall.setPuck(puck);

        EnemyBall enemyBall = new EnemyBall(25d);

        //arena
        Line line = new Line();
        line.setStartX(0);
        line.setStartY(Main.frameHeight/2);
        line.setEndX(Main.frameWidth);
        line.setEndY(Main.frameHeight/2);
        line.setStroke(Color.WHITE);
        line.setStrokeWidth(4d);

        Circle centreSmall  = new Circle(70d);
        centreSmall.setStroke(Color.WHITE);
        centreSmall.setCenterY(Main.frameHeight/2);
        centreSmall.setCenterX(Main.frameWidth/2);
        centreSmall.setStrokeWidth(4d);

        Line leftLine = new Line();
        leftLine.setStroke(Color.WHITE);
        leftLine.setStartY(0);
        leftLine.setStartX(0);
        leftLine.setEndY(Main.frameHeight);
        leftLine.setEndX(0);
        leftLine.setStrokeWidth(strokeWidth);

        Line rightLine = new Line();
        rightLine.setStartY(0);
        rightLine.setStartX(Main.frameWidth);
        rightLine.setEndY(Main.frameHeight);
        rightLine.setEndX(Main.frameWidth);
        rightLine.setStroke(Color.WHITE);
        rightLine.setStrokeWidth(strokeWidth);

        Line topLine = new Line();
        topLine.setStroke(Color.WHITE);
        topLine.setStartY(0);
        topLine.setStartX(0);
        topLine.setEndY(0);
        topLine.setEndX(Main.frameWidth);
        topLine.setStrokeWidth(strokeWidth);

        Line bottomLine = new Line();
        bottomLine.setStroke(Color.WHITE);
        bottomLine.setStartY(Main.frameHeight);
        bottomLine.setStartX(0);
        bottomLine.setEndY(Main.frameHeight);
        bottomLine.setEndX(Main.frameWidth);
        bottomLine.setStrokeWidth(strokeWidth);

        Line mainGoalLine = new Line();
        mainGoalLine.setStartX(Main.frameWidth / 4);
        System.out.println(mainGoalLine.getStartX());
        mainGoalLine.setStartY(frameHeight);
        mainGoalLine.setEndX(Main.frameWidth *3 /4);
        System.out.println(mainGoalLine.getEndX());
        mainGoalLine.setEndY(frameHeight);
        mainGoalLine.setStrokeWidth(strokeWidth);

        Line enemyGoalLine = new Line();
        enemyGoalLine.setStartX(Main.frameWidth / 4);
        enemyGoalLine.setEndX(Main.frameWidth *3 /4);
        enemyGoalLine.setStartY(0);
        enemyGoalLine.setEndY(0);
        enemyGoalLine.setStrokeWidth(strokeWidth);

        Circle enemyGoalCircle = new Circle(frameWidth/4 + 4);
        enemyGoalCircle.setStrokeWidth(4d);
        enemyGoalCircle.setStroke(Color.WHITE);
        enemyGoalCircle.setCenterX(frameWidth/2);
        enemyGoalCircle.setCenterY(0);

        Circle mainGoalCircle = new Circle(frameWidth/4 + 4);
        mainGoalCircle.setStroke(Color.WHITE);
        mainGoalCircle.setStrokeWidth(4d);
        mainGoalCircle.setCenterX(frameWidth/2);
        mainGoalCircle.setCenterY(frameHeight);

        mainText = new Text(String.valueOf(mainScore));
        mainText.setFill(Color.YELLOW);
        mainText.setFont(Font.font("Helvetica", FontWeight.SEMI_BOLD,45));
        mainText.setTextAlignment(TextAlignment.CENTER);
        System.out.println(mainText.getWrappingWidth());
        HBox hBox1 = new HBox();
        hBox1.setMinWidth(50);
        hBox1.setAlignment(Pos.CENTER);
        hBox1.getChildren().addAll(mainText);
        hBox1.setLayoutX(frameWidth/2 - 50/2);
        hBox1.setLayoutY(frameHeight/2);

        enemyText = new Text(String.valueOf(enemyScore));
        enemyText.setFill(Color.YELLOW);
        enemyText.setFont(Font.font("Helvetica", FontWeight.SEMI_BOLD,45));
        enemyText.setTextAlignment(TextAlignment.CENTER);
        HBox hBox2 = new HBox();
        hBox2.setMinWidth(50);
        hBox2.setAlignment(Pos.CENTER);
        hBox2.getChildren().addAll(enemyText);
        hBox2.setLayoutX(frameWidth/2 - 50/2);
        hBox2.setLayoutY(frameHeight/2 - 60);
//        mainText.setX(frameWidth/2 - mainText.getWrappingWidth());

               //


        Group root = new Group();
        root.getChildren().addAll(
                centreSmall,
                line,
                rightLine,
                bottomLine,
                leftLine,
                topLine,
                mainGoalLine,
                enemyGoalLine,
                enemyGoalCircle,
                mainGoalCircle,
                hBox1,
                hBox2,
                mainBall.getCircle(),
                puck.getCircle(),
                enemyBall.getCircle());



        SocketClient client = new SocketClient();
        client.startConnection("localhost", 6666);
        client.sendMessage("new-room");
        puck.setClient(client);
        mainBall.setClient(client);

        BallsTracker ballsTracker = new BallsTracker();
        ballsTracker.setClient(client);
        ballsTracker.setMainBall(mainBall);
        ballsTracker.setPuck(puck);
        ballsTracker.start();


        CollisionChecking collisionChecking = new CollisionChecking(puck);
        collisionChecking.setMainBall(mainBall);
        collisionChecking.setEnemyBall(enemyBall);
        collisionChecking.setPuck(puck);
        collisionChecking.setPrimaryStage(primaryStage);
        collisionChecking.setClient(client);
        collisionChecking.start();

        primaryStage.setResizable(true);
        primaryStage.setScene(new Scene(root, frameWidth, frameHeight, Color.BLACK));
        primaryStage.setTitle("1");
        primaryStage.show();
    }
}
