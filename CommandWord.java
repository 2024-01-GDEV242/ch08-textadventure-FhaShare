/**
 * Representations for all the valid command words for the game
 * along with a string in a particular language.
 * 
 * @author Fhaungfha Suvannakajorn
 * @version 2024.03.25
 */
public enum CommandWord
{
    // A value for each command word along with its
    // corresponding user interface string.
    GO("go"), BACK("back"), QUIT("quit"), HELP("help"), UNKNOWN("?"), LOOK("look"), EAT("eat"), PICKUP("pick-up"), DROP("drop"), BACKPACK("backpack"), STATUS("status");
    
    // The command string.
    private String commandString;
    
    /**
     * Initialise with the corresponding command string.
     * @param commandString The command string.
     */
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }
    
    /**
     * @return The command word as a string.
     */
    public String toString()
    {
        return commandString;
    }
}
