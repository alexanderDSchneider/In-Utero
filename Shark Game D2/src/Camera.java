
public class Camera {

	private float x, y;
	
	//Camera constructor
	public Camera(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void tick(GameObject object) {
		
		x += ((object.getX() - x) - Game.WIDTH/2) * 0.05f;//helps the camera lock on two player's x
		y += ((object.getY() - y) - Game.HEIGHT/2) * 0.05f;//and y coordinates
		
		if(x <= 0) x = 0;
		if(x >= Game.WIDTH) x = Game.WIDTH;
		if(y <= 0) y = 0;
		if(y >= Game.HEIGHT+22) y = Game.HEIGHT+22;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	
}
