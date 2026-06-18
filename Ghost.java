import java.net.URL;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Ghost {
   private JLabel ghost;
   private int x;
   private int y;
   private String movementType = "right";
   private String[][] map;
   private boolean lockedOn = false;
   private int movesLockedOn = 0;
   private int cooldownTimer = 0;
   private String tileGhostOn = " ";
   private int spawnX;
   private int spawnY;
   private int prevX;
   private int prevY;
   
   public Ghost(String color, String[][] map, int spawnX, int spawnY) {
       URL url = PacMan.class.getResource(color + "Ghost.gif");
       ImageIcon ghostIcon = new ImageIcon(url);
       ghost = new JLabel(ghostIcon);
       this.map = map;
       this.spawnX = spawnX;
       this.spawnY = spawnY;
       x = spawnX;
       y = spawnY;
       ghost.setBounds(x * 32, y * 32, 32, 32);
   }
   public void move() throws InterruptedException {
	   prevX = x;
	   prevY = y;
       if (movementType.equals("up") && canMove("up")) {
           for (int i = 1; i <= 4; i++) {
               ghost.setBounds(x * 32, y * 32 - i * 8, 32, 32);
               TimeUnit.MILLISECONDS.sleep(5);
               ghost.paintImmediately(0, 0, 32, 32);
           }
           map[y][x] = tileGhostOn;
           y -= 1;
           tileGhostOn = map[y][x];
           map[y][x] = "G";
       }
       else if (movementType.equals("down") && canMove("down")) {
           for (int i = 1; i <= 4; i++) {
               ghost.setBounds(x * 32, y * 32 + i * 8, 32, 32);
               TimeUnit.MILLISECONDS.sleep(5);
               ghost.paintImmediately(0, 0, 32, 32);            
           }
           map[y][x] = tileGhostOn;
           y += 1;
           tileGhostOn = map[y][x];
           map[y][x] = "G";
       }
       else if (movementType.equals("left") && canMove("left")) {
           for (int i = 1; i <= 4; i++) {
               ghost.setBounds(x * 32 - i * 8, y * 32, 32, 32);
               TimeUnit.MILLISECONDS.sleep(5);
               ghost.paintImmediately(0, 0, 32, 32);
           }
           map[y][x] = tileGhostOn;
           x -= 1;
           tileGhostOn = map[y][x];
           map[y][x] = "G";
       }
       else if (movementType.equals("right") && canMove("right")) {
           for (int i = 1; i <= 4; i++) {
               ghost.setBounds(x * 32 + i * 8, y * 32, 32, 32);
               TimeUnit.MILLISECONDS.sleep(5);
               ghost.paintImmediately(0, 0, 32, 32);
           }
           map[y][x] = tileGhostOn;
           x += 1;
           tileGhostOn = map[y][x];
           map[y][x] = "G";
       }
       if (lockedOn) {
           movesLockedOn++;
       } else {
           if (cooldownTimer > 0) {
               cooldownTimer--;
           }
       }
   }
   public void whereToMove(PacMan pacMan) throws InterruptedException {
       int pacX = pacMan.getX();
       int pacY = pacMan.getY();
       if (movesLockedOn > 15) {
           cooldownTimer = 15;
           movesLockedOn = 0;
           lockedOn = false;
       }
       else if (Math.hypot(Math.abs(pacX - x), Math.abs(pacY - y)) <= 4 && cooldownTimer == 0) {
           lockedOn = true;
           String preferredX = (pacX > x) ? "right" : "left";
           String preferredY = (pacY > y) ? "down" : "up";
           if (Math.abs(pacX - x) > Math.abs(pacY - y)) {
               if (canMove(preferredX)) {
                   movementType = preferredX;
               } else if (canMove(preferredY)) {
                   movementType = preferredY;
               }
               else {
                   lockedOn = false;
               }
           } else {
               if (canMove(preferredY)) {
                   movementType = preferredY;
               } else if (canMove(preferredX)) {
                   movementType = preferredX;
               }
               else {
                   lockedOn = false;
               }
           }
       }
       else {
           lockedOn = false;
           movesLockedOn = 0;
           boolean foundPath = false;
           if (!canMove(movementType) || movementType.equals("")) {
        	       if (!canMove("up") && !canMove("down") && !canMove("left") && !canMove("right")) {
                   movementType = ""; 
                   return; 
               }
               while (!foundPath) {
                   int random = (int) (Math.random() * 4 + 1);
                   if (random == 1 && canMove("up")) {
                       movementType = "up";
                       foundPath = true;
                   } else if (random == 2 && canMove("down")) {
                       movementType = "down";
                       foundPath = true;
                   } else if (random == 3 && canMove("left")) {
                       movementType = "left";
                       foundPath = true;
                   } else if (random == 4 && canMove("right")) {
                       movementType = "right";
                       foundPath = true;
                   }
               }
           }
       }
   }
   public boolean canMove(String movementType) {
       if (movementType.equals("up")) {
           if (map[y-1][x].equals("X") || map[y-1][x].equals("G")) {
               return false;
           }
           return true;
       }
       if (movementType.equals("down")) {
           if (map[y+1][x].equals("X") || map[y+1][x].equals("G")) {
               return false;
           }
           return true;
       }
       if (movementType.equals("left")) {
           if (map[y][x-1].equals("X") || map[y][x-1].equals("G")) {
               return false;
           }
           return true;
       }
       else {
           if (map[y][x+1].equals("X") || map[y][x+1].equals("G")) {
               return false;
           }
           return true;
       }
   }
   public void reset() {
	   map[y][x] = tileGhostOn;
	   x = spawnX;
	   y = spawnY;
	   tileGhostOn = map[y][x];
	   map[y][x] = "G";
	   ghost.setBounds(x * 32, y * 32, 32, 32);
	   lockedOn = false;
	   movesLockedOn = 0;
	   cooldownTimer = 0;
	   movementType = "right";
   }
   public JLabel getLabel() {
       return ghost;
   }
   public int getX() {
	   return x;
   }
   public int getY() {
	   return y;
   }
   public int getPrevX() { 
		return prevX; 
   }
   public int getPrevY() { 
	   return prevY; 
   }
}