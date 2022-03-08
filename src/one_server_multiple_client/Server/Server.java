package one_server_multiple_client.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Server {

    public static ArrayList<ClientHandler> clientList = new ArrayList<ClientHandler>();

    public static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));


    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("Waiting for the client...");

        new Thread(() -> {
            while (true) {
                try {
                    Socket soc = serverSocket.accept();
                    DataInputStream dis = new DataInputStream(soc.getInputStream());
                    DataOutputStream dos = new DataOutputStream(soc.getOutputStream());
                    String name = dis.readUTF();
                    System.out.println(name + " is connected");
                    ClientHandler clientHandler = new ClientHandler(soc, name, dis, dos);
                    Thread thread = new Thread(clientHandler);
                    clientList.add(clientHandler);
                    thread.start();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }

            }
        }).start();
        while (true) {

            String messageCode = input.readLine();
            StringTokenizer str = new StringTokenizer(messageCode, "-");
            String messageToShow = str.nextToken();
            String recipient = str.nextToken();
            for (ClientHandler client : Server.clientList) {
                if (recipient.equals("all")) {
                    client.dos.writeUTF("Server :" + messageToShow);
                    client.dos.flush();
                }
                if (client.name.equals(recipient)) {
                    client.dos.writeUTF("Server :" + messageToShow);
                    client.dos.flush();
                    break;

                }
            }

        }

    }


}