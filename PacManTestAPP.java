import javax.swing.*;
import java.awt.Color;
public class PacManTestAPP {

	public static void main(String[] args) throws InterruptedException {
		PacMan pacMan = new PacMan(20);
		JFrame frame = new JFrame("GIF Example");
		frame.getContentPane().setBackground(Color.BLACK);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(pacMan.getLabel());
        frame.setVisible(true);
        while (1 == 1) {
        		pacMan.move();
        		frame.add(pacMan.getLabel());
        }

	}

}
