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
   public Ghost(String color, String[][] map, int spawnX, int spawnY) {
       URL url = PacMan.class.getResource(color + "Ghost.gif");
       ImageIcon ghostIcon = new ImageIcon(url);
       ghost = new JLabel(ghostIcon);
       this.map = map;
       x = spawnX;
       y = spawnY;
       ghost.setBounds(x * 32, y * 32, 32, 32);
   }
   public void move() throws InterruptedException {
       TimeUnit.MILLISECONDS.sleep(25);
       if (movementType.equals("up") && canMove("up")) {
           for (int i = 1; i <= 4; i++) {
               ghost.setBounds(x * 32, y * 32 - i * 8, 32, 32);
               ghost.paintImmediately(0, 0, 32, 32); // Forces smooth animation
               TimeUnit.MILLISECONDS.sleep(5);
           }
           map[y][x] = " ";
           y -= 1;
           map[y][x] = "G";
       }
       else if (movementType.equals("down") && canMove("down")) {
           for (int i = 1; i <= 4; i++) {
               ghost.setBounds(x * 32, y * 32 + i * 8, 32, 32);
               ghost.paintImmediately(0, 0, 32, 32);
               TimeUnit.MILLISECONDS.sleep(5);
           }
           map[y][x] = " ";
           y += 1;
           map[y][x] = "G";
       }
       else if (movementType.equals("left") && canMove("left")) {
           for (int i = 1; i <= 4; i++) {
               ghost.setBounds(x * 32 - i * 8, y * 32, 32, 32);
               ghost.paintImmediately(0, 0, 32, 32);
               TimeUnit.MILLISECONDS.sleep(5);
           }
           map[y][x] = " ";
           x -= 1;
           map[y][x] = "G";
       }
       else if (movementType.equals("right") && canMove("right")) {
           for (int i = 1; i <= 4; i++) {
               ghost.setBounds(x * 32 + i * 8, y * 32, 32, 32);
               ghost.paintImmediately(0, 0, 32, 32);
               TimeUnit.MILLISECONDS.sleep(5);
           }
           map[y][x] = " ";
           x += 1;
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
           cooldownTimer = 10;
           movesLockedOn = 0;
           lockedOn = false;
       }
       else if (Math.hypot(Math.abs(pacX - x), Math.abs(pacY - y)) <= 5 && cooldownTimer == 0) {
           lockedOn = true;
           String preferredX = (pacX > x) ? "right" : "left";
           String preferredY = (pacY > y) ? "down" : "up";
           if (Math.abs(pacX - x) > Math.abs(pacY - y)) {
               if (canMove(preferredX)) {
                   movementType = preferredX;
               } else if (canMove(preferredY)) {
                   movementType = preferredY;
               }
           } else {
               if (canMove(preferredY)) {
                   movementType = preferredY;
               } else if (canMove(preferredX)) {
                   movementType = preferredX;
               }
           }
       }
       else {
           lockedOn = false;
           movesLockedOn = 0;
           boolean foundPath = false;
           if (!canMove(movementType) || movementType.equals("")) {
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
   public JLabel getLabel() {
       return ghost;
   }
}