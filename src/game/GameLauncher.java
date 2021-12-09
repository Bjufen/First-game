package game;

public class GameLauncher
{
    private GameLauncher()
    {
    }
    
    public static void main(String[]args)
    {
        Game game = new Game();
        game.run(args);
    }
}
