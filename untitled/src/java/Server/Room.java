package Server;

import sun.plugin.javascript.navig4.Link;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Room {

    private Integer id;
    private Socket clientRoot;
    private Socket clientUnRoot;

    public Room(Socket clientRoot) {
        id = ++Constants.id;
        this.clientRoot = clientRoot;
    }

    public void startGame() {
        new LinkHandler(clientRoot, clientUnRoot).start();
        new LinkHandler(clientUnRoot, clientRoot).start();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Socket getClientRoot() {
        return clientRoot;
    }

    public void setClientRoot(Socket clientRoot) {
        this.clientRoot = clientRoot;
        startGame();
    }

    public Socket getClientUnRoot() {
        return clientUnRoot;
    }

    public void setClientUnRoot(Socket clientUnRoot) {
        this.clientUnRoot = clientUnRoot;
        startGame();
    }
}
