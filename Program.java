import javax.swing.*;

public class Program extends JFrame{
	
	private static final long serialVersionUID = 1L;
	Wall maze;

	public Program() throws InterruptedException {
		
		this.setTitle("Pacman");
		this.setResizable(false);

		maze = new Wall(true);
		
		this.add(maze);
		
		this.pack();
		
		this.setLocationRelativeTo(null);		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		this.setVisible(true);
		this.requestFocusInWindow();
		while (true) {
			maze.getPacMan().move();
			moveGhost("Red");
			moveGhost("Blue");
			moveGhost("Pink");
			moveGhost("Orange");
			
		}
	}
	public void moveGhost(String color) throws InterruptedException {
		maze.getGhost(color).whereToMove(maze.getPacMan());
		maze.getGhost(color).move();
	}
	
}