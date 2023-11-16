/*************************************************************************
 *
 *  June 2022
 *  Monopoly Empire Project 
 *
 *  Team authors: Tyler Yeung, Madelyn Zambrano
 *  
 *  References: GeeksforGeeks, StackOverflow
 *  
 *  Description: The code below for Monopoly class, which serves as the main class for 
 *  a Monopoly game simulation. The main method initializes a new instance of the Game 
 *  class, representing the Monopoly game, and then calls the firstTimeIntro method on 
 *  this instance to introduce the game to the players. In essence, this code sets up 
 *  and initiates the Monopoly game by creating an instance of the game and providing 
 *  an introductory message to the players.
 *
 */
import java.util.*;

public class Monopoly { //Main class representing the Monopoly game
       
    public static void main(String[] args) //Main method where the Monopoly game starts 
    {
        Game game1 = new Game(); //Create a new instance of the Game class to represent the Monopoly game 

        game1.firstTimeIntro(); //Call the firstTimeIntro method on the Game instance to introduce the game to the players 
    }
}