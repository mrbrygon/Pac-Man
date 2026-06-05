import javax.swing.*;
import java.awt.Color;
import java.awt.*;

public class PacManTestAPP {
	private static final long serialVersionUID = 1L;
	private int totalTicks = 0;
	private int width = 32;
	private int height = 32;
	private Image image = new ImageIcon("./Images/wall.png").getImage();
	private int rowCount = 21;
	private int columnCount = 19;
	private int boardWidth = columnCount * this.width;
	private int boardHeight = rowCount * this.height;

	public static void main(String[] args) throws InterruptedException {
		String[][] mapImage = 
			{
				{"X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X"},
				{"X", " ", " ", " ", " ", " ", " ", " ", " ", "X", " ", " ", " ", " ", " ", " ", " ", " ", "X"},
				{"X", " ", "X", "X", " ", "X", "X", "X", " ", "X", " ", "X", "X", "X", " ", "X", "X", " ", "X"},
				{"X", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "X"},
				{"X", " ", "X", "X", " ", "X", " ", "X", "X", "X", "X", "X", " ", "X", " ", "X", "X", " ", "X"},
				{"X", " ", " ", " ", " ", "X", " ", " ", " ", " ", " ", " ", " ", "X", " ", " ", " ", " ", "X"},
				{"X", "X", "X", "X", " ", "X", "X", "X", "X", " ", "X", "X", "X", "X", " ", "X", "X", "X", "X"},
				{" ", " ", " ", "X", " ", "X", " ", " ", " ", " ", " ", " ", " ", "X", " ", "X", " ", " ", " "},
				{"X", "X", "X", "X", " ", "X", " ", "X", "X", " ", "X", "X", " ", "X", " ", "X", "X", "X", "X"},
				{"X", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "X"},
				{"X", "X", "X", "X", " ", "X", " ", "X", "X", "X", "X", "X", " ", "X", " ", "X", "X", "X", "X"},
				{" ", " ", " ", "X", " ", "X", " ", " ", " ", " ", " ", " ", " ", " ", " ", "X", " ", " ", " "},
				{"X", "X", "X", "X", " ", "X", " ", "X", "X", "X", "X", "X", " ", "X", " ", "X", "X", "X", "X"},
				{"X", " ", " ", " ", " ", " ", " ", " ", " ", "X", " ", " ", " ", " ", " ", " ", " ", " ", "X"},
				{"X", " ", "X", "X", " ", "X", "X", "X", " ", "X", " ", "X", "X", "X", " ", "X", "X", " ", "X"},
				{"X", " ", " ", "X", " ", " ", " ", " ", " ", "P", " ", " ", " ", " ", " ", "X", " ", " ", "X"},
				{"X", "X", " ", "X", " ", "X", " ", "X", "X", "X", "X", "X", " ", "X", " ", "X", " ", "X", "X"},
				{"X", " ", " ", " ", " ", "X", " ", " ", " ", "X", " ", " ", " ", "X", " ", " ", " ", " ", "X"},
				{"X", " ", "X", "X", "X", "X", "X", "X", " ", "X", " ", "X", "X", "X", "X", "X", "X", " ", "X"},
				{"X", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "X"},
				{"X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X"}
				
			};
		
		
		
		PacMan pacMan = new PacMan(mapImage);
		Ghost ghost = new Ghost("Red");
		JFrame frame = new JFrame("GIF Example");
		frame.getContentPane().setBackground(Color.BLACK);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(pacMan.getLabel());
        frame.add(ghost.getLabel());
        frame.setPreferredSize(new Dimension(19 * 32, 21 * 32));
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        while (1 == 1) {
        		pacMan.move();
        		frame.add(ghost.getLabel());
        		frame.add(pacMan.getLabel());
        }
	}

}
