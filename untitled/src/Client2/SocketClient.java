package Client2;

//import controllers.MessageResolver;



import java.io.*;
import java.net.Socket;

public class SocketClient {
    private Socket clientSocket;
    private BufferedWriter out;
    private PrintWriter out2;
    private BufferedReader in;
//    MessageResolver resolver;

    public void startConnection(String ip, int port) {
        try {
            clientSocket = new Socket(ip, port);
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            this.out2 = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            new Thread(receiverMessageTask).start();
//            resolver = new MessageResolver(this);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    private Runnable receiverMessageTask = new Runnable() {
        @Override
        public void run() {
            while (true) {
                try {
                    String response = in.readLine();
                    if (response != null) {
//                        System.out.println(response);
//                        resolver.resolve(response);
//                        menuController.handleResponse(response);
                    }
                } catch (IOException e) {
                    throw new IllegalStateException(e);
                }
            }

        }
    };

    public void sendMessage(String message) {
//        try {
            System.out.println(message);
//            out.write(message);
            out2.println(message);
//            out.flush();
//        } catch (IOException e) {
//            throw new IllegalStateException(e);
//        }
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }


    public BufferedReader getIn() {
        return in;
    }

    public void setIn(BufferedReader in) {
        this.in = in;
    }

    public Runnable getReceiverMessageTask() {
        return receiverMessageTask;
    }

    public void setReceiverMessageTask(Runnable receiverMessageTask) {
        this.receiverMessageTask = receiverMessageTask;
    }
}
