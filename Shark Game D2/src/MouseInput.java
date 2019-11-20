import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter{
	
	private Handler handler;
	private Camera camera;
	private Game game;
	
	//Mouse Input constructor
	public MouseInput(Handler handler, Camera camera, Game game) {
		this.handler = handler;
		this.camera = camera;
		this.game = game;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = (int) (e.getX() + camera.getX());
		int my = (int) (e.getY() + camera.getY());
		
		for(int ix = 0; ix < handler.object.size(); ix++) {
			GameObject tempObject = handler.object.get(ix);
			
			if(tempObject.getId() == ID.Player && game.ammo >= 1) {
														//+16 & +24 makes the bullet shoot from the center of Player
				handler.addObject(new Bullet(tempObject.getX()+16, tempObject.getY()+24, ID.Bullet, handler, mx, my));
				game.ammo--;
			}
		}
	}

}
