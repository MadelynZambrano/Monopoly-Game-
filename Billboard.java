/*************************************************************************
 *
 *  June 2022
 *  Monopoly Empire Project 
 *
 *  Team authors: Tyler Yeung, Madelyn Zambrano
 *  
 *  References: GeeksforGeeks, StackOverflow
 *  
 *  Description: The code below defines the Billboard Class that extends another Purchasable. 
 *  The Billboard class represents a type of purchasable property on a board game. Each billboard object has attributes 
 *  such as a name, cost, and value, inherited from the Purchasable class. Additionally, the Billboard class has its
 *  specific attributes, including an owner (of type Player) and a boolean flag indicating whether the billboard is available for purchase.
 *
 */
import java.util.*;

public class Billboard extends Purchasable // Billboard class represents a type of purchasable property on the board
{
    private Player owner; // Owner of the billboard
    private boolean isAvailable; // Flag indicating whether the billboard is available for purchase

    public Billboard(String n, int c, int v, boolean a) // Constructor for creating a new billboard with a name, cost, value, and availability status
    {
        super(n, c, v); // Call the constructor of the superclass (Purchasable)
        owner = null; // Initially, no owner
        isAvailable = true; // Set the initial availability status
    }

    public Player getOwner() // Method to get the owner of the billboard
    {
        return owner;
    }

    public boolean getAvailability()  // Method to check the availability of the billboard
    {
        return isAvailable;
    }

    public void setOwner(Player owner) // Method to set the owner of the billboard
    {
        this.owner = owner;
    }

    public void setAvailability(boolean a) // Method to set the availability status of the billboard
    {
        isAvailable = a;
    }
}