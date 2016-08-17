package Frontend;

import java.util.ArrayList;
import java.util.HashMap;

import Backend.Pattern;
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
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Application extends javafx.application.Application {
	private final int windowWidth = 1000;
	private final int windowHeight = 830;

	private int scale = 10;
	private int barHeight = 30;
	private Group group = new Group();
	private ArrayList<Line> vlineList = new ArrayList<Line>();
	private ArrayList<Line> hlineList = new ArrayList<Line>();
	private int duration = 100;
	private HashMap<int[], Rectangle> rectMap = new HashMap<int[], Rectangle>();

	Timeline tl;
	final int baseTickTime = 250;
	Duration sliderTime = Duration.millis(baseTickTime);

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO This is dummy stuff
		BorderPane bp = new BorderPane();

		tl = new Timeline();
		tl.setCycleCount(Timeline.INDEFINITE);

		//changeTimer(tl);

		tl.playFromStart();

		StackPane root = DrawCenterPane(group);

		bp.setCenter(root);
		bp.setTop(setUpControls());

		root.getChildren().add(group);
		Rectangle r1 = new Rectangle();
		r1.setX(0 * scale);
		r1.setY(0 * scale);
		r1.setWidth(scale);
		r1.setHeight(scale);
		r1.setFill(Color.BLUE);
		group.getChildren().add(r1);

		Rectangle r2 = new Rectangle();
		r2.setX(-10 * scale);
		r2.setY(-10 * scale);
		r2.setWidth(scale);
		r2.setHeight(scale);
		r2.setFill(Color.BLACK);
		group.getChildren().add(r2);

		Rectangle r3 = new Rectangle();
		r3.setX(30 * scale);
		r3.setY(10 * scale);
		r3.setWidth(scale);
		r3.setHeight(scale);
		r3.setFill(Color.BLACK);
		group.getChildren().add(r3);

		Rectangle r4 = new Rectangle();
		r4.setX(80 * scale);
		r4.setY(90 * scale);
		r4.setWidth(scale);
		r4.setHeight(scale);
		r4.setFill(Color.RED);
		group.getChildren().add(r4);

		Scene scene = new Scene(bp);

		// World world = new World();

		// KeyFrame frame = new KeyFrame(Duration.millis(duration), new
		// EventHandler<ActionEvent>() {
		//
		// @Override
		// public void handle(ActionEvent t) {
		// //world.updateWorld();
		//
		// }
		//
		// });

		primaryStage.setWidth(1000);
		primaryStage.setHeight(830);

		primaryStage.setScene(scene);
		primaryStage.centerOnScreen();
		primaryStage.setResizable(false);
		primaryStage.show();

	}

	public StackPane DrawCenterPane(Group group) {
		// Will redraw the grid based on window width & height and scale.
		int minX = -(windowWidth / 2);
		int minY = -(windowHeight - barHeight) / 2;
		int maxX = windowWidth / 2;
		int maxY = (windowHeight - barHeight) / 2;
		StackPane pane = new StackPane();
		pane.autosize();

		// Draw horizontal lines
		for (int i = 0; i < windowWidth / scale; i++) {
			Line line = new Line(minX + i * scale, minY, minX + i * scale, maxY);
			line.setStroke(Color.LIGHTGREY);
			vlineList.add(line);
			group.getChildren().add(line);
		}

		// Draw vertical lines
		for (int i = 0; i < (windowHeight - barHeight) / scale; i++) {
			Line line = new Line(minX, minY + i * scale, maxX, minY + i * scale);
			line.setStroke(Color.LIGHTGREY);
			hlineList.add(line);
			group.getChildren().add(line);
		}
		return pane;
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
			int i = 0;

			@Override
			public void handle(ActionEvent t) { // Every frame.
				System.out.println("test " + i + " " + sliderTime);
				i++;
			}
		});
		timer.stop(); // Pretty sure this is necessary.
		timer.getKeyFrames().setAll(kf);

	}

	public HBox setUpControls() {
		HBox buttonBox = new HBox();

		double speedMin = 0.1, speedMax = 4.0, speedDefault = 1.0;
		Button reset, play, pause, step, viewShowMore, viewShowLess;
		reset = new Button("\u2B6F"); // Unicode character for an anticlockwise
										// circular arrow.
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
				else System.out.println("Cannot Step while running."); //TODO This is a debugging message, we should delete this.
			}
		});
		viewShowMore = new Button("+"); // Just a plus.
		viewShowLess = new Button("\u2212"); // Unicode for a more fitting minus
												// symbol.
		Slider runSpeed = new Slider(speedMin, speedMax, speedDefault);
		runSpeed.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				sliderTime = Duration.millis(baseTickTime * runSpeed.getValue());
				Boolean timerWasRunning = (tl.getStatus() == Status.RUNNING);
				changeTimer(tl);
				if (timerWasRunning)
					tl.play();
			}
		});
		ComboBox<String> patternChooser = new ComboBox<String>(); // TODO
																	// Replace
																	// Object
																	// typed
																	// ComboBox
																	// with
																	// Pattern
																	// typed
																	// ComboBox
																	// when
																	// patterns
																	// are
																	// added.
		Pattern pattern = new Pattern();
		patternChooser.getItems().addAll(pattern.patternNames);
		buttonBox.getChildren().addAll(reset, play, pause, step, runSpeed, patternChooser,
				new Separator(Orientation.VERTICAL), viewShowMore, viewShowLess);
		buttonBox.setMinHeight(barHeight);
		return buttonBox;
	}

}
