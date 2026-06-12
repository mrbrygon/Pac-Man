import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Wall extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	private int width = 32;
	private int height = 32;
	private Image image = new ImageIcon("src/wall.png").getImage();
	private String isReplay = "false";
	private int rowCount = 21;
	private int columnCount = 19;
	private int boardWidth = columnCount * this.width;
	private int boardHeight = rowCount * this.height;
	private int playerScore = 0;
	
	private String[][] mapImage = 
	{
		{"X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X"},
		{"X", "G", " ", " ", " ", " ", " ", " ", " ", "X", " ", " ", " ", " ", " ", " ", " ", " ", "X"},
		{"X", " ", "X", "X", " ", "X", "X", "X", " ", "X", " ", "X", "X", "X", " ", "X", "X", " ", "X"},
		{"X", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "X"},
		{"X", " ", "X", "X", " ", "X", " ", "X", "X", "X", "X", "X", " ", "X", " ", "X", "X", " ", "X"},
		{"X", " ", " ", " ", " ", "X", " ", " ", " ", " ", " ", " ", " ", "X", " ", " ", " ", " ", "X"},
		{"X", "X", "X", "X", " ", "X", "X", "X", "X", " ", "X", "X", "X", "X", " ", "X", "X", "X", "X"},
		{" ", " ", " ", "X", " ", "X", " ", " ", " ", " ", " ", " ", " ", "X", " ", "X", " ", " ", " "},
		{"X", "X", "X", "X", " ", "X", " ", "X", "X", " ", "X", "X", " ", "X", " ", "X", "X", "X", "X"},
		{"X", "G", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "X"},
		{"X", "X", "X", "X", " ", "X", " ", "X", "X", "X", "X", "X", " ", "X", " ", "X", "X", "X", "X"},
		{" ", " ", " ", "X", " ", "X", " ", " ", " ", " ", " ", " ", " ", " ", " ", "X", " ", " ", " "},
		{"X", "X", "X", "X", " ", "X", " ", "X", "X", "X", "X", "X", " ", "X", " ", "X", "X", "X", "X"},
		{"X", " ", " ", " ", " ", " ", " ", " ", " ", "X", " ", " ", " ", " ", " ", " ", " ", " ", "X"},
		{"X", " ", "X", "X", " ", "X", "X", "X", " ", "X", " ", "X", "X", "X", " ", "X", "X", " ", "X"},
		{"X", "G", " ", "X", " ", " ", " ", " ", " ", "P", " ", " ", " ", " ", " ", "X", " ", " ", "X"},
		{"X", "X", " ", "X", " ", "X", " ", "X", "X", "X", "X", "X", " ", "X", " ", "X", " ", "X", "X"},
		{"X", " ", " ", " ", " ", "X", " ", " ", " ", "X", " ", " ", " ", "X", " ", " ", " ", " ", "X"},
		{"X", " ", "X", "X", "X", "X", "X", "X", " ", "X", " ", "X", "X", "X", "X", "X", "X", " ", "X"},
		{"X", "G", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "X"},
		{"X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X"}
		
	};
	
	private PacMan pacMan;
	private Ghost ghost1;
	private Ghost ghost2;
	private Ghost ghost3;
	private Ghost ghost4;
	
	public Wall(boolean hard) {
		this.setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
		this.setBackground(Color.black);
		this.setLayout(null);
		this.setFocusable(true);
		this.pacMan = new PacMan(mapImage);	
		this.ghost1 = new Ghost("Red", mapImage, 1, 1);	
		this.ghost2 = new Ghost("Blue", mapImage, 1, 9);	
		this.ghost3 = new Ghost("Pink", mapImage, 1, 15);	
		this.add(this.pacMan.getLabel());
		this.add(this.ghost1.getLabel());
		this.add(this.ghost2.getLabel());
		this.add(this.ghost3.getLabel());
		if (hard) {
			this.ghost4 = new Ghost("Orange", mapImage, 1, 19);
			this.add(this.ghost4.getLabel());
		}
		
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.loadMazeAndPoints(g);
		
		g.setColor(Color.white);
		
		
		String currentScore = String.valueOf(this.pacMan.getScore());
		g.drawString("Score: " + currentScore, 16, 16);

		if (this.pacMan.getScore() >= 189) {
			g.setColor(Color.black);
			
			FontMetrics metrics = g.getFontMetrics();
			
			
			String won = "You Win!";
			
			g.fillRect(0, 0, this.boardWidth, this.boardHeight);
			Font simpleFont = new Font("Times New Roman", Font.PLAIN + Font.BOLD, 40);
			g.setColor(Color.white);
			g.setFont(simpleFont);
			g.drawString(won, (this.boardWidth - metrics.stringWidth(won)) / 2 - metrics.stringWidth(won), this.boardHeight / 2);
			simpleFont = new Font("Times New Roman", Font.PLAIN + Font.BOLD, 20);
			g.setFont(simpleFont);
			
		}
		
		this.repaint();
	}
	
	public void loadMazeAndPoints(Graphics g) {
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (mapImage[i][j].equals("X")) {
					
					int pixelX = (j * 32);
					int pixelY = (i * 32);
					
					g.drawImage(this.image, pixelX, pixelY, this.width, this.height, this);
				}
				
				if (mapImage[i][j].equals(" ")) {
					int pixelX = (j * 32) + (32 / 2);
					int pixelY = (i * 32) + (32/ 2);
					
					g.setColor(Color.white);
					
					g.fillRect(pixelX, pixelY, 2, 2);
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
	public Ghost getGhost(String ghostType) {
		if (ghostType.equals("Red")) {
			return ghost1;
		}
		else if (ghostType.equals("Blue")) {
			return ghost2;
		}
		else if (ghostType.equals("Pink")) {
			return ghost3;
		}
		else {
			return ghost4;
		}
		
	}

	public void actionPerformed(ActionEvent e) {	
		this.repaint();
	}
	
	public void getScore(int score) {
		this.playerScore = score;
	}
	
	
    
}