package Client1.handling;

import Client1.balls.MainBall;
import Client1.balls.Puck;
import Client1.net.SocketClient;
import javafx.animation.AnimationTimer;

public class BallsTracker extends AnimationTimer {
    private Puck puck;
    private MainBall mainBall;
    private SocketClient client;


    @Override
    public void handle(long now) {
        String location = "ball " + mainBall.getCircle().getCenterX() + " " + mainBall.getCircle().getCenterY()  + " " + puck.getCircle().getCenterX() + " "
                + puck.getCircle().getCenterY();
        client.sendMessage(location);
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

    public SocketClient getClient() {
        return client;
    }

    public void setClient(SocketClient client) {
        this.client = client;
    }
}
