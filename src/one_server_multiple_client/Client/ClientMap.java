package one_server_multiple_client.Client;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ClientMap {
    public static Map<String, Socket> map=new HashMap<>();
}
