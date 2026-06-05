import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Wall extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	private int width = 32;
	private int height = 32;
	private Image image = new ImageIcon("src/wall.png").getImage();
	
	private int rowCount = 21;
	private int columnCount = 19;
	private int boardWidth = columnCount * this.width;
	private int boardHeight = rowCount * this.height;
	
	private String[][] mapImage = 
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
	
	private PacMan pacMan;
	
	public Wall() {
		this.setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
		this.setBackground(Color.black);
		this.setLayout(null);
		this.setFocusable(true);
		this.pacMan = new PacMan(mapImage);	
		this.add(this.pacMan.getLabel());
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.loadMaze(g);
		
	
	}
	
	public void loadMaze(Graphics g) {
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (mapImage[i][j].equals("X")) {
					
					int pixelX = (j * 32);
					int pixelY = (i * 32);
					
					g.drawImage(this.image, pixelX, pixelY, this.width, this.height, this);
				}
				
			}
		}
	}
	
	public String[][] getMap() {
		return this.mapImage;
	}
	public PacMan getPacMan() {
		return pacMan;
	}

	public void actionPerformed(ActionEvent e) {	
		this.repaint();
	}
	
	
    
}