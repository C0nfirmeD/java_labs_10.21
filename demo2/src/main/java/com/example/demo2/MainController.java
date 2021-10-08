package com.example.demo2;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;


public class MainController {

    private Boolean[] reserved = new Boolean[36];

    @FXML
    void initialize() {
        grid.getChildren().stream().forEach(a -> System.out.println(((Label)a).getText()));
        for (int i = 0; i < 36; i++) {
            reserved[i] = false;
            }
    }

    @FXML
    private Label informLabel;

    @FXML
    private GridPane grid;

    @FXML
    private Button button;

    @FXML
    private TextField choiceField;

    @FXML
    void okBTN(ActionEvent event) {
        String text = choiceField.getText();
        try {
            if (!reserved[Integer.parseInt(text)-1]){
                grid.getChildren().filtered(elem -> ((Label)elem).getText().
                        equals(text)).get(0).setStyle("-fx-background-color: #A52A2A;");
                reserved[Integer.parseInt(text)-1] = true;
                informLabel.setText("Вы заняли место " + text + "!");
            } else {
                informLabel.setText("Место " + text + " уже занято");
            }

        } catch (Exception e){
            informLabel.setText("Неверный номер места");
        }

    }

    @FXML
    void handleDelete(ActionEvent event) {
        for (int i = 0; i < 36; i++) {
            reserved[i] = false;
        }
        grid.getChildren().forEach(elem -> elem.setStyle("-fx-background-color: #32CD32;"));
    }

    @FXML
    void handleExit(ActionEvent event) {
        Platform.exit();
    }



}
