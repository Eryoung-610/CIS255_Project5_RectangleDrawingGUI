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
	
	private RadioButton red,yellow,blue,thin,thick;
	private Button clear;
	private CheckBox fillCheckBox;
	private final static int WIDTH = 500, HEIGHT = 500;

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
		HBox test = new HBox(red,yellow,blue,thin,thick,fillCheckBox,clear);
		test.setAlignment(Pos.CENTER);
		test.setPadding(new Insets(10,10,10,10));
		test.setSpacing(10);
		
		HBox test1 = new HBox(clear);
		test1.setAlignment(Pos.CENTER);
		
		
		borderPane.setBottom(test);
			
		borderPane.getChildren().addAll(colorButtons);
		

		Scene scene = new Scene(borderPane, WIDTH, HEIGHT, Color.TRANSPARENT);
		stage.setTitle("Rectangles!");
		stage.setScene(scene);
		stage.setResizable(true);
		stage.show();

	}
	
	public void handleButton(ActionEvent event) {
		
	}
	
	public void handleMouseClicks(MouseEvent event) {
		
	}
	
	public void handleMouseMotion(MouseEvent event) {
		
	}


	public static void main(String[] args) {
		launch(args);
	}

}
