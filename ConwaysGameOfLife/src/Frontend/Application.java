package Frontend;

import java.util.ArrayList;

import Backend.*;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Application extends javafx.application.Application {
	private final int windowWidth = 1200;
	private final int windowHeight = 900;

	private int scale = 10;
	private int barHeight = 30;
	private Group group;
	private ArrayList<Rectangle> rectList = new ArrayList<Rectangle>();
	//private HashMap<int[], Rectangle> rectMap = new HashMap<int[], Rectangle>();
	private String currentPattern = "glider";
	
	World world = new World();

	Timeline tl;
	final int baseTickTime = 250;
	Duration sliderTime = Duration.millis(baseTickTime);

	@Override
	public void start(Stage primaryStage) throws Exception {
		group = new Group();
		
		
		BorderPane bp = new BorderPane();

		tl = new Timeline();
		tl.setCycleCount(Timeline.INDEFINITE);

		changeTimer(tl);
		tl.stop();

		DrawCenterPane(group);
		world.tobealive(0,0);
		world.tobealive(1,1);
		world.tobealive(1,2);
		world.tobealive(0,2);
		world.tobealive(-1,2);
		drawCells(group);

		bp.setCenter(group);
		bp.setTop(setUpControls());

		Scene scene = new Scene(bp);
		scene.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				if (event.getY() < barHeight) {
					// Do nothing if mouse click is in the buttons bar
					return;
				}
				
				int worldX, worldY;
				double mouseX = event.getX() - windowWidth / 2;
				double mouseY = event.getY() - barHeight - (windowHeight-barHeight)/2;
				
				// convert scene mouse x and y position to world x and y position
				if (mouseX < 0) {
					worldX = (int)(mouseX / scale) - 1;
				} else {
					worldX = (int)(mouseX / scale);
				}
				if (mouseY < 0) {
					worldY = (int)(mouseY / scale) - 1;
				} else {
					worldY = (int)(mouseY / scale);
				}
				
				// toggle cells on/off
				if (!world.checkAlive(new int[]{worldX, worldY})) {
					world.tobealive(worldX, worldY);
					Rectangle rect = new Rectangle(worldX * scale, worldY * scale, scale, scale);
					rectList.add(rect);
					group.getChildren().add(rect);
				} else {
					world.removeAlive(new int[]{worldX, worldY});
					drawCells(group);
				}
			}
			
		});

		primaryStage.setWidth(windowWidth);
		primaryStage.setHeight(windowHeight);

		primaryStage.setScene(scene);
		primaryStage.centerOnScreen();
		primaryStage.setResizable(false);
		primaryStage.show();

	}

	public void DrawCenterPane(Group group) {
		
		// Will redraw the grid based on window width & height and scale.
		int minX = -(windowWidth / 2);
		int minY = -(windowHeight - barHeight) / 2;
		int maxX = windowWidth / 2;
		int maxY = (windowHeight - barHeight) / 2;
		
		group.getChildren().clear();
		
		for (int i = 0; i <= maxX / scale; i++) {
			Line line = new Line(i * scale, minY, i * scale, maxY);
			line.setStroke(Color.LIGHTGREY);
			group.getChildren().add(line);
		}
		
		for (int i = -1; i >= minX / scale; i--) {
			Line line = new Line(i * scale, minY, i * scale, maxY);
			line.setStroke(Color.LIGHTGREY);
			group.getChildren().add(line);
		}
		
		for (int i = 0; i <= maxY / scale; i++) {
			Line line = new Line(minX, i * scale, maxX, i * scale);
			line.setStroke(Color.LIGHTGREY);
			group.getChildren().add(line);
		}
		
		for (int i = -1; i >= minY / scale; i--) {
			Line line = new Line(minX, i * scale, maxX, i * scale);
			line.setStroke(Color.LIGHTGREY);
			group.getChildren().add(line);
		}
		
		//return pane;
	}
	
	public void drawCells(Group group) {
		ArrayList<int[]> alive = world.getAlive();
		group.getChildren().removeAll(rectList);
		rectList.clear();
		for (int[] cell : alive) {
			Rectangle rect = new Rectangle();
			rect.setX(cell[0] * scale);
			rect.setY(cell[1] * scale);
			rect.setFill(Color.BLACK);
			rect.setWidth(scale);
			rect.setHeight(scale);
			rectList.add(rect);
		}
		group.getChildren().addAll(rectList);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch();
	}

	/*
	 * Updates the timer with a new set of KeyFrames, with duration read from a
	 * field which contains an updated duration. Currently used to initially set
	 * up the first frame, and to re-populate the KeyFrames whenever the slider
	 * is moved. This does not start the timer, that should be done at a higher
	 * level.
	 */
	public void changeTimer(Timeline timer) {
		
		KeyFrame kf = new KeyFrame(sliderTime, new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent t) { // Every frame.
				world.updateworld();
				drawCells(group);
			}
		});
		timer.stop(); // Pretty sure this is necessary.
		timer.getKeyFrames().setAll(kf);

	}
	
	public HBox setUpControls() {
		HBox buttonBox = new HBox();

		double speedMin = 0.1, speedMax = 4.0, speedDefault = 1.0; //ranges for slider.
		Button reset, play, pause, step, viewShowMore, viewShowLess;
		reset = new Button("\u2B6F"); // Unicode character for an anticlockwise
										// circular arrow.
		reset.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Boolean timerWasRunning = (tl.getStatus() == Status.RUNNING); 
				tl.stop(); //Stop the timeline. Unsure if strictly necessary. Maybe a threading thing.
				group.getChildren().removeAll(rectList);//Clear the rectangles from the group.
				world.getAlive().clear(); //Purge the world.
				rectList.clear(); //Clear out all the rectangle representations.
				//Reset the size? Maybe not.
				
 				int[][] pattern = Pattern.getPattern(currentPattern, 0, 0); //Below re-used from the pattern button code.
 				for (int[] coords: pattern) {
 					//Populate the grid with the current combo choice.
 					int x = coords[0];
 					int y = coords[1];
 				
 					world.tobealive(x, y);
 					
 					Rectangle rect = new Rectangle(x * scale, y * scale, scale, scale);
 					group.getChildren().add(rect);
 					rectList.add(rect);
 				}		
 				if (timerWasRunning) tl.play(); //Start the timeline again.
			}
		});
		play = new Button("\u23F5"); // Unicode character for a small rightward
										// filled isosceles triangle.
		play.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				tl.play();
				System.out.println(tl.getStatus());
			}
		});
		pause = new Button("\u23F8"); // Unicode character for two vertical
										// bars.
		pause.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				tl.pause();
				System.out.println(tl.getStatus());
			}
		});
		step = new Button("\u21E2"); // Unicode character for a rightward facing
										// arrow with a dashed line, to indicate
										// partial movement.
		step.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (tl.getStatus() != Status.RUNNING) {
					tl.stop(); //Stop because we can't change cycle count on a non-stopped timeline.
					tl.setCycleCount(1); //Set the cycle count such that play will only tick through one frame.
					tl.play(); // //Make the timeline play, which should go for one cycle.
					tl.setCycleCount(Timeline.INDEFINITE); //Next time the timeline plays, we assume we want it to be indefinite again.
				}
				else System.out.println("Cannot Step while running."); //This was a debugging message, but turns out it's actually decent feedback.
			}
		});
		viewShowMore = new Button("+"); // Just a plus.
		viewShowMore.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (scale <= 20) {
					scale++;
					DrawCenterPane(group);
					drawCells(group);
				}
			}
			
		});
		viewShowLess = new Button("\u2212"); // Unicode for a more fitting minus
												// symbol.
		viewShowLess.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (scale > 1) {
					scale--;
					DrawCenterPane(group);
					drawCells(group);
				}
			}
			
		});
		
		Slider runSpeed = new Slider(speedMin, speedMax, speedDefault);
		runSpeed.setOnMouseReleased(new EventHandler<MouseEvent>() { //This currently only works for when a mouse drag happens
			@Override												//On the slider. Arrow keys move the pip but don't adjust.
			public void handle(MouseEvent event) {
				sliderTime = Duration.millis(baseTickTime * runSpeed.getValue()); //Recalculate the slider time.
				Boolean timerWasRunning = (tl.getStatus() == Status.RUNNING); //Store whether we were already running.
				changeTimer(tl); //Do the timer updating, which involves stopping the tl and changing the keyframes.
				if (timerWasRunning) //If the timer was running before the change...
					tl.play();	//start it again.
			}
		});
		ComboBox<String> patternChooser = new ComboBox<String>(); //Combo box contains strings for names of patterns from Pattern.
		Pattern pattern = new Pattern();
		patternChooser.getItems().addAll(pattern.patternNames);
		
		patternChooser.setOnAction(new EventHandler<ActionEvent>() {
 			@Override
 			public void handle(ActionEvent event) {
 				currentPattern = patternChooser.getSelectionModel().getSelectedItem();
 				System.out.println(currentPattern);
 				int[][] pattern = Pattern.getPattern(currentPattern, 0, 0);
 				group.getChildren().removeAll(rectList);
 				rectList.clear();
 				world.getAlive().clear();
 				for (int[] coords: pattern) {
 					int x = coords[0];
 					int y = coords[1];
 				
 					world.tobealive(x, y);
 					
 					Rectangle rect = new Rectangle(x * scale, y * scale, scale, scale);
 					group.getChildren().add(rect);
 					rectList.add(rect);
 				}		
 				
 			}
 			
 		});
		
		buttonBox.getChildren().addAll(reset, play, pause, step, runSpeed, patternChooser,
				new Separator(Orientation.VERTICAL), viewShowMore, viewShowLess);
		buttonBox.setMinHeight(barHeight);
		return buttonBox;
	}

}