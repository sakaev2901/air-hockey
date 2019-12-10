package Client1;

public class StartClientServer {
    public static void main(String[] args) {
//        DragAndMove dragAndMove = new DragAndMove();
//        dragAndMove.startGame();
        Client1Server server = new Client1Server();
        server.start(5555);
    }
}
