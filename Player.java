/*
 * Authors: Tyler Yeung, Madelyn Zambrano 
 */
import java.util.*;

public class Player
{
    private String name; //Player's name 
    private int index; //Player's index 
    private int money; //Amount of money the player has 
    private int position = 0; //Player's position on the game board 
    private boolean isInJail; //Indicating if player is in jail or not

    public Player(String n, int i) // Constructor to create a player with a name and index, initializing default values
    {
        name = n;
        index = i;
        money = 1000; //Default starting amount 
        position = 0; //Starts player at the beginning of board 
        isInJail = false; //Not initially in jail 
    }

    public String getName() //Method to get players name.
    {
        return name;
    }

    public int getIndex() //Method to get the space in which the player is on. 
    {
        return index;
    }

    public int getMoney() //Method to get the amount of money a player has. 
    {
        return money;
    }

    public int getPosition() //Methods to get the players position on the board. 
    {
        return position;
    }

    public void setPosition(int p, int towerValue, Board b) //Method to set the players position on the board. 
    {
        position += p;
        if (position > b.getBoardLength() - 1)
        {
            position -= b.getBoardLength();
            money += towerValue;
            System.out.println("You passed GO! Collect $" + towerValue + " from the bank. Your balance is now $" + money + "!");
        }
            
    }

    public void addMoney(int amount) //Method to add money to the existing amount the player has 
    {
        money += amount;
    }

    public void deductMoney(int amount) //Method to deduct money from the amount the player has
    {
        money -= amount;
    }

    public void goToJail() //Method that moves player to the jail position on board
    {
        isInJail = true;
        position = 9;
    }

    public void leaveJail() //Method to allow player to leave jail 
    {
        isInJail = false;
    }

    public boolean isInJail() //Method that returns if the player is in jail or not
    {
        return isInJail;
    }

}