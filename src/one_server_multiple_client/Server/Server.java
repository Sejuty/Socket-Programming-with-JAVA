package one_server_multiple_client.Server;


import one_server_multiple_client.Client.ReadFromServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class Server {
    private ServerSocket serverSocket;


    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void startServer() throws IOException, InterruptedException {
        while (!serverSocket.isClosed()) {
            Socket socket = serverSocket.accept();
            System.out.println("If you want to send a message to a client PRESS 1\nElse PRESS 0");
            ClientHandler clientHandler = new ClientHandler(socket);
            Thread thread = new Thread(clientHandler);
            thread.start();
            Scanner s = new Scanner(System.in);
            int flag = s.nextInt();
            if (flag == 1) {
                WriteToClient writeToClient = new WriteToClient();
                Thread t1 = new Thread(writeToClient);
                CountDownLatch latch = new CountDownLatch(1);
                t1.start();
                latch.await();
                ReadFromServer readFromServer = new ReadFromServer();
                Thread t2= new Thread(readFromServer);
                t2.start();
            } else if (flag == 0) {
                continue;
            }

        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(1234);
        Server server = new Server(serverSocket);
        server.startServer();
    }
}
