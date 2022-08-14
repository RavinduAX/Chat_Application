package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatRoomFormController {

    public JFXTextField txtMassage;
    public ScrollPane scrlPane;
    public VBox vBox;
    public AnchorPane contextChatRoom;
    Socket socket;
    final int PORT = 9999;

    public void initialize(){
        new Thread(() -> {
            try {
                socket = new Socket("localhost", PORT);

            }catch (IOException e){
                e.printStackTrace();
            }
        }).start();
    }

    public void btnLeaveOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) contextChatRoom.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml"))));
    }

    public void btnSendOnAction(ActionEvent actionEvent) {

    }
}
