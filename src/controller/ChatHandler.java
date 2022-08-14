package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ChatHandler implements Runnable{

    Socket socket;
    String userName;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    public static ArrayList<ChatHandler> chatHandlers = new ArrayList<>();

    public ChatHandler(Socket socket, String userName) {
        this.socket=socket;
        this.userName=userName;
        chatHandlers.add(this);
    }

    @Override
    public void run() {

    }
}
