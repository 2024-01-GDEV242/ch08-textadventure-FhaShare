import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Fhaungfha Suvannakajorn
 * @version 2024.03.25
 */

public class Room 
{
    private String description;
    private String name;
    private HashMap<String, Room> exits;        // stores exits of this room.
    private ArrayList<Items> itemList;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String name, String description) 
    {
        this.description = description;
        this.name = name;
        exits = new HashMap<>();
        itemList = new ArrayList<>();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     * You are in the kitchen.
     * Exits: north west
     * @return A long description of this room
     */
    public void getLongDescription()
    {
        if (itemList.size() < 1)
        {
            System.out.println("You are " + description + ".\n" + getExitString());
        } else {
            System.out.println("You are " + description); 
           for(Items items : itemList) 
           {
               System.out.println(items.getItemInfo());
           }
           System.out.println(getExitString());
        }
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    /**
     * Exercis 8.22 - extension to items - make it so rooms can hold multiple items
     * Add a new item to the room.
     * @param newItem The item to be added to the room.
     */
    public void addItems(Items newItem) 
    {
        itemList.add(newItem);
    }
    
    /**
     * This method checks if an item in the room's inventory weighs less than 10 units and returns true 
     * if it finds at least one matching item; otherwise, it returns false.
     * @param itemName The name of the item to check for weight.
     * @return true if an item weighing less than 10 units is found; otherwise, false.
     */
    public boolean weightCheck(String itemName)
    {
        boolean found = false;
        
        for (int index = 0; index < itemList.size(); index++){
            if (itemList.get(index).getWeight() < 10){
                found = true;
            }
        }
        
        return found;
    }
    
    /**
     * This method removes an item by name from the itemList and returns it. 
     * If multiple items have the same name, it removes the one with the same name from the list.
     * @param itemName The name of the item to be removed.
     * @return The removed item.
     */
    public Items removeItems(String itemName) 
    {
        int itemNumber = 0;
        
        for (int index = 0; index < itemList.size(); index++) 
        {
            if (itemList.get(index).getName().equals(itemName))
            {
                itemNumber = index;
            }
        }
        
        Items newItem = itemList.get(itemNumber);
        
        itemList.remove(itemNumber);
        
        System.out.println("You took " + itemName + " and put it into your backpack.");
        
        return newItem;
    }
    
    /**
     * This method Checks if the itemList contains an item with the specified name. 
     * It then iterates through the itemList, comparing each item's name with the given name. 
     * @param item The name of the item to search for in the itemList.
     * @return true if the item is found, false otherwise.
     */
    public boolean hasItems(String item)
    {
        boolean found = false;
        
        for (int index = 0; index < itemList.size(); index++){
            if (itemList.get(index).getName().equals(item)){
                found = true;
            }
        }
        
        return found;
    }
    
    /**
     * This method retrieves the name of the item, allowing for identification or display purposes within the game.
     * @return The name of the item as a String.
     */
    public String getName()
    {
        return name;
    }
}

