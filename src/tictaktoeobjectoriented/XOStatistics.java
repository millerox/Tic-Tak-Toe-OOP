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
public class XOStatistics
{
    private int m_nWinCount;
    private int m_nLossCount;
    private int m_nTieCount;
    
    public XOStatistics()
    {
        m_nWinCount = 0;
        m_nLossCount = 0;
        m_nTieCount = 0;
    }
    
    // Member data setter methods
    public void registerWin()
    {
        ++m_nWinCount;
    }
    public void registerLoss()
    {
        ++m_nLossCount;
    }
    public void registerTie()
    {
        ++m_nTieCount;
    }
    
    // Member data getter methods
    public int getWinCount()
    {
        return m_nWinCount;
    }
    public int getLossCount()
    {
        return m_nLossCount;
    }
    public int getTieCount()
    {
        return m_nTieCount;
    }
    public int getNumberOfGames()
    {
        return (getWinCount() + getLossCount() + getTieCount());
    }
}
