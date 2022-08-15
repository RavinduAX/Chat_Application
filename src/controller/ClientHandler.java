package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{

    Socket socket;
    String userName;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();

    public ClientHandler(String userName, Socket socket) {
        try {
            this.socket=socket;
            this.userName=userName;
            this.dataInputStream = new DataInputStream(socket.getInputStream());
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
            clientHandlers.add(this);
            //****
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String msgFromClient;
        try {
            while (socket.isConnected()){
                msgFromClient = dataInputStream.readUTF();
                broadcastMsg(msgFromClient);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void broadcastMsg(String msgToSend){
        for (ClientHandler clientHandler : clientHandlers) {
            try {
                if (!clientHandler.userName.equals(userName)){
                    clientHandler.dataOutputStream.writeUTF(msgToSend);
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

}
