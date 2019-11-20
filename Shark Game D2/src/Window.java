//This class creates the window.
import java.awt.Dimension;
import javax.swing.JFrame;

public class Window {
	
	//constructor
	public Window(int width, int height, String title, Game game) {
		
		JFrame frame = new JFrame(title);
		
		//Set window size
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		
		
		
		
	}

}
