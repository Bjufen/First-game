package game;

public class Item
{
    private String name, description;
    private int weight;
    private boolean canBePickedUp;
    
    public Item(){}
    
    public Item(int weight, String description, String name, boolean
                canBePickedUp)
    {
        this.weight = weight;
        this.description = description;
        this.name = name;
        this.canBePickedUp = canBePickedUp;
    }
    
    public void setWeight(int weight)
    {
        this.weight = weight;
    }
    
    public int getWeight()
    {
        return weight;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getName()
    {
        return name;
    }
    
    private void setCanBePickedUp(boolean canBePickedUp)
    {
        this.canBePickedUp = canBePickedUp;
    }
    
    public boolean getCanBePickedUp()
    {
        return canBePickedUp;
    }
    
    public String getItemDescription()
    {
        String returnString = "Item: " + ".\n" + "Name: " + getName() +
        ".\n" + "description: " + getDescription()+ ".\n" + "Weight: " +
        getWeight() + "g";
        return returnString;
    }
}