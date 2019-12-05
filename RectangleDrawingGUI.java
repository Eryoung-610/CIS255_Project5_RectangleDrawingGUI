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

    private static Rectangle rectangle;
    private double xPos = 0;
    private double yPos = 0;
    private RadioButton red,yellow,blue,thin,thick;
    private Button clear;
    private CheckBox fillCheckBox;
    private int countRectangles = 0;

    public void start(Stage stage) {

        BorderPane borderPane = new BorderPane();
        Canvas canvas = new Canvas(350,350);
        canvas.setStyle("-fx-border-color : black");
        Pane pane = new Pane();
        pane.setStyle("-fx-border-color : black");
        pane.setPadding(new Insets(10,10,10,10));

        borderPane.setCenter(pane);

        //COLOR BUTTONS
        red = new RadioButton("Red");
        yellow = new RadioButton("Yellow");
        blue = new RadioButton("Blue");

        //COLOR BUTTON TOGGLE GROUP
        ToggleGroup colorGroup = new ToggleGroup();
        red.setToggleGroup(colorGroup);
        yellow.setToggleGroup(colorGroup);
        blue.setToggleGroup(colorGroup);

        //BORDER BUTTONS
        thin = new RadioButton("Thin Border");
        thick = new RadioButton("Thick Border");

        //BORDER BUTTON TOGGLE GROUP
        ToggleGroup borderGroup = new ToggleGroup();
        thin.setToggleGroup(borderGroup);
        thick.setToggleGroup(borderGroup);

        //FILLED IN BUTTON
        fillCheckBox = new CheckBox("Fill");

        //Clear Button
        clear = new Button("Clear");

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



        HBox rectBox = new HBox();

        rectBox.prefWidthProperty().bind(borderPane.widthProperty());
        rectBox.prefHeightProperty().bind(borderPane.heightProperty());

        AnchorPane anchorPane = new AnchorPane();

        anchorPane.prefWidthProperty().bind(rectBox.widthProperty());
        anchorPane.prefHeightProperty().bind(rectBox.heightProperty());


        rectBox.getChildren().add(anchorPane);

        rectBox.setStyle("-fx-background-color: white");

        borderPane.setCenter(rectBox);

        rectBox.addEventFilter(MouseEvent.ANY, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if(event.getEventType() == MouseEvent.MOUSE_PRESSED) {
                    xPos = event.getX();
                    yPos = event.getY();
                    initializeRectangle(event, anchorPane);

                }
                if(event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                    translateRectangle(event);
                    xPos = event.getX();
                    yPos = event.getY();
                }

                if(event.getEventType() == MouseEvent.MOUSE_RELEASED) {
                    countRectangles++;
                }
            }
        });

        Scene scene = new Scene(borderPane, 600, 400);
        stage.setScene(scene);
        stage.show();


    }
    public void initializeRectangle(MouseEvent event, AnchorPane anchorPane) {
        rectangle = new Rectangle(0, 0, 0, 0);
        rectangle.relocate(event.getX(), event.getY());

        Color currentColor = Color.TRANSPARENT;

        if(red.isSelected()) {
            currentColor = Color.RED;
            rectangle.setFill(Color.TRANSPARENT);

        }
        if(yellow.isSelected()) {
            currentColor = Color.YELLOW;
            rectangle.setFill(Color.TRANSPARENT);

        }
        if(blue.isSelected()) {
            currentColor = Color.BLUE;
            rectangle.setFill(Color.TRANSPARENT);

        }

        if(thin.isSelected()) {
            rectangle.setStroke(currentColor);
            rectangle.setStrokeWidth(1);
        }

        if(thick.isSelected()) {
            rectangle.setStroke(currentColor);
            rectangle.setStrokeWidth(10);
        }

        if(fillCheckBox.isSelected()) {
            rectangle.setFill(currentColor);
        }
        anchorPane.getChildren().add(rectangle);
        clear.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    anchorPane.getChildren().remove(0,countRectangles);
                    countRectangles = 0;
                }
                catch(IndexOutOfBoundsException ex) {
                    System.out.println("No Rectangles Created");
                }
            }
        });
    }

    public void translateRectangle(MouseEvent event) {

        double rectWidth = event.getX() - xPos;
        double rectHeight = event.getY() - yPos;

        double width = rectangle.getWidth() + rectWidth;
        double height = rectangle.getHeight() + rectHeight;

        rectangle.setWidth(width);
        rectangle.setHeight(height);

    }



    public static void main(String[] args) {
        launch(args);
    }

}
