
public class Camera {

	private float x, y;
	
	//Camera constructor
	public Camera(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void tick(GameObject object) {
		
		x += ((object.getX() - x) - 1000/2) * 0.05f;//helps the camera lock on two player's x
		y += ((object.getY() - y) - 563/2) * 0.05f;//and y coordinates
		
		if(x <= 0) x = 0;
		if(x >= 1032) x = 1032;
		if(y <= 0) y = 0;
		if(y >= 563+48) y = 563+48;
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
	
	public void endCamera() {
		this.setX(0);
		this.setY(0);
	}
	
	
}
