import java.util.ArrayList;
import java.util.Scanner;
/**
 * Class Player - the player in the "World of Zuul" adventure game.
 * 
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text-based adventure game.
 * 
 * The "Player" represents the game's protagonist and is the main interface for interacting with the game world. 
 * The player character can navigate rooms, carry items in a backpack, 
 * and manage health and hunger levels to survive. The class allows manipulation of these attributes, 
 * offering methods to move between rooms, handle items, and monitor the player's state. 
 *
 * @author Fhaungfha Suvannakajorn
 * @version 2024.03.25
 */
public class Player
{
    private Room currentRoom;
    private ArrayList<Items> backpack;
    private int hp;
    private int hungerLevel;
    
    /**
     * The constructor creates a new Player object with default health and hunger levels, 
     * and initializes the player's backpack as an empty ArrayList for item storage during the game.
     */
    public Player()
    {
        // Player Status
        hp = 100;
        hungerLevel = 100;
        
        // The player's backpack is initialized as an empty ArrayList to store acquired Item objects.
        backpack = new ArrayList();
    }

    /**
     * This method updates the player's location to a new room, facilitating navigation and interaction within 
     * the game environment.
     * @param currentRoom The new room to set as the player's current location.
     */
    public void setCurrentRoom(Room currentRoom) 
    {
        this.currentRoom = currentRoom;
    }

    /**
     * This method retrieves the player's current room. It returns the room where the player is currently located, 
     * allowing access to room-specific information or actions within the game. 
     * @return currentRoom The player's current room.
     */
    public Room getCurrentRoom() 
    {
        return currentRoom;
    }
    
    /**
     * This method manages the player's hunger level and simulates the effects of starvation on health.
     * @param level The amount by which to decrease the hunger level.
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
     * This method retrieves the player's current hunger level, which is crucial for survival decisions 
     * and actions, such as eating to restore it.
     * @return hungerLevel The current hunger level of the player.
     */
    public int getHungerLevel()
    {
        return hungerLevel;
    }
    
    /**
     * This method retrieves the player's current health points (HP) to check their status and determine 
     * if healing is needed.
     * @return hp The current HP level of the player.
     */
    public int getHpLevel()
    {
        return hp;
    }
    
    /**
     * This method displays the player's current health points (HP) and hunger level, 
     * providing essential feedback for informed decision-making in the game.
     */
    public void getStatus()
    {
        System.out.println("HP: " + getHpLevel());
        System.out.println("HungerLevel: " + getHungerLevel());
    }
    
    /**
     * This method increases the player's hunger level and health points.
     * @param level The amount by which to increase the hunger level.
     */
    public void raiseHuger(int level)
    {
        hungerLevel += level;
        hp += 10;
    }
    
    /**
     * Exercise 8.28-8.33 - Add a Player that can carry one or more objects according to the options presented
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
     * The method adds new items to the player's backpack, allowing them to carry objects encountered in the game. 
     * It extends its functionality to include inventory management.
     * @param itemName The item to be added to the player's backpack.
     * @return newItem The item that was removed, allowing further action to be taken with it.
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
     * The method checks if the player's backpack contains an item with the specified name. 
     * If a matching item is found, it returns true; otherwise, it returns false.
     * @param item The name of the item to check for in the backpack.
     * @return true if the item is found in the backpack, false otherwise.
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
    
    /**
     * The method presents the player's backpack inventory.
     */
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
    
    /**
     * The method checks for edible items in the player's backpack, 
     * but it does not currently use the itemName parameter, indicating a possible oversight.
     * @param itemName The name of the item to check for edibility. 
     * @return true if there is at least one edible item in the backpack, false otherwise.
     */
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
