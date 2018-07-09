package driver;

import java.io.IOException;
import java.util.Scanner;

public class Console {

  public static void main(String[] args) throws IOException {
    // Starts the game up
    System.out.println("= = = = = = = = = = = = = = = = = = = =");
    System.out.println("          Welcome to my first          ");
    System.out.println("                  Game                 ");
    System.out.println("= = = = = = = = = = = = = = = = = = = =");
    
    // Ask user for maze map
    Scanner scanner = new Scanner(System.in);
    System.out.print("Please choose the map file: ");
    
    // loads up map
    Enviroment env = new Enviroment(scanner.next());
    System.out.println("You are going to play the map: " + env.getMapName());
    int exitStatus = 0;
    while (exitStatus != -1) {
      System.out.print(">>> ");
      String action = scanner.next();
      exitStatus = env.execute(action);
    }
  }

}
