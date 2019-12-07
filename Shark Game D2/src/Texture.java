import java.awt.image.BufferedImage;

public class Texture {
	
	SpriteSheet bs;
	SpriteSheet ss;
	private BufferedImage blockSheet = null;
	private BufferedImage sharkSheet = null;

	public BufferedImage[] block = new BufferedImage[1];
	public BufferedImage[] shark = new BufferedImage[1];
	
	public Texture() {
		BufferedImageLoader loader = new BufferedImageLoader();
		
		try {
			blockSheet = loader.loadImage("SpriteSheet_Blocks.png");
			sharkSheet = loader.loadImage("SpriteSheet_Sharks.png");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		bs = new SpriteSheet(blockSheet);
		ss = new SpriteSheet(sharkSheet);
		
		getTextures();
		
	}
	
	public void getTextures() {
		block[0] = bs.grabImage(1, 1, 32, 32);
		shark[0] = ss.grabImage(1, 1, 64, 32);
	}
	
}
