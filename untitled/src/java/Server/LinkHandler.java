package Server;

import javax.sound.sampled.Line;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class LinkHandler extends Thread {
    private Socket sender;
    private Socket receiver;
    private BufferedReader inSender;
    private PrintWriter outSender;


    public LinkHandler(Socket sender, Socket receiver) {
        this.sender = sender;
        this.receiver = receiver;
        try {
            this.inSender = new BufferedReader(new InputStreamReader(sender.getInputStream()));
            this.outSender = new PrintWriter(receiver.getOutputStream(), true);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

    }

    @Override
    public void run() {
        String inputLine;
        while (true) {
            try {
                if (!(!sender.isClosed() &&(inputLine = inSender.readLine()) != null)) break;
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
            outSender.println(inputLine);
        }
    }
}
