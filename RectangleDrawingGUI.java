package com;

import javafx.application.*;
import javafx.collections.*;
import javafx.scene.*;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.*;
import javafx.scene.control.Alert.*;
import javafx.scene.control.Button;
import java.nio.Buffer;
import java.util.*;
import java.io.*;
import javafx.scene.text.*;
import javafx.scene.Group;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;

public class RectangleDrawingGUI extends Application {

    private static Rectangle customRectangle = null;
    private double firstX = 0;
    private double firstY = 0;

    public void start(Stage stage) {

        BorderPane mainPane = new BorderPane();

        HBox centerPane = new HBox();

        centerPane.prefWidthProperty().bind(mainPane.widthProperty());
        centerPane.prefHeightProperty().bind(mainPane.heightProperty());

        AnchorPane anchorPane = new AnchorPane();

        anchorPane.prefWidthProperty().bind(centerPane.widthProperty());
        anchorPane.prefHeightProperty().bind(centerPane.heightProperty());


        centerPane.getChildren().add(anchorPane);

        centerPane.setStyle("-fx-background-color: yellow");

        mainPane.setCenter(centerPane);

        centerPane.addEventFilter(MouseEvent.ANY, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
                    firstX = event.getX();
                    firstY = event.getY();
                    createCustomRectangle(event, anchorPane);

                }
                if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                    editCustomRectangle(event);
                    firstX = event.getX();
                    firstY = event.getY();
                }
            }
        });

        Scene scene = new Scene(mainPane, 600, 400);
        stage.setScene(scene);
        stage.show();


    }
    public void createCustomRectangle(MouseEvent event, AnchorPane canvasGroup) {
        customRectangle = new Rectangle(0, 0, 10, 10); // or just set the actual X and Y from the start
        customRectangle.relocate(event.getX(), event.getY());

        customRectangle.setFill(Color.RED);
        canvasGroup.getChildren().add(customRectangle);
    }

    public void editCustomRectangle(MouseEvent event) {

        double deltaX = event.getX() - firstX;
        double deltaY = event.getY() - firstY;

        double width = customRectangle.getWidth() + deltaX;
        double height = customRectangle.getHeight() + deltaY;

        customRectangle.setWidth(width);
        customRectangle.setHeight(height);
    }



    public static void main(String[] args) {
        launch(args);
    }

}
