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

public class ChatRoomFormController {

    public JFXTextField txtMassage;
    public ScrollPane scrlPane;
    public VBox vBox;
    public AnchorPane contextChatRoom;

    public void btnLeaveOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) contextChatRoom.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml"))));
    }

    public void btnSendOnAction(ActionEvent actionEvent) {
    }
}
