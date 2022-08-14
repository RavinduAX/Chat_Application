package controller;

import java.net.Socket;

public class ChatHandler implements Runnable{

    Socket socket;
    String userName;

    public ChatHandler(Socket socket, String userName) {
        this.socket=socket;
        this.userName=userName;
    }

    @Override
    public void run() {

    }
}
