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

public class Board
{
    private final int NUM_TILES = 36; //Total number of tiles on the board
    private Tile[] board; //Array to store the tiles on the board 

    public Board() //This creates the board and is a constuctor to initialize the board with various tiles 
    {
        board = new Tile[NUM_TILES];
        board[0] = new Tile("GO");
        board[1] = new Billboard("Nerf", 50, 50, true);
        board[2] = new Tile("Rival Tower Tax");
        board[3] = new Billboard("Transformers", 50, 50, true); 
        board[4] = new Tile("Chance Card");
        board[5] = new Billboard("My Little Pony", 100, 50, true); 
        board[6] = new Tile("Chance Card"); 
        board[7] = new Billboard("Fender", 100, 50, true);
        board[8] = new Billboard("Beats", 100, 50, true); 
        board[9] = new Tile("Jail");
        board[10] = new Billboard("Spotify", 150, 100, true);
        board[11] = new Billboard("Angry Birds", 150, 100, true); 
        board[12] = new Purchasable("Electric Company", 150, 100); 
        board[13] = new Billboard("EA", 150, 100, true); 
        board[14] = new Billboard("Under Armour", 200, 100, true); 
        board[15] = new Tile("Chance Card"); 
        board[16] = new Billboard("Carnival", 250, 100, true); 
        board[17] = new Billboard("Yahoo", 250, 100, true); 
        board[18] = new Tile("Free Parking"); 
        board[19] = new Billboard("Paramount", 250, 150, true);
        board[20] = new Billboard("Chevrolet", 250, 150, true); 
        board[21] = new Tile("Chance Card"); 
        board[22] = new Billboard("Ebay", 250, 150, true);  
        board[23] = new Billboard("XGames", 300, 150,  true);
        board[24] = new Tile("Chance Card"); 
        board[25] = new Billboard("Ducati", 300, 150, true); 
        board[26] = new Billboard("McDonalds", 300, 150, true); 
        board[27] = new Tile("Go To Jail"); 
        board[28] = new Billboard("Intel", 350, 200, true); 
        board[29] = new Billboard("Xbox", 350, 200, true); 
        board[30] = new Purchasable("Water Works", 150, 50); 
        board[31] = new Billboard("Nestle", 350, 200, true); 
        board[32] = new Tile("Chance Card"); 
        board[33] = new Billboard("Samsung", 400, 200, true); 
        board[34] = new Tile("Tower Tax"); 
        board[35] = new Billboard("Coca-Cola", 400, 200, true); 
    }

    public int getBoardLength() // Method to get the length of the board
    {
        return board.length;
    }

    public Tile getBoardTile(int index) // Method to get a specific tile on the board
    {
        return board[index];
    }

    public Purchasable getBoardPurchasable(int index) // Method to get a specific purchasable tile on the board
    {
        if (board[index] instanceof Purchasable)
            return (Purchasable)(board[index]);
        return null;
    }

    public Billboard getBoardBillboard(int index) //Method to get the billboard at location of index
    {
        if (board[index] instanceof Billboard)
            return (Billboard)(board[index]);
        return null;
    }
     
}