package client_server_application_without_thread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

//without threading
public class server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(22222);
        System.out.println("Server Started...");
        while(true) {
            Socket socket = serverSocket.accept();
            System.out.println("Client Connected...");
            ObjectInputStream oin = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            try {
                //read from client
                Object cMsg = oin.readObject();
                System.out.println("From client : " + (String) cMsg);
                //String serverMsg = ((String) cMsg).toUpperCase();

                //sent to client...
                oos.writeObject("Message Received!!");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

}
