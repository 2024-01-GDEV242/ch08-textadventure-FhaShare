import java.util.Stack;
import java.util.Scanner;
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author Fhaungfha Suvannakajorn
 * @version 2024.03.25
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Room previousRoom;
    private Stack<Room> roomPath;
    private Player player;
    
    /**
     * 
     */
    public static void main(String[] args){
        Game start = new Game();
        start.play();
    }
    
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        player = new Player();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, theater, pub, lab, office, admission, acts, bookstore, library, 
        bateman, studentLife, studentLounge, tutor, planetarium, cafeteria, 
        physicalEducation, pool, fitness, westBuilding, gamingLab, aiClub;
        
        Items book, paper, jacket, computer, vr, aiProject, coke, 
        dumbbell, salad, frenchFries, pingPong, chocolate, sportsDrink, telescope, key;    
      
        // create the rooms
        outside = new Room("outside", "outside the main entrance of Raritan Valley Community College");
        theater = new Room("theater", "in a theater");
        pub = new Room("pub", "in the campus pub");
        lab = new Room("lab", "in a computing lab");
        office = new Room("office", "in the admin office");
        admission = new Room("admission", "in the admission office");
        acts = new Room("acts", "in the Advising, Counseling and Transfer Services");
        bookstore = new Room("bookstore", "in the RVCC bookstore");
        library = new Room("library", "in the Library");
        bateman = new Room("bateman", "in the Bateman Student Center");
        studentLife = new Room("studentLife", "in the RVCC Student Life office");
        studentLounge = new Room("studentLounge", "in the Student Lounge");
        tutor = new Room("tutor", "in the Tutoring Center");
        planetarium = new Room("planetarium", "in the Planetarium");
        cafeteria = new Room("cafeteria", "in the Cafeteria");
        physicalEducation = new Room("physicalEducation", "in the Physical Education");
        pool = new Room("pool", "in the pool");
        fitness = new Room("fitness", "in the Fitness Center");
        westBuilding = new Room("westBuilding", "in the West Building");
        gamingLab = new Room("gamingLab", "in the Gaming Lab");
        aiClub = new Room("aiClub", "in the AI club");
        
        // initialise room exits
        outside.setExit("east", theater);
        outside.setExit("south", lab);
        outside.setExit("west", pub);
        outside.setExit("north", westBuilding);

        theater.setExit("west", outside);
        theater.setExit("east", library);

        pub.setExit("east", outside);
        pub.setExit("west", cafeteria);
        pub.setExit("south", planetarium);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);
        office.setExit("east", admission);
        office.setExit("south", bateman);
        
        admission.setExit("north", library);
        admission.setExit("east", acts);
        
        acts.setExit("north", bookstore);
        
        bookstore.setExit("west", library);
        
        library.setExit("south", admission);
        library.setExit("west", theater);
        
        bateman.setExit("north", office);
        bateman.setExit("east", studentLounge);
        bateman.setExit("south", studentLife);
        bateman.setExit("west", tutor);
        
        studentLounge.setExit("west", bateman);
        studentLife.setExit("north", bateman);
        tutor.setExit("east", bateman);
        
        planetarium.setExit("north", pub);
        
        cafeteria.setExit("east", pub);
        cafeteria.setExit("west", physicalEducation);
        
        physicalEducation.setExit("east", cafeteria);
        physicalEducation.setExit("north", pool);
        physicalEducation.setExit("south", fitness);
        
        pool.setExit("south", physicalEducation);
        fitness.setExit("north", physicalEducation);
        
        westBuilding.setExit("south", outside);
        westBuilding.setExit("east", gamingLab);
        westBuilding.setExit("west", aiClub);
        
        gamingLab.setExit("west", westBuilding);
        
        aiClub.setExit("east", westBuilding);
        
        currentRoom = outside;  // start game outside
        
        // Exercise 8.20 - Add items to your game. Items have description and weight.
        // create the items
        book = new Items("book", "an Objects First With Java A Practical Introduction Using Bluej 6Th Edition", 1.85, false);
        paper = new Items("paper", "a letter of paper is 8–1/2 x 11 inches in size", 0.002, false);
        jacket = new Items("jacket", "a gray jacket with the RVCC logo is present", 0.8, false);
        computer = new Items("computer", "a computer desktop", 20.50, false);
        vr = new Items("vr", "a Virtual Reality Headset - Quest 2 Advanced All-In-One", 1.83, false);
        aiProject = new Items("aiProject", "a folder for the AI Club's AI project", 0.45, false);
        coke = new Items("coke", "a can of coke", 0.5, true);
        dumbbell = new Items("dumbbell", "a workout dumbbell - 2 lbs", 2, false);
        salad = new Items("salad", "a bowl of Green Salad", 0.35, true);
        frenchFries = new Items("frenchFries", "a box of french-fried", 0.25, true);
        pingPong = new Items("pingPong", "a Ping Pong ball", 0.001, false);
        chocolate = new Items("chocolate", " a Feastables Chocolate Mr Beast Bar", 0.1, true);
        sportsDrink = new Items("sportsDrink", "a bottle of Gatorade G2 Grape", 0.35, true);
        telescope = new Items("telescope", "a Professional Astronomy Refractor Telescope", 50, false);
        key = new Items("key", "a Game Lab key", 0.25, false);
        
        // Exercise 8.20 - put the items in the rooms
        library.addItems(book);
        admission.addItems(paper);
        bookstore.addItems(jacket);
        lab.addItems(computer);
        gamingLab.addItems(vr);
        aiClub.addItems(aiProject);
        aiClub.addItems(key);
        cafeteria.addItems(coke);
        physicalEducation.addItems(coke);
        fitness.addItems(dumbbell);
        cafeteria.addItems(salad);
        cafeteria.addItems(frenchFries);
        studentLife.addItems(pingPong);
        physicalEducation.addItems(chocolate);
        physicalEducation.addItems(sportsDrink);
        planetarium.addItems(telescope);
        
        // Exercise 8.26 - implement the go back many rooms version of the command command (back repeatedly retraces your steps)
        // initialize the room path stack
        roomPath = new Stack<>();
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        currentRoom.getLongDescription();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;
            
            case BACK:
                back(command);
                break;
                
            case LOOK:
                lookRoom(command);
                break;
                
            case PICKUP:
                pickUp(command);
                break;
                
            case DROP:
                drop(command);
                break;
            
            case BACKPACK:
                backpack(command);
                break;
                
            case EAT:
                eat(command);
                break;
            
            case STATUS:
                status(command);
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander around at the university.");
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else if (nextRoom.getName().equals("gamingLab")){
           if (!player.hasItems("key")){
            System.out.println("The door is locked, you have to find the key.");
            } else {
                System.out.println("You used the key.");
                roomPath.push(currentRoom);
                previousRoom = currentRoom;
                currentRoom = nextRoom;
                currentRoom.getLongDescription();
                player.lowerHuger(5);
            }
        }
        else {
           //update the previous room
            roomPath.push(currentRoom);
            previousRoom = currentRoom;
            currentRoom = nextRoom;
            currentRoom.getLongDescription();
            player.lowerHuger(20);
        }
    }
    
    /**
     * Exercise 8.23 - implement the back command (back one room) 
     */
    private void back(Command command)
    {
        if(roomPath.size() == 0) {
            System.out.println("You are at the start there is no previous room.");
        } else {
            // The previous room becomes the current.
            currentRoom = roomPath.pop();
            currentRoom.getLongDescription();
        }
    }
    
    /**
     * Exercise 8.14 - Add the look command to your version of the zuul game.
     */
    private void lookRoom(Command command)
    {
        currentRoom.getLongDescription();
    }
    
    /**
     * Exercise 8.15 - Add another command, ie. eat with a simple text response (it does not need to interact with other systems)
     */
    private void eat(Command command)
    {
        String item = command.getSecondWord();
        
        if (!player.hasItems(item)){
            System.out.println("There is no item in this room.");
            return;
        }
        
        if (!player.eatableCheck(item)) {
            System.out.println("This item can't be eaten.!");
            return; 
        }
        
        if (player.getHungerLevel() >= 100){
            System.out.println("You are too full to eat and you are not hungry right now.");
        } else {
            player.eatItems(item);
            player.raiseHuger(10);
            System.out.println("You feel less hungry after eating.");
        }
    }
    
    /**
     * 
     */
    private void pickUp(Command command)
    {
        String item = command.getSecondWord();
        
        if (!currentRoom.hasItems(item)){
            System.out.println("There is no item in this room.");
            return;
        }
        
        if (!currentRoom.weightCheck(item)) {
           System.out.println("Thia item is too heavy too pickup!");
            return; 
        } else {
            Items newItem = currentRoom.removeItems(item);
        
            player.addItems(newItem);
        }
    }
    
    /**
     * 
     */
    private void drop(Command command)
    {
        String item = command.getSecondWord();
        
        if (!player.hasItems(item)){
            System.out.println("There is no item in the backpack.");
            return;
        }
        
        Items newItem = player.removeItems(item);
            
        currentRoom.addItems(newItem);
    }
    
    /**
     * 
     */
    private void status(Command command)
    {
        player.getStatus();
    }
    
    /**
     * 
     */
    private void backpack(Command command)
    {
        player.getInventory();
    }
    
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
