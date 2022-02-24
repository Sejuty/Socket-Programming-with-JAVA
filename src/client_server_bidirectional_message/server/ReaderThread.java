package client_server_bidirectional_message.server;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

class ReaderThread implements Runnable {

    Socket clientSocket;
    Thread t;

    ReaderThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
        t = new Thread(this);
        t.start();
    }


    @Override
    public void run() {

        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(clientSocket.getInputStream());

            while (true) {
                //read from client...
                Object cMsg = ois.readObject();
                System.out.println("From Client: " + (String) cMsg);
            }

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(ois != null)
                    ois.close();
                if(clientSocket != null)
                    clientSocket.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

    }
}

