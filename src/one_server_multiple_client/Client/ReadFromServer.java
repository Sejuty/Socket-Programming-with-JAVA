package one_server_multiple_client.Client;

import one_server_multiple_client.SpecificClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReadFromServer implements Runnable {
    private String name;
    private Socket socket;
    private BufferedReader bufferedReader;


    @Override
    public void run() {
        String messageFromChat ="";
        name = SpecificClient.UserName;
        System.out.println(name);
        socket = SpecificClient.ClientSocket;
        System.out.println(socket);
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            messageFromChat = bufferedReader.readLine();
            System.out.println("Message from Server: " + messageFromChat);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null)
                    bufferedReader.close();
                if (socket != null)
                    socket.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());

            }
        }
    }
}

