//X and Y position will focus on player by default.
public class Camera {
	
	int x, y;
	private Handler handler;
	private GameObject tempPlayer = null;
	
	//constructor
	public Camera(int x, int y, Handler handler) {
		this.x = x;
		this.y =y;
		this.handler = handler;
		
		findPlayer();
	}
	
	public void findPlayer() {
		for(int ix = 0; ix < handler.object.size(); ix++ ) {
			if(handler.object.get(ix).getId() == ID.Player ) {
				tempPlayer = handler.object.get(ix);
				break;
			}
		}
	}
	
	public void tick() {
		
		if(tempPlayer != null) {
			x = (int) tempPlayer.x - Game.WIDTH / 2;
			y = (int) tempPlayer.y - Game.HEIGHT / 2;
		}else findPlayer();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}
