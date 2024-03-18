import java.util.ArrayList;
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

    /**
     *
     */
    public Player(String name, Room startingRoom){
        this.name = name;
        this.currentRoom = startingRoom;
    }

    /**
     *
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    /**
     *
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     *
     */
    public String getName() {
        return name;
    }
}
