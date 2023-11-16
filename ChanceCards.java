/*************************************************************************
 *
 *  June 2022
 *  Monopoly Empire Project 
 *
 *  Team authors: Tyler Yeung, Madelyn Zambrano
 *  
 *  References: GeeksforGeeks, StackOverflow
 *  
 *  Description: The code below for the the ChanceCards class provides a predefined set of chance cards with 
 *  various effects. It allows for the random selection of a chance card, retrieval of specific chance cards 
 *  by index, checking whether a specific chance card has been used, and obtaining the total number of chance 
 *  cards available in the collection. This class is designed to facilitate the integration of chance card 
 *  mechanics into a larger game or simulation.
 *
 */
import java.util.*;

public class ChanceCards
{
    // Number of cards we are suppose to have
    private final int NUM_CARDS = 14;
    // This is the arraylist that holds the chance cards 
    private Card[] chanceCards;
    // This creates the chance cards and their effecs
    public ChanceCards()
    {
        //Initializing chance cards with their names and effects 
        chanceCards = new Card[NUM_CARDS];
        chanceCards[0] = new Card("Chance", "Casino Night!", "Pick an opponent. Both roll. Highest roller collects $200 from the Bank.");
        chanceCards[1] = new Card("Chance", "Successful advertising campaign!", "Collect $100 from the Bank.");
        chanceCards[2] = new Card("Chance", "It's your empire's first birthday!", "All players pay you $50. Players with less than $50 will do nothing.");
        chanceCards[3] = new Card("Chance", "Go to jail for fraud!", "Do not collect cash for passing GO.");
        chanceCards[4] = new Card("Chance", "Solar power bonus!", "Take a free electric company billboard. Add it to your tower.");
        chanceCards[5] = new Card("Chance", "Speed ahead!", "Move over 5 spaces in your private limo.");
        chanceCards[6] = new Card("Chance", "Go to Jail!", "Do not collect cash for passing GO.");
        chanceCards[7] = new Card("Chance", "Casino Night!", "Pick an opponent. Both roll. Highest roller collects $200 from the Bank.");
        chanceCards[8] = new Card("Chance", "Insider trading fine!", "Pay the bank $200. Do nothing if you have less than $200.");
        chanceCards[9] = new Card("Chance", "Water bonus!", "Take a free water works billboard and add it to your tower.");
        chanceCards[10] = new Card("Chance", "Launch your website!", "Sales Rocket! Collect $300 from the bank.");
        chanceCards[11] = new Card("Chance", "Profits soar!", "Advance to GO to collect your tower value.");
        chanceCards[12] = new Card("Chance", "Win 'Business of the Year' award!", "Collect $400 from the bank.");
        chanceCards[13] = new Card("Chance", "Get out of jail for free!", "");

    } 

    public Card useChanceCard() // Method to randomly select and return a chance card
    {
        int randomNumber = (int)(Math.random() * 14);
        return chanceCards[randomNumber];
    }


    public Card getChanceCard(int i) // Method to get a specific chance card by index
    {
        return chanceCards[i];
    }

    public boolean isChanceCardUsed(int i) // Method to check if a specific chance card has been used
    {
        return chanceCards[i].isCardUsed();
    }

    public int getChanceCardLength() // Method to get the total number of chance cards
    {
        return chanceCards.length;
    }
}