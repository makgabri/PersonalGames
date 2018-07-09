package Maps;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Map {
  
  int length, height, startX, startY;
  String title, row;
  private char[][] map;
  
  // Initialize Map if given map
  public Map(String name, int length, int height) {
    this.title = name;
    this.length = length;
    this.height = height;
    map = new char [length][height];
    for (int y = 0; y != this.height; y++) {
      for (int x = 0; x != this.length; x++) {
        map[x][y] = '#';
      }
    }
  }
  
  // Initialize Map if given no map
  public Map(String file) throws IOException {
    // Setup of reading file
    FileInputStream fStream = new FileInputStream(file);
    BufferedReader br = new BufferedReader(new InputStreamReader(fStream));
    
    // Setup of title, length, width, startX and startY
    this.title = br.readLine();
    String[] temp = br.readLine().split(" ");
    this.length = Integer.parseInt(temp[0]);
    this.height = Integer.parseInt(temp[1]);
    temp = br.readLine().split(" ");
    this.startX = Integer.parseInt(temp[0]);
    this.startY = Integer.parseInt(temp[1]);

    // Setup of map as an array
    map = new char [length][height];
    int counterY = 0;
    while ((row = br.readLine()) != null) {
      for (int counterX = 0; counterX != this.length; counterX++) {
        map[counterX][counterY] = row.charAt(counterX);
      }
      counterY++;
    }
    
    fStream.close();
  }
  
  // Get Map name
  public String getMapName() {
    return this.title;
  }
  
  // Get starting position of X
  public int getStartX() {
    return this.startX;
  }

  // Get starting position of Y
  public int getStartY() {
    return this.startY;
  }
  
  // Get Map Length
  public int getLength() {
    return this.length;
  }
  
  // Get Map Height
  public int getHeight() {
    return this.height;
  }
  
  // Get tile info at coordinates
  public char getTile(int one, int two) {
    return this.map[one][two];
  }
  
  public void setTile(int one, int two, char value) {
    this.map[one][two] = value;
  }
  
  public String toString() {
    String result = "";
    int counterX, counterY;
    for (counterY = 0; counterY != this.height; counterY++) {
      for (counterX = 0; counterX != this.length; counterX++) {
        result += map[counterX][counterY] + " ";
      }
      if (counterY != this.height -1) {
        result += System.lineSeparator();
      }
    }
    return result;
  }
}
