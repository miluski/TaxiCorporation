package com.projects.taxicorporation.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import static javafx.scene.paint.Color.WHITE;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(ApplicationExtension.class)
public class LightRedButtonDecoratorTest {
    private static AddManagerController addManagerController;

    @Test
    void draw_shouldCreateGrayButtonWithCorrectProperties(FxRobot robot) throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(() -> new javafx.application.Application() {
            @Override
            public void start(Stage primaryStage) {
                FXMLLoader fxmlLoader = new FXMLLoader(StartForm.class.getResource("AddManager.fxml"));
                try {
                    Scene scene = new Scene(fxmlLoader.load());
                    addManagerController = fxmlLoader.getController();
                    primaryStage.setTitle("Dodawanie menadżera");
                    primaryStage.setScene(scene);
                    primaryStage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        ButtonPrototype decoratedButton = new BlackButtonPrototype();
        decoratedButton.setHeight(30);
        decoratedButton.setWidth(100);
        decoratedButton.setX(50);
        decoratedButton.setY(20);
        decoratedButton.setText("Test Button");
        LightRedButtonDecorator lightRedButtonDecorator = new LightRedButtonDecorator(decoratedButton);
        Button createdButton = getButton(decoratedButton);
        robot.interact(() -> lightRedButtonDecorator.draw(addManagerController, null));
        assertEquals(decoratedButton.getHeight(), createdButton.getPrefHeight());
        AnchorPane buttonsAnchorPane = addManagerController.getButtonsAnchorPane();
        assertEquals(2, buttonsAnchorPane.getChildren().size());
        assertEquals("System", createdButton.getFont().getFamily());
        assertEquals(16.0d, createdButton.getFont().getSize());
        assertEquals(Cursor.HAND, createdButton.getCursor());
    }

    private static Button getButton(ButtonPrototype decoratedButton) {
        Button createdButton = new Button();
        createdButton.setPrefHeight(decoratedButton.getHeight());
        createdButton.setPrefWidth(decoratedButton.getWidth());
        createdButton.setLayoutX(decoratedButton.getX());
        createdButton.setLayoutY(decoratedButton.getY());
        createdButton.setText(decoratedButton.getText());
        createdButton.setFont(new Font("System Bold", 16.0d));
        createdButton.setCursor(Cursor.HAND);
        createdButton.setTextFill(WHITE);
        createdButton.setOnMouseClicked(event -> {
            try {
                System.out.println("Click");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        createdButton.setStyle("-fx-background-color: rgb(246,162,162)");
        return createdButton;
    }
}
