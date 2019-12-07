import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Block extends GameObject{
	
	Texture texture = Game.getInstance();
	private int type;
	
	public Block(int x, int y, int type, ID id) {
		super(x, y, id);
		this.type = type;
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		if(type == 0) {    //if type = 0, draw block '0' from the sprite sheet
			g.drawImage(texture.block[0], x, y, null);
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

	
	
}
