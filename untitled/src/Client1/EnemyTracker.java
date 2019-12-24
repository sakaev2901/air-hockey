package Client1;

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

            double x =  400 - Double.valueOf(location[0]);
            double y =  450 - Double.valueOf(location[1]);
            if (x > 400 - 25) {
                x = 375;
            }
            if (x < 25) {
                x = 25;
            }
            System.out.println("not reverse location: " + location[0] + " " + location[1]);
            System.out.println("current location:" +  x + " " + y);
            enemyCircle.setCenterX(x);
            enemyCircle.setCenterY(y);
//            enemyCircle.setTranslateX(x);
//            enemyCircle.setTranslateY(y);
        }

    }
}
