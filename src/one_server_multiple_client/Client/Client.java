package one_server_multiple_client.Client;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket clientSocket=new Socket("localhost",5000);
        DataInputStream dis=new DataInputStream(clientSocket.getInputStream());
        DataOutputStream dos=new DataOutputStream(clientSocket.getOutputStream());
        System.out.println("Enter your name: ");
        BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
        String name=input.readLine();
        dos.writeUTF(name);

        Thread sendmessage = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true)
                {
                    try {
                        String message = input.readLine();
                        dos.writeUTF(message);
                        dos.flush();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }


            }
        });

        Thread recieveMessage = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true)
                {

                    try {
                        String reciveMessage = dis.readUTF();
                        System.out.println(reciveMessage);
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }

            }
        });
        sendmessage.start();
        recieveMessage.start();


    }
}