package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ChatHandler implements Runnable{

    Socket socket;
    String userName;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    public static ArrayList<ChatHandler> chatHandlers = new ArrayList<>();

    public ChatHandler(Socket socket, String userName) {
        try {
            this.socket=socket;
            this.userName=userName;
            this.dataInputStream = new DataInputStream(socket.getInputStream());
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
            chatHandlers.add(this);


        }catch (IOException e){
            e.printStackTrace();
        }


    }

    @Override
    public void run() {

    }
}
