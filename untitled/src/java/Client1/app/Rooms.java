package Client1.app;

import Client1.net.SocketClient;
import javafx.application.Application;
import javafx.stage.Stage;

public class Rooms {
    public void start() {
        SocketClient client = new SocketClient();
        client.startConnection("localhost", 6666);
        client.sendMessage("get-rooms");
//        System.out.println(SocketClient.responce);
        while (SocketClient.responce == null) {
            System.out.print("");
            if (SocketClient.responce != null) {
                System.out.println(SocketClient.responce);
                break;
            }
        }

    }
}
