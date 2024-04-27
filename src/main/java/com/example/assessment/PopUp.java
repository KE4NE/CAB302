package com.example.assessment;

import javafx.scene.control.Label;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class PopUp {

    private String style = "-fx-background-color:grey; -fx-padding: 10 10 10 10;" +
            "-fx-font-size: 20px;";

    private int minWidth = 100;

    private int minHeight = 50;
    public PopUp(String text, Stage currStage) {
        final Popup popup = new Popup();
        Label label = new Label(text);
        label.setMinWidth(minWidth);
        label.setMinHeight(minHeight);
        label.setStyle(style);
        popup.getContent().add(label);
        popup.show(currStage);
        popup.setAutoHide(true);
    }
}
