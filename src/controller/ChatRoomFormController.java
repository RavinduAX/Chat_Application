package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatRoomFormController {

    public JFXTextField txtMassage;
    public ScrollPane scrlPane;
    public VBox vBox;
    public AnchorPane contextChatRoom;
    Socket socket;
    String userName;
    final int PORT = 9999;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    public ChatRoomFormController(String userName) {
        this.userName = userName;
    }

    public void initialize(){
        new Thread(() -> {
            try {
                socket = new Socket("localhost", PORT);
                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());

                listenForMsg();

                vBox.heightProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                        scrlPane.setVvalue((Double) newValue);
                    }
                });

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
        try {
            String sendMsg = txtMassage.getText();
            dataOutputStream.writeUTF(sendMsg);
            dataOutputStream.flush();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void listenForMsg(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String msgFromGroupChat;

                while(socket.isConnected()){
                    try {
                        msgFromGroupChat=dataInputStream.readUTF();

                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }

}
