package driver;

import java.io.IOException;
import java.util.ArrayList;
import Maps.Map;
import Maps.Player;
import executables.Exit;
import executables.Help;
import executables.Movement;

public class Enviroment {

  // Initialize Enviroment's map, playermap and player
  Map map;
  Map playermap;
  Player player;
  ArrayList<String> movement = new ArrayList<String>();
  
  // Enviroment setup
  public Enviroment(String map) throws IOException {
    this.map = new Map(map);
    this.player = new Player(this.map.getStartX(), this.map.getStartY());
    this.playermap = new Map("Player", this.map.getLength(),
        this.map.getHeight());
    this.playermap.setTile(player.getX(), player.getY(), 'P');
    this.movement.add("left"); this.movement.add("right");
    this.movement.add("up"); this.movement.add("down");
  }
  
  // Get map name
  public String getMapName() {
    return this.map.getMapName();
  }
  
  // execute action
  public int execute(String action) {
    if (action.equals("exit")) {
      Exit.exit();
      return -1;
    } else if (action.equals("help")) {
      System.out.println(Help.general());
    } else if (this.movement.contains(action)) {
      return Movement.movement(map, playermap, player, action);
    } else if (action.equals("map")){
      System.out.println(playermap.toString());
    } else {
      System.out.println("That is an invalid action, refer to help for actions");
    }
    return 0;
  }
}
