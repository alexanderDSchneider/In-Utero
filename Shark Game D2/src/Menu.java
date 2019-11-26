import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;


public class Menu extends MouseAdapter{
	
	private Game game;
	private Handler handler;
	
	public Menu(Game game, Handler handler) {
		this.game = game;
		this.handler = handler;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();  //get the mouse position at a click
		int my = e.getY();
		
		if(game.gameState == Game.STATE.Menu) {  //make sure we are in the menu state
			//If play button is clicked then start the game
			if(mouseOver(mx, my, 425, 100, 150, 64)) {
				game.gameState = Game.STATE.Game;
				handler.addObject(new Shark(100, 100, ID.Player, handler, game));
				
				handler.addObject(new Block(200, 200, ID.Block)); //test block for collision
				handler.addObject(new Block(300, 300, ID.Block)); //test block for collision
				handler.addObject(new Block(500, 500, ID.Block)); //test block for collision
				
				handler.addObject(new Enemy(400, 400, ID.Enemy, handler)); //test enemy
				handler.addObject(new Enemy(400, 400, ID.Enemy, handler)); //test enemy
				handler.addObject(new Enemy(600, 600, ID.Enemy, handler)); //test enemy
				handler.addObject(new Enemy(400, 400, ID.Enemy, handler)); //test enemy
				
				handler.addObject(new Crate(800, 400, ID.Crate)); // test crate
				handler.addObject(new Crate(800, 400, ID.Crate)); // test crate
			}
			//help button
			if(mouseOver(mx, my, 425, 200, 150, 64)) {
				game.gameState = Game.STATE.Help;
			}
			
			//back button for help menu
			if(game.gameState == Game.STATE.Help) {
				if(mouseOver(mx, my, 400, 300, 200, 64)) {
					game.gameState = Game.STATE.Menu;
					return;
				}
			}
			
			//quit button
			if(mouseOver(mx, my, 425, 300, 150, 64)) {
				System.exit(1);
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
	
	public void tick() {
		
	}
	
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
			
			g.setFont(title);
			g.setColor(Color.WHITE);
			g.drawString("Help", 445, 70);
			
			g.setFont(buttonFont);
			g.drawRect(425, 300, 150, 64);
			g.drawString("Back", 465, 340);
		}else if(game.gameState == Game.STATE.End) {
			Font title = new Font("arial", 1, 50);
			Font buttonFont = new Font("arial", 1, 30);
			
			g.setFont(title);
			g.setColor(Color.WHITE);
			g.drawString("Game Over", 445, 70);
			
			g.setFont(buttonFont);
			g.drawRect(425, 300, 150, 64);
			g.drawString("Try Again", 465, 340);
		}
		
	}

}
