package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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

    public void initialize(){
        new Thread(() -> {
            try {
                this.socket = new Socket("localhost", PORT);
                this.userName=LoginFormController.uName;
                this.dataInputStream = new DataInputStream(socket.getInputStream());
                this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
                listenForMsg();

                //vbox property
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
        stage.close();
    }

    public void btnSendOnAction(ActionEvent actionEvent) {
        try {

            while (socket.isConnected()) { //***************
                String msgSend = txtMassage.getText();
                dataOutputStream.writeUTF(userName + "-" + msgSend);
                dataOutputStream.flush();

                HBox hBox = new HBox();
                hBox.setAlignment(Pos.CENTER_RIGHT);
                hBox.setPadding(new Insets(5, 5, 5, 10));

                Label label = new Label(msgSend);
                label.setStyle("-fx-background-color: #0f7df2;" + "-fx-background-radius: 20px;");
                label.setPadding(new Insets(5, 10, 5, 10));
                label.setTextFill(Color.color(0.934, 0.945, 0.996));

                hBox.getChildren().add(label);
                vBox.getChildren().add(hBox);

            } /************/

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
                        HBox hBox = new HBox();
                        hBox.setAlignment(Pos.CENTER_LEFT);
                        hBox.setPadding(new Insets(5,5,5,10));

                        Label label = new Label(msgFromGroupChat);
                        label.setStyle("-fx-background-color: #EAEAEA;" + "-fx-background-radius: 20px;");
                        label.setPadding(new Insets(5,10,5,10));
                        label.setTextFill(Color.color(0.934,0.945,0.996));

                        hBox.getChildren().add(label);
                        vBox.getChildren().add(hBox);

                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}
