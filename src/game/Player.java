package game;

import java.util.ArrayList; 
public class Player
{
    private Room currentRoom, previousRoom, chargedRoom;
    private ArrayList <Item> items = new ArrayList();
    private int currentWeight, maxWeight;
    private ArrayList <Item> keys = new ArrayList();
    
    public Player()
    {
        maxWeight = 5000;
        Item beamer = new Item();
        items.add(beamer);
    }

    public void setCurrentRoom(Room currentRoom)
    {

        this.currentRoom = currentRoom;
    }
    
    public void setCurrentRoomString(String currentRoom)
    {
        
    }

    public Room getCurrentRoom()
    {
        return currentRoom;
    }

    public void setPreviousRoom(Room previousRoom)
    {
        this.previousRoom = previousRoom;
    }

    public Room getPreviousRoom()
    {
        return previousRoom;
    }

    public int getMaxWeight()
    {
        return maxWeight;
    }

    public void addWeight(int weight)
    {
        currentWeight += weight;
    }

    public void subtractWeight(int weight)
    {
        currentWeight -= weight;
    }

    public int getCurrentWeight()
    {
        return currentWeight;
    }

    public void addItem(Item item)
    {
        items.add(item);
    }

    public boolean gotItem(String item)
    {
        boolean found = false;
        int n = 0;
        for(n = 0 ; n < items.size() ; n++)
        {
            if(item.equals(items.get(n).getName()))
            {
                found = true;
                return found;
            }
        }
        return found;
    }

    public int getItemNumber(String item)
    {
        boolean found = false;
        int n = 0;
        for(n = 0 ; n < items.size() ; n++)
        {
            if(item.equals(items.get(n).getName()))
            {
                found = true;
                return n;
            }
        }
        return n;
    }

    public Item getItem(String item)
    {
        return items.get((getItemNumber(item)));
    }

    public void removeItem(int n)
    {
        items.remove(n);
    }

    public String inventoryDescription()
    {
        String returnString = "Item(s) in your inventory:";
        if(items.size()>0)
        {
            for(int n = 0 ; n < items.size() ; n++)
            {
                returnString +="\n" + "Item no. " +(n+1) + ".\n" + 
                "Name: "+ items.get(n).getName() +".\n" +
                "description: " + items.get(n).getDescription() 
                + ".\n" + "Weight: " + items.get(n).getWeight() + 
                "g";
            }
            returnString += ".\n" + "Total weight: " + getCurrentWeight();
            return returnString;
        }
        else
        {
            returnString = "You aren't carrying any items.";
            return returnString;
        }
    }

    public void addItems(String description, int weight, String name, boolean canBePickedUp)
    {
        Item a = new Item(weight, description, name, canBePickedUp);
        items.add(a);
    }

    public void addKey()
    {
        Item key = new Item();
        keys.add(key);
    }

    public void removeKey()
    {
        keys.remove((keys.size()-1));
    }

    public int getNumberOfKeys()
    {
        return keys.size();
    }

    public void chargeBeamer()
    {
        chargedRoom = currentRoom;
        System.out.println("The beamer has been charged.");
    }

    public void fireBeamer()
    {
        currentRoom = chargedRoom;
        previousRoom = null;
        items.remove(0);
        System.out.println("You have returned to the room" + ".\n" 
        + "The beamer vanished into thin air.");
    }
    
    public Room getChargedRoom()
    {
        return chargedRoom;
    }
}
