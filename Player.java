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
    private ArrayList<Items> backpack;
    //private ArrayList<Room> visitRooms;
    private int hp;
    private int hungerLevel;
    
    /**
     * 
     */
    public Player()
    {
        // Player Status
        hp = 100;
        hungerLevel = 100;
        
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
        hungerLevel -= level;
        
        if (hungerLevel <= 50)
        {
            System.out.println("You are hungry. Find something to eat!");
        }
        
        if (hungerLevel <= 0)
        {
            hp -= 5;
            System.out.println("You are starving. You lost 1 hp! Find something to eat!");
        }
    } 
    
    /**
     * 
     */
    public int getHungerLevel()
    {
        return hungerLevel;
    }
    
    /**
     * 
     */
    public int getHpLevel()
    {
        return hp;
    }
    
    /**
     * 
     */
    public void getStatus()
    {
        System.out.println("HP: " + getHpLevel());
        System.out.println("HungerLevel: " + getHungerLevel());
    }
    
    /**
     * 
     */
    public void raiseHuger(int level)
    {
        hungerLevel += level;
        hp += 10;
    }
    
    /**
     *
     */
    public void addItems(Items newItem) 
    {
        backpack.add(newItem);
    }
    
    public void eatItems(String itemName)
    {
        int itemNumber = 0;
        
        for (int index = 0; index < backpack.size(); index++) 
        {
            if (backpack.get(index).getName() == itemName)
            {
                index = itemNumber;
            }
        }
        
        backpack.remove(itemNumber);
        
    }
    
    /**
     * 
     */
    public Items removeItems(String itemName) 
    {
        int itemNumber = 0;
        
        for (int index = 0; index < backpack.size(); index++) 
        {
            if (backpack.get(index).getName() == itemName)
            {
                index = itemNumber;
            }
        }
        
        Items newItem = backpack.get(itemNumber);
        
        backpack.remove(itemNumber);
        
        System.out.println("You took " + itemName + " and put it into the room.");
        
        return newItem;
    }
    
    /**
     * 
     */
    public boolean hasItems(String item)
    {
        boolean found = false;
        
        for (int index = 0; index < backpack.size(); index++){
            if (backpack.get(index).getName().equals(item)){
                found = true;
            }
        }
        
        return found;
    }
    
    public void getInventory()
    {
        if (backpack.size() < 1)
        {
            System.out.println("You don't have any item in your backpack.");
        } else {
            System.out.println("Backpack inventory:");
            for(Items items : backpack) 
            {
                System.out.println(items.getItemInfo());
            }
        }
    }
    
    public boolean eatableCheck(String itemName)
    {
        boolean found = false;
        
        for (int index = 0; index < backpack.size(); index++){
            if (backpack.get(index).getEatable()){
                found = true;
            }
        }
        
        return found;
    }
}
