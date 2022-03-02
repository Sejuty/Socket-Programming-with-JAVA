package one_server_multiple_client.Server;

import one_server_multiple_client.Client.ClientMap;
import one_server_multiple_client.SpecificClient;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;

public class WriteToClient implements Runnable {
    private String name;
    private Socket socket;
    private BufferedWriter oos;

    @Override
    public void run() {
        System.out.println("Enter the client name:");
        Scanner scanner = new Scanner(System.in);
        SpecificClient.UserName = scanner.nextLine();
        name = SpecificClient.UserName;
        for (Map.Entry m : ClientMap.map.entrySet()) {
            if (m.getKey().equals(name)) {
                SpecificClient.ClientSocket = (Socket) m.getValue();
            }
        }
        socket = SpecificClient.ClientSocket;
        System.out.println(socket);
        String cMsg;
        Scanner scan = null;
        try {
            oos = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            System.out.println("Enter The message -->");
            scan = new Scanner(System.in);
            cMsg = scan.nextLine();
            oos.write(cMsg);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null)
                    oos.close();
                if (socket != null)
                    socket.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

