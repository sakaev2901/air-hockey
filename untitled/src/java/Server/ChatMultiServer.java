package Server;


import java.io.IOException;
import java.net.ServerSocket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ChatMultiServer  {
    private List<ClientHandler> clients;
    private List<Room> rooms;

    public ChatMultiServer() {
        clients = new CopyOnWriteArrayList<>();
        rooms = new CopyOnWriteArrayList<>();
    }

    public void start(int port) {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        while (true) {
            try {
                new ClientHandler(serverSocket.accept(), this).start();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    public List<ClientHandler> getClients() {
        return clients;
    }


    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public void setClients(List<ClientHandler> clients) {
        this.clients = clients;
    }
}
