package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class LoginFormController {

    public JFXTextField txtUserName;
    public AnchorPane contextLogin;

    ServerSocket serverSocket;
    Socket socket;
    final int PORT = 9999;
    String userName;

    static String uName;

    public void initialize(){
        new Thread(() -> {
            try {
                    serverSocket = new ServerSocket(PORT);
                    System.out.println("Server Connected..!");
                    socket = serverSocket.accept();
                    System.out.println("Client Connected..!");
            }catch (IOException e){
                e.printStackTrace();
            }
        }).start();
    }

    public void btnLogInOnAction(ActionEvent actionEvent) throws IOException {
        userName = txtUserName.getText();
        uName = userName;
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("ChatRoomForm.fxml"))));
        stage.setTitle(userName);
        stage.show();

        try {
            ClientHandler clientHandler = new ClientHandler(userName, socket);
            Thread thread = new Thread(clientHandler);
            thread.start();
        }catch (Exception e){
            e.printStackTrace();
        }
        txtUserName.clear();
    }
}
