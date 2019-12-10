package Client1;

import javafx.animation.AnimationTimer;
import javafx.scene.shape.Circle;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class EnemyTracker extends AnimationTimer {
    private Socket enemy;
    private Circle enemyCircle;

    public Socket getEnemy() {
        return enemy;
    }

    public void setEnemy(Socket enemy) {
        this.enemy = enemy;
    }

    public Circle getEnemyCircle() {
        return enemyCircle;
    }

    public void setEnemyCircle(Circle enemyCircle) {
        this.enemyCircle = enemyCircle;
    }

    @Override
    public void handle(long now) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(enemy.getInputStream()));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        String response = null;
        try {
            response = in.readLine();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        if (response != null) {
            String[] location = response.split(" ");
            double x = Double.valueOf(location[0]);
            double y = Double.valueOf(location[1]);
            enemyCircle.setTranslateX(x);
            enemyCircle.setTranslateY(y);

        }
    }
}
