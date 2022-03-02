package one_server_multiple_client.Server;

import one_server_multiple_client.Client.ClientMap;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {

    //loop through all the client to send message
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String clientUserName;

    public ClientHandler(Socket socket) {

        try {
            this.socket = socket;

            //convert byte stream to char stream
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.clientUserName = bufferedReader.readLine();
            //adding clientHandler object to the arrayList
            clientHandlers.add(this);
            broadCastMessage(clientUserName + " has entered the chat!!");
            System.out.println(clientUserName+" has entered the chat!!!");
            ClientMap.map.put(clientUserName, socket);
        } catch (Exception e) {
            closeEverything(socket, bufferedWriter, bufferedReader);
        }
    }

    @Override
    public void run() {
        String cMsg;
        while (socket.isConnected()) {
            try {
                cMsg = bufferedReader.readLine();
                broadCastMessage(cMsg);
                System.out.println(cMsg);
            } catch (Exception e) {
                closeEverything(socket, bufferedWriter, bufferedReader);
                break;
            }

        }

    }

    public void broadCastMessage(String message) {
        for (ClientHandler clientHandler : clientHandlers) {
            try {
                if (!clientHandler.clientUserName.equals(clientUserName)) {
                    clientHandler.bufferedWriter.write(message);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
                }
            } catch (Exception e) {
                closeEverything(socket, bufferedWriter, bufferedReader);
            }
        }
    }

//    public void removeClientHandler()
//    {
//        clientHandlers.remove(this);
//        broadCastMessage("Server : "+clientUserName+" has left the chat");
//    }
    public void closeEverything(Socket socket, BufferedWriter bufferedWriter, BufferedReader bufferedReader)
    {
        //removeClientHandler();
        try{
            if(bufferedReader!=null)
            {
                bufferedReader.close();
            }
            if(bufferedWriter != null)
            {
                bufferedWriter.close();
            }
            if(socket != null)
            {
                socket.close();
            }
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
