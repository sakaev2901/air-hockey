package Client1.handling;

import Client1.app.Main;
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

    public EnemyTracker(Circle enemyCircle) {
        this.enemyCircle = enemyCircle;
        this.start();
    }

    @Override
    public void handle(long now) {
        if (SocketClient.responce != null){
            String[] location = SocketClient.responce.split(" ");
            if (location[0].equals("ball")) {
                double x =  Main.frameWidth - Double.valueOf(location[1]);
                double y =  Main.frameHeight - Double.valueOf(location[2]);
                enemyCircle.setCenterX(x);
                enemyCircle.setCenterY(y);
            }
//            if (x > 400 - 25) {
//                x = 375;
//            }
//            if (x < 25) {
//                x = 25;
//            }

//            enemyCircle.setTranslateX(x);
//            enemyCircle.setTranslateY(y);
        }

    }
}
