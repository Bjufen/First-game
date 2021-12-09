package game;

import java.util.ArrayList;
public class NPC
{
    private Room currentRoom;
    private ArrayList <Item> items = new ArrayList();
    private String name, description, needed, condition, extraInfo;
    
    public NPC(Room currentRoom, String name, String description, 
               String needed, String condition)
    {
        this.currentRoom = currentRoom;
        this.name = name;
        this.description = description;
        this.needed = needed;
        this.condition = condition;
    }
    
    public NPC(){}
    
    public int numberOfItems()
    {
        return items.size();
    }
    
    public void setCurrentRoom(Room currentRoom)
    {
        this.currentRoom = currentRoom;
    }
    
    public Room getCurrentRoom()
    {
        return currentRoom;
    }
    
    public void addItem(Item item)
    {
        items.add(item);
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getdescription()
    {
        String returnString = "Hi I am " + name + ".\n" + description
        + ".\n" + needed + ".\n" +  condition;
        return returnString;
    }
}
