package Client1;

import javafx.animation.AnimationTimer;
import javafx.scene.shape.Circle;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
//imp     try {
//            in = new DataInputStream(enemy.getInputStream());
//        } catch (IOException e) {
//            throw new IllegalStateException(e);
//        }ort java.io.InputStreamReader;
import java.net.Socket;

public class EnemyTracker extends AnimationTimer {
    private Socket enemy;
    private Circle enemyCircle;
    private DataInputStream in = null;

    public Socket getEnemy() {
        return enemy;
    }

    public void setEnemy(Socket enemy) {
        this.enemy = enemy;
        try {
            in = new DataInputStream(enemy.getInputStream());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

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
