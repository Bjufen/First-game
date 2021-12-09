package game;

import java.util.Scanner;
public class Game 
{
    private Parser parser;
    private Player player;
    private NPC npc;
    private boolean completed = false;
    private int turnsLeft;
    private Scanner n;

    public Game() 
    {
        player = new Player();
        createRooms();
        parser = new Parser();
        System.out.print('\u000C');
    }

    private void createRooms()
    {
        Room outside, lobby, bathroomGround, livingRoom, kitchen, 
        diningRoom, staircaseUp, staircaseGround, staircaseBasement,
        bathroomUp, hallway, office, kidBedroom, masterBedroom, 
        guestBedroom, masterBathroom, gym, garage, laundryRoom;

        outside = new Room("outside the house");
        lobby = new Room("in the lobby");
        bathroomGround = new Room("in the ground floor bathroom");
        livingRoom = new Room("in the living room");
        kitchen = new Room("in the kitchen");
        diningRoom = new Room("in the dining room");
        staircaseGround = new Room("on the ground floor staircase");

        masterBedroom = new Room("in the master bedroom");
        masterBathroom = new Room("in the master bathroom");
        office = new Room("in the office");
        hallway = new Room("in the hallway");
        staircaseUp = new Room("at the top of the staircase");
        bathroomUp = new Room("in the 1st floor bathroom");
        kidBedroom = new Room("in the kids bedroom");
        guestBedroom = new Room("in the guest bedroom");

        gym = new Room("in the gym");
        laundryRoom = new Room("in the laundry room");
        garage = new Room("in the garage");
        staircaseBasement = new Room("at the bottom of the staircase");

        outside.setExit("North", lobby);

        lobby.setExit("NorthWest", livingRoom);
        lobby.setExit("East", bathroomGround);
        lobby.setExit("South", outside);

        bathroomGround.setExit("West", lobby);

        livingRoom.setExit("North", kitchen);
        livingRoom.setExit("NorthEast", diningRoom);
        livingRoom.setExit("West", staircaseGround);
        livingRoom.setExit("SouthEast", lobby);

        kitchen.setExit("SouthEast", diningRoom);
        kitchen.setExit("South", livingRoom);

        diningRoom.setExit("SouthWest", livingRoom);
        diningRoom.setExit("NorthWest", kitchen);

        staircaseGround.setExit("East", livingRoom);
        staircaseGround.setExit("Up", staircaseUp);
        staircaseGround.setExit("Down", staircaseBasement);

        masterBedroom.setExit("NorthWest", masterBathroom);
        masterBedroom.setExit("SouthWest", hallway);

        masterBathroom.setExit("SouthEast", masterBedroom);

        office.setExit("SouthEast", hallway);

        hallway.setExit("NorthEast", masterBedroom);
        hallway.setExit("SouthEast", guestBedroom);
        hallway.setExit("SouthWest", kidBedroom);
        hallway.setExit("South", bathroomUp);
        hallway.setExit("West", staircaseUp);
        hallway.setExit("NorthWest", office);

        staircaseUp.setExit("East", hallway);
        staircaseUp.setExit("Down", staircaseGround);

        bathroomUp.setExit("North", hallway);

        kidBedroom.setExit("NorthEast", hallway);

        guestBedroom.setExit("NorthWest", hallway);

        gym.setExit("NorthEast", laundryRoom);
        gym.setExit("East", garage);
        gym.setExit("West", staircaseBasement);

        laundryRoom.setExit("SouthWest", gym);
        laundryRoom.setExit("SouthEast", garage);

        garage.setExit("NorthWest", laundryRoom);
        garage.setExit("West", gym);

        staircaseBasement.setExit("East", gym);
        staircaseBasement.setExit("Up", staircaseGround);

        masterBedroom.addItem("Yusuf's playstation 4", 3500, "ps4", true);
        kitchen.addItem("Yusuf's computer", 1600, "computer", true);
        laundryRoom.addItem("Yusuf's Huawei P Smart", 353, "phone", true);

        diningRoom.addKey();
        guestBedroom.addKey();
        masterBathroom.addKey();

        masterBedroom.setLocked(true);
        gym.setLocked(true);
        kitchen.setLocked(true);

        

        player.setCurrentRoom(outside);

        npc = new NPC(outside, "Yusuf", "I can't get inside my house", "I need you to get me three things from my house: my playstation, my phone and my computer", 
            "You must know this: you can't carry everything at the same time, so after you give me an item you need to return and get the other items");

    }

    public void play() 
    {            
        String inputLine;

        n = new Scanner(System.in);

        while(turnsLeft == 0)
        {
            System.out.println("What difficulty do you want? \nBrutal, Hard, Intermediate, Easy or Training Wheels?");
            inputLine = n.nextLine();

            if(inputLine.equals("Brutal"))
            {
                turnsLeft = 27;
            }
            else if(inputLine.equals("Hard"))
            {
                turnsLeft = 35;
            }
            else if(inputLine.equals("Intermediate"))
            {
                turnsLeft = 40;
            }
            else if(inputLine.equals("Easy"))
            {
                turnsLeft = 50;
            }
            else if(inputLine.equals("Training Wheels"))
            {
                turnsLeft = 999999999;
            }
            else
            {
                System.out.println("What?");
            }
        }
        printWelcome();

        boolean finished = false;
        while (! finished&& ! completed) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to Yusuf's game!");
        System.out.println();
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(player.getCurrentRoom().getLongDescription());
        System.out.println();
        System.out.println(npc.getdescription());
    }

    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(turnsLeft == 0)
        {
            System.out.println("You ran out of moves!");
            return true;
        }

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if(commandWord.equals("help")) {
            printHelp();
        }
        else if(commandWord.equals("go")) {
            goRoom(command);
        }
        else if(commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if(commandWord.equals("look")){
            look(command);
        }
        else if(commandWord.equals("eat")){
            eat(command);
        }
        else if(commandWord.equals("back"))
        {
            back();
        }
        else if(commandWord.equals("take"))
        {
            pickUpItem(command);
        }
        else if(commandWord.equals("dinfar"))
        {
            System.out.println("HOLD DIN KÆFT!!!");
            System.out.println("FUCKING TÆVE!!!!");
            wantToQuit = true;
        }
        else if(commandWord.equals("drop"))
        {
            dropItem(command);
        }
        else if(commandWord.equals("items"))
        {
            items();
        }
        else if(commandWord.equals("give"))
        {
            give(command);
        }
        else if(commandWord.equals("charge"))
        {
            charge();
        }
        else if(commandWord.equals("fire"))
        {
            fire();
        }

        return wantToQuit;
    }

