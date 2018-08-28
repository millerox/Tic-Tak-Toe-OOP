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
public class XOMove 
{
    private int[] nArrayInput = new int [2]; // Creating an array to store player's input;
    
    
    public int[] promptPlayerToMove(String strName, XOBoard obBoard)
    {
        boolean bCorrectInput = false;  // flag to determine if the input is coorect.
        String strRow;
        String strColumn;
        while(!bCorrectInput)
        {
            bCorrectInput = true;
            //Getting the user's input
            Scanner obScan = new Scanner(System.in);
            System.out.println("\n" + strName + ",to make a move enter a cell number in the format 'Row/Column' : ");
            String strInput = obScan.nextLine();
            
            // Validate the input for general mistakes:
            // Converting char to String 
            try
            {
                strRow = "" + strInput.charAt(0);
                strColumn = "" + strInput.charAt(2);
            }
            catch (StringIndexOutOfBoundsException e)
            {
                System.out.println
                ("WRONG FORMAT!Use a separator to enter a cell number, like 'Row/Column', where / is a separator");
                bCorrectInput = false;
                continue;
            }
            // Checking for NumberFormatExcepition
            try
            {
                nArrayInput[0] = Integer.parseInt(strRow);// Row#
                nArrayInput[1] = Integer.parseInt(strColumn) ;// Column #
            }
            catch (NumberFormatException e)
            {
                System.out.println
                ("WRONG FORMAT!Enter a cell number in the format 'Row/Column', where Row and Column are numbers. "
                        + "Other characters will not be accepted. ");
                bCorrectInput = false; 
                continue;
            }
            // Validate the user's input for the field size and make sure the cell is not occupied
            bCorrectInput = obBoard.validateInput(nArrayInput);
            
            //obBoard.updateTheBoard(nArrayInput, strName);
        }
        return nArrayInput; 
    }
    
    public int[] getMove()
    {
        return nArrayInput;
    }
}
