package Client3.gameProcess;

import Client3.app.Main;
import Client3.physics.Vector2d;
import Client3.balls.Ball;
import Client3.balls.EnemyBall;
import Client3.balls.MainBall;
import Client3.balls.Puck;
import Client3.gameProcess.CollisionsHandling;
import Client3.net.SocketClient;
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
        if (puck.getCircle().getCenterY() < 0) {
            Main.enemyText.setText(String.valueOf(Main.enemyScore++));

            resetAll();
        }
        if (puck.getCircle().getCenterY() > Main.frameHeight) {
            Main.mainText.setText(String.valueOf(Main.mainScore++));
            resetAll();
        }
    }

    public void resetAll() {
        System.out.println("Mы тута ");
        puck.getCircle().setCenterX(Main.frameWidth/2);
        puck.getCircle().setCenterY(Main.frameHeight/2);
        puck.setVelocity(new Vector2d(0d, 0d));
        enemyBall.getCircle().setCenterY(0d + enemyBall.getRadius());
        enemyBall.getCircle().setCenterX(Main.frameWidth/2);
        mainBall.getCircle().setCenterY(Main.frameHeight - enemyBall.getRadius());
        mainBall.getCircle().setCenterX(Main.frameWidth/2);
    }



}
