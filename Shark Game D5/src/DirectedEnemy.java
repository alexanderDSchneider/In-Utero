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
	
	public int findPlayerX() {
		int pX = 0; //player x coordinate
		
		for(int ix = 0; ix < handler.object.size(); ix++) {
			GameObject tempObject = handler.object.get(ix); //create a temp object
			GameObject playerCord;
			
		    if(tempObject.getId() == ID.Player) {
		        playerCord = tempObject; //if temp is a player
		        pX = playerCord.getX();  //the x coord of temp is player X coord		        
		    }
		} 
		return pX;
	}
	
	public int findPlayerY() {
		int pY = 0;
		
		for(int ix = 0; ix < handler.object.size(); ix++) {
			GameObject tempObject = handler.object.get(ix);//create a temp object
			GameObject playerCord;
			
		    if(tempObject.getId() == ID.Player) {
		        playerCord = tempObject; //if temp is a player
		        pY = playerCord.getY();  //the y coord of temp is player y coord
		    }
		} 
		return pY;
	}

	public void tick() {
		x += velX;
		y += velY;
		
		
		choose = r.nextInt(10);
		
		for(int ix = 0; ix < handler.object.size(); ix++) {
			GameObject tempObject = handler.object.get(ix);
		
			int playerX = findPlayerX(); //players x coordinate
			int playerY = findPlayerY(); //players y coordinate
			double directX = 0;
			double directY = 0;
			double angle, xScaling, yScaling;


		        //y = (slope)x + b
			    //line from Enemy to Shark = x((playerX-x)/(playerY-y)) + y
			directX = playerX - x;   //(playerX-x)
			directY = playerY - y;   //(playerY-y)
			
			angle = Math.atan2(directY, directX);   // find angle of line between player and enemy using tangent inverse
						
			xScaling = Math.cos(angle);   //x velocity component is cosine of the angle
			yScaling = Math.sin(angle);   //y velocity component is the sine of the angle
			
			if(1.11 < angle && angle < 2) {
				velY = 2;      //moves enemy when tangent is invalid value
			}
			else if (-1.11 > angle && angle > -2) {
				velY = -2;     //moves enemy when tangent is invalid value
			}
			else {
				if(xScaling > 0 && yScaling < 0) {
					velX = (int)(2.3*xScaling);  //correct value of the velocity to set it in the right direction
					velY = -(int)(2.3*xScaling); 
				}
				else if(xScaling < 0 && yScaling > 0) {
					velX = (int)(2.3*xScaling);   //correct value of the velocity to set it in the right direction
					velY = -(int)(2.3*xScaling);
				}
				else if(xScaling > 0 && yScaling > 0 || xScaling < 0 && yScaling < 0){
			        velX = (int)(2.3*xScaling);   //base case of the velocity, needs no correction
				    velY = (int)(2.3*xScaling);
				}
			}
			    
		
	
			
			
			if(tempObject.getId() == ID.Block) {
				if(getBounds().intersects(tempObject.getBounds())) {
					velX += (velX *20) * -1;//Shoots enemy in opposite direction
					velX += (velX *20) * -1;//at 5x speed
					//velX = 0;
					System.out.println("fuck");
					//velY = 0;
					//velX = -1;
					//velX = -1;
				}
			}	
			
			if(tempObject.getId() == ID.Bullet) {
				if(getBounds().intersects(tempObject.getBounds())) {
					hp -= 50; //each bullets takes off 50 health points
					handler.removeObject(tempObject);
				}
				
			}
			
			
		}
		
		if(hp <= 0) handler.removeObject(this);  //enemy dies
				
	}

	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(x, y, 32, 32);
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}
	
	public Rectangle getBoundsBig() {
		return new Rectangle(x-16, y-16, 64, 64);//accounts for giving space between enemy and enemy bounding box
	}

}

