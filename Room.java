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
     * @param items
     */
    public void addItems(Items newItem) 
    {
        itemList.add(newItem);
    }
    
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
     * 
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
     * 
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
     * Constructor for objects of class Items
     */
    public String getName()
    {
        return name;
    }
}

