// File Name:World.java
// Developers:Yujing Chen
// Purpose:generate a world, display it, update the changes in the world. ask the user if to start the simulator again 
// after 100 steps. This Class generally dispaly what happens in the world with A,M and I. Main method is also contained here
// Inputs:None 
// Outputs:None
// Method Contained: buildWorld(int ROWS,int COLS),display(),step(),updateWorld(),add(Item item, int x, int y),hasNext(),main()
// 2018.4.17; Main class for this simulator

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;


public class World extends JFrame {
  
    private int ROWS;
    private int COLS;
    
    private Item[][] map;
    private JButton[][] buttons;
    
// constructor for the World
    public World(int ROWS, int COLS) {
        map = new Item[ROWS][COLS];
        buttons = new JButton[ROWS][COLS];
        this.ROWS = ROWS;
        this.COLS = COLS;
    }
    
// Name: World buildWorld(int ROWS,int COLS)
// Creator:Yujing Chen
// Purpose: build a world with 5 Immovable, 3 Moveable, and 2 Autonomous
// Parameters:int ROWS,int COLS
// Returns: the world we built
    private static World buildWorld(int ROWS,int COLS) {
        World world = new World(ROWS,COLS); 
      
        Random ran = new Random();
        int count = 0;
        while(count < 5) { //generate 5 Immovable Items
            int x = ran.nextInt(ROWS);
            int y = ran.nextInt(COLS);
            Immovable item = new Immovable(x, y, 'I', "immovable");
            if(world.add(item, x, y)) {
                count++;
            }
        }
        
        count = 0;
        while(count < 3) { //generate 3 movable items
            int x = ran.nextInt(ROWS);
            int y = ran.nextInt(COLS);
            Moveable item = new Moveable(x, y, 'M', "movable");
            if(world.add(item, x, y)) {
                count++;
            }
        }
        
        count = 0;
        while(count < 2) { // generate 2 Autonomous
            int x = ran.nextInt(ROWS);
            int y = ran.nextInt(COLS);
            Autonomous item = new Autonomous(x, y, 'A', "autonomous", ran);
            if(world.add(item, x, y)) {
                count++;
            }
        }        
        return world;
    }
    
// Name: display()
// Creator:Yujing Chen
// Purpose: display the world with swing
// Parameters:none
// Returns:none
    public void display() {
        
        this.setVisible(true);
        
        JPanel panel = new JPanel();//create the GridLayout JPanel with ROWSxCOLS buttons
        panel.setLayout(new GridLayout(ROWS, COLS));
        panel.setBackground(Color.white);
        
        for (int x = 0; x < ROWS; x++) {
            for (int y = 0; y < COLS; y++) {
                String token = "";
                if(map[x][y] != null) {
                    token = "" + map[x][y].getToken();
                }
                buttons[x][y] = new JButton(token);
                buttons[x][y].setName(x + "_" + y);
                buttons[x][y].setBackground(Color.white);
                panel.add(buttons[x][y]);
            }
        }
        
        this.setLayout(new BorderLayout());
        this.add(panel);
        this.setSize(800, 800);
        
    }
    
// Name: step()
// Creator:Yujing Chen
// Purpose: change the state of the world by call the step method of Autonomous items
// Parameters:none
// Returns:none
    public void step() {
        List<Autonomous> list = new ArrayList<Autonomous>();
        for (int x = 0; x < ROWS; x++) {
            for (int y = 0; y < COLS; y++) {
                if(map[x][y] instanceof Autonomous) {
                    list.add((Autonomous)map[x][y]);
                }
            }
        }
        for(Autonomous auto : list) {
            auto.step(map);
        }
    }
    
// Name: updateWorld()
// Creator:Yujing Chen
// Purpose: update the buttons and repaint the panel
// Parameters:none
// Returns:none
    private void updateWorld() {
        for (int x = 0; x < ROWS; x++) {
            for (int y = 0; y < COLS; y++) {
                if(map[x][y] != null) {
                    buttons[x][y].setText(map[x][y].getToken() + "");
                } else {
                    buttons[x][y].setText("");
                }
            }
        }
        this.repaint();
    }
    
// Name: add(Item item, int x, int y)
// Creator:Yujing Chen
// Purpose: create an item at position (x,y), given false if the add fails.
// Parameters:Item item, int x, int y
// Returns:true if add pass, false if add fail
    public boolean add(Item item, int x, int y) {
        if(x < 0 || y < 0 || x >= map.length || y >= map[0].length) {
            return false;
        }
        
        if(map[x][y] != null) {
            return false;
        }
        
        map[x][y] = item;
        return true;
    }
    
// Name: hasNext()
// Creator:Yujing Chen
// Purpose: check if the user want the simulator continue to run, given out a JOptionPane
// Parameters:none
// Returns:true if they want simulator continue, false if they don't    
    public boolean hasNext() {
        int choosed = JOptionPane.showConfirmDialog(null, "Would you like to run the simulation again ?", "Try again?",JOptionPane.YES_NO_OPTION);
        if(choosed == 0) {
            return true;
        } else {
            return false;  
        }      
    }
    

    
// Name: main()
// Creator:Yujing Chen
// Purpose: main method for the whole program
// Parameters:none
// Returns:none  
    public static void main(String[] args) {
        boolean next = true;
        World world = buildWorld(8,8);
        world.display();
        while(next) {
            for(int i=0; i<100; i++) { //100 steps
                try {
                    Thread.sleep(300); //sleep for a while so we can see the changes
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                world.step();
                world.updateWorld();
            }
            next = world.hasNext();
        }
        System.exit(0);
    }
}
