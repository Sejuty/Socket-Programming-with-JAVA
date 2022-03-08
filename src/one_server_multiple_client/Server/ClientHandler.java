package one_server_multiple_client.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler implements Runnable {
    public String name;
    Socket soc;
    final DataOutputStream dos;
    final DataInputStream dis;

    public ClientHandler(Socket soc, String name, DataInputStream dis, DataOutputStream dos) {
        this.soc = soc;
        this.name = name;
        this.dis = dis;
        this.dos = dos;

    }

    @Override
    public void run() {
        while (true) {
            String message;
            try {
                message = dis.readUTF();
                System.out.println(name+": "+message);
                break;

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        }
    }
}