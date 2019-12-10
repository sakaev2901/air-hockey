package Client1;


import Server.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Client1Server {
    private List<ClientHandler> clients;
    private Socket enemy;

    public Client1Server() {
        clients = new CopyOnWriteArrayList<>();
    }

    public void start(int port) {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        DragAndMove dragAndMove = new DragAndMove();
//        while (true) {
            try {
//                new ClientHandler(serverSocket.accept(), this).start();
                DragAndMove.enemy = serverSocket.accept();
                dragAndMove.startGame();


            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
//    }

    public List<ClientHandler> getClients() {
        return clients;
    }




    public void setClients(List<ClientHandler> clients) {
        this.clients = clients;
    }
}
