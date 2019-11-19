import java.awt.Color;
import java.awt.Graphics;

//Temporary class stand objects for player to navigate around.
public class Box extends GameObject {

	public Box(float x, float y, ID id) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		x+= velX;
		y+= velY;
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 64, 64);
		
	}

}
