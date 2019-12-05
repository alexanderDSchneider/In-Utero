import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Menu extends MouseAdapter{
	
	private Game game;
	private Handler handler;
	private Camera camera;
	
	public Menu(Game game, Handler handler, Camera camera) {
		this.game = game;
		this.handler = handler;
		this.camera = camera;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();  //get the mouse position at a click
		int my = e.getY();
		
		if(game.gameState == Game.STATE.Menu || game.gameState == Game.STATE.Help|| game.gameState == Game.STATE.End) {
			//If play button is clicked then start the game
			if(mouseOver(mx, my, 425, 100, 150, 64)) {
				game.gameState = Game.STATE.Game;
				handler.addObject(new Shark(100, 100, ID.Player, handler, game));
				handler.addObject(new Block(490, 600, ID.Block)); //test enemy
				handler.addObject(new DirectedEnemy(300, 550, ID.DirectedEnemy, handler)); //test enemy
				handler.addObject(new DirectedEnemy(400, 290, ID.DirectedEnemy, handler)); //test enemy
				
				//handler.addObject(new DirectedEnemy(400, 400, ID.DirectedEnemy, handler)); //test enemy
				//handler.addObject(new Enemy(500, 400, ID.Enemy, handler)); //test enemy
				//d
				handler.addObject(new SplitDirectedEnemy(600, 600, ID.SplitDirectedEnemy, handler)); //test enemy
				handler.addObject(new DirectedEnemy(400, 500, ID.DirectedEnemy, handler)); //test enemy
				
				handler.addObject(new Crate(800, 400, ID.Crate)); // test crate
				handler.addObject(new Crate(800, 400, ID.Crate)); // test crate
			} 
			//help button
			else if(mouseOver(mx, my, 425, 200, 150, 64)) {
				game.gameState = Game.STATE.Help;
			} 
			//back button for help menu
			else if(game.gameState == Game.STATE.Help) {
				if(mouseOver(mx, my, 425, 400, 150, 64)) {
					game.gameState = Game.STATE.Menu;
					return;
				}
			} 
			//quit button
			else if(mouseOver(mx, my, 425, 300, 150, 64)) {
				System.exit(1);
			}
			
			//go back to main menu if game is over
			if(game.gameState == Game.STATE.End) {
				if(mouseOver(mx, my, 405, 400, 200, 64)) {
					handler.resetList();
					game.gameState = Game.STATE.Menu; 
				}
			}
		}
	}	
		
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if(mx > x && mx < x + width) {        //if mouse position x is greater than the start of a menu button and its less than the width plus x
			if(my > y && my < y + height) {   //if mouse position y is greater than the start of a menu button and its less than the height plus y
				return true;                  //return true  -- then our mouse position is within a specific box
			}
			else { return false; }            //else return false
		}
		else { return false; }
	}
	
	public void tick() {	}
	
	public void render(Graphics g) {
		if(game.gameState == Game.STATE.Menu) {
			Font title = new Font("arial", 1, 50);
			Font buttonFont = new Font("arial", 1, 30);
			
			g.setFont(title);
			g.setColor(Color.WHITE);
			g.drawString("Shark Bait", 375, 70);
			
			g.setFont(buttonFont);
			g.setColor(Color.WHITE);
			g.drawRect(425, 100, 150, 64);
			g.drawString("Play", 465, 140);
			
			g.setColor(Color.WHITE);
			g.drawRect(425, 200, 150, 64);
			g.drawString("Help", 465, 245);
			
			g.setColor(Color.WHITE);
			g.drawRect(425, 300, 150, 64);
			g.drawString("Quit", 465, 340);
		}else if(game.gameState == Game.STATE.Help) {
			Font title = new Font("arial", 1, 50);
			Font buttonFont = new Font("arial", 1, 30);
			Font helpFont = new Font("arial", 1, 20);
			
			g.setFont(title);
			g.setColor(Color.WHITE);
			g.drawString("Help", 445, 70);
			
			g.setFont(helpFont);
			g.drawString("Press W to move up", 400, 120);
			g.drawString("Press S to move down", 400, 150);
			g.drawString("Press A to move left", 400, 180);
			g.drawString("Press D to move right", 400, 210);
			g.drawString("Left click to shoot", 400, 240);
			
			g.setFont(buttonFont);
			g.drawRect(425, 400, 150, 64);
			g.drawString("Back", 465, 440);
		}else if(game.gameState == Game.STATE.End) {
			Font title = new Font("arial", 1, 50);
			Font buttonFont = new Font("arial", 1, 30);

			g.setFont(title);
			g.setColor(Color.WHITE);
			g.drawString("Game Over", 365, 90);
		
			g.setFont(buttonFont);
			g.drawRect(405, 400, 200, 64);
			g.drawString("Main Menu", 427, 443);
		}
		
	}

}
