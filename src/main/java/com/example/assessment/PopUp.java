package com.example.assessment;

import javafx.scene.control.Label;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * Utility class for displaying a pop-up message in a JavaFX application.
 */
public class PopUp {

    private String style = "-fx-background-color:grey; -fx-padding: 10 10 10 10;" +
            "-fx-font-size: 20px;";

    private int minWidth = 100;

    private int minHeight = 50;

    /**
     * Constructs a PopUp object that displays a message in a popup window.
     *
     * @param text The message text to be displayed in the popup.
     * @param currStage The current stage on which the popup will be displayed.
     */
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
