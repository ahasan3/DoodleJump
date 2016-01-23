package DoodleJump;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;
public class Doodle {
	private Rectangle _doodle;
	private double _velocity;
	private Pane _gamePane;
	//deleted pane from the arguments
	public Doodle(Pane gamePane){
		_doodle = new Rectangle(Constants.DOODLE_WIDTH, Constants.DOODLE_HEIGHT);
		_doodle.setFill(javafx.scene.paint.Color.BLUE);
		gamePane.getChildren().add(_doodle);
		_doodle.setX(Constants.DOODLESTARTX);
		_doodle.setY(Constants.DOODLESTARTY);
	}
	public double getY(){
		return _doodle.getY();
	}
	public void setY(double y){
		_doodle.setY(y);
	}
	public void setX(double x){
		_doodle.setX(x);
	}
	public double getX(){
		return _doodle.getX();
	}
	public Node getNode(){
		return _doodle;
	}
	public double updateVelocity(){
		_velocity = _velocity + ((double) Constants.GRAVITY) *
		(((double)Constants.DURATION)/1000.0);
		return _velocity;			
	}
	public void setVelocity(int velocity){
		_velocity = velocity;
	}
	public void updatePosition(){
		_doodle.setY(_doodle.getY()+_velocity*
		((double)Constants.DURATION)/1000.0);
	 }
	public void remove(Pane gamePane){
		gamePane.getChildren().remove(_doodle);
	}
	
}
