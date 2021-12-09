package game;

import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;
public class Room
{
    private String description;
    private HashMap<String, Room> exits;
    private ArrayList <Item> items = new ArrayList();
    private boolean locked;
    private ArrayList <Item> keys = new ArrayList();

    public Room(String description)
    {
        this.description = description;
        exits = new HashMap<String, Room>();
    }

    public void setExit(String direction, Room neighbor)
    {
        exits.put(direction, neighbor);
    }

    public Room getExit(String direction)
    {
        return exits.get(direction);
    }

    public String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    public String getDescription()
    {
        return description;
    }

    public void addItem(String description, int weight, String name, boolean canBePickedUp)
    {
        Item a = new Item(weight, description, name, canBePickedUp);
        items.add(a);
    }

    public void receiveItem(Item item)
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

    public Item getItem(String item)
    {
        return items.get(getItemNumber(item));
    }

    public int getItemNumber(String item)
    {
       int n = 0;
        for(n = 0 ; n < items.size() ; n++)
        {
            if(item.equals(items.get(n).getName()))
            {
                return n;
            }
        }
        return n;
    }

    public void removeItem(int n)
    {
        items.remove(n);
    }

    public String itemDescription()
    {
        String returnString = "Item(s) in this room:";
        if(items.size()>0)
        {
            for(int n = 0 ; n < items.size() ; n++)
            {
                returnString +="\n" + "Item no. " +(n+1) + ".\n" + "Name: " + items.get(n).getName() +
                ".\n" + "description: " + items.get(n).getDescription()+ ".\n" + "Weight: " +
                items.get(n).getWeight() + "g";
            }
            return returnString;
        }
        else
        {
            returnString = "There aren't any items present in this room.";
            return returnString;
        }
    }

    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString()
        + ".\n" + itemDescription() + ".\n" + keyDescription();
    }
    
    public void setLocked(boolean locked)
    {
        this.locked = locked;
    }
    
    public boolean getLocked()
    {
        return locked;
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
    
    public String keyDescription()
    {
        String returnString = "";
        if(keys.size() > 0)
        {
            if(keys.size() > 1)
            {
                System.out.println("There are " + keys.size() + " keys in this room");
            }
            else
            {
                System.out.println("There is 1 key in this room.");
            }
        }
        else
        {
            System.out.println("There aren't any keys in this Room");
        }
        return returnString;
    }
    
    public int getNumberOfKeys()
    {
        return keys.size();
    }
}