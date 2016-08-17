package Frontend;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Application extends javafx.application.Application {
	private int scale = 10;
	private int barHeight = 200;
	private int width = 100 * scale;
	private int height = 80 * scale + barHeight;
	private int minX = -(width / 2);
	private int minY = -(height - 200) / 2;
	private int maxX = width / 2;
	private int maxY = (height - 200) / 2;
	private Group group = new Group();
	private ArrayList<Line> vlineList = new ArrayList<Line>();
	private ArrayList<Line> hlineList = new ArrayList<Line>();
	private int duration = 100;
	private HashMap<int[], Rectangle> rectMap = new HashMap<int[], Rectangle>();

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane bp = new BorderPane();
		
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
		
		Rectangle r4 = new Rectangle();
		r4.setX(101*scale);
		r4.setY(90*scale);
		r4.setWidth(scale);
		r4.setHeight(scale);
		r4.setFill(Color.RED);
		group.getChildren().add(r4);
		
		Scene scene = new Scene(bp);
		
		//World world = new World();
		
		KeyFrame frame = new KeyFrame(Duration.millis(duration), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent t) {
				//world.updateWorld();
				
			}

		});
		
		Timeline tl = new Timeline(); 
	 	tl.setCycleCount(Timeline.INDEFINITE);
	 	tl.getKeyFrames().addAll(frame); 
	 	tl.stop();
		
		
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
		for (int i = 0; i < width/scale; i++) {
			Line line = new Line(minX + i * scale, minY, minX + i * scale, maxY);
			vlineList.add(line);
			group.getChildren().add(line);
		}
		
		// Draw vertical lines
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
