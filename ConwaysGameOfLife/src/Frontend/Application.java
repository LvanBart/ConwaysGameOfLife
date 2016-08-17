package Frontend;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO This is dummy stuff 
		BorderPane bp = new BorderPane();
		bp.setBottom(setUpControls());
		arg0.setScene(new Scene(bp));
		arg0.show();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch();
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
				//tl.play(); //TODO Add a timeline.
			}
		});
		pause = new Button("\u23F8"); //Unicode character for two vertical bars.
		pause.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//tl.pause(); //TODO Add a timeline.
			}
		});
		step = new Button("\u21E2"); //Unicode character for a rightward facing arrow with a dashed line, to indicate partial movement.
		viewShowMore = new Button("+"); //Just a plus.
		viewShowLess = new Button("\u2212"); //Unicode for a more fitting minus symbol.
		Slider runSpeed = new Slider(speedMin,speedMax,speedDefault); //TODO get these values from fields potentially.
		ComboBox<Object> patternChooser = new ComboBox<Object>(); //TODO Replace Object typed ComboBox with Pattern typed ComboBox when patterns are added.
		
		buttonBox.getChildren().addAll(reset,play,pause,step,runSpeed,patternChooser, new Separator(),viewShowMore,viewShowLess);
		
		return buttonBox;
	}

}
