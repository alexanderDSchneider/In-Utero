import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {

	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	private boolean up = false, down = false, right = false, left = false;  //for key input
	
	public Handler() {
		
	}
	
	public void tick() {
		for(int ix = 0; ix < object.size(); ix++) {
			
			object.get(ix).tick();
		}
	}
	
	public void render(Graphics g) {
		for(int ix = 0; ix < object.size(); ix++) {
			
			object.get(ix).render(g);
		}
	}
	
	public GameObject addObject(GameObject tempObject) {
		object.add(tempObject);
		return tempObject;
	}
	
	public void removeObject (GameObject tempObject) {
		object.remove(tempObject);
	}
	
	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}
}
