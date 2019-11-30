import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public static int WIDTH = 1000, HEIGHT = 563;
	public String title = "Shark Game";

	private boolean isRunning = false;
	private Thread thread;
	private Handler handler;
	private Camera camera;

	private BufferedImage level = null;
	private BufferedImage spriteSheet = null;

	public int ammo = 100; // ammunition
	public int hp = 100;// health points
	
	private Shark shark;  //Shark player object
	
	private void init() {
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			level = loader.loadImage("/Level1_Background.png");
			spriteSheet = loader.loadImage("/SpriteSheet_Sharks.png");
			throw new IOException();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		shark = new Shark(100, 100, ID.Player, handler, this);
	}
	
	public Game() {
		// constructor
		new Window(WIDTH, HEIGHT, title, this);
		start();

		handler = new Handler();
		camera = new Camera(0, 0);
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(new MouseInput(handler, camera, this));// this refers to game

		// BufferedImageLoader loader = new BufferedImageLoader();
		// level = loader.loadImage("/shark_level.png");//Photoshop file loaded into
		// here.

		//handler.addObject(new Shark(100, 100, ID.Player, handler, this));
		//handler.addObject(shark);

		handler.addObject(new Block(200, 200, ID.Block)); // test block for collision
		handler.addObject(new Block(300, 300, ID.Block)); // test block for collision
		handler.addObject(new Block(500, 500, ID.Block)); // test block for collision

		handler.addObject(new Enemy(400, 400, ID.Enemy, handler)); // test enemy
		handler.addObject(new Enemy(400, 400, ID.Enemy, handler)); // test enemy
		handler.addObject(new Enemy(600, 600, ID.Enemy, handler)); // test enemy
		handler.addObject(new Enemy(400, 400, ID.Enemy, handler)); // test enemy

		handler.addObject(new Crate(800, 400, ID.Crate)); // test crate
		handler.addObject(new Crate(800, 400, ID.Crate)); // test crate

		// loadLevel(level); //Method to load the level
	}

	private void start() {
		isRunning = true;
		thread = new Thread(this);
		thread.start();
		isRunning = true;
	}

	private void stop() {
		isRunning = false;

		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		isRunning = false;
	}

	public void run() {
		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountofTicks = 60.0;
		double ns = 1000000000 / amountofTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frames = 0;
			}
		}
		stop();
	}

	// updates the game at 60fps
	private void tick() {
		// which game object is our player?
		for (int ix = 0; ix < handler.object.size(); ix++) {
			if (handler.object.get(ix).getId() == ID.Player) {
				camera.tick(handler.object.get(ix));
			}
		}

		handler.tick();
		shark.tick();

	}

	// renders the game
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		//////////////////////////////////
		// Meat and bones of our rendering
		g.setColor(Color.red);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.drawImage(level, 0, 0, null);  // This draws the background image for Level 1.

		g2d.translate(-camera.getX(), -camera.getY());

		handler.render(g);
		shark.render(g);

		g2d.translate(camera.getX(), camera.getY());

		// This health bar code stays below all other code in this section
		g.setColor(Color.gray);
		g.fillRect(5, 5, 200, 32);
		g.setColor(Color.green);
		g.fillRect(5, 5, hp * 2, 32);
		g.setColor(Color.black);
		g.drawRect(5, 5, 200, 32);

		g.setColor(Color.white);
		g.drawString("Ammo: " + ammo, 5, 50);

		///////////////////////////////////
		g.dispose();
		bs.show();

	}

	// loading the level
	private void loadLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();

		for (int xx = 0; xx < w; xx++) {
			for (int yy = 0; yy < h; yy++) {
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff; // bit operator
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				// if color values are correct make the block
				if (red == 255)
					handler.addObject(new Block(xx * 32, yy * 32, ID.Block));
				// if "...." make player
				if (red == 255)
					handler.addObject(new Shark(xx * 32, yy * 32, ID.Player, handler, this));// this refers to game

			}
		}

	}

	public static void main(String[] args) {
		new Game();
	}
	
	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}

}
