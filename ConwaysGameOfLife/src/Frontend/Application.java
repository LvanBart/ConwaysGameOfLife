package Frontend;

import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Application extends javafx.application.Application {

	Timeline tl;
	final int baseTickTime = 250;
	Duration sliderTime = Duration.millis(baseTickTime);
	
	
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO This is dummy stuff 
		BorderPane bp = new BorderPane();
		
		tl = new Timeline();
		bp.setBottom(setUpControls());
	 	tl.setCycleCount(Timeline.INDEFINITE);
	 	
	 	changeTimer(tl);
	 	
		tl.playFromStart();
		
		arg0.setScene(new Scene(bp));
		arg0.show();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch();
	}
	
	
	/*
	 * Updates the timer with a new set of KeyFrames, with duration read from a field which contains an updated duration.
	 * Currently used to initially set up the first frame, and to re-populate the KeyFrames whenever the slider is moved.
	 * This does not start the timer, that should be done at a higher level.
	 */
	public void changeTimer(Timeline timer) {
		KeyFrame kf = new KeyFrame(sliderTime, new EventHandler<ActionEvent>() {
			int i = 0;
			@Override
			public void handle(ActionEvent t) { //Every frame.
				System.out.println("test " + i + " " + sliderTime);
				i++;
			}
			});
		timer.stop(); //Pretty sure this is necessary.
		timer.getKeyFrames().setAll(kf);
		
	}
	
	public HBox setUpControls () {
		HBox buttonBox = new HBox();
		
		double speedMin = 0.1, speedMax = 4.0, speedDefault = 1.0;
		Button reset, play, pause, step, viewShowMore, viewShowLess;
		reset = new Button("\u2B6F"); //Unicode character for an anticlockwise circular arrow.
		play = new Button("\u23F5"); //Unicode character for a small rightward filled isosceles triangle.
		play.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				tl.play();
				System.out.println(tl.getStatus());
			}
		});
		pause = new Button("\u23F8"); //Unicode character for two vertical bars.
		pause.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				tl.pause();
				System.out.println(tl.getStatus());
			}
		});
		step = new Button("\u21E2"); //Unicode character for a rightward facing arrow with a dashed line, to indicate partial movement.
		step.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println(tl.getKeyFrames());
				//tl.
			}
		});
		viewShowMore = new Button("+"); //Just a plus.
		viewShowLess = new Button("\u2212"); //Unicode for a more fitting minus symbol.
		Slider runSpeed = new Slider(speedMin,speedMax,speedDefault);
		runSpeed.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				sliderTime = Duration.millis(baseTickTime * runSpeed.getValue());
				Boolean timerWasRunning = (tl.getStatus() == Status.RUNNING);
				changeTimer(tl);
				if (timerWasRunning) tl.play();
			}
		});
		ComboBox<Object> patternChooser = new ComboBox<Object>(); //TODO Replace Object typed ComboBox with Pattern typed ComboBox when patterns are added.
		
		buttonBox.getChildren().addAll(reset,play,pause,step,runSpeed,patternChooser, new Separator(Orientation.VERTICAL),viewShowMore,viewShowLess);
		
		return buttonBox;
	}

}
