package DoodleJump;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
//for event handlers
import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.Node;
//needed for the array list
import java.util.ArrayList;
public class Game {
	private BorderPane _root;
	private Doodle _doodle;
	private DoodlePlatform _platform;
	private ArrayList<DoodlePlatform> _arrayPlat;
	private int _randx;
	private int _randy;
	private int _rand2;
	private int _arrayNum;
	private double _velocity;
	private Pane _gamePane;
	
	public Game(Pane gamePane){
		_velocity = (double)Constants.REBOUND_VELOCITY;
		_platform = new DoodlePlatform(gamePane);
		_doodle = new Doodle(gamePane);
		this.createArray(gamePane);
		this.createPlatforms(gamePane);
		gamePane.addEventHandler(KeyEvent.KEY_PRESSED, new KeyHandler());

	}
	public int generateNumber(double low, double high){
		_rand2 = (int)((high- low+1 )*Math.random() + low);
		return _rand2;						
	}
	
	public ArrayList createArray(Pane gamePane){
		_arrayPlat = new ArrayList<DoodlePlatform>();
		_arrayPlat.add(_platform);
		return _arrayPlat;
	}	
	public ArrayList createPlatforms(Pane gamePane){
	    _randx = Constants.GAMEWIDTH/2;
	    _randy = Constants.GAMEHEIGHT/2;
	   _arrayNum = _arrayPlat.size();
		do{
	    			_platform = new DoodlePlatform(gamePane);
				_randy= this.generateNumber(_arrayPlat.get(_arrayNum - 1).getY()-75, _arrayPlat.get(_arrayNum - 1).getY()-45);
				_platform.setY(_randy);
				_randx = this.generateNumber(0, Constants.GAMEWIDTH);
				if (_randx > _doodle.getX() + 30 || _randx < _doodle.getX() - 30 && _randx != _arrayPlat.get(_arrayNum-1).getX() ){
					_platform.setX(_randx);
			}
		_arrayPlat.add(_platform);
		_arrayNum = _arrayPlat.size();
		} while(_arrayNum < 6);
		return _arrayPlat;
	}
	public void checkCollision(){
				_doodle.updateVelocity();
				_doodle.updatePosition();
				if (_doodle.updateVelocity() > 0){
					for (int k = 0; k < _arrayPlat.size(); k++){
						if (_doodle.getNode().intersects(_arrayPlat.get(k).getX(), _arrayPlat.get(k).getY(), Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGHT)){
							_doodle.setVelocity(Constants.REBOUND_VELOCITY);
						}
					}
				}
	}
	public void scrollScreen(){
		if (_doodle.getY() < (Constants.GAMEHEIGHT/3)){
			for (int a = 0; a < _arrayPlat.size(); a++){
				_arrayPlat.get(a).setY(_arrayPlat.get(a).getY() + (Constants.GAMEHEIGHT/3 - _doodle.getY()) );
			}
			_doodle.setY(Constants.GAMEHEIGHT/3);
		}
	}
		
	public ArrayList updatePlat(Pane gamePane){
		for (int c = 0; c < _arrayPlat.size(); c++){
			if (_arrayPlat.get(c).getY() > Constants.GAMEHEIGHT){
				_arrayPlat.get(c).remove(gamePane);
				_arrayPlat.remove(c);
			}
		}
		return _arrayPlat;
	}

	public ArrayList addPlat(Pane gamePane){
		if (_arrayPlat.size()<6){
			do{ 
				_arrayNum = _arrayPlat.size();
				_platform = new DoodlePlatform(gamePane);
				_platform.setY(0);
				_randx = this.generateNumber(_arrayPlat.get(_arrayNum - 1).getX()-55, _arrayPlat.get(_arrayNum - 1).getX()+15);
				if (_randx > _doodle.getX() + 35 || _randx < _doodle.getX() - 35 && _randx != _arrayPlat.get(_arrayNum-1).getX() && _randx > 35 && _randx < Constants.GAMEWIDTH-35){
					_platform.setX(_randx);
				} 
				_arrayPlat.add(_platform);
			} while (_doodle.getY() == Constants.GAMEHEIGHT/3 && _doodle.updateVelocity() == 0);
		}	
		return _arrayPlat;
	}
	private class KeyHandler implements EventHandler<KeyEvent>{
		public void handle(KeyEvent e){
			KeyCode keyPressed = e.getCode();
			if (keyPressed == KeyCode.LEFT){
				_doodle.setX(_doodle.getX()-15);
			} else if (keyPressed == KeyCode.RIGHT){
				_doodle.setX(_doodle.getX()+15);
			}
			e.consume();
		}
	}
	public double doodlePos(){
		return _doodle.getY();
	}
	public void removeDoodle(Pane gamePane){
			_doodle.remove(gamePane);
	}
}
