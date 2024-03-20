import java.util.ArrayList;
import java.util.Scanner;
/**
 * Write a description of class Player here.
 *
 * @author Fhaungfha Suvannakajorn
 * @version 2024.03.25
 */
public class Player
{
    private Room currentRoom;
    private String name;
    private ArrayList<String> backpack;
    //private ArrayList<Room> visitRooms;
    private int hp;
    private int hunger_level;
    
    /**
     * 
     */
    public Player()
    {
        // Player Status
        hp = 100;
        hunger_level = 100;
        
        //
        backpack = new ArrayList();
    }

    /**
     *
     */
    public void setCurrentRoom(Room currentRoom) 
    {
        this.currentRoom = currentRoom;
    }

    /**
     *
     */
    public Room getCurrentRoom() 
    {
        return currentRoom;
    }

    /**
     *
     */
    public String getName() 
    {
        return name;
    }
    
    /**
     *
     */
    public void lowerHuger(int level)
    {
        hunger_level -= level;
        
        if (hunger_level <= 50)
        {
            System.out.println("You are hungry. Find something to eat!");
        }
        
        if (hunger_level <= 0)
        {
            hp -= 1;
            System.out.println("You are starving. You lost 1 hp! Find something to eat!");
        }
    } 
    
    /**
     *
     */
    public void addItems(String newItem) 
    {
        backpack.add(newItem);
    }
}
