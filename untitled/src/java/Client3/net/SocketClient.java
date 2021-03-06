package Client3.net;

//import controllers.MessageResolver;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    public static String responce;

    public void startConnection(String ip, int port) {
        try {
            clientSocket = new Socket(ip, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
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
                        SocketClient.responce = response;
                    }
                } catch (IOException e) {
                    throw new IllegalStateException(e);
                }
            }

        }
    };

    public void sendMessage(String message) {
        out.println(message);
        }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public PrintWriter getOut() {
        return out;
    }

    public void setOut(PrintWriter out) {
        this.out = out;
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
