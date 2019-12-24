package Client3.gameProcess;

import Client1.app.Main;
import Client1.balls.Ball;
import Client1.balls.EnemyBall;
import Client1.balls.MainBall;
import Client1.balls.Puck;
import Client1.gameProcess.CollisionsHandling;
import Client1.physics.Vector2d;
import javafx.animation.AnimationTimer;

public class CollisionChecking extends AnimationTimer {

    private Puck puck;
    private MainBall mainBall;
    private EnemyBall enemyBall;
    private CollisionsHandling collisionsHandling;

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
            puck.getVelocity().setX(puck.getVelocity().getX() * -1);
            puck.getVelocity().setY(puck.getVelocity().getY());
        }
        if (puck.getCircle().getCenterX() >= (Main.frameWidth -  puck.getRadius())) {
            puck.getVelocity().setX(puck.getVelocity().getX() * -1);
            puck.getVelocity().setY(puck.getVelocity().getY());
        }
        if (puck.getCircle().getCenterY() <= puck.getRadius()) {
            puck.getVelocity().setY(puck.getVelocity().getY() * -1);
            puck.getVelocity().setX(puck.getVelocity().getX());
        }
        if (puck.getCircle().getCenterY() >= (Main.frameHeight - puck.getRadius())) {
            puck.getVelocity().setY(puck.getVelocity().getY() * -1);
            puck.getVelocity().setX(puck.getVelocity().getX());
        }
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
            System.out.println(mainBallX + " " + mainBallY);
        }


        // walls


    }
}
