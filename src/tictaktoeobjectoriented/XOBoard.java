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
public class XOBoard
{
    private String[][] strBoard; // declarign array representing the board
    
    // Declaring variablies responsible for the field size
    private int nRows = 3;
    private int nColumns = 3;
    
    public XOBoard()
    { 
        nRows += 1; //creating extra row for horizontal labels
        nColumns +=1; //creating extra row for vertical labels
        
        strBoard = new String[nRows][nColumns];
        
        // creating horizontal cell labels
        for(int nIndex=0, nInnerIndex=1; nInnerIndex < nColumns; nInnerIndex++)
        {
            strBoard[nIndex][nInnerIndex] = "  " + nInnerIndex + " "; 
        }
        // creating vertical labels
        for(int nIndex=1, nIndexZero=0; nIndex < nRows; nIndex++)
        {
            strBoard[nIndex][nIndexZero] = "" + nIndex;
        }
        //creating the empty field 
        for(int nIndex=1; nIndex < nRows; nIndex++) //Populate an array
        {
            for(int nIndex1=1; nIndex1 < nColumns; nIndex1++)
            { 
                strBoard[nIndex][nIndex1] = "[ ] ";
            }
        }
    }
    
    public int getBoardSize()
    {
        int nBoardSize = (nRows-1) * (nColumns-1);
        return nBoardSize;
    }
    public void printTheBoard()
    {
          // Printing the array representing the field, excluding element at index [0][0]
        for(int nIndex=0; nIndex < strBoard.length; nIndex++) 
        { 
            if(nIndex==0)
            {
                for(int nIndex1=1; nIndex1 < strBoard.length; nIndex1++)
                {
                    System.out.print(strBoard[nIndex][nIndex1]);
                }
            }
            else
            {
                for(int nIndex1=0; nIndex1 < strBoard[0].length; nIndex1++)
                {
                    System.out.print(strBoard[nIndex][nIndex1]);
                }
            }
            System.out.println(" ");
        }
    }
    
    public boolean validateInput(int nArrayInput[])
    {
        boolean bCorrectInput = true;
        if(nArrayInput[0]< 1 || nArrayInput[0] > nRows || nArrayInput[1] < 1 || nArrayInput[1] > nColumns)
        {
            System.out.println
            (   "You are out of the field size" + 
                "\nEnter a cell number in the format 'Row/Column', where Row is # from 1-"+
                 nRows + " and Column is # from 1-" + nColumns);
            
            bCorrectInput = false; 
        }
        try
        {
            if("[X] ".equals(strBoard[nArrayInput[0]][nArrayInput[1]]) || "[O] ".equals(strBoard[nArrayInput[0]][nArrayInput[1]]))
            {
                System.out.println("This cell is already occupied. Try Again");
                bCorrectInput = false;
            }
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            
        }
        return bCorrectInput;
    }
    
    public void updateTheBoard(int nArrayInput[], String strMarker)
    {
        strBoard[nArrayInput[0]][nArrayInput[1]] = strMarker;
    }
    
    public void cleanTheBoard()
    {
        //creating the empty field 
        for(int nIndex=1; nIndex < nRows; nIndex++) //Populate an array
        {
            for(int nIndex1=1; nIndex1 < nColumns; nIndex1++)
            { 
                strBoard[nIndex][nIndex1] = "[ ] ";
            }
        }
    }
    
    public boolean hasWinner(int[] nArrCellNumber)
    {
        boolean bWinnerFound = false; // bWinnerFound tells if there is a match
        
        //Declaring Index variables
        int nInnerIndex;
        int nOuterIndex;
        int nIndex1, nIndex2;
        int nHorizontalIndex = nArrCellNumber[0];
        int nVerticalIndex = nArrCellNumber[1];
        
        //Cheking for horizontal match 
        for(nInnerIndex=1; nInnerIndex < strBoard[nHorizontalIndex].length-1; nInnerIndex++)
        {
            bWinnerFound = ((strBoard[nHorizontalIndex][nInnerIndex+1].equals(strBoard[nHorizontalIndex][nInnerIndex]))
                                                                &&
           ((strBoard[nHorizontalIndex][nInnerIndex].equals("[X] "))||(strBoard[nHorizontalIndex][nInnerIndex].equals("[O] "))));
            if(!bWinnerFound)
            {
                break;
            }
        }
        if(!bWinnerFound) // if horizontal match is NOT found
        {
            //Cheking for vertical match
            for(nOuterIndex=1; nOuterIndex < strBoard.length-1; nOuterIndex++)
            {
                bWinnerFound = ((strBoard[nOuterIndex+1][nVerticalIndex].equals(strBoard[nOuterIndex][nVerticalIndex]))
                                                              &&
            ((strBoard[nOuterIndex][nVerticalIndex].equals("[X] "))||((strBoard[nOuterIndex][nVerticalIndex].equals("[O] ")))));
                if(!bWinnerFound)
                {
                    break;
                }
            }
        }
        if(!bWinnerFound) // if vertical match is NOT found
        {
           //Cheking for left-to-right diagonal match
            for(nIndex1=1,nIndex2=1; nIndex1 < strBoard.length-1; nIndex1++, nIndex2++)
            {
               bWinnerFound = (strBoard[nIndex1][nIndex2].equals(strBoard[nIndex1+1][nIndex2+1]))
                                                &&
                ((strBoard[nIndex1][nIndex2].equals("[X] ")) || (strBoard[nIndex1][nIndex2].equals("[O] ")));
                if(!bWinnerFound)
                {
                        break;
                }
            }
        }
        if(!bWinnerFound)// if left-to-right diagonal match is NOT found
        {
            //Cheking for right-to-left diagonal match
            for(nIndex1=1,nIndex2=strBoard.length-1; nIndex1 < strBoard.length-1; nIndex1++, nIndex2--)
            {
                bWinnerFound = (strBoard[nIndex1][nIndex2].equals(strBoard[nIndex1+1][nIndex2-1]))
                                                    &&
                ((strBoard[nIndex1][nIndex2].equals("[X] ")) || (strBoard[nIndex1][nIndex2].equals("[O] ")));
                if(!bWinnerFound)
                {
                    break;
                }
            }
        }
        return bWinnerFound;
    }
}
