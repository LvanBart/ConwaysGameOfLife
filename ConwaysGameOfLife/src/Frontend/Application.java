package Frontend;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {
	int scale = 10;
	int barHeight = 200;
	int width = 100 * scale;
	int height = 80 * scale + barHeight;
	int minX = -(width / 2);
	int minY = -(height - 200) / 2;
	int maxX = width / 2;
	int maxY = (height - 200) / 2;

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane bp = new BorderPane();
		Group group = new Group();
		
		StackPane root = DrawCenterPane(group);
		
		bp.setCenter(root);
		
		root.getChildren().add(group);
		Rectangle r1 = new Rectangle();
		r1.setX(0*scale);
		r1.setY(0*scale);
		r1.setWidth(scale);
		r1.setHeight(scale);
		r1.setFill(Color.BLACK);
		group.getChildren().add(r1);
		
		Rectangle r2 = new Rectangle();
		r2.setX(-10*scale);
		r2.setY(-10*scale);
		r2.setWidth(scale);
		r2.setHeight(scale);
		r2.setFill(Color.BLACK);
		group.getChildren().add(r2);
		
		Rectangle r3 = new Rectangle();
		r3.setX(30*scale);
		r3.setY(10*scale);
		r3.setWidth(scale);
		r3.setHeight(scale);
		r3.setFill(Color.BLACK);
		group.getChildren().add(r3);
		
		Scene scene = new Scene(bp);
		
		
		primaryStage.setWidth(width);
		primaryStage.setHeight(height);
		
		primaryStage.setScene(scene);
		primaryStage.centerOnScreen();
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public StackPane DrawCenterPane(Group group) {
		// initial world will be 100 x 100 grid size @ 10px
		
		StackPane pane = new StackPane();
		
		// Draw horizontal lines
		ArrayList<Line> vlineList = new ArrayList<Line>();
		
		for (int i = 0; i < width/scale; i++) {
			Line line = new Line(minX + i * scale, minY, minX + i * scale, maxY);
			vlineList.add(line);
			group.getChildren().add(line);
		}
		// Draw vertical lines
		ArrayList<Line> hlineList = new ArrayList<Line>();
		
		for (int i = 0; i < (height-barHeight)/scale; i++) {
			Line line = new Line(minX, minY + i * scale, maxX, minY + i * scale);
			hlineList.add(line);
			group.getChildren().add(line);
		}
		return pane;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch();
	}

}
