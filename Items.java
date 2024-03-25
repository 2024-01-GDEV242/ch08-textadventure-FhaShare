/**
 * Class Items - items in the "World of Zuul" adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text-based adventure game.
 *
 * An "Item" represents an object within the game that players can interact with. 
 * Items are not inherently connected to rooms but can be found within them. 
 * They have attributes such as name, description, weight, and whether they can be eaten, which affects gameplay.
 *
 * @author Fhaungfha Suvannakajorn
 * @version 2024.03.25
 */
public class Items
{
    private String name;
    private String description;
    private double weight;
    private boolean eatable;

    /**
     * Constructor to create a new item with specific attributes, such as name, description, weight, 
     * and eatable status. This allows each item to have unique characteristics and effects in the game.
     * @param name The name of the item.
     * @param description A brief description of the item.
     * @param weight The weight of the item.
     * @param eatable A boolean indicating whether the item is eatable or not.
     */
    public Items(String name, String description, double weight, boolean eatable)
    {
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.eatable = eatable;
    }
    
    /**
     * This method creates an item's information by combining its name, description, 
     * and weight into a single informative sentence for display or interaction within the game.
     * @return String A string containing the item's name, description, and weight.
     */
    public String getItemInfo() 
    {
        return "There is a " + name + ", " + description + " ,weighing approximately " + weight + " lbs.";
    }
    
    /**
     * This method retrieves the name of the item. This getter method is used to access the private field "name" of 
     * an item, facilitating its use in other parts of the program for identification or comparison.
     * @return name The name of the item as a String.
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * This method allows external access to the item's weight for calculations or gameplay conditions, 
     * such as inventory management or determining if a player can pick up the item 
     * based on their carrying capacity.
     * @return The weight of the item as a double.
     */
    public double getWeight()
    {
        return weight;
    }
    
    /**
     * This method checks if the item is edible and returns the value of the 'eatable' attribute. 
     * It determines which items in the game the player can eat.
     * @return true if the item is edible, false otherwise.
     */
    public boolean getEatable()
    {
        return eatable;
    }
}
