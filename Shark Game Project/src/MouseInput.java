import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter{

	private Handler handler;
	private Camera cam;
	private GameObject tempPlayer = null;
	
	public MouseInput(Handler handler, Camera cam) {
		this.handler = handler;
		this.cam = cam;
	}
	//allows mouse to find player among all other objects
	public void findPlayer() {
		for(int ix = 0; ix < handler.object.size(); ix++ ) {
			if(handler.object.get(ix).getId() == ID.Player ) {
				tempPlayer = handler.object.get(ix);
				break;
			}
		}
	}
	
	public void mousePressed(MouseEvent e) {
		//finds x & y location of our mouse
		int mx = e.getX();
		int my = e.getY();
		
		if(tempPlayer != null) {	
			GameObject tempBullet = handler.addObject(new Bullet(tempPlayer.x+16, tempPlayer.y+16, ID.Bullet));
			//algorithm for angle of bullet
			float angle = (float) Math.atan2(my - tempPlayer.y-16+cam.getY(), mx - tempPlayer.x-16+cam.getX());
			int bulletVel = 10;
			
			tempBullet.velX = (float) ((bulletVel) * Math.cos(angle));
			tempBullet.velY = (float) ((bulletVel) * Math.sin(angle));
			
		}else findPlayer();
	}
}
