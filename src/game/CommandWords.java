package game;

public class CommandWords
{
    private static final String[] validCommands = {
        "go", "quit", "help", "look", "eat", "back", "take", "dinfar",
        "drop", "items", "give", "charge", "fire"};

    public CommandWords()
    {
        
    }

    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                return true;
        }
        return false;
    }

    public String getCommandList()
    {
        String commandList="";
        for(String command: validCommands)
        {
            commandList+=command + " ";
        }
        return commandList;
    }
}
