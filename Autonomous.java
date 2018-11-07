// Developers:Yujing Chen
// Purpose:Class for Autonomous items, contain 2 method, one for bumped checking, one for random moving
// Inputs:None 
// Outputs:None
// Method Contained: constructor, bumped(int, int, Item[][]), step(Item[][])
import java.util.Random;

public class Autonomous extends Item{
  
    private static final int[][] dirs = {{-1,0}, {0,1}, {1,0}, {0,-1}};
    
    private Random ran;
    
    public Autonomous(int x, int y, char token, String name, Random ran) {
        this.x = x;
        this.y = y;
        this.token = token;
        this.name = name;
        this.ran = ran;
    }
    
// Name: bumped(int, int, Item[][])
// Creator:Yujing Chen
// Purpose: when the Autonomous item is bumped, check if it can be moved to the aimed position,and change the map
// Parameters:int dx, int dy, item[][] map
// Returns: false if it can't move, true if it can
// Similar to the bumped method in Immovable
    public boolean bumped(int dx, int dy, Item[][] map) {
        if(!isValid(x+dx, y+dy, map)) {
            return false;
        }
        
        Item item = map[x+dx][y+dy];
        if(item instanceof Immovable) {
            return false;
        }
        
        boolean canMove = true;
        if(item instanceof Moveable) {
            canMove = ((Moveable) item).bumped(dx, dy, map);
        } else if (item instanceof Autonomous) {
            canMove = ((Autonomous) item).bumped(dx, dy, map);
        }
        if(canMove) {
            this.x = x+dx;
            this.y = y+dy;
            map[x][y] = this;
            return true;
        }
        return false;
    }
    
// Name: step(Item[][])
// Creator:Yujing Chen
// Purpose: control the Autonomous Random moving,ahd change the map
// Parameters:item[][] map
// Returns: none
    public void step(Item[][] map) {    
      
        int n = ran.nextInt(4); //generate random move
        int dx = dirs[n][0];
        int dy = dirs[n][1];
        
        if(!isValid(x+dx, y+dy, map)) {
            return;
        }
        
        Item item = map[x+dx][y+dy];
        if(item instanceof Immovable) {
            return;
        }
        boolean canMove = true;
        
        if(item instanceof Moveable) {
            canMove = ((Moveable) item).bumped(dx, dy, map);
        } else if (item instanceof Autonomous) {
            canMove = ((Autonomous) item).bumped(dx, dy, map);
        }
        if(canMove) { // if can move is true, erase the current item and put it in the new position
            map[x][y] = null;
            this.x = x+dx;
            this.y = y+dy;
            map[x][y] = this;
        }
    }
}
