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
                    }
                } catch (IOException e) {
                    throw new IllegalStateException(e);
                }
            }

        }
    };

    public void sendMessage(String message) {
            System.out.println(message);
            out2.println(message);
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
