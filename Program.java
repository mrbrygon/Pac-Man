import java.util.concurrent.TimeUnit;

import javax.swing.*;

public class Program extends JFrame{
	
	private static final long serialVersionUID = 1L;
	Wall maze;

	public Program(boolean hard) throws InterruptedException {
		
		this.setTitle("Pacman");
		this.setResizable(false);

		maze = new Wall(hard);
		
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
			if (maze.getGhost("Orange") != null) {
			    maze.getGhost("Orange").move();
			    maze.getGhost("Orange").whereToMove(maze.getPacMan());
			}
			maze.checkCollisions();
			TimeUnit.MILLISECONDS.sleep(100);
			if (maze.getGameOver()) {
				return;
			}
		}
	}
	public void moveGhost(String color) throws InterruptedException {
		maze.getGhost(color).whereToMove(maze.getPacMan());
		maze.getGhost(color).move();
	}
	
}