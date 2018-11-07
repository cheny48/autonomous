// Developers:Yujing Chen
// Purpose:Class for moveable items, contains method for bumped checking
// Inputs:None 
// Outputs:None
// Method Contained: constructor, bumped(int, int, Item[][])
public class Moveable extends Item{
    public Moveable(int x, int y, char token, String name) {
        this.x = x;
        this.y = y;
        this.token = token;
        this.name = name;
    }
    
    
// Name: bumped(int, int, Item[][])
// Creator:Yujing Chen
// Purpose: when the moveable item is bumped, check if it can be moved to the aimed position, and change the map
// Parameters:int dx, int dy, item[][] map
// Returns: false if it can't move, true if it can
    public boolean bumped(int dx, int dy, Item[][] map) {
        
        if(!isValid(x+dx, y+dy, map)) { //out of range
            return false;
        }
        
        Item item = map[x+dx][y+dy];//check the item in the aimed position
        
        if(item instanceof Immovable) { //aimed position is occupied by Immovable
            return false;
        }
        
        boolean canMove = true;
        
        if(item instanceof Moveable) { //if is occupied by Moveable, check if the second Moveable item can be moved
            canMove = ((Moveable) item).bumped(dx, dy, map);
        } else if (item instanceof Autonomous) { //if is occupied by Autonomous, check if the Autonomous item can be moved
            canMove = ((Autonomous) item).bumped(dx, dy, map);
        }
        if(canMove) { //if there is no item in the aimed position
            this.x = x+dx;
            this.y = y+dy;
            map[x][y] = this;
            return true;
        }
        return false;
    }
}
