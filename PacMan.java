import javax.swing.*;
import java.net.URL;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

public class PacMan extends AbstractAction {
	private String[][] tileMap;
	private ImageIcon upIcon;
	private ImageIcon downIcon;
	private ImageIcon leftIcon;
	private ImageIcon rightIcon;
	private JLabel PacMan;
	private int x = 8;
	private int y = 10;
	private String movementType = "up";
	private boolean gameComplete = false;
	private int score = 0;
	private int prevX;
	private int prevY;
	
	public PacMan(String[][] mapImage) {
		URL upUrl = PacMan.class.getResource("PacManGifUp.gif");
        upIcon = new ImageIcon(upUrl);
		URL downUrl = PacMan.class.getResource("PacManGifDown.gif");
        downIcon = new ImageIcon(downUrl);
		URL leftUrl = PacMan.class.getResource("PacManGifLeft.gif");
        leftIcon = new ImageIcon(leftUrl);
		URL rightUrl = PacMan.class.getResource("PacManGifRight.gif");
        rightIcon = new ImageIcon(rightUrl);
        PacMan = new JLabel(upIcon);
        upIcon.setImageObserver(PacMan);
        downIcon.setImageObserver(PacMan);
        leftIcon.setImageObserver(PacMan);
        rightIcon.setImageObserver(PacMan);
        boolean isFinished = false;
		for (int i = 0; i < mapImage.length && !isFinished; i++) {
			for (int j = 0; j < mapImage[0].length; j++) {
				if (mapImage[i][j].equals("P")) {
					x = i;
					y = j;
					isFinished = true;
					break;
				}
			}
		}
        this.tileMap = mapImage;
        PacMan.setBounds(x*32, y*32, 32, 32);
	}
	public void move() throws InterruptedException {
		prevX = x;
	    prevY = y;
		InputMap im = PacMan.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		im.put(KeyStroke.getKeyStroke("UP"), "moveUp");
		im.put(KeyStroke.getKeyStroke("DOWN"), "moveDown");
		im.put(KeyStroke.getKeyStroke("LEFT"), "moveLeft");
		im.put(KeyStroke.getKeyStroke("RIGHT"), "moveRight");
		ActionMap am = PacMan.getActionMap();
		am.put("moveUp", upAction);
		am.put("moveDown", downAction);
		am.put("moveLeft", leftAction);
		am.put("moveRight", rightAction);
		if (movementType.equals("up") && canMove("up")) {
			for (int i = 1; i <= 4; i++) {
				PacMan.setBounds(x * 32, y * 32 - i * 8, 32, 32);
			}
			y -= 1;
			this.isCoin();	
		}
		if (movementType.equals("down") && canMove("down")) {
			for (int i = 1; i <= 4; i++) {
				PacMan.setBounds(x * 32, y * 32 + i * 8, 32, 32);
			}
			y += 1;
			this.isCoin();
		}
		if (movementType.equals("left") && canMove("left")) {
			for (int i = 1; i <= 4; i++) {
				PacMan.setBounds(x * 32 - i * 8, y * 32, 32, 32);
			}
			x -= 1;
			this.isCoin();
		}
		if (movementType.equals("right") && canMove("right")) {
			for (int i = 1; i <= 4; i++) {
				PacMan.setBounds(x * 32 + i * 8, y * 32, 32, 32);
			}
			x += 1;
			this.isCoin();
		}			
		if (this.getScore() == 189) {
			this.gameComplete = false;
		}
	}
	public void stop() {
		movementType = null;
	}
	Action upAction = new AbstractAction() {
	    public void actionPerformed(ActionEvent e) {
	    		movementType = "up";
	    		PacMan.setIcon(upIcon);	
	    }
	};
	
	Action leftAction = new AbstractAction() {
	    public void actionPerformed(ActionEvent e) {
	    		movementType = "left";
	    		PacMan.setIcon(leftIcon);	
	    		
	    }
	};
	
	Action rightAction = new AbstractAction() {
	    public void actionPerformed(ActionEvent e) {
	    		movementType = "right";
	    		PacMan.setIcon(rightIcon);	
	    }
	};
	
	Action downAction = new AbstractAction() {
	    public void actionPerformed(ActionEvent e) {
	    		movementType = "down";
	    		PacMan.setIcon(downIcon);	
	    }
	};
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public JLabel getLabel() {
		return PacMan;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	public boolean canMove(String movementType) {
		if (movementType.equals("up")) {
			if (tileMap[y-1][x].equals("X")) {
				return false;
			}
			return true;
		}
		if (movementType.equals("down")) {
			if (tileMap[y+1][x].equals("X")) {
				return false;
			}
			return true;
		}
		if (movementType.equals("left")) {
			if (tileMap[y][x-1].equals("X")) {
				return false;
			}
			return true;
		}
		else {
			if (tileMap[y][x+1].equals("X")) {
				return false;
			}
			return true;
		}
	}
	
	public void isCoin() {
		if (this.tileMap[this.y][this.x].equals(" ")) {
			score++;
			this.tileMap[this.y][this.x] = "O";
		}
	}
	public void reset() {
		x = 16;
		y = 9;
		movementType = "up";
		PacMan.setBounds(x * 32, y * 32, 32, 32);
	}
	public int getScore() {
		return this.score;
	}
	
	public void changeScore(int n) {
		this.score = n;
	}
	public int getPrevX() { 
		return prevX; 
	}
	public int getPrevY() { 
		return prevY; 
	}
	
	
	
	
	
}