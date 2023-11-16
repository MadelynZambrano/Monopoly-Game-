/*************************************************************************
 *
 *  June 2022
 *  Monopoly Empire Project 
 *
 *  Team authors: Tyler Yeung, Madelyn Zambrano
 *  
 *  References: GeeksforGeeks, StackOverflow
 *  
 *  Description: The code below for the Card class provides a basic structure for creating and managing 
 *  cards in a card-based system or game. It allows retrieval of card attributes and provides a mechanism
 *  to update the usage status of a card.
 */
import java.util.*;

public class Card
{
    // Attributes of a card
    private String type;
    private String name;
    private String description;
    private boolean used;

    public Card(String t, String n, String d) // Constructor to initialize a card with type, name, and description
    {
        type = t;
        name = n;
        description = d;
        used = false; //Initially, the card not used 
    }

    public String getCardType() // Method to get the type of the card
    {
        return type;
    }

    public String getCardName() /// Method to get the name of the card
    {
        return name;
    }

    public String getCardDescription() // Method to get the description of the card
    {
        return description;
    }

    public boolean isCardUsed() // Method to check if the card has been used
    {
        return used;
    }

    public void updateCardUse() // Method to update the card's usage status
    {
        used = true;
    }
}
