package com.itp.pos.controller;

import com.itp.pos.db.Database;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Date;


public class BackupFormController {
    public AnchorPane context;
    public TextArea txtClipboard;

    public void initialize(){
        setClipboardData();
    }

    private void setClipboardData() {
        txtClipboard.setEditable(false);
        // create the header ==========
        txtClipboard.appendText("Date: "+ new Date());
        txtClipboard.appendText("\n===========================");

        txtClipboard.appendText(
                "\n\n*====Customer Data====*\n"
        );
        for (int i = 0; i <
                Database.customerTable.size(); i++) {
            txtClipboard.appendText(
                    i+". "+Database.customerTable.get(i)
                            .toString()+
                            "\n"
            );
        }

    }


    public void backToHomeOnAction(ActionEvent actionEvent) {
        try {
            setUi("DashboardForm");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void startBackupOnAction(ActionEvent actionEvent) {
    }

    private void setUi(String location) throws IOException {
        Stage stage = (Stage)
                context.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"));

        FadeTransition fadeOut = new FadeTransition(Duration.millis(300), stage.getScene().getRoot());
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setOnFinished(event -> {

            stage.setScene(new Scene(root));


            FadeTransition fadeIn = new FadeTransition(Duration.millis(300), root);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();
        });


        fadeOut.play();

    }

}