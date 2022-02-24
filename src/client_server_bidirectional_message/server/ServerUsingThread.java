package client_server_bidirectional_message.server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerUsingThread {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(22222);
        System.out.println("Server Started..");

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Client connected..");

            // new Server Thread Start.....
            new ReaderThread(socket);
            new WriterThread(socket);

        }
    }
}

