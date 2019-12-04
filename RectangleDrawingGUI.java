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
    private RadioButton red,yellow,blue,thin,thick;
    private Button clear;
    private CheckBox fillCheckBox;

    public void start(Stage stage) {

        BorderPane borderPane = new BorderPane();
        Canvas canvas = new Canvas(350,350);
        canvas.setStyle("-fx-border-color : black");
        Pane pane = new Pane();
        pane.setStyle("-fx-border-color : black");
        pane.setPadding(new Insets(10,10,10,10));
        pane.setOnMouseMoved(this::handleMouseMotion);

        borderPane.setCenter(pane);

        //COLOR BUTTONS
        red = new RadioButton("Red");
        red.setOnAction(this::handleButton);
        yellow = new RadioButton("Yellow");
        yellow.setOnAction(this::handleButton);
        blue = new RadioButton("Blue");
        blue.setOnAction(this::handleButton);

        //COLOR BUTTON TOGGLE GROUP
        ToggleGroup colorGroup = new ToggleGroup();
        red.setToggleGroup(colorGroup);
        yellow.setToggleGroup(colorGroup);
        blue.setToggleGroup(colorGroup);

        //BORDER BUTTONS
        thin = new RadioButton("Thin Border");
        thin.setOnAction(this::handleButton);
        thick = new RadioButton("Thick Border");
        thick.setOnAction(this::handleButton);

        //BORDER BUTTON TOGGLE GROUP
        ToggleGroup borderGroup = new ToggleGroup();
        thin.setToggleGroup(borderGroup);
        thick.setToggleGroup(borderGroup);

        //FILLED IN BUTTON
        fillCheckBox = new CheckBox("Fill");
        fillCheckBox.setOnAction(this::handleButton);

        //Clear Button
        clear = new Button("Clear");
        clear.setOnAction(this::handleButton);

        //Color and width Groups
        Group colorButtons = new Group();
        colorButtons.getChildren().addAll(red,yellow,blue);
        Group widthButtons = new Group();
        widthButtons.getChildren().addAll(thin,thick);


        //POSITION TESTING
        HBox box = new HBox(red,yellow,blue,thin,thick,fillCheckBox,clear);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(10,10,10,10));
        box.setSpacing(10);


        borderPane.setBottom(box);

        borderPane.getChildren().addAll(colorButtons);



        HBox centerPane = new HBox();

        centerPane.prefWidthProperty().bind(borderPane.widthProperty());
        centerPane.prefHeightProperty().bind(borderPane.heightProperty());

        AnchorPane anchorPane = new AnchorPane();

        anchorPane.prefWidthProperty().bind(centerPane.widthProperty());
        anchorPane.prefHeightProperty().bind(centerPane.heightProperty());


        centerPane.getChildren().add(anchorPane);

        centerPane.setStyle("-fx-background-color: white");

        borderPane.setCenter(centerPane);

        centerPane.addEventFilter(MouseEvent.ANY, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
                    firstX = event.getX();
                    firstY = event.getY();
                    initializeRectangle(event, anchorPane);

                }
                if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                    translateRectangle(event);
                    firstX = event.getX();
                    firstY = event.getY();
                }
            }
        });

        Scene scene = new Scene(borderPane, 600, 400);
        stage.setScene(scene);
        stage.show();


    }
    public void initializeRectangle(MouseEvent event, AnchorPane canvasGroup) {
        customRectangle = new Rectangle(0, 0, 0, 0); // or just set the actual X and Y from the start
        customRectangle.relocate(event.getX(), event.getY());

        Color currentColor = Color.TRANSPARENT;
        customRectangle.setFill(Color.TRANSPARENT);

        if(red.isSelected()) {
            currentColor = Color.RED;
        }
        if(yellow.isSelected()) {
            currentColor = Color.YELLOW;
        }
        if(blue.isSelected()) {
            currentColor = Color.BLUE;
        }

        if(thin.isSelected()) {
            customRectangle.setStroke(currentColor);
            customRectangle.setStrokeWidth(1);
        }

        if(thick.isSelected()) {
            customRectangle.setStroke(currentColor);
            customRectangle.setStrokeWidth(10);
        }

        if(fillCheckBox.isSelected()) {
            customRectangle.setFill(currentColor);
        }

        canvasGroup.getChildren().add(customRectangle);
    }

    public void translateRectangle(MouseEvent event) {

        double deltaX = event.getX() - firstX;
        double deltaY = event.getY() - firstY;

        double width = customRectangle.getWidth() + deltaX;
        double height = customRectangle.getHeight() + deltaY;

        customRectangle.setWidth(width);
        customRectangle.setHeight(height);
    }

    public void handleButton(ActionEvent event) {
        System.out.println(event.getSource());
    }

    public void handleMouseClicks(MouseEvent event) {

    }

    public void handleMouseMotion(MouseEvent event) {

    }



    public static void main(String[] args) {
        launch(args);
    }

}