    private void printHelp() 
    {
        System.out.println("You are lost. You are looking");
        System.out.println("around the house for Yusuf's stuff.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(parser.showCommands());
        System.out.println();
        System.out.println(player.getCurrentRoom().getLongDescription());
    }

    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            if(!nextRoom.getLocked())
            {
                player.setPreviousRoom(player.getCurrentRoom());
                player.setCurrentRoom(nextRoom);
                turnsLeft --;
                System.out.println("You have " + turnsLeft + " turns left.");
                printLocationInfo();
            }
            else
            {
                if(player.getNumberOfKeys() > 0)
                {
                    player.removeKey();
                    player.setPreviousRoom(player.getCurrentRoom());
                    player.setCurrentRoom(nextRoom);
                    player.getCurrentRoom().setLocked(false);
                    System.out.println("You unlocked the door.");
                    System.out.println("You now have " + player.getNumberOfKeys() + " keys left.");
                    System.out.println();
                    turnsLeft --;
                    System.out.println("You have " + turnsLeft + " turns left.");
                    printLocationInfo();
                }
                else
                {
                    System.out.println("The door is locked and you don't have a key!");
                }
            }
        }
    }

    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;
        }
    }

    private void printLocationInfo()
    {
        System.out.println(player.getCurrentRoom().getLongDescription());
    }

    private void look(Command command)
    {
        if(!command.hasSecondWord())
        {
            System.out.println(player.getCurrentRoom().getLongDescription());
            return;
        }
        
        String direction = command.getSecondWord();

        Room lookRoom = player.getCurrentRoom().getExit(direction);
        
        if(lookRoom == null)
        {
            System.out.println("There is no door!");
        }
        else
        {
            System.out.println(lookRoom.getLongDescription());
        }
    }

    private void eat(Command command)
    {
        if(!command.hasSecondWord())
        {    
            System.out.println("You have eaten now and you are not hungry any more.");
        }
        
        else 
        {
            System.out.println("Eat what?");
        }
    }

    private void back()
    {
        Room n;
        n = player.getCurrentRoom();
        player.setCurrentRoom(player.getPreviousRoom());
        player.setPreviousRoom(n);
        System.out.println("You have returned to the previous room.");
        printLocationInfo();
    }

    private void pickUpItem(Command command)
    {
        if(!command.hasSecondWord())
        {    
            System.out.println("take what?");
            return;
        }

        String wantedItem = command.getSecondWord();
        if(wantedItem.equals("key"))
        {
            if(player.getCurrentRoom().getNumberOfKeys() == 0)
            {
                System.out.println("There aren't any keys in this room!");
            }
            else
            {
                player.addKey();
                player.getCurrentRoom().removeKey();
                System.out.println("You have picked up the key.");
            }
        }
        else
        {    
            if(player.getCurrentRoom().gotItem(wantedItem))
            {
                if(player.getCurrentRoom().getItem(wantedItem).getCanBePickedUp())
                {
                    if(player.getCurrentWeight() + 
                    player.getCurrentRoom().getItem(wantedItem).getWeight() <= 
                    player.getMaxWeight())
                    {
                        player.addItem(player.getCurrentRoom().getItem(wantedItem));
                        player.addWeight(player.getCurrentRoom().getItem(wantedItem).getWeight());
                        player.getCurrentRoom().removeItem(player.getCurrentRoom().getItemNumber(wantedItem));
                        System.out.println("You have picked up the item.");
                    }
                    else
                    {
                        System.out.println("This item weighs too much!");
                    }
                }
                else
                {
                    System.out.println("This item can't be picked up!");
                }
            }
            else
            {
                System.out.println("That's not an item!");
            }
        }
    }

    private void dropItem(Command command)
    {
        if(!command.hasSecondWord())
        {    
            System.out.println("drop what?");
            return;
        }

        String dropItem = command.getSecondWord();

        if(player.gotItem(dropItem))
        {
            player.getCurrentRoom().receiveItem(player.getItem(dropItem));
            player.subtractWeight(player.getItem(dropItem).getWeight());
            player.removeItem(player.getItemNumber(dropItem));
            System.out.println("You have dropped the item.");
        }
        else
        {
            System.out.println("You aren't carrying the above mentioned item!");
        }
    }

    private void items()
    {
        System.out.println(player.inventoryDescription());
    }

    private void give(Command command)
    {
        if(!command.hasSecondWord())
        {
            if(!command.hasThirdWord())
            {
                System.out.println("give whom what?");
                return;
            }
            else
            {
                System.out.println("give whom the "+command.getThirdWord()+"?");
                return;
            }
        }

        if(!command.hasThirdWord())
        {   
            System.out.println("give "+command.getSecondWord()+" what?");
            return;
        }

        String receiver = command.getSecondWord();
        String giveItem = command.getThirdWord();
        if(receiver.equals(npc.getName()))
        {
            if(npc.getCurrentRoom() == player.getCurrentRoom())
            {
                if(player.gotItem(giveItem))
                {
                    if(giveItem.equals("beamer"))
                    {
                        System.out.println("You can't give this item away");
                    }
                    else
                    {
                        npc.addItem(player.getItem(giveItem));
                        player.subtractWeight(player.getItem(giveItem).getWeight());
                        player.removeItem(player.getItemNumber(giveItem));
                        System.out.println("You have given " + receiver + " the " + giveItem + ".\n");
                    }
                    if(npc.numberOfItems() == 3)
                    {
                        won();
                    }
                }
                else
                {
                    System.out.println("You don't have that item!");
                }
            }
            else
            {
                System.out.println("You aren't in the same room");
            }
        }
        else
        {
            System.out.println("Who?");
        }
    }

    private void charge()
    {
        if(player.getChargedRoom() == null)
        {
            player.chargeBeamer();
        }
        else
        {
            System.out.println("You can only charge the beamer once!");
        }
    }

    private void fire()
    {
        if(player.getChargedRoom() == null)
        {
            System.out.println("You need to charge the beamer before firing it!");
        }
        else
        {
            player.fireBeamer();
            System.out.println();
            printLocationInfo();
        }
    }

    private void won()
    {
        System.out.println("You have completed the mission!");
        completed = true;
    }

    public final void run(String[]args)
    {
        Game game = new Game();
        game.play();
    }
}