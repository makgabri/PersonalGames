package executables;

import Maps.Map;
import Maps.Player;

public class Movement {
  
  public static int movement(Map map, Map playermap, Player player,
      String action) {
    char tile;
    int x, y;
    
    if (action.equals("left")) {
      x = player.getX()-1;
      y = player.getY();
      tile = map.getTile(x, y);
      playermap.setTile(x, y, tile);
    } else if (action.equals("right")) {
      x = player.getX()+1;
      y = player.getY();
      tile = map.getTile(x, y);
      playermap.setTile(x, y, tile);
    } else if (action.equals("up")) {
      x = player.getX();
      y = player.getY()-1;
      tile = map.getTile(x, y);
      playermap.setTile(x, y, tile);
    } else {
      x = player.getX();
      y = player.getY()+1;
      tile = map.getTile(x, y);
      playermap.setTile(x, y, tile);
    }
    
    // Compare and take the correct action
    if (tile == 'X') {
      System.out.println(playermap.toString());
      System.out.println("That is a wall and your player cannot move in ");
      System.out.println("that direction. Please try another direction.");
    } else if (tile == 'Y') {
      System.out.println("User has moved " + action + " one step.");
      playermap.setTile(player.getX(), player.getY(), 'Y');
      if (action.equals("right")) {
        player.moveRight();
      } else if (action.equals("left")) {
        player.moveLeft();
      } else if (action.equals("up")) {
        player.moveUp();
      } else {
        player.moveDown();
      }
      playermap.setTile(player.getX(), player.getY(), 'P');
      System.out.println(playermap.toString());
    } else {
      System.out.println("You have reached the destination. Congratulations,");
      System.out.println("you have won the game.");
      return -1;
    }
    return 0;
  }

}
