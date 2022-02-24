package client_server_bidirectional_message.client;


import java.io.IOException;
import java.net.Socket;

public class ClientUsingThread {
    public static void main(String[] args) throws IOException {

        try {
            System.out.println("Client started...");
            Socket socket = new Socket("127.0.0.1", 22222);
            System.out.println("Client Connected...");

            new ReaderThread(socket);
            new WriterThread(socket);


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
