/*************************************************************************
 *
 *  June 2022
 *  Monopoly Empire Project 
 *
 *  Team authors: Tyler Yeung, Madelyn Zambrano
 *  
 *  References: GeeksforGeeks, StackOverflow
 *  
 *  Description: The code below for the Tower class serves as a container for managing and manipulating a stack 
 *  of purchasable assets with a focus on calculating and updating the total value of the tower. The tower can 
 *  hold various types of purchasable objects, and methods are provided to retrieve, add, and remove assets.
 *
 */
import java.util.*;

public class Tower
{
    private int value; //Total value of the tower 
    private ArrayList<Purchasable> assets; //List to store purchasable assets 

    public Tower() //Constructor to initialize the Tower object 
    {
        value = 0;
        assets = new ArrayList<Purchasable>();
    }

    public Purchasable getTopBillboard() //Method to get te top billboard from the tower
    {
        return assets.get(assets.size() - 1);
    }

    public void updateTowerValue() // Method to return the updated value of the tower
    {
        int sum = 0;
        for (int i = 0; i < assets.size(); i++)
            sum += assets.get(i).getValue();
        value = sum;
    }

    public int getTowerValue() //Method to return the value of the tower
    {
        return value;
    }

    public Purchasable getTopPurchasableAsset()  //Method to get the top purchasable asset from the tower 
    {
        return (Purchasable)(assets.get(assets.size() - 1));
    }

    public Billboard getTopBillboardAsset() //Method to get the top billboard asset from the tower 
    {
        if(assets.get(assets.size() - 1) instanceof Billboard) //Check if the top asset is an instance of Billboard
            return (Billboard)(assets.get(assets.size() - 1)); // Return the top billboard
        return null; // Return null if the top asset is not a Billboard
         
    }
    
    public void addAsset(Purchasable p) // Method to add a new asset to the tower
    {
        assets.add(p);
    }
    
    public void removeTopAsset() // Method to remove the top asset from the tower
    {
        assets.remove(assets.size() - 1);
    }

}