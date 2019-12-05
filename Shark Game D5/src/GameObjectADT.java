 import java.awt.Graphics;

public interface GameObjectADT {

	public void tick();
	
	public void render(Graphics g);
	
	public int getX();
	
	public int getY();
}
