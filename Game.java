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
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
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
        dumbbell, salad, frenchFries, pingPong, chocolate, sportsDrink, telescope;    
      
        // create the rooms
        outside = new Room("outside the main entrance of Raritan Valley Community College");
        theater = new Room("in a theater");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the admin office");
        admission = new Room("in the admission office");
        acts = new Room("in the Advising, Counseling and Transfer Services");
        bookstore = new Room("in the RVCC bookstore");
        library = new Room("in the Library");
        bateman = new Room("in the Bateman Student Center");
        studentLife = new Room("in the RVCC Student Life office");
        studentLounge = new Room("in the Student Lounge");
        tutor = new Room("in the Tutoring Center");
        planetarium = new Room("in the Planetarium");
        cafeteria = new Room("in the Cafeteria");
        physicalEducation = new Room("in the Physical Education");
        pool = new Room("in the pool");
        fitness = new Room("in the Fitness Center");
        westBuilding = new Room("in the West Building");
        gamingLab = new Room("in the Gaming Lab");
        aiClub = new Room("in the AI club");
        
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
        
        
        // create the items
        book = new Items("book", "Objects First With Java A Practical Introduction Using Bluej 6Th Edition", 1.85);
        paper = new Items("paper", "A letter of paper is 8–1/2 x 11 inches in size.", 0.01);
        jacket = new Items("jacket", "A gray jacket with the RVCC logo is present.", 0.8);
        computer = new Items("computer", "A computer desktop.", 20.50);
        vr = new Items("vr", "Virtual Reality Headset - Quest 2 Advanced All-In-One.", 1.83);
        aiProject = new Items("aiProject", "A folder for the AI Club's AI project.", 0.45);
        coke = new Items("coke", "A Coke can, Coke is a carbonated soft drink manufactured by the Coca-Cola Company.", 0.5);
        dumbbell = new Items("dumbbell", "Workout Dumbbell - 2 lbs", 2);
        salad = new Items("salad", "A bowl of Green Salad", 0.35);
        frenchFries = new Items("frenchFries", "A French-fried is in a box", 0.25);
        pingPong = new Items("pingPong", "A Ping Pong ball.", 0.001);
        chocolate = new Items("chocolate", "Feastables Chocolate Mr Beast Bar.", 0.1);
        sportsDrink = new Items("sportsDrink", "A Bottle of Gatorade G2 Grape.", 0.35);
        telescope = new Items("telescope", "Professional Astronomy Refractor Telescope.", 50);
        
        
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
        System.out.println(currentRoom.getLongDescription());
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
                
            case LOOK:
                lookRoom(command);
                break;
                
            case EAT:
                eat(command);
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
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
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
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }
    
    /**
     * Exercise 8.14
     */
    private void lookRoom(Command command)
    {
        System.out.println(currentRoom.getLongDescription());
    }
    
    /**
     * Exercise 8.15
     */
    private void eat(Command command)
    {
        System.out.println("You have eaten now and you are not hungry any more.");
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
