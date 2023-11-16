/*************************************************************************
 *
 *  June 2022
 *  Monopoly Empire Project 
 *
 *  Team authors: Tyler Yeung, Madelyn Zambrano
 *  
 *  References: GeeksforGeeks, StackOverflow
 *  
 *  Description: The code below for the Purchasable class extends the concept of a game tile (Tile) 
 *  to include properties specific to purchasable tiles, such as cost and value. Instances of this class 
 *  could represent locations on a game board that players can acquire or invest in during the course 
 *  of a game. This structure provides a foundation for implementing a game where players can interact 
 *  with and potentially purchase specific tiles on the game board.
 */
import java.util.*;

public class Purchasable extends Tile // Purchasable class extends Tile class
{
    private int cost; // cost of the purchasable tile
    private int value; // value of the purchasable tile

    public Purchasable(String n, int c, int v) // Constructor for creating a Purchasable tile with a name, cost, and value
    {
        super(n);
        cost = c; 
        value = v;
    }

    public int getValue() //Method that gets the value of the tile 
    {
        return value;
    }

    public int getCost() //Method that gets the cost of the tile
    {
        return cost;
    }
}