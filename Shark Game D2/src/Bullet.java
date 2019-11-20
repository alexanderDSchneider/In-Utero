import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends GameObject{
	
	private Handler handler;

	public Bullet(int x, int y, ID id, Handler handler, int mx, int my) {//handler could go in the GameObject abstract class
		super(x, y, id);												//since all objects have some form of collision
		this.handler = handler;
		//where do we want the bullet to travel to.
		velX = (mx - x) / 10; //10 = bullet speed
		velY = (my - y) / 10;
	}

	public void tick() {
		x += velX;
		y += velY;
		
		for(int ix = 0; ix < handler.object.size(); ix++ ) {
			GameObject tempObject = handler.object.get(ix);
			//bullet collides with solid surface destroy bullet
			if(tempObject.getId() == ID.Block) {
				if(getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(this);
				}
			}
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillOval(x, y, 8, 8);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 8, 8);
	}

}
