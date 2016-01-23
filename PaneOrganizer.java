package DoodleJump;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;
import javafx.event.*;
//for event handlers
import javafx.scene.input.*;
//positioning on the vbox
import javafx.geometry.Pos;
import javafx.application.*;
//import for the timeline
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
public class PaneOrganizer {
	private BorderPane _root;
	private Pane _gamePane;
	private Game _game;
	private Timeline _timeline;
	private Label _label;

	public PaneOrganizer(){
		_root = new BorderPane();
		this.gamePane();
		this.labelPane();
		this.setupTimeline();
	}

	public BorderPane getRoot(){
		return _root;
	}
	public void gamePane(){
		_gamePane = new Pane();
		_gamePane = new Pane();
		_gamePane.setPrefSize(Constants.GAMEWIDTH, Constants.GAMEHEIGHT);
		_gamePane.setStyle("-fx-background-color: white;");	
		_root.setCenter(_gamePane);
		_game = new Game(_gamePane);
		_gamePane.setFocusTraversable(true); 
		_gamePane.requestFocus();
	}
	public void labelPane(){
		VBox labelPane = new VBox();
		labelPane.setPrefSize(Constants.LABELWIDTH, Constants.LABELHEIGHT);
		labelPane.setStyle("-fx-background-color: grey;");
		_root.setBottom(labelPane);
		labelPane.setAlignment(Pos.TOP_CENTER);
		labelPane.setSpacing(Constants.BUTTONSPACING);
		Button quit = new Button("quit?");
		quit.addEventHandler(MouseEvent.MOUSE_CLICKED, new QuitHandler());
		_label = new Label("move left or right to play");
		labelPane.getChildren().addAll(quit,_label);
	}
	public void setupTimeline(){
		KeyFrame kf = new KeyFrame(Duration.millis(Constants.DURATION), new TimeHandler
		());
		_timeline = new Timeline(kf);
		_timeline.setCycleCount(Animation.INDEFINITE);
		_timeline.play();
	}
	private class TimeHandler implements EventHandler<ActionEvent>{ 
		public void handle(ActionEvent event){
				_game.checkCollision();
				_game.scrollScreen();
				_game.updatePlat(_gamePane);
				_game.addPlat(_gamePane);
				if (_game.doodlePos() > Constants.GAMEHEIGHT){
					_label.setText("GAME OVER!");
					_game.removeDoodle(_gamePane);
				}
			}
	}	

	private class QuitHandler implements EventHandler<MouseEvent>{
		@Override
		public void handle(MouseEvent e){
			Platform.exit();
			e.consume();
		}
	}

	
	
}
