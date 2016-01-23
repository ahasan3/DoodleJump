package DoodleJump;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;


public class DoodlePlatform {
	private Rectangle _platform;
	public DoodlePlatform(Pane gamePane){
		_platform = new Rectangle(Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGHT);
		_platform.setFill(javafx.scene.paint.Color.BLACK);
		gamePane.getChildren().add(_platform);
		_platform.setX(Constants.PLATFORMSTARTX);
		_platform.setY(Constants.PLATFORMSTARTY);
	}
	public double getY(){
		return _platform.getY();
	}
	public void setY(double y){
		_platform.setY(y);
	}
	public void setX(double x){
		_platform.setX(x);
	}
	public double getX(){
		return _platform.getX();
	}
	public Node getNode(){
		return _platform;
	}
	public void remove(Pane gamePane){
		gamePane.getChildren().remove(_platform);
	}
}
