/*************************************************************************
 *
 *  June 2022
 *  Monopoly Empire Project 
 *
 *  Team authors: Tyler Yeung, Madelyn Zambrano
 *  
 *  References: GeeksforGeeks, StackOverflow
 *  
 *  Description: The code below for the Tile class provides a basic structure to represent a 
 *  game tile, including methods to retrieve the tile's name, add a player to the tile, and 
 * remove a player from the tile. This could be part of a larger game system where players 
 *  move between different tiles or locations on a game board.
 *
 */
import java.util.*;

public class Tile 
{
    private String name; // Name of the tile
    private ArrayList<Player> playersPresent; // List to store players on the tile

    public Tile(String n) // Constructor to initialize the Tile object with a given name
    {
        name = n;
        playersPresent = new ArrayList<Player>();
    }

    public String getName() // Method to get name of the player who owns the tower
    {
        return name;
    }

    public void addPlayerToTile(Player p) // Method that adds the player to the tile
    {
        playersPresent.add(p);
    } 

    public void removePlayerFromTile(Player p) // Method that removes the player from the tile
    {
        playersPresent.remove(p);
    }
}