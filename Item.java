// File Name:Item.java
// Developers:Yujing Chen
// Purpose:this is an abstract class for Autonomous, Immovable, and Moveable.It contains String Name, char token,
// and x,y to represent items position in map. Also it has a method isValid to check the validation of x,y
// Inputs:None 
// Outputs:None
// Method Contained: getToken(), isVaild(int, int, item[][])
// 2018.4.16; super class for A I M
public abstract class Item {
    protected String name; //protected to be reached from subclass
    
    protected char token;
    
    protected int x;
    
    protected int y;
    
    public char getToken() {
        return token;
    }
    
// Name: isVaild(int, int, item[][])
// Creator:Yujing Chen
// Purpose: To check if the item's position is valid
// Parameters:int x, int y, item[][] map
// Returns: true if is valid, false if not 
    public boolean isValid(int x, int y, Item[][] map) {
        if(x>=map.length || y>=map.length || x<0 || y<0) {
            return false;
        }
        return true;
    }
}
