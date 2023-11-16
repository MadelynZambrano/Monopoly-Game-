/*************************************************************************
 *
 *  June 2022
 *  Monopoly Empire Project 
 *
 *  Team authors: Tyler Yeung, Madelyn Zambrano
 *  
 *  References: GeeksforGeeks, StackOverflow
 *  
 *  Description: The code below is what runs the game. 
 *  The Game class implements many features, such as arrays, created methods that will be implemented later in the game,
 *  asking and validating input from users, and a series of if/else/elif statements to take into accout all possible 
 *  scenarios the game may go in.
 *
 */
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Game
{
    //Declare class level variables 
    String input = "";
    Board board1 = new Board();
    ChanceCards chanceCards = new ChanceCards();
    private int numberOfPlayers;
    private boolean playerWon = false;
    private String winner = "";
    private Player[] players = new Player[4];
    private Tower[] towers = new Tower[4];
    private int[] jailDoubleCounter = new int[4];
    private boolean[] canGetOutOfJailFree = new boolean[4];
    ArrayList<String> availableSwappers;
    ArrayList<String> taxablePlayers;
    ArrayList<String> availableRollers;
    ArrayList<String> playerNames = new ArrayList<String>();
    Scanner scanner = new Scanner(System.in);
    
    public void firstTimeIntro() //Game introduction method 
    {
        System.out.println("---------------------");
        runTimer(1);
        System.out.println("MONOPOLY EMPIRE © 2013 Hasbro, Korea Boardgames Co., Ltd.");
        runTimer(1);
        System.out.println("---------------------");
        runTimer(1);
        System.out.println("MONOPOLY EMPIRE: JAVA CONSOLE EDITION");
        runTimer(1);
        System.out.println("VALHALLA HIGH SCHOOL, VALHALLA, NEW YORK, 2022");
        runTimer(1);
        System.out.println("CREATED BY: BIANCA BARABASI | JEFFREY LEO | TYLER YEUNG | MADELYN ZAMBRANO");
        runTimer(1);
        System.out.println("NOT AN EXACT REPLICA OF MONOPOLY EMPIRE. SOME FEATURES MAY BE MODIFIED OR REMOVED ENTIRELY.");
        runTimer(1);
        System.out.println("---------------------");
        runTimer(1);
        System.out.print("Welcome to Monopoly Empire! Type 'instructions' to learn how to play. Type 'skip' to skip the instructions: ");  //Prompt user to skip instructions or read them 
        input = scanner.nextLine();
        while (!(input.equalsIgnoreCase("instructions") || input.equalsIgnoreCase("skip"))) //Validate user input
        {
            System.out.print("Please type a valid input: ");
            input = scanner.nextLine();
        }
        if (input.equalsIgnoreCase("instructions")) //If user chooses instructions, display them
            runInstructions();
        gameIntro(); //Start the game intro
    }

    public void gameIntro() //Establishes the necessary parameters to play the game 
    {
        System.out.print("Enter number of players (2-4): "); //Get number of players 
        numberOfPlayers = scanner.nextInt();
        while (numberOfPlayers < 2 || numberOfPlayers > 4) //Validate the number of players 
        {
            System.out.print("Please input a number between 2 and 4: ");
            numberOfPlayers = scanner.nextInt();
        }
        scanner.nextLine();
        for (int i = 1; i <= numberOfPlayers; i++) //Initialize players and towers 
        {
            System.out.print("What is Player " + i + "'s name? ");
            input = scanner.nextLine();
            while (input.equals("")) //Validate player name 
            {
                System.out.print("Please input a name: ");
                input = scanner.nextLine();
            }
            players[i - 1] = new Player(input, i - 1); //Create player names and initialize board 
            towers[i - 1] = new Tower();
            playerNames.add(players[i - 1].getName());
        }
        System.out.println("The following people are playing: "); //Display player names and initialize board 
        for(int j = 0; j < numberOfPlayers; j++)
        {
            System.out.println(players[j].getName());
            board1.getBoardTile(0).addPlayerToTile(players[j]);
        }
        System.out.println("---------------------");
        System.out.println("MONOPOLY EMPIRE BOARD");
        for (int f = 0; f < board1.getBoardLength(); f++) //Display board information 
        {
            if (board1.getBoardTile(f) instanceof Purchasable) //Check if the board tile at current position is a purchasable property
            {
                //Diaply information about purchasable properties
                System.out.println("---------------------"); /
                System.out.println("POSITION: " + f);
                System.out.println("NAME: " + board1.getBoardPurchasable(f).getName());
                System.out.println("COST: $" + board1.getBoardPurchasable(f).getCost());
                System.out.println("VALUE: $" + board1.getBoardPurchasable(f).getValue());
            }
            else 
            {
                // Display information for non-purchasable tiles (e.g., special tiles)
                System.out.println("---------------------");
                System.out.println("POSITION: " + f);
                System.out.println("NAME: " + board1.getBoardTile(f).getName());
            }
        }
        System.out.println("---------------------"); // Display a message indicating the end of board information
        System.out.println("Above is the entire Monopoly Empire Board. Familiarize yourself with it and refer back to it whenever necessary.");
        System.out.println("Type 'start' to begin: "); // Prompt the user to type 'start' to begin the game
        input = scanner.nextLine();
        while (!input.equalsIgnoreCase("start")) // Validate user input to start the game
        {
            System.out.println("Type 'start' to begin: ");
            input = scanner.nextLine();
        }
        runGame(); //Start the game 
    }

    public void runInstructions() //Displays innstructions for the players of the game 
    {
        //Display introductory messages 
        System.out.println("---------------------");
        runTimer(1);
        System.out.println("INSTRUCTIONS:");
        runTimer(1);
        System.out.println("---------------------");
        runTimer(1);
        //Explain the goal of game 
        System.out.println("To win, you must fill your tower first!");
        System.out.println("Buy billboards to fill your tower.");
        System.out.println("The more you buy, the more cash you collect!");
        System.out.println("The first person to achieve $800 in assets in their tower will win!");
        runTimer(1);
        System.out.println("---------------------");
        runTimer(1);
        //Explain the acquisition of brands 
        System.out.println("All your favorite brands are up for grabs!");
        System.out.println("When you land on a brand space, buy it and add it to your tower.");
        System.out.println("You now own that brand!");
        runTimer(1);
        System.out.println("---------------------");
        runTimer(1);
        //Explain tower value collection when passing GO
        System.out.println("Collect your tower value when you pass GO!");
        System.out.println("The more billboards you stack, the more cash you collect!");
        runTimer(1);
        System.out.println("---------------------");
        runTimer(1);
        //Explain the Chance Card mechanics
        runTimer(1);
        System.out.println("There are Chance Cards!");
        System.out.println("Chance cards contain mystery events, both negative and positive!");
        System.out.println("You get a chance card every time you land on a Chance space.");
        runTimer(1);
        System.out.println("---------------------");
        runTimer(1);
        //Provice a closing message 
        System.out.println("Other instructions will be posted throughout the game. Pay attention and have fun!");
        runTimer(1);
        System.out.println("---------------------");
        runTimer(1);
    }

    public void runTimer(int time) // Provides a time interval using a parameter to pause the console for the specified interval. CITATION: https://www.geeksforgeeks.org/timeunit-sleep-method-in-java-with-examples/
    {
        try 
        {
            TimeUnit.SECONDS.sleep(time); //Pause the execution of the program for the specifies time using TimeUnit
        } catch (InterruptedException e) {
            e.printStackTrace(); //pring the stack trace in case of an interruption during sleep 
        }
    }
    
    public int[] rollDice() // Rolls the dice. Stores the first dice value, second dice value, and the sum of the two dice values in an array and returns it.
    {
        int[] diceResults = new int[3]; 
        diceResults[0] = (int)(Math.random() * 6) + 1; 
        diceResults[1] = (int)(Math.random() * 6) + 1; 
        diceResults[2] = diceResults[0] + diceResults[1]; 
        return diceResults; 
    }

    public void move(int playerIndex, int total) //Moves the player on the game board 
    {  
        board1.getBoardTile(players[playerIndex].getPosition()).removePlayerFromTile(players[playerIndex]); // Remove the player from their current position on the board
        players[playerIndex].setPosition(total, towers[playerIndex].getTowerValue(), board1); // Update the player's position, tower value, and update the board accordingly
        board1.getBoardTile(players[playerIndex].getPosition()).addPlayerToTile(players[playerIndex]); // Add the player to their new position on the board
    }

    public void swap(int receivingPlayerIndex) //Swaps player positions 
    {
        System.out.println("Type the name of the player whose asset you would like to swap: "); // Prompt the user to enter the name of the player for the swap
        input = scanner.nextLine();
        while (!availableSwappers.contains(input.toLowerCase())) // Validate the entered player name
        {
            System.out.print("Please choose a valid player: ");
            input = scanner.nextLine();
        }
        for(int j = 0; j < numberOfPlayers; j++) // Iterate through players to find the selected player for the swap
        {
            if (players[j].getName().equalsIgnoreCase(input))
            {
                if (towers[players[j].getIndex()].getTopPurchasableAsset() instanceof Purchasable && !(towers[players[j].getIndex()].getTopPurchasableAsset() instanceof Billboard))
                {
                    if (towers[receivingPlayerIndex].getTopPurchasableAsset() instanceof Purchasable && !(towers[receivingPlayerIndex].getTopPurchasableAsset() instanceof Billboard)) // Check the type of assets being swapped and perform the swap accordingly
                    {
                        System.out.println("Successfully swapped your " + towers[receivingPlayerIndex].getTopPurchasableAsset().getName() + " with " + players[j].getName() + "'s " + towers[players[j].getIndex()].getTopPurchasableAsset().getName() + ". Type 'next' to proceed: "); //Swap non-Billboard assets between player
                        Purchasable temp = towers[players[j].getIndex()].getTopPurchasableAsset();
                        towers[players[j].getIndex()].addAsset(towers[receivingPlayerIndex].getTopPurchasableAsset()); 
                        towers[receivingPlayerIndex].removeTopAsset();
                        towers[receivingPlayerIndex].addAsset(temp); 
                    }
                    else 
                    {
                        System.out.println("Successfully swapped your " + towers[receivingPlayerIndex].getTopPurchasableAsset().getName() + " with " + players[j].getName() + "'s " + towers[players[j].getIndex()].getTopPurchasableAsset().getName() + ". Type 'next' to proceed: "); //Swap Billboard asset with non-Billboard asset
                        Purchasable temp = towers[players[j].getIndex()].getTopPurchasableAsset();
                        towers[players[j].getIndex()].addAsset(towers[receivingPlayerIndex].getTopBillboardAsset()); 
                        towers[players[j].getIndex()].getTopBillboardAsset().setOwner(players[j]);
                        towers[receivingPlayerIndex].removeTopAsset();
                        towers[receivingPlayerIndex].addAsset(temp); 
                    }
                    
                }
                else if (towers[players[j].getIndex()].getTopPurchasableAsset() instanceof Billboard)
                {
                    if (towers[receivingPlayerIndex].getTopPurchasableAsset() instanceof Billboard)
                    {
                        System.out.println("Successfully swapped your " + towers[receivingPlayerIndex].getTopBillboardAsset().getName() + " with " + players[j].getName() + "'s " + towers[players[j].getIndex()].getTopBillboardAsset().getName() + ". Type 'next' to proceed: "); //Swap Billboard asset between players 
                        Billboard temp = towers[players[j].getIndex()].getTopBillboardAsset();
                        towers[players[j].getIndex()].addAsset(towers[receivingPlayerIndex].getTopBillboardAsset()); 
                        towers[players[j].getIndex()].getTopBillboardAsset().setOwner(players[j]);
                        towers[receivingPlayerIndex].removeTopAsset();
                        towers[receivingPlayerIndex].addAsset(temp); 
                        towers[receivingPlayerIndex].getTopBillboardAsset().setOwner(players[receivingPlayerIndex]);
                    }
                    else 
                    {
                        System.out.println("Successfully swapped your " + towers[receivingPlayerIndex].getTopPurchasableAsset().getName() + " with " + players[j].getName() + "'s " + towers[players[j].getIndex()].getTopPurchasableAsset().getName() + ". Type 'next' to proceed: "); //Swap Billboard asset with non-Billboard player 
                        Billboard temp = towers[players[j].getIndex()].getTopBillboardAsset();
                        towers[players[j].getIndex()].addAsset(towers[receivingPlayerIndex].getTopPurchasableAsset()); 
                        towers[receivingPlayerIndex].removeTopAsset();
                        towers[receivingPlayerIndex].addAsset(temp); 
                        towers[receivingPlayerIndex].getTopBillboardAsset().setOwner(players[receivingPlayerIndex]);
                    }
                }
                towers[receivingPlayerIndex].updateTowerValue(); //Update tower values for both players after the swap 
                towers[players[j].getIndex()].updateTowerValue();
                input = scanner.nextLine(); //Prompt the user to type 'next' to proceed 
                while (!input.equalsIgnoreCase("next"))
                {
                    System.out.print("Please type 'next': ");
                    input = scanner.nextLine();
                }
            }
        }


    }

    public void executePurchasable(int playerIndex) //Allows user to interact with the tile in which they landed on
    {
        if (board1.getBoardTile(players[playerIndex].getPosition()) instanceof Purchasable && !(board1.getBoardPurchasable(players[playerIndex].getPosition()) instanceof Billboard))
        {
            if (players[playerIndex].getMoney() >= board1.getBoardPurchasable(players[playerIndex].getPosition()).getCost())
            {
                System.out.println("You landed on " + board1.getBoardPurchasable(players[playerIndex].getPosition()).getName() + ". You may purchase one for $" + board1.getBoardPurchasable(players[playerIndex].getPosition()).getCost() + ". The tower value of this office asset is $" + board1.getBoardPurchasable(players[playerIndex].getPosition()).getValue() + ". Type 'purchase' to make this purchase. Type 'pass' if you do not want it. Typing 'pass' will proceed: ");
                input = scanner.nextLine();
                while (!(input.equalsIgnoreCase("purchase") || input.equalsIgnoreCase("pass")))
                {
                    System.out.print("Please type either 'purchase' or 'pass': ");
                    input = scanner.nextLine();
                }
                if (input.equalsIgnoreCase("purchase"))
                {
                    players[playerIndex].deductMoney(board1.getBoardPurchasable(players[playerIndex].getPosition()).getCost());
                    towers[playerIndex].addAsset(board1.getBoardPurchasable(players[playerIndex].getPosition()));
                    towers[playerIndex].updateTowerValue();
                    System.out.println("Successfully purchased " + board1.getBoardPurchasable(players[playerIndex].getPosition()).getName() + ". Your tower value is now $" + towers[playerIndex].getTowerValue() + ". Your balance is now $" + players[playerIndex].getMoney() + ". Type 'next' to proceed: ");
                    input = scanner.nextLine();
                    while (!input.equalsIgnoreCase("next"))
                    {
                        System.out.print("Please type 'next': ");
                        input = scanner.nextLine();
                    }
                }
            }
            else 
            {
                System.out.println("You landed on " + board1.getBoardPurchasable(players[playerIndex].getPosition()).getName() + ". You do not have enough funds to make this purchase!");
                System.out.println("Cost of This Purchase: $" + board1.getBoardPurchasable(players[playerIndex].getPosition()).getCost());   
                System.out.println("Your Balance: $" + players[playerIndex].getMoney());  
                System.out.println("Type 'next' to proceed: ");
                input = scanner.nextLine();
                while (!input.equalsIgnoreCase("next"))
                {
                    System.out.print("Please type 'next': ");
                    input = scanner.nextLine();
                }
            }
                
        }
        else if (board1.getBoardTile(players[playerIndex].getPosition()) instanceof Billboard)
        {
            if (board1.getBoardBillboard(players[playerIndex].getPosition()).getAvailability())
            {
                if (players[playerIndex].getMoney() >= board1.getBoardBillboard(players[playerIndex].getPosition()).getCost())
                {
                    System.out.println("You landed on " + board1.getBoardBillboard(players[playerIndex].getPosition()).getName() + ". This billboard is available! You may purchase it for $" + board1.getBoardBillboard(players[playerIndex].getPosition()).getCost() + ". The tower value of the billboard is $" + board1.getBoardBillboard(players[playerIndex].getPosition()).getValue() + ". Type 'purchase' to make this purchase. Type 'pass' if you do not want it. Typing 'pass' will proceed: ");
                    input = scanner.nextLine();
                    while (!(input.equalsIgnoreCase("purchase") || input.equalsIgnoreCase("pass")))
                    {
                        System.out.print("Please type either 'purchase' or 'pass': ");
                        input = scanner.nextLine();
                    }
                    if (input.equalsIgnoreCase("purchase"))
                    {
                        players[playerIndex].deductMoney(board1.getBoardBillboard(players[playerIndex].getPosition()).getCost());
                        towers[playerIndex].addAsset(board1.getBoardBillboard(players[playerIndex].getPosition()));
                        towers[playerIndex].updateTowerValue();
                        board1.getBoardBillboard(players[playerIndex].getPosition()).setOwner(players[playerIndex]);                          
                        board1.getBoardBillboard(players[playerIndex].getPosition()).setAvailability(false);
                        System.out.println("Successfully purchased " + board1.getBoardBillboard(players[playerIndex].getPosition()).getName() + ". Your tower value is now $" + towers[playerIndex].getTowerValue() + ". Your balance is now $" + players[playerIndex].getMoney() + ". Type 'next' to proceed: ");
                        input = scanner.nextLine();
                        while (!input.equalsIgnoreCase("next"))
                        {
                            System.out.print("Please type 'next': ");
                            input = scanner.nextLine();
                        }
                    }
                }
                else 
                {
                    System.out.println("You landed on " + board1.getBoardBillboard(players[playerIndex].getPosition()).getName() + ". You do not have enough funds to make this purchase!");
                    System.out.println("Cost of This Purchase: $" + board1.getBoardBillboard(players[playerIndex].getPosition()).getCost());   
                    System.out.println("Your Balance: $" + players[playerIndex].getMoney());  
                    System.out.println("Type 'next' to proceed: ");
                    input = scanner.nextLine();
                    while (!input.equalsIgnoreCase("next"))
                    {
                        System.out.print("Please type 'next': ");
                        input = scanner.nextLine();
                    }
                }
                
            }
            else 
            {
                if (!board1.getBoardBillboard(players[playerIndex].getPosition()).getOwner().equals(players[playerIndex]))
                {
                    int rivalIndex = board1.getBoardBillboard(players[playerIndex].getPosition()).getOwner().getIndex();
                    int rivalTowerValue = towers[rivalIndex].getTowerValue();
                    System.out.println("You landed on " + board1.getBoardBillboard(players[playerIndex].getPosition()).getName() + ". This billboard is owned by " + board1.getBoardBillboard(players[playerIndex].getPosition()).getOwner().getName() + ". You must may them the value of their tower, which is $" + rivalTowerValue + ". Type 'pay' to proceed: ");
                    input = scanner.nextLine();
                    while (!input.equalsIgnoreCase("pay"))
                    {
                        System.out.print("Please type 'pay': ");
                        input = scanner.nextLine();
                    }
                    if (players[playerIndex].getMoney() >= rivalTowerValue)
                    {
                        players[playerIndex].deductMoney(rivalTowerValue);
                        players[board1.getBoardBillboard(players[playerIndex].getPosition()).getOwner().getIndex()].addMoney(rivalTowerValue);
                        System.out.println("Successfully paid " + board1.getBoardBillboard(players[playerIndex].getPosition()).getOwner().getName() + " $" + rivalTowerValue + ". Your balance is now $" + players[playerIndex].getMoney() + ". Type 'next' to proceed: ");
                        input = scanner.nextLine();
                        while (!input.equalsIgnoreCase("next"))
                        {
                            System.out.print("Please type 'next': ");
                            input = scanner.nextLine();
                        }
                    }
                    else 
                    {
                        System.out.println("You do not have enough money to pay " + board1.getBoardBillboard(players[playerIndex].getPosition()).getOwner().getName() + ". You must give them your topmost billboard. Type 'give' to proceed: ");
                        input = scanner.nextLine();
                        while (!input.equalsIgnoreCase("give"))
                        {
                            System.out.print("Please type 'give': ");
                            input = scanner.nextLine();
                        }
                        if (towers[playerIndex].getTowerValue() > 0)
                        {
                            if (towers[playerIndex].getTopPurchasableAsset() instanceof Purchasable)
                                towers[rivalIndex].addAsset(towers[playerIndex].getTopPurchasableAsset()); 
                            else if (towers[playerIndex].getTopPurchasableAsset() instanceof Billboard)
                                towers[rivalIndex].addAsset(towers[playerIndex].getTopBillboardAsset()); 
                            towers[playerIndex].removeTopAsset();
                            if (towers[rivalIndex].getTopPurchasableAsset() instanceof Billboard)
                                towers[rivalIndex].getTopBillboardAsset().setOwner(players[rivalIndex]);
                            towers[playerIndex].updateTowerValue();
                            towers[rivalIndex].updateTowerValue();
                            System.out.println("Successfully gave " + towers[rivalIndex].getTopPurchasableAsset().getName() + " to " + players[rivalIndex].getName()+ ". Type 'next' to proceed: ");
                            input = scanner.nextLine();
                            while (!input.equalsIgnoreCase("next"))
                            {
                                System.out.print("Please type 'next': ");
                                input = scanner.nextLine();
                            }                  
                        }
                        else 
                        {
                            System.out.println("You don't have any assets in your tower. Type 'next' to proceed: "); 
                            input = scanner.nextLine();
                            while (!input.equalsIgnoreCase("next"))
                            {
                                System.out.print("Please type 'next': ");
                                input = scanner.nextLine();
                            } 
                        }
                    }
                }
                else 
                {
                    System.out.println("You landed on " + board1.getBoardBillboard(players[playerIndex].getPosition()).getName() + ". You own this billboard, so do nothing. Type 'next' to proceed: "); 
                    input = scanner.nextLine();
                    while (!input.equalsIgnoreCase("next"))
                    {
                        System.out.print("Please type 'next': ");
                        input = scanner.nextLine();
                    } 
                }
            }
        }
        else 
        {
            String boardTileName = board1.getBoardTile(players[playerIndex].getPosition()).getName();
            if (boardTileName.equals("Rival Tower Tax"))
            {
                taxablePlayers = new ArrayList<String>();
                boolean taxIsAvailable = false;
                for (int n = 0; n < numberOfPlayers; n++)
                {
                    if (towers[n].getTowerValue() > 0 && n != playerIndex)
                        taxIsAvailable = true;
                }
                if (taxIsAvailable)
                {
                    System.out.println("You landed on Tower Tax. You may choose to return any player's topmost asset to the board!");
                    System.out.println("Below are the topmost assets for each player that has one:");
                    for (int h = 0; h < numberOfPlayers; h++)
                    {
                        if (h != playerIndex)
                        {
                            if (towers[h].getTowerValue() > 0)
                            {
                                taxablePlayers.add(players[h].getName().toLowerCase());
                                System.out.println(players[h].getName() + ": " + towers[h].getTopBillboard().getName() + " - Value: $" + towers[h].getTopBillboard().getValue());
                            }
                        }
                    }
                    System.out.print("Type the name of the player who you'd like to tax: ");
                    input = scanner.nextLine();
                    while (!taxablePlayers.contains(input.toLowerCase()))
                    {
                        System.out.print("Please choose a valid player: ");
                        input = scanner.nextLine();
                    }
                    for (int l = 0; l < numberOfPlayers; l++)
                    {
                        if (players[l].getName().equalsIgnoreCase(input))
                        {
                            System.out.print("Successfully returned " + players[l].getName() + "'s " + towers[players[l].getIndex()].getTopPurchasableAsset().getName() + " to the board. Type 'next' to proceed: ");
                            if (towers[players[l].getIndex()].getTopPurchasableAsset() instanceof Billboard)
                            {
                                towers[players[l].getIndex()].getTopBillboardAsset().setAvailability(true);
                                towers[players[l].getIndex()].getTopBillboardAsset().setOwner(null);
                            }
                            towers[players[l].getIndex()].removeTopAsset();
                            towers[players[l].getIndex()].updateTowerValue();
                        }
                    }
                    input = scanner.nextLine();
                    while (!input.equalsIgnoreCase("next"))
                    {
                        System.out.print("Please type 'next': ");
                        input = scanner.nextLine();
                    }
                }
                else 
                {
                    System.out.println("You landed on Rival Tower Tax. Nobody has any assets, so do nothing. Type 'next' to proceed: ");
                    input = scanner.nextLine();
                    while (!input.equalsIgnoreCase("next"))
                    {
                        System.out.print("Please type 'next': ");
                        input = scanner.nextLine();
                    }
                }      
            }
            else if (boardTileName.equals("Chance Card")) // Check if the current tile is a "Chance Card"
            {
                boolean chanceCardsAvailable = false;
                for (int d = 0; d < chanceCards.getChanceCardLength(); d++)
                {
                    if (!chanceCards.isChanceCardUsed(d))
                        chanceCardsAvailable = true;
                }
                if (chanceCardsAvailable) //Chance card options 
                {
                    System.out.println("You landed on Chance Cards! Your chance card is: ");
                    Card currentCard = chanceCards.useChanceCard();
                    while(currentCard.isCardUsed())
                        currentCard = chanceCards.useChanceCard();
                    currentCard.updateCardUse();
                    if (currentCard.getCardName().equals("Get out of jail for free!"))
                    {
                        canGetOutOfJailFree[playerIndex] = true;
                        System.out.println(currentCard.getCardName() + " Use whenever you land in jail. Type 'next' to proceed: ");
                        input = scanner.nextLine();
                        while (!input.equalsIgnoreCase("next"))
                        {
                            System.out.print("Please type 'next': ");
                            input = scanner.nextLine();
                        }
                    }
                    else if (currentCard.getCardName().equals("Casino Night!"))
                    {
                        System.out.println(currentCard.getCardName() + " " + currentCard.getCardDescription());
                        System.out.println("Choose an opponent: ");
                        for(int o = 0; o < playerNames.size(); o++)
                        {
                            if (!playerNames.get(o).equals(playerNames.get(playerIndex)))
                            {
                                System.out.print(playerNames.get(o));
                                System.out.println(" - BALANCE: $" + players[o].getMoney());
                            }
                        }
                        input = scanner.nextLine();
                        String opponentName = input;
                        while (!playerNames.contains(input) && !playerNames.contains(playerNames.get(playerIndex)))
                        {
                            System.out.print("Please select a valid player: ");
                            input = scanner.nextLine();
                        }
                        int opponentIndex = playerNames.indexOf(opponentName);
                        System.out.println("You roll first! Type 'roll' to roll the dice: ");
                        input = scanner.nextLine();
                        while (!input.equalsIgnoreCase("roll"))
                        {
                            System.out.print("Roll the dice correctly! ");
                            input = scanner.nextLine();
                        }
                        int[] firstRollerDice = rollDice();
                        if (firstRollerDice[1] == 6)
                            firstRollerDice[2] = firstRollerDice[0];
                        System.out.println("Your combined dice value is " + firstRollerDice[2] + ".");
                        System.out.println("It is " + opponentName + "'s turn to roll! Type 'roll' to roll the dice: ");
                        input = scanner.nextLine();
                        while (!input.equalsIgnoreCase("roll"))
                        {
                            System.out.print("Roll the dice correctly! ");
                            input = scanner.nextLine();
                        }
                        int[] secondRollerDice = rollDice();
                        if (secondRollerDice[1] == 6)
                            secondRollerDice[2] = secondRollerDice[0];
                        System.out.println("Your combined dice value is " + secondRollerDice[2] + ".");
                        while (firstRollerDice == secondRollerDice)
                        {
                            System.out.println("You rolled the same combined dice value as your opponent! Type 'roll' to roll again: ");
                            input = scanner.nextLine();
                            while (!input.equalsIgnoreCase("roll"))
                            {
                                System.out.print("Roll the dice correctly! ");
                                input = scanner.nextLine();
                            }
                            secondRollerDice = rollDice();
                            if (secondRollerDice[1] == 6)
                                secondRollerDice[2] = secondRollerDice[0];
                        }
                        if (firstRollerDice[2] > secondRollerDice[2])
                        {
                            System.out.print(playerNames.get(playerIndex) + " rolled higher! Collect $200 from the bank. ");
                            players[playerIndex].addMoney(200);
                            System.out.print("Your current balance is $" + players[playerIndex].getMoney() + ". ");
                        }
                        else 
                        {
                            System.out.print(playerNames.get(opponentIndex) + " rolled higher! Collect $200 from the bank.");
                            players[opponentIndex].addMoney(200);
                            System.out.print("Your current balance is $" + players[opponentIndex].getMoney() + ". ");
                        }
                        System.out.println("Type 'next' to proceed: ");
                        input = scanner.nextLine();
                        while (!input.equalsIgnoreCase("next"))
                        {
                            System.out.print("Please type 'next': ");
                            input = scanner.nextLine();
                        }
                    }
                    else if (currentCard.getCardName().equals("Successful advertising campaign!"))
                    {
                        System.out.println(currentCard.getCardName() + " " + currentCard.getCardDescription());
                        players[playerIndex].addMoney(100);
                        System.out.println("Your current balance is $" + players[playerIndex].getMoney() + ". Type 'next' to proceed: ");
                        input = scanner.nextLine();
                        while (!input.equalsIgnoreCase("next"))
                        {
                            System.out.print("Please type 'next': ");
                            input = scanner.nextLine();
                        }  
                    }
                    else if (currentCard.getCardName().equals("It's your empire's first birthday!"))
                    {
                        System.out.println(currentCard.getCardName() + " " + currentCard.getCardDescription());
                        boolean paymentsAvailable = false;
                        for (int c = 0; c < numberOfPlayers; c++)
                        {
                            if (c != playerIndex && players[c].getMoney() >= 50)
                            {
                                paymentsAvailable = true;
                                players[c].deductMoney(50);
                                players[playerIndex].addMoney(50);
                            }
                        }
                        if (paymentsAvailable)
                            System.out.print("Your current balance is $" + players[playerIndex].getMoney() + ". ");
                        else 
                        {
                           System.out.println("Nobody has enough funds to pay you! ");
                        }
                        System.out.println("Type 'next' to proceed: ");
                        input = scanner.nextLine();
                        while (!input.equalsIgnoreCase("next"))
                        {
                            System.out.print("Please type 'next': ");
                            input = scanner.nextLine();
                        }
                    }
                    else if (currentCard.getCardName().equals("Go to jail for fraud!") || currentCard.getCardName().equals("Go to Jail!"))
                    {
                        System.out.println(currentCard.getCardName() + " " + currentCard.getCardDescription());
                        players[playerIndex].goToJail();
                        System.out.println("Type 'next' to proceed: ");
                        input = scanner.nextLine();
                        while (!input.equalsIgnoreCase("next"))
                        {
                            System.out.print("Please type 'next': ");
                            input = scanner.nextLine();
                        }
                    }
                    else if (currentCard.getCardName().equals("Solar power bonus!"))
                    {
                        System.out.println(currentCard.getCardName() + " " + currentCard.getCardDescription());
                        towers[playerIndex].addAsset(board1.getBoardPurchasable(12));
                        towers[playerIndex].updateTowerValue();
                        System.out.println("Your tower value is now $" + towers[playerIndex].getTowerValue() + ". Type 'next' to proceed: ");
                        input = scanner.nextLine();
                        while (!input.equalsIgnoreCase("next"))
                        {
                            System.out.print("Please type 'next': ");
                            input = scanner.nextLine();
                        }
                    }
                    else if (currentCard.getCardName().equals("Speed ahead!"))
                    {
                        System.out.print(currentCard.getCardName() + " " + currentCard.getCardDescription());
                        System.out.println(" Type 'move' to move: ");
                        input = scanner.nextLine();
                        while (!input.equalsIgnoreCase("move"))
                        {
                            System.out.print("Please type 'move': ");
                            input = scanner.nextLine();
                        }
                        move(playerIndex, 5);
                        executePurchasable(playerIndex);
                    }
                    else if (currentCard.getCardName().equals("Insider trading fine!"))
                    {
                        System.out.println(currentCard.getCardName() + " " + currentCard.getCardDescription());
                        if (players[playerIndex].getMoney() >= 200)
                        {
                            players[playerIndex].deductMoney(200);
                            System.out.print("Your current balance is $" + players[playerIndex].getMoney() + ". ");
                        }
                        else 
                            System.out.print("You do not have $200! ");
                        System.out.println("Type 'next' to proceed: ");
                        input = scanner.nextLine();
                        while (!input.equalsIgnoreCase("next"))
                        {
                            System.out.print("Please type 'next': ");
                            input = scanner.nextLine();
                        }
                    }
                    else if (currentCard.getCardName().equals("Water bonus!"))
                    {
                        System.out.println(currentCard.getCardName() + " " + currentCard.getCardDescription());
                        towers[playerIndex].addAsset(board1.getBoardPurchasable(30));
                        towers[playerIndex].updateTowerValue();
                        System.out.println("Your tower value is now $" + towers[playerIndex].getTowerValue() + ". Type 'next' to proceed: ");
                        input = scanner.nextLine();
                        while (!input.equalsIgnoreCase("next"))
                        {
                            System.out.print("Please type 'next': ");
                            input = scanner.nextLine();
                        }
                    }
                    else if (currentCard.getCardName().equals("Launch your website!"))
                    {
                        System.out.println(currentCard.getCardName() + " " + currentCard.getCardDescription());
                        players[playerIndex].addMoney(300);
                        System.out.println("Your current balance is $" + players[playerIndex].getMoney() + ". Type 'next' to proceed: ");
                        input = scanner.nextLine();
                        while (!input.equalsIgnoreCase("next"))
                        {
                            System.out.print("Please type 'next': ");
                            input = scanner.nextLine();
                        }  
                    }
                    else if (currentCard.getCardName().equals("Profits soar!"))
                    {
                        System.out.println(currentCard.getCardName() + " " + currentCard.getCardDescription());
                        move(playerIndex, board1.getBoardLength() - players[playerIndex].getPosition());
                        System.out.println("Type 'next' to proceed: ");
                        input = scanner.nextLine();
                        while (!input.equalsIgnoreCase("next"))
                        {
                            System.out.print("Please type 'next': ");
                            input = scanner.nextLine();
                        }
                    }
                    else
                    {
                        System.out.println(currentCard.getCardName() + " " + currentCard.getCardDescription());
                        players[playerIndex].addMoney(400);
                        System.out.println("Your current balance is $" + players[playerIndex].getMoney() + ". Type 'next' to proceed: ");
                        input = scanner.nextLine();
                        while (!input.equalsIgnoreCase("next"))
                        {
                            System.out.print("Please type 'next': ");
                            input = scanner.nextLine();
                        }  
                    }
                }
                else 
                {
                    System.out.println("You landed on Chance Cards, but there are no cards left! Type 'next' to proceed: ");  
                    input = scanner.nextLine();
                    while (!input.equalsIgnoreCase("next"))
                    {
                        System.out.print("Please type 'next': ");
                        input = scanner.nextLine();
                    } 
                }
            }
            else if (boardTileName.equals("Jail"))
            {
                System.out.println("You landed in jail, but don't worry! You are just visiting. Type 'next' to proceed: ");
                input = scanner.nextLine();
                while (!input.equalsIgnoreCase("next"))
                {
                    System.out.print("Please type 'next': ");
                    input = scanner.nextLine();
                }
            }
            else if (boardTileName.equals("Free Parking"))
            {
                System.out.println("You landed on Free Parking. Type 'move' to pay $100 to move to any space. Type 'pass' to do nothing (skipping turn): ");
                input = scanner.nextLine();
                while (!(input.equalsIgnoreCase("move") || input.equalsIgnoreCase("pass")))
                {
                    System.out.print("Please type a valid input: ");
                    input = scanner.nextLine();
                }
                if (input.equalsIgnoreCase("move"))
                {
                    System.out.println("FULL BOARD:");
                    for (int g = 0; g < board1.getBoardLength(); g++)
                    {
                        if (board1.getBoardTile(g) instanceof Purchasable && !(board1.getBoardTile(g) instanceof Billboard))
                        {
                            System.out.println("---------------------");
                            System.out.println("POSITION: " + g);
                            System.out.println("NAME: " + board1.getBoardPurchasable(g).getName());
                            System.out.println("COST: $" + board1.getBoardPurchasable(g).getCost());
                            System.out.println("VALUE: $" + board1.getBoardPurchasable(g).getValue());
                        }
                        else if (board1.getBoardTile(g) instanceof Billboard)
                        {
                            System.out.println("---------------------");
                            System.out.println("POSITION: " + g);
                            System.out.println("NAME: " + board1.getBoardBillboard(g).getName());
                            System.out.println("COST: $" + board1.getBoardBillboard(g).getCost());
                            System.out.println("VALUE: $" + board1.getBoardBillboard(g).getValue());
                            if (board1.getBoardBillboard(g).getOwner() != null)
                                System.out.println("OWNER: " + board1.getBoardBillboard(g).getOwner().getName());
                            else
                                System.out.println("OWNER: None");
                        }
                        else 
                        {
                            System.out.println("---------------------");
                            System.out.println("POSITION: " + g);
                            System.out.println("NAME: " + board1.getBoardTile(g).getName());
                        }
                    }
                    System.out.print("Type the position number of the tile on the board you want travel to: ");
                    int chosenPosition = scanner.nextInt();
                    while ((chosenPosition < 0) || (chosenPosition >= board1.getBoardLength()))
                    {
                        System.out.print("Please select a valid position number: ");
                        chosenPosition = scanner.nextInt();
                    }
                    while (chosenPosition == 18)
                    {
                        System.out.print("You can't go to Free Parking again! Please select another position number: ");
                        chosenPosition = scanner.nextInt();
                    }
                    scanner.nextLine();
                    if (chosenPosition < 18)
                        move(playerIndex, (18 + chosenPosition));
                    else 
                        move(playerIndex, (chosenPosition - 18));
                    executePurchasable(playerIndex);
                }
            }
            else if (boardTileName.equals("Go To Jail"))
            {
                players[playerIndex].goToJail();
                System.out.println("You must go to jail! You will be given options to get out of jail on your next turn. Type 'next' to proceed: ");
                input = scanner.nextLine();
                while (!input.equalsIgnoreCase("next"))
                {
                    System.out.print("Please type 'next': ");
                    input = scanner.nextLine();
                }
            }
            else if (boardTileName.equals("Tower Tax"))
            {
                if (towers[playerIndex].getTowerValue() > 0)
                {
                    System.out.println("You landed on Tower Tax. You must return your topmost asset to the board! Type 'return' to proceed: ");
                    while (!input.equalsIgnoreCase("return"))
                    {
                        System.out.print("Please type 'return': ");
                        input = scanner.nextLine();
                    }
                    System.out.print("Successfully returned " + towers[playerIndex].getTopPurchasableAsset().getName() + " to the board. ");
                    if (towers[playerIndex].getTopPurchasableAsset() instanceof Billboard)
                    {
                        towers[playerIndex].getTopBillboardAsset().setAvailability(true);
                        towers[playerIndex].getTopBillboardAsset().setOwner(null);
                    }
                    towers[playerIndex].removeTopAsset();
                    towers[playerIndex].updateTowerValue();
                    System.out.println("Your tower value is now $" + towers[playerIndex].getTowerValue() + ". Type 'next' to proceed: ");
                }
                else 
                    System.out.println("You landed on Tower Tax. You have no assets, so do nothing. Type 'next' to proceed: ");
                input = scanner.nextLine();
                while (!input.equalsIgnoreCase("next"))
                {
                    System.out.print("Please type 'next': ");
                    input = scanner.nextLine();
                }
            }
            else 
            {
                System.out.println("Since you landed on GO, do nothing. Type 'next' to proceed: ");
                input = scanner.nextLine();
                while (!input.equalsIgnoreCase("next"))
                {
                    System.out.print("Please type 'next': ");
                    input = scanner.nextLine();
                }
            }
        }
    }

    
    public void runGame() //Runs the game 
    {
        int currentTurn = 0;
        while(!playerWon)
        {
            int doubleCounter = 0;
            boolean choseRoll = false;
            boolean usedGetOutOfJailFree = false;
            System.out.print("It is " + players[currentTurn].getName() + "'s turn! ");
            int[] dice = rollDice();
            if (players[currentTurn].isInJail()) //Checks if player is in jail 
            {
                System.out.print("You are in jail! ");
                if (canGetOutOfJailFree[currentTurn])
                {
                    System.out.println("You have a get out of jail free card! Type 'use' to use it. Type 'skip' to try to get out without using it: ");
                    input = scanner.nextLine();
                    while (!(input.equalsIgnoreCase("use") || input.equalsIgnoreCase("skip"))) //Validate input 
                    {
                        System.out.print("Please type 'use' or 'skip' to proceed: ");
                        input = scanner.nextLine();
                    }
                    if (input.equalsIgnoreCase("use"))
                    {
                        players[currentTurn].leaveJail();
                        canGetOutOfJailFree[currentTurn] = false;
                        usedGetOutOfJailFree = true;
                        System.out.println("Successfully used get out of jail free card to leave jail.");
                    }
                }
                if (!usedGetOutOfJailFree) //Cheack id the player still needs to roll or pay to get out of jail 
                {
                    if (players[currentTurn].getMoney() >= 100)
                    {
                        System.out.println("You must either pay $100 or roll a double to get out! Type 'pay' or 'roll' to proceed: ");
                        input = scanner.nextLine();
                        while (!(input.equalsIgnoreCase("pay") || input.equalsIgnoreCase("roll")))
                        {
                            System.out.print("Please type 'pay' or 'roll' to proceed: ");
                            input = scanner.nextLine();
                        }
                        if (input.equalsIgnoreCase("pay"))
                        {
                            players[currentTurn].deductMoney(100);
                            players[currentTurn].leaveJail();
                            System.out.println("Successfully paid $100 to leave jail. Your balance is now $" + players[currentTurn].getMoney() + ".");
                        }
                        else 
                            choseRoll = true;
                    }
                    if ((players[currentTurn].getMoney() < 100) || choseRoll) //Check if the player has insufficient funds to roll 
                    {
                        if (players[currentTurn].getMoney() < 100)
                        {
                            System.out.println("You do not have the $100 required to pay your way out of jail. You must roll a double to get out! Type 'roll' to proceed: ");
                            input = scanner.nextLine();
                            while (!input.equalsIgnoreCase("roll"))
                            {
                                System.out.print("Please type 'roll': ");
                                input = scanner.nextLine();
                            }
                        }
                        if (dice[0] == dice[1] && dice[1] != 6)
                        {
                            players[currentTurn].leaveJail();
                            jailDoubleCounter[currentTurn] = 0;
                            System.out.println("You rolled a double " + dice[0] + " and have been permitted to leave jail.");
                        }
                        else 
                        {
                            if (dice[1] == 6)
                                System.out.print("You rolled a " + dice[0] + " and a sneaky swap. ");
                            else
                                System.out.print("You rolled a " + dice[0] + " and a " + dice[1] + ". ");
                            jailDoubleCounter[currentTurn]++;
                            if (jailDoubleCounter[currentTurn] < 3)
                            {
                                System.out.print("Type 'next' to proceed: ");
                                input = scanner.nextLine();
                                while (!input.equalsIgnoreCase("next"))
                                {
                                    System.out.print("Please type 'next': ");
                                    input = scanner.nextLine();
                                }
                            }
                            else 
                            {
                                players[currentTurn].leaveJail();
                                jailDoubleCounter[currentTurn] = 0;
                                System.out.print("You failed to roll a double 3 times in a row. Pay $50 to get out. Type 'pay' to proceed: ");   
                                input = scanner.nextLine();
                                while (!input.equalsIgnoreCase("pay"))
                                {
                                    System.out.print("Please type 'pay' to proceed: ");
                                    input = scanner.nextLine();
                                } 
                                players[currentTurn].deductMoney(50);
                                players[currentTurn].leaveJail();
                                System.out.println("Successfully paid $50 to leave jail. Your balance is now $" + players[currentTurn].getMoney() + ".");
                            }
                        }
                    }
                }
            }
            if (!players[currentTurn].isInJail()) 
            {
                System.out.print("Roll the dice by typing 'roll': ");
                input = scanner.nextLine();
                while (!input.equalsIgnoreCase("roll"))
                {
                    System.out.print("Roll the dice correctly! ");
                    input = scanner.nextLine();
                }
                while (dice[0] == dice[1] && dice[1] != 6 && !players[currentTurn].isInJail())
                {
                    doubleCounter++;
                    if (doubleCounter < 3) //Check if the player rolled two consecutive zeros 
                    {
                        System.out.println("You rolled a double " + dice[0] + "! Move " + dice[2] + " tiles forward and roll again!");
                        move(currentTurn, dice[2]);
                        if (doubleCounter == 2)
                            System.out.println("You already rolled 2 doubles. If you roll a third, you will go to Jail!");
                        executePurchasable(currentTurn);
                        if (!players[currentTurn].isInJail())
                        {
                            System.out.print("Since you rolled a double, you can roll again. Type 'roll' to roll the dice again: ");
                            input = scanner.nextLine();
                            while (!input.equalsIgnoreCase("roll"))
                            {
                                System.out.print("Roll the dice correctly! ");
                                input = scanner.nextLine();
                            }
                            dice = rollDice();
                        }
                    }
                    else 
                    {
                        System.out.println("You rolled a double " + dice[0] + ". You rolled 3 doubles in a row and must go to jail!");
                        players[currentTurn].goToJail();  
                    }   
                }
                if (!players[currentTurn].isInJail())
                {
                    if (dice[1] != 6)
                    {
                        System.out.println("You rolled a " + dice[0] + " and a " + dice[1] + "! Move " + dice[2] + " tiles forward.");  
                        move(currentTurn, dice[2]);
                        executePurchasable(currentTurn);
                    }
                    else 
                    {
                        availableSwappers = new ArrayList<String>();
                        boolean swapIsAvailable = false;
                        for (int k = 0; k < numberOfPlayers; k++)
                        {
                            if (towers[k].getTowerValue() > 0 && k != currentTurn)
                                swapIsAvailable = true;
                        }
                        if (swapIsAvailable && towers[currentTurn].getTowerValue() > 0)
                        {
                            System.out.println("You rolled a " + dice[0] + " and a sneaky swap! You may either move " + dice[0] + " tiles forward or swap the top tile on your billboard with another players'.");
                            System.out.println("Below are the top billboards for each player that has one:");
                            for (int i = 0; i < numberOfPlayers; i++)
                            {
                                if (i != currentTurn)
                                {
                                    if (towers[i].getTowerValue() > 0)
                                    {
                                        availableSwappers.add(players[i].getName().toLowerCase());
                                        System.out.println(players[i].getName() + ": " + towers[i].getTopBillboard().getName() + " - Value: $" + towers[i].getTopBillboard().getValue());
                                    }
                                }
                            }
                            if (towers[currentTurn].getTopPurchasableAsset() instanceof Billboard)
                                System.out.println("Your top asset: " + towers[currentTurn].getTopBillboard().getName() + " - Value: $" + towers[currentTurn].getTopBillboard().getValue());
                            else 
                                System.out.println("Your top asset: " + towers[currentTurn].getTopPurchasableAsset().getName() + " - Value: $" + towers[currentTurn].getTopPurchasableAsset().getValue());
                            System.out.print("The space you would land on if you move: ");
                            if (players[currentTurn].getPosition() + dice[0] > board1.getBoardLength() - 1)
                            {
                                System.out.println(board1.getBoardTile(players[currentTurn].getPosition() + dice[0] - board1.getBoardLength()).getName());
                            }
                            else 
                            {
                                System.out.println(board1.getBoardTile(players[currentTurn].getPosition() + dice[0]).getName());    
                            }
                            
                            if (board1.getBoardTile(players[currentTurn].getPosition() + dice[0]) instanceof Billboard)
                            {
                                if (board1.getBoardBillboard(players[currentTurn].getPosition() + dice[0]).getOwner() == null)
                                    System.out.println(" - AVAILABLE!");
                                else
                                    System.out.println(" - Owned by " + board1.getBoardBillboard(players[currentTurn].getPosition() + dice[0]).getOwner().getName());   
                            }
                            else 
                                System.out.println();
                            System.out.println("Type 'move' or 'swap' to proceed: ");
                            input = scanner.nextLine();
                            while (!(input.equalsIgnoreCase("move") || input.equalsIgnoreCase("swap")))
                            {
                                System.out.print("Please type a valid input: ");
                                input = scanner.nextLine();
                            }
                            if (input.equalsIgnoreCase("move"))
                            {
                                move(currentTurn, dice[0]);
                                executePurchasable(currentTurn);
                            }
                            else 
                                swap(currentTurn);
                        }
                        else 
                        {
                            if (!swapIsAvailable)
                                System.out.println("You rolled a " + dice[0] + " and a sneaky swap! Nobody has any tower assets, so no assets can be swapped. Move " + dice[0] + " tiles forward.");
                            else
                                System.out.println("You rolled a " + dice[0] + " and a sneaky swap! You do not own any assets, so no assets can be swapped. Move " + dice[0] + " tiles forward.");
                            move(currentTurn, dice[0]);
                            executePurchasable(currentTurn);
                        }
                    }
                }
            }
            currentTurn++;
            if (currentTurn >= numberOfPlayers)
                currentTurn = 0;
            for (int m = 0; m < numberOfPlayers; m++)
            {
                if(towers[m].getTowerValue() >= 800)
                {
                    winner = players[m].getName();
                    playerWon = true;
                }
            }
        }
        runTimer(1);
        System.out.println("---------------------");
        System.out.println(winner + " has filled up their tower ($800 or more in assets) and won the game!");
        runTimer(1);
        System.out.println("---------------------");
        runTimer(1);
        System.out.print("Type 'play again' to start over or 'quit' to exit the game: ");
        input = scanner.nextLine();
        while (!(input.equalsIgnoreCase("play again") || input.equalsIgnoreCase("quit")))
        {
            System.out.print("Please type a valid input: ");
            input = scanner.nextLine();
        }
        if(input.equalsIgnoreCase("play again"))
            gameIntro();
        else 
            System.out.println("Thank you for playing!");
    }

}