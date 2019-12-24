package Client2;

import Client1.net.SocketClient;
import javafx.animation.AnimationTimer;
import javafx.scene.shape.Circle;

public class EnemyTracker extends AnimationTimer {

    private Circle enemyCircle;

    public Circle getEnemyCircle() {
        return enemyCircle;
    }

    public void setEnemyCircle(Circle enemyCircle) {
        this.enemyCircle = enemyCircle;
    }

    @Override
    public void handle(long now) {
        if (SocketClient.responce != null){
            String[] location = SocketClient.responce.split(" ");
            double x = Double.valueOf(location[0]);
            double y = Double.valueOf(location[1]);
            System.out.println(x + " " + y);

            enemyCircle.setTranslateX(x);
            enemyCircle.setTranslateY(y);
        }

    }
}
