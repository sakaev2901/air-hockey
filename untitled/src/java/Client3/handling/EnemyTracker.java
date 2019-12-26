package Client3.handling;

import Client3.app.Main;
import Client3.net.SocketClient;
import javafx.animation.AnimationTimer;
import javafx.scene.shape.Circle;

public class EnemyTracker extends AnimationTimer {

    private Circle enemyCircle;
    private Circle puckCircle;

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
        if (SocketClient.responce != null) {
            String[] location = SocketClient.responce.split(" ");
            if (location[0].equals("ball")) {
                double x = Main.frameWidth - Double.valueOf(location[1]);
                double y = Main.frameHeight - Double.valueOf(location[2]);
                double puckX = Main.frameWidth - Double.valueOf(location[3]);
                double puckY = Main.frameHeight - Double.valueOf(location[4]);
                enemyCircle.setCenterX(x);
                enemyCircle.setCenterY(y);
                puckCircle.setCenterX(puckX);
                puckCircle.setCenterY(puckY);
            } else {
                Main.mainText.setText(location[2]);
                Main.enemyText.setText(location[1]);
            }

        }
    }

    public Circle getPuckCircle() {
        return puckCircle;
    }

    public void setPuckCircle(Circle puckCircle) {
        this.puckCircle = puckCircle;
    }
}
