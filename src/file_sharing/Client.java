package file_sharing;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        System.out.println("Client started...");

        Socket socket = new Socket("127.0.0.1", 22222);
        while (true) {
            System.out.println("Client Connected...");

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            //to take input
            System.out.println("Enter the Requested File name: ");
            Scanner sc = new Scanner(System.in);

            String message = sc.nextLine();

            //requested file name sent to server
            oos.writeObject(message);
            File serverFile = new File("G:\\ServerFiles\\" + (String) message);

            byte[] b = new byte[(int) serverFile.length()];

            InputStream is = socket.getInputStream();
            FileOutputStream fr = new FileOutputStream("G:\\ClientFiles\\dc.txt");
            is.read(b, 0, b.length);
            fr.write(b, 0, b.length);
            System.out.println("File Copied!!");
            System.out.println("=================\n\n");
        }
    }
}

