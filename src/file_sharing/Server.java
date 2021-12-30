package file_sharing;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(22222);
        System.out.println("Server Started...");


        //Creating a File object for directory
        File directoryPath = new File("G:\\ServerFiles");
        //List of all files and directories
        File filesList[] = directoryPath.listFiles();
        System.out.println("List of files and directories in the specified directory:");
        for (File file : filesList) {
            System.out.println("File name: " + file.getName());
            System.out.println("File path: " + file.getAbsolutePath());
            System.out.println("Size :" + file.getTotalSpace());
            System.out.println(" ");
        }

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Client Connected...");
            ObjectInputStream oin = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            try {
                //read from client
                Object cMsg = oin.readObject();
                //System.out.println("Requested file from client : " + (String) cMsg);
                for (File compareFile : filesList) {
                    if (cMsg.equals(compareFile.getName())) {
                        System.out.println("Requested file from client : " + (String) cMsg);
                        String requestedFileName = "G:\\ServerFiles\\" + (String) cMsg;
                        //System.out.println(requestedFileName);
                        File file = new File(requestedFileName);
                        FileInputStream fr = new FileInputStream(requestedFileName);
                        byte[] b = new byte[(int) file.length()];
                        fr.read(b, 0, b.length);
                        OutputStream os = socket.getOutputStream();
                        os.write(b, 0, b.length);
                    }

                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
}


