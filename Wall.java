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
	private int playerScore = 0;
	private int lives = 3;
	private boolean gameOver = false;
	
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
		g.drawString("Score: " + currentScore, 260, 16);
		g.drawString("Lives: " + lives, 330, 16 );
		if (this.pacMan.getScore() >= 188) {
			g.setColor(Color.black);
			String won = "You Win!";
			g.fillRect(0, 0, boardWidth, boardHeight);
			Font simpleFont = new Font("Times New Roman", Font.PLAIN + Font.BOLD, 40);
			g.setColor(Color.white);
			g.setFont(simpleFont);
			g.drawString(won, (boardWidth / 2) , boardHeight / 2);
			simpleFont = new Font("Times New Roman", Font.PLAIN + Font.BOLD, 20);
			g.setFont(simpleFont);
			pacMan.getLabel().setVisible(false);
		    ghost1.getLabel().setVisible(false);
		    ghost2.getLabel().setVisible(false);
		    ghost3.getLabel().setVisible(false);
		    if (ghost4 != null)  {
		    		ghost4.getLabel().setVisible(false);
		    }
			
		}
		else if(gameOver) {
			g.setColor(Color.black);
			g.fillRect(0, 0, boardWidth, boardHeight);
			g.setColor(Color.red);
			g.drawString("You Lose!", boardHeight / 2, boardHeight / 2);
			g.drawString("Final Score: " + pacMan.getScore(), boardWidth / 2, boardHeight / 2 + 20);
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
	public void checkCollisions() {
	    if (gameOver) return;

	    int pX = pacMan.getX();
	    int pY = pacMan.getY();
	    if (colliding(ghost1) || colliding(ghost2) || colliding(ghost3) || colliding(ghost4)) {        
	        death();
	    }
	}
	private boolean colliding(Ghost ghost) {
		if (ghost == null) {
			return false;
		}
		if (pacMan.getX() == ghost.getX() && pacMan.getY() == ghost.getY()) {
	        return true;
	    }
	    
	    if (pacMan.getX() == ghost.getPrevX() && pacMan.getY() == ghost.getPrevY() &&
	        pacMan.getPrevX() == ghost.getX() && pacMan.getPrevY() == ghost.getY()) {
	        return true;
	    }
		return false;
	}
	private void death() {
	    lives--;
	    if (lives <= 0) {
	        gameOver = true;
	        pacMan.getLabel().setVisible(false);
	        ghost1.getLabel().setVisible(false);
	        ghost2.getLabel().setVisible(false);
	        ghost3.getLabel().setVisible(false);
	        if (ghost4 != null)  {
	        		ghost4.getLabel().setVisible(false);
	        }
	    } 
	    else {
	        pacMan.reset();
	        ghost1.reset();
	        ghost2.reset();
	        ghost3.reset();
	        if (ghost4 != null) {
	        		ghost4.reset();
	        }
	    }
	}

	public void actionPerformed(ActionEvent e) {	
		this.repaint();
	}
	
	public void getScore(int score) {
		this.playerScore = score;
	}
	public boolean getGameOver() {
		return gameOver;
	}
	
    
}