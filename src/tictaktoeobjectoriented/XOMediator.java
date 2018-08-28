/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictaktoeobjectoriented;

import java.util.Scanner;

/**
 *
 * @author student
 */
public class XOMediator 
{
    private XOPlayer obPlayer1;
    private XOPlayer obPlayer2;
    private XOPlayer obActivePlayer;
    private XOBoard obBoard;
    
    
    public XOMediator()
    {
        obPlayer1 = new XOPlayer(collectName(" first player: "), "1");
        obPlayer2 = new XOPlayer(collectName(" second player: "), "2");
        obBoard  = new XOBoard();
    }
    
    public void playGame()
    { 
        boolean bPlayAgain = true;
        welcomePlayers();
        
        while(bPlayAgain)
        {
            int nMoveCount = obBoard.getBoardSize();
            //boolean bTie = nMoveCount > 0;
            obActivePlayer = obPlayer1;

            while((!obBoard.hasWinner(obActivePlayer.getMoveCellNumber())) && nMoveCount > 0)
            {
                obActivePlayer.makeMove(obBoard);
                obBoard.updateTheBoard(obActivePlayer.getMoveCellNumber(), obActivePlayer.getMarker());
                obBoard.printTheBoard();
                
                if(!obBoard.hasWinner(obActivePlayer.getMoveCellNumber()))
                {
                    obActivePlayer = obActivePlayer.nextPlayer(obActivePlayer, obPlayer1, obPlayer2);
                }
                nMoveCount--;
            }
            if(nMoveCount <= 0)
            {
               System.out.println(" ");
               System.out.println("You played a tie!");
            }
            else
            {
               System.out.println(" ");
               System.out.println(obActivePlayer.getName() + ", Congratulations! You are THE WINNER! ");   
            }
            obPlayer1.updateStats(obActivePlayer, obPlayer1, nMoveCount <= 0);
            obPlayer2.updateStats(obActivePlayer, obPlayer2, nMoveCount <= 0);
            obBoard.cleanTheBoard();
            
            bPlayAgain = wouldYouLikeToPlayAgain();
        }
        System.out.println("\n Thanks for the game! Take a look at your statistics:");
        obPlayer1.displayStats(obPlayer1.getName());
        obPlayer2.displayStats(obPlayer2.getName());
    }
    
    private boolean wouldYouLikeToPlayAgain()
    {
        boolean bPlayAgain = true;
        
        Scanner obScanner = new Scanner(System.in);
        System.out.println("Would you like to play again? Enter 'Y' to continue or 0 to quit: ");
        String strInput = obScanner.nextLine();
        
        // Validate the input. If after 4 attempts, input is wrong, quit the game
        int nCount;
        for(nCount=0; (nCount < 3) && !(strInput.equals("0")|| strInput.equalsIgnoreCase("y")); nCount++)
        {
            System.out.println("Wrong Input. Enter 'Y' to continue or 0(zero) to quit: ");
            strInput = obScanner.nextLine();
        }
        if(strInput.equals("0") || nCount == 3)
        {
            bPlayAgain = false;
        }
        return bPlayAgain;          
    }
    
    private String collectName(String strName)
    {
       Scanner obScan = new Scanner(System.in);
       System.out.println("Enter a name of the " + strName);
       String strInput = obScan.nextLine();
       return strInput;
    }
    
    private void welcomePlayers()
    {
        // Create and print the initial Field with labels
        System.out.println("\nHi " + obPlayer1.getName()+ " and " + obPlayer2.getName() + ". Here is the field you will play in: "); 
        obBoard.printTheBoard();
    }
}
