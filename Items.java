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
    // instance variables - replace the example below with your own
    private String name;
    private String description;
    private double weight;
    private boolean eatable;

    /**
     * Constructor for objects of class Items
     */
    public Items(String name, String description, double weight, boolean eatable)
    {
       this.name = name;
       this.description = description;
       this.weight = weight;
       this.eatable = eatable;
    }
    
    public String getItemInfo() 
    {
        return "There is a " + name + ", " + description + " ,weighing approximately " + weight + " lbs.";
    }
    
    /**
     * Constructor for objects of class Items
     */
    public String getName()
    {
        return name;
    }
    
    public double getWeight()
    {
        return weight;
    }
    
    public boolean getEatable()
    {
        return eatable;
    }
}
