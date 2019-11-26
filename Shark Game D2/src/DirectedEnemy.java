import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class DirectedEnemy extends GameObject {
	
	private Handler handler;
	Random r = new Random();
	int choose = 0;
	int hp = 100; //health points = 100
	
	//Enemy constructor
	public DirectedEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}

	public void tick() {
		x += velX;
		y += velY;
		
		choose = r.nextInt(10);
		
		for(int ix = 0; ix < handler.object.size(); ix++) {
			GameObject tempObject = handler.object.get(ix);
			//if colliding with block...correct course
			if(tempObject.getId() == ID.Block) {
				if(getBoundsBig().intersects(tempObject.getBounds())) {
					velX += (velX *5) * -1;//Shoots enemy in opposite direction
					velX += (velX *5) * -1;//at 5x speed
					
					velX = -1;
					velX = -1;
				}
				else if(tempObject.getId() == ID.Player) {//Enemy randomized movement algorithm;
					//velX = (r.nextInt(4 - -4) + -4);
					//velY = (r.nextInt(4 - -4) + -4);
					velX = tempObject.getX()*3;
					velY = tempObject.getY()*3;
				}
			}
			
			if(tempObject.getId() == ID.Bullet) {
				if(getBounds().intersects(tempObject.getBounds())) {
					hp -= 50; //each bullets takes off 50 health points
					handler.removeObject(tempObject);
				}
				
			}
			
			if(tempObject.getId() == ID.Player) {
				    //velX = tempObject.getX()*3;
				    //velY = tempObject.getY()*3;
			}
			
		}
		
		if(hp <= 0) handler.removeObject(this);  //enemy dies
				
	}

	public void render(Graphics g) {
		g.setColor(Color.pink);
		g.fillRect(x, y, 32, 32);
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}
	
	public Rectangle getBoundsBig() {
		return new Rectangle(x-16, y-16, 64, 64);//accounts for giving space between enemy and enemy bounding box
	}

}

