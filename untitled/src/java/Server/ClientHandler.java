package Server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ClientHandler extends Thread {
    private Socket clientSocket;
    private BufferedReader in;
//    private MessageResolver messageResolver;
    private List<ClientHandler> clients;
    private List<Room> rooms;
    private ChatMultiServer server;
    private PrintWriter out;

    ClientHandler(Socket socket, ChatMultiServer chatMultiServer) {
        this.server = chatMultiServer;
        this.clientSocket = socket;
        this.rooms = chatMultiServer.getRooms();
        clients = chatMultiServer.getClients();
//        clients.add(this);
        System.out.println("New Client");
        try {
            this.out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine;
            while (!clientSocket.isClosed() &&(inputLine = in.readLine()) != null) {
                String[] parts = inputLine.split(" ");
                if (parts[0].equals("new-room")) {
                    Room room = new Room(clientSocket);
                    rooms.add(room);
                } else if(parts[0].equals("get-rooms")) {
                    //TODO
                    String roomList ="";
                    for (Room room:rooms) {
                        roomList += room.getId() + " ";
                    }
                    out.println(roomList);
                } else if(parts[0].equals("enter-room")) {
                    for (Room room :rooms) {
                        if(room.getId() == Integer.parseInt(parts[1])) {
                            room.setClientUnRoot(clientSocket);
                            System.out.println("success");
                        }
                    }
                }
            }
            in.close();
            clientSocket.close();
            clients.remove(this);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public Socket getClientSocket() {
        return clientSocket;
    }
}