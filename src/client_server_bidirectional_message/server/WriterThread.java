package client_server_bidirectional_message.server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

class WriterThread implements Runnable{

    Socket socket;
    Thread t;

    public WriterThread(Socket socket) {
        this.socket = socket;
        t = new Thread(this);
        t.start();

    }

    @Override
    public void run() {

        ObjectOutputStream oos = null;
        Scanner scanner =null;
        try{
            oos = new ObjectOutputStream(socket.getOutputStream());
            scanner = new Scanner(System.in);
            String cMsg;
            while(true)
            {

                cMsg = scanner.nextLine();
                oos.writeObject(cMsg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(oos != null)
                    oos.close();
                if(socket != null)
                    socket.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}

