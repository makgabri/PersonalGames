package Maps;

public class Player {
  
  int x, y;
  
  public Player(int one, int two) {
    this.x = one;
    this.y = two;
  }
  
  public void setX(int value) {
    this.x = value;
  }
  
  public int getX() {
    return this.x;
  }
  
  public void setY(int value) {
    this.y = value;
  }
  
  public int getY() {
    return this.y;
  }
  
  public void moveRight() {
    this.x = this.x + 1;
  }
  
  public void moveLeft() {
    this.x = this.x - 1;
  }
  
  public void moveUp() {
    this.y = this.y - 1;
  }
  
  public void moveDown() {
    this.y = this.y + 1;
  }
  
}
