import javax.swing.*;
import java.net.URL;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

public class PacMan extends AbstractAction {
	private ImageIcon upIcon;
	private ImageIcon downIcon;
	private ImageIcon leftIcon;
	private ImageIcon rightIcon;
	private JLabel PacMan;
	private int x = 250;
	private int y = 250;
	private String movementType = "up";
	private boolean gameComplete = false;
	
	public PacMan(int size) {
		URL upUrl = PacMan.class.getResource("PacManGifUp.gif");
        upIcon = new ImageIcon(upUrl);
		URL downUrl = PacMan.class.getResource("PacManGifDown.gif");
        downIcon = new ImageIcon(downUrl);
		URL leftUrl = PacMan.class.getResource("PacManGifLeft.gif");
        leftIcon = new ImageIcon(leftUrl);
		URL rightUrl = PacMan.class.getResource("PacManGifRight.gif");
        rightIcon = new ImageIcon(rightUrl);
        PacMan = new JLabel(upIcon);
	}
	public void move() throws InterruptedException {
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
		while (!gameComplete) {
			TimeUnit.MILLISECONDS.sleep(100);
			if (movementType.equals("up")) {
				y -= 10;
				PacMan.setBounds(x, y, 32, 32);
				System.out.println(movementType);
			}
			if (movementType.equals("down")) {
				y += 10;
				PacMan.setBounds(x, y, 32, 32); 
				System.out.println(movementType);
			}
			if (movementType.equals("left")) {
				x -= 10;
				PacMan.setBounds(x, y, 32, 32); 
				System.out.println(movementType);
			}
			if (movementType.equals("right")) {
				x += 10;
				PacMan.setBounds(x, y, 32, 32); 
				System.out.println(movementType);
			}		
		}
		return;
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
}
