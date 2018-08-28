/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictaktoeobjectoriented;

/**
 *
 * @author student
 */
public class XOPlayer 
{
    private String m_strName;
    private XOStatistics obStatistics;
    private XOMove obMove;
    private String strMarker;
   
    
    public XOPlayer(String strName, String strNum)
    {
        m_strName = strName;
        strMarker = setMarker(strNum);
        obMove = new XOMove();
        obStatistics = new XOStatistics();
    }
    
    private String setMarker(String strNum)
    {
        String myMarker;
        if("1".equals(strNum))
        {
            myMarker = "[X] ";
        }
        else
        {
            myMarker = "[O] ";
        }
        return myMarker;
    }
    public String getMarker()
    {
        return strMarker;
    }
    
    public String getName()
    {
        return m_strName;
    }  
    public int[] makeMove(XOBoard obBoard)
    {
       return obMove.promptPlayerToMove(m_strName, obBoard);
    }
    public int[] getMoveCellNumber()
    {
        return obMove.getMove();
    }
    public XOPlayer nextPlayer(XOPlayer obActiveOne, XOPlayer obPlayer1, XOPlayer obPlayer2)
    {
        if (obActiveOne == obPlayer1)
        {
            obActiveOne = obPlayer2;
        }
        else if (obActiveOne == obPlayer2)
        {
            obActiveOne = obPlayer1;
        }
        else
        {
            System.out.println("Smth went wrong...");
        }
        return obActiveOne;
    }
    
    public void updateStats(XOPlayer obWinner, XOPlayer obPlayer, boolean bTie)
    {
        if(bTie)
        {
            incrementTies();
        }
        else
        {
            if(obPlayer == obWinner)
            {
                incrementWins();   
            }
            else
            {
                incrementLosses();
            }
        }  
    }
    public void displayStats(String strName)
    {
        System.out.println(strName + "'s Statistics for "+ getGamesPlayed() + (getGamesPlayed()>1?" games played: ":" game played: "));
        System.out.println("Wins: " + getWins() + " Losses: " + getLosses() + " Ties: " + getTies());
        System.out.println(" ");
    }
    
    // Statistics wrapper interface
    private int getGamesPlayed()
    {
        return obStatistics.getNumberOfGames();
    }
    private int getWins()
    {
        return obStatistics.getWinCount();
    }
    private int getLosses()
    {
        return obStatistics.getLossCount();
    }
    private int getTies()
    {
        return obStatistics.getTieCount();
    }
    private void incrementWins()
    {
        obStatistics.registerWin();
    }
    private void incrementLosses()
    {
        obStatistics.registerLoss();
    }
    private void incrementTies()
    {
        obStatistics.registerTie();
    }          
}
