package com.projects.taxicorporation.client;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import static javafx.scene.paint.Color.WHITE;

public class GrayButtonDecorator extends ButtonDecorator {
    public GrayButtonDecorator(ButtonPrototype decoratedButton) {
        super(decoratedButton);
    }
    @Override
    public void draw(Controller controller, HandleButtonClick handleButtonClick) {
        AnchorPane buttonsAnchorPane = controller.getButtonsAnchorPane();
        Button button = new Button();
        button.setPrefHeight(this.decoratedButton.getHeight());
        button.setPrefWidth(this.decoratedButton.getWidth());
        button.setLayoutX(this.decoratedButton.getX());
        button.setLayoutY(this.decoratedButton.getY());
        button.setText(this.decoratedButton.getText());
        button.setFont(new Font("System Bold", 16.0d));
        button.setCursor(Cursor.HAND);
        button.setTextFill(WHITE);
        button.setOnMouseClicked(event -> {
            try {
                handleButtonClick.handleButtonClick();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        button.setStyle("-fx-background-color: rgb(134,134,134)");
        buttonsAnchorPane.getChildren().add(button);
    }
}
