package Server;

public class StartServer {
    public static void main(String[] args) {
        ChatMultiServer chatMultiServer = new ChatMultiServer();
        chatMultiServer.start(6666);
    }
}
