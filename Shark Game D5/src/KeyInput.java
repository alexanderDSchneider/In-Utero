import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
		
		Handler handler;
		
		public KeyInput(Handler handler) {
			this.handler = handler;
		}
		
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			
			for(int ix = 0; ix < handler.object.size(); ix++) {
				GameObject tempObject = handler.object.get(ix);
				
				if(tempObject.getId() == ID.Player){
					if(key == KeyEvent.VK_W) handler.setUp(true);
					if(key == KeyEvent.VK_S) handler.setDown(true);
					if(key == KeyEvent.VK_A) handler.setLeft(true);
					if(key == KeyEvent.VK_D) handler.setRight(true);
				}
			}
			
		}
		
		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
			
			for(int ix = 0; ix < handler.object.size(); ix++) {
				GameObject tempObject = handler.object.get(ix);
				
				if(tempObject.getId() == ID.Player){
					if(key == KeyEvent.VK_W) handler.setUp(false);
					if(key == KeyEvent.VK_S) handler.setDown(false);
					if(key == KeyEvent.VK_A) handler.setLeft(false);
					if(key == KeyEvent.VK_D) handler.setRight(false);
				}
			}
		
		}
			
}
