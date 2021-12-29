package client_server_application_using_threading;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientUsingThread {
    public static void main(String[] args) throws IOException {
        System.out.println("Client started...");


            Socket socket = new Socket("127.0.0.1", 22222);
            System.out.println("Client Connected...");

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

        while (true) {
            //to take input
            Scanner sc = new Scanner(System.in);

            String message = sc.nextLine();
            if(message.equals("exit"))
                break;
            if(message.equals(""))
            {
                System.out.println("ERROR!!!!\nEnter a Message...");
                socket.close();
                break;
            }
            //sent to server
            oos.writeObject(message);

            try {
                Object fromServer = ois.readObject();
                System.out.println((String) fromServer);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
        socket.close();
    }
}
