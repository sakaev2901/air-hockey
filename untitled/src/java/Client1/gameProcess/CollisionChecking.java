package Client1.gameProcess;

import Client1.app.Main;
import Client1.balls.Ball;
import Client1.balls.EnemyBall;
import Client1.balls.MainBall;
import Client1.balls.Puck;
import Client1.net.SocketClient;
import Client1.physics.Vector2d;
import javafx.animation.AnimationTimer;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;


public class CollisionChecking extends AnimationTimer {

    private Puck puck;
    private MainBall mainBall;
    private EnemyBall enemyBall;
    private CollisionsHandling collisionsHandling;
    private Stage primaryStage;
    private Main main = new Main();
    private SocketClient client;


    public CollisionChecking(Puck puck) {
        this.puck = puck;
        collisionsHandling = new CollisionsHandling();
        collisionsHandling.setPuck(puck);
        collisionsHandling.start();
    }

    public Puck getPuck() {
        return puck;
    }

    public void setPuck(Puck puck) {
        this.puck = puck;
    }

    public MainBall getMainBall() {
        return mainBall;
    }

    public void setMainBall(MainBall mainBall) {
        this.mainBall = mainBall;
    }

    public EnemyBall getEnemyBall() {
        return enemyBall;
    }

    public void setEnemyBall(EnemyBall enemyBall) {
        this.enemyBall = enemyBall;
    }

    @Override
    public void handle(long now) {
       checkCollisions(mainBall);
       checkCollisions(enemyBall);
        if (puck.getCircle().getCenterX() <= puck.getRadius()) {
            Vector2d newVelocity = new Vector2d(puck.getVelocity().getX() * -1, puck.getVelocity().getY());
            puck.setVelocity(newVelocity);
        }
        if (puck.getCircle().getCenterX() >= (Main.frameWidth -  puck.getRadius())) {
            Vector2d newVelocity = new Vector2d(puck.getVelocity().getX() * -1, puck.getVelocity().getY());
            puck.setVelocity(newVelocity);
        }
        if (puck.getCircle().getCenterY() <= puck.getRadius() && puck.getCircle().getCenterX()<=(Main.frameWidth/4)
            || puck.getCircle().getCenterY() <= puck.getRadius() && puck.getCircle().getCenterX()>=(Main.frameWidth* 3/4)) {
            Vector2d newVelocity = new Vector2d(puck.getVelocity().getX(), puck.getVelocity().getY() * -1);
            puck.setVelocity(newVelocity);
        }
        if (puck.getCircle().getCenterY() >= (Main.frameHeight - puck.getRadius()) && puck.getCircle().getCenterX()<=(Main.frameWidth/4)
                || puck.getCircle().getCenterY() >= (Main.frameHeight - puck.getRadius())&& puck.getCircle().getCenterX()>=(Main.frameWidth* 3/4)) {
            Vector2d newVelocity = new Vector2d(puck.getVelocity().getX(), puck.getVelocity().getY() * -1);
            puck.setVelocity(newVelocity);
        }
        if (puck.getCircle().getCenterY() < 0) {
            Main.mainText.setText(String.valueOf(++Main.mainScore));
            resetAll();
        }
        if (puck.getCircle().getCenterY() > Main.frameHeight) {
            Main.enemyText.setText(String.valueOf(++Main.enemyScore));
            resetAll();
        }
    }

    public void resetAll() {
        client.sendMessage("score " + Main.mainText.getText() + " " + Main.enemyText.getText());
        puck.getCircle().setCenterX(Main.frameWidth/2);
        puck.getCircle().setCenterY(Main.frameHeight/2);
        puck.setVelocity(new Vector2d(0d, 0d));
        enemyBall.getCircle().setCenterY(0d + enemyBall.getRadius());
        enemyBall.getCircle().setCenterX(Main.frameWidth/2);
        mainBall.getCircle().setCenterY(Main.frameHeight - enemyBall.getRadius());
        mainBall.getCircle().setCenterX(Main.frameWidth/2);
    }

    public void checkCollisions(Ball ball) {
        Double mainBallX = ball.getCircle().getCenterX();
        Double mainBallY = ball.getCircle().getCenterY();
        Double distanceBetweenTwoBalls =Math.sqrt( Math.pow(puck.getCircle().getCenterX() - mainBallX, 2)
                + Math.pow(puck.getCircle().getCenterY() - mainBallY, 2));
        // between two balls
        // between two balls
        if (distanceBetweenTwoBalls - (puck.getRadius() + ball.getRadius())< 1) {
            ball.getCircle().setCenterX(mainBallX);
            ball.getCircle().setCenterY(mainBallY);
            collisionsHandling.setPuck(puck);
//            puck.getCircle().setFill(Color.RED);
            Vector2d velocity = new Vector2d(puck.getCircle().getCenterX() - mainBallX, puck.getCircle().getCenterY() - mainBallY);
            velocity.normalize();
            puck.setVelocity(velocity);
//            puck.setVelocity(new Vector2d(0d - puck.getCircle().getCenterX(), 0d - puck.getCircle().getCenterY()));
            collisionsHandling.speed = 6d;
            String musicFile1 = "src/assets/sound/ta.mp3";     // For example
            String musicFile2 = "src/assets/sound/sha.mp3";     // For example

            Media sound1 = new Media(new File(musicFile1).toURI().toString());
            MediaPlayer mediaPlayer1 = new MediaPlayer(sound1);
            Media sound2 = new Media(new File(musicFile2).toURI().toString());
            MediaPlayer mediaPlayer2 = new MediaPlayer(sound2);
            if (ball instanceof MainBall) {
                mediaPlayer1.play();
            } else {
                mediaPlayer2.play();
            }
        }


        // walls


    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public SocketClient getClient() {
        return client;
    }

    public void setClient(SocketClient client) {
        this.client = client;
    }
}
