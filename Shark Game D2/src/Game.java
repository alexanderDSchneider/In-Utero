import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;



public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 1L;
	
	public static int WIDTH = 1000, HEIGHT = 563;
	public String title = "Shark Game";
	
	private boolean isRunning = false;
	private Thread thread;
	private Handler handler;
	private Camera camera;
	private Menu menu;
	
	private BufferedImage level = null;
	
	public int ammo = 100; //ammunition
	public int hp = 100;//health points
	
	public enum STATE {
		Menu,
		Game,
		Help,
		End
	}
	
	public STATE gameState = STATE.Menu; // cast STATE as a type

	
	
	public Game() {
		//constructor
		new Window(WIDTH, HEIGHT, title, this);
		start();
		
		handler = new Handler();
		camera = new Camera(0,0);
		menu = new Menu(this, handler);
		this.addMouseListener(menu);
		
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(new MouseInput(handler, camera, this));//this refers to game
		
		//BufferedImageLoader loader = new BufferedImageLoader();
		//level = loader.loadImage("/shark_level.png");//Photoshop file loaded into here.
		
		
		
		if(gameState == STATE.Game) {
			/*handler.addObject(new Shark(100, 100, ID.Player, handler, this));
			
			handler.addObject(new Block(200, 200, ID.Block)); //test block for collision
			handler.addObject(new Block(300, 300, ID.Block)); //test block for collision
			handler.addObject(new Block(500, 500, ID.Block)); //test block for collision
			
			handler.addObject(new Enemy(400, 400, ID.Enemy, handler)); //test enemy
			handler.addObject(new Enemy(400, 400, ID.Enemy, handler)); //test enemy
			handler.addObject(new Enemy(600, 600, ID.Enemy, handler)); //test enemy
			handler.addObject(new Enemy(400, 400, ID.Enemy, handler)); //test enemy
			
			handler.addObject(new Crate(800, 400, ID.Crate)); // test crate
			handler.addObject(new Crate(800, 400, ID.Crate)); // test crate*/
		}
		
		
		//loadLevel(level);  //Method to load the level
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
		
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountofTicks = 60.0;
		double ns = 1000000000 / amountofTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frames = 0;
			}
		}
		stop();
	}
	
	//updates the game at 60fps
		private void tick() {
			//which game object is our player?
			for(int ix = 0; ix < handler.object.size(); ix++) {
				if (handler.object.get(ix).getId() == ID.Player) {
					  camera.tick(handler.object.get(ix));
				}
			}
			
			// If the game state is in game then update the game
			if(gameState == STATE.Game) {
				handler.tick();
				if(hp <= 0) {
					gameState = STATE.End;
				}
			} else if(gameState == STATE.Menu || gameState == STATE.End) {
				menu.tick();
			}
			
			
		}
		
		//renders the game
		private void render() {
			BufferStrategy bs = this.getBufferStrategy();
			if (bs == null) {
				this.createBufferStrategy(3);
				return;
			}
			Graphics g = bs.getDrawGraphics();
			Graphics2D g2d = (Graphics2D) g;
			//////////////////////////////////
		    //Meat and bones of our rendering
			g.setColor(Color.GRAY);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			
			g2d.translate(-camera.getX(), -camera.getY());
				
			handler.render(g);   //We always want to render
			
			// If the state of the game is Game then continue to render parts of the game
			if(gameState == STATE.Game) {
				g2d.translate(camera.getX(), camera.getY());
					
				//This health bar code stays below all other code in this section
				g.setColor(Color.gray);
				g.fillRect(5,5, 200, 32);
				g.setColor(Color.green);
				g.fillRect(5,5, hp*2, 32);
				g.setColor(Color.black);
				g.drawRect(5,5, 200, 32);
				
				g.setColor(Color.white);
				g.drawString("Ammo: " + ammo, 5, 50);

			} 
			// Else show the menu
			else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End) {
				menu.render(g);
			}
			
			g.dispose();
		    bs.show();
        }  //End render method
		
		//loading the level
		private void loadLevel(BufferedImage image) {
			int w = image.getWidth();
			int h = image.getHeight();
			
			for(int xx = 0; xx< w; xx++) {
				for(int yy = 0; yy < h; yy++) {
					int pixel = image.getRGB(xx, yy);
					int red = (pixel >> 16) & 0xff;  //bit operator
					int green = (pixel >> 8) & 0xff;
					int blue = (pixel) & 0xff;
					//if color values are correct make the block
					if(red == 255)
						handler.addObject(new Block(xx*32, yy*32, ID.Block));
					//if "...." make player
					if(red == 255)
						handler.addObject(new Shark(xx*32, yy*32, ID.Player, handler, this));//this refers to game
			
				}
			}
			
		}


	public static void main(String[] args) {
		new Game();
	}

}

