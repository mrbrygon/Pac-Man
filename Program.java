import javax.swing.*;

public class Program extends JFrame{
	
	private static final long serialVersionUID = 1L;

	public Program() throws InterruptedException {
		
		this.setTitle("Pacman");
		this.setResizable(false);

		Wall maze = new Wall();
		
		this.add(maze);
		
		this.pack();
		
		this.setLocationRelativeTo(null);		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		this.setVisible(true);
		this.requestFocusInWindow();
		while (true) {
			maze.getPacMan().move();
		}
	}
	
}