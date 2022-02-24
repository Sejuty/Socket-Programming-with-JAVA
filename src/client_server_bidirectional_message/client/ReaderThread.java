package client_server_bidirectional_message.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

class ReaderThread implements Runnable {

    Socket socket;
    Thread t;

    public ReaderThread(Socket socket) {
        this.socket = socket;
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(socket.getInputStream());
            while (true) {
                //read from server...
                Object cMsg = ois.readObject();
                System.out.println("From Server: " + (String) cMsg);
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if(ois != null)
                    ois.close();
                if(socket != null)
                    socket.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

    }
}
