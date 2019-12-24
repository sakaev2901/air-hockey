package Client1.gameProcess;

import Client1.balls.EnemyBall;
import Client1.balls.MainBall;
import Client1.balls.Puck;
import javafx.animation.AnimationTimer;

public class CollisionsHandling extends AnimationTimer {
    private Puck puck;
    private MainBall mainBall;
    private EnemyBall enemyBall;
    Double speed = 0d;

    public CollisionsHandling() {

    }

    @Override
    public void handle(long now) {
        puck.move();
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
}
