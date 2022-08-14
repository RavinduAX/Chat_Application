package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class LoginFormController {

    public JFXTextField txtUserName;
    public AnchorPane contextLogin;
    ServerSocket serverSocket;
    Socket socket;
    final int PORT = 9999;

    public void initialize(){
        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(PORT);
                System.out.println("Server Conneced !");
                socket = serverSocket.accept();
                System.out.println("Client Conneced !");

            }catch (IOException e){
                e.printStackTrace();
            }
        }).start();
    }

    public void btnLogInOnAction(ActionEvent actionEvent) throws IOException {
        String userName = txtUserName.getText();
        System.out.println(userName);

//        Stage stage = new Stage();
//        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ChatRoomForm.fxml"))));
//        stage.setTitle("Chat Room");
//        stage.show();

        Stage stage = (Stage) contextLogin.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ChatRoomForm.fxml"))));

    }
}
