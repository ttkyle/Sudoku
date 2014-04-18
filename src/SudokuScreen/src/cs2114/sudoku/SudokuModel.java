package SudokuScreen.src.cs2114.sudoku;


import java.util.Collections;
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

/**
 * The Sudoku model that will generate and solve the puzzles.
 *
 * @author Kyle Bentley (ttkyle)
 * @version 2013.11.28
 */
public class SudokuModel
{
    private int[][] generated;

    private int[][] board;
    private int[][] boardBackwards;
    private Random  random = new Random();


    /**
     * Construct the 2-D array to hold the values for the board.
     */
    public SudokuModel()
    {
        board = new int[9][9];
        boardBackwards = new int[9][9];

        generated = new int[9][9];

        try
        {
            generate(0, 0);
        }
        catch (Exception e)
        {
            //
        }

        for (int row = 0; row < 9; row++)
        {
            for (int column = 0; column < 9; column++)
            {
                boardBackwards[row][column] = generated[row][column];
                board[row][column] = generated[row][column];
            }
        }

        coverCells(0);

        while (!isUnique())
        {
            board = new int[9][9];
            boardBackwards = new int[9][9];

            generated = new int[9][9];

            try
            {
                generate(0, 0);
            }
            catch (Exception e)
            {
                //
            }

            for (int row = 0; row < 9; row++)
            {
                for (int column = 0; column < 9; column++)
                {
                    boardBackwards[row][column] = generated[row][column];
                    board[row][column] = generated[row][column];
                }
            }
            coverCells(0);
        }
    }

    /**
     * Allows the program to change the value of the generated 2-D array
     * @param x the x coordinate
     * @param y the y coordinate
     * @param value the value to insert
     */
    public void setGenerated(int x, int y, int value)
    {
        generated[y][x] = value;
    }


    /**
     * Check if a number can be inserted in this row (no duplicate numbers)
     *
     * @param row
     *            the row number you want to check
     * @param num
     *            the number you want to check against
     * @param aBoard
     *            the board to check
     * @return true if the number can be inserted false otherwise
     */
    public boolean checkRow(int row, int num, int[][] aBoard)
    {
        for (int column = 0; column < 9; column++)
        {
            if (aBoard[row][column] == num)
            {
                return false;
            }
        }
        return true;
    }


    /**
     * Check if a number can be inserted in this column (no duplicate numbers)
     *
     * @param column the column number you want to check against
     * @param num the number you want to check against
     * @return true if the number can be inserted false otherwise
     * @param aBoard the board to check
     */
    public boolean checkColumn(int column, int num, int[][] aBoard)
    {
        for (int row = 0; row < 9; row++)
        {
            if (aBoard[row][column] == num)
            {
                return false;
            }
        }
        return true;
    }


    /**
     * Check if a number can be inserted in the 3x3 box it's located in
     *
     * @param row the row the number is in
     * @param column the column the number is in
     * @param num the number to check against
     * @param aBoard the board to check
     * @return true if the number can go in this square false otherwise
     */
    public boolean check3x3(int row, int column, int num, int[][] aBoard)
    {
        for (int i = 0; i < 3; i++)
        {
            for (int n = 0; n < 3; n++)
            {
                if (aBoard[((row / 3) * 3) + i][((column / 3) * 3) + n] == num)
                {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * Check if the number is valid to place
     * @param row the row the number is in
     * @param column the column the number is in
     * @param num the number to check
     * @param board1 the board to check
     * @return true if can be placed, false otherwise
     */
    public boolean canPlace(int row, int column, int num, int[][] board1)
    {
        return checkRow(row, num, board1) && checkColumn(column, num, board1)
            && check3x3(row, column, num, board1);
    }


    /**
     * Solve the puzzle with recursion
     *
     * @param row the row we're on
     * @param column the column we're on
     * @return true if there is a solution, false if not
     * @param aBoard the board to check
     */
    public boolean solve(int row, int column, int[][] aBoard)
    {
        if (row <= 9)
        {
            if (aBoard[row][column] != 0)
            {
                if (column < 8)
                {
                    solve(row, column + 1, aBoard);
                }
                else
                {
                    solve(row + 1, 0, aBoard);
                }
            }
            else
            {
                for (int num = 1; num < 10; num++)
                {
                    if (canPlace(row, column, num, aBoard))
                    {
                        aBoard[row][column] = num;

                        if (column < 8)
                        {
                            solve(row, column + 1, aBoard);
                        }
                        else
                        {
                            solve(row + 1, 0, aBoard);
                        }

                        aBoard[row][column] = 0;
                    }
                }
                return true;
            }
        }
        return false;
    }


    /**
     * Solve the puzzle with recursion
     *
     * @param row the row we're on
     * @param column the column we're on
     * @return true if there is a solution, false if not
     */
    public boolean solveBackwards(int row, int column)
    {
        if (row >= -1)
        {
            if (boardBackwards[row][column] != 0)
            {
                if (column > 0)
                {
                    solveBackwards(row, column - 1);
                }
                else
                {
                    solveBackwards(row - 1, 8);
                }
            }
            else
            {
                for (int num = 9; num > 0; num--)
                {
                    if (canPlace(row, column, num, boardBackwards))
                    {
                        boardBackwards[row][column] = num;

                        if (column > 0)
                        {
                            solveBackwards(row, column - 1);
                        }
                        else
                        {
                            solveBackwards(row - 1, 8);
                        }

                        boardBackwards[row][column] = 0;
                    }
                }
                return true;
            }
        }
        return false;
    }


    /**
     * Print out the board in an easy to read fashion.
     *
     * @return the solved board
     */
    public String toString()
    {
        String s = "";
        for (int i = 0; i < 9; i++)
        {
            for (int n = 0; n < 9; n++)
            {
                if (n < 8)
                {
                    s = s + board[i][n] + " ";
                }
                else
                {
                    s = s + board[i][n] + "\n";
                }
            }
        }
        return s;
    }


    /**
     * Print out the board in an easy to read fashion.
     *
     * @return the solved board
     */
    public String toStringBackwards()
    {
        String s = "";
        for (int i = 0; i < 9; i++)
        {
            for (int n = 0; n < 9; n++)
            {
                if (n < 8)
                {
                    s = s + boardBackwards[i][n] + " ";
                }
                else
                {
                    s = s + boardBackwards[i][n] + "\n";
                }
            }
        }
        return s;
    }


    /**
     * Print out the board in an easy to read fashion.
     *
     * @return the solved board
     */
    public String toStringGenerated()
    {
        String s = "";
        for (int i = 0; i < 9; i++)
        {
            for (int n = 0; n < 9; n++)
            {
                if (n < 8)
                {
                    s = s + generated[i][n] + " ";
                }
                else
                {
                    s = s + generated[i][n] + "\n";
                }
            }
        }
        return s;
    }


    /**
     * Check if the board can be solved two different ways
     *
     * @return true if the board has a unique solution
     */
    public boolean isUnique()
    {

        for (int ro = 0; ro < 9; ro++)
        {
            for (int column = 0; column < 9; column++)
            {
                boardBackwards[ro][column] = generated[ro][column];
                board[ro][column] = generated[ro][column];
            }
        }
        try
        {
            solve(0, 0, board);
        }
        catch (Exception e)
        {
            //
        }

        try
        {
            solveBackwards(8, 8);
        }
        catch (Exception e)
        {
            //
        }

        return Arrays.deepEquals(board, boardBackwards);
    }


    /**
     * Generate a board
     *
     * @param row the row we're on
     * @param column the column we're on
     * @return true if generated, false if not
     */
    public boolean generate(int row, int column)
    {

        if (row <= 9)
        {

            if (generated[row][column] != 0)
            {
                if (column < 8)
                {
                    solve(row, column + 1, generated);
                }
                else
                {
                    solve(row + 1, 0, generated);
                }

            }
            else
            {
                Integer[] random1 = generateRandomNumbers();
                for (int i = 0; i < 9; i++)
                {

                    if (canPlace(row, column, random1[i], generated))
                    {
                        generated[row][column] = random1[i];

                        if (column < 8)
                        {
                            generate(row, column + 1);
                        }
                        else
                        {
                            generate(row + 1, 0);
                        }

                        generated[row][column] = 0;
                    }
                }
                return true;
            }
        }

        return false;
    }


    /**
     * Generate random numbers
     *
     * @return random numbers
     */
    private Integer[] generateRandomNumbers()
    {
        ArrayList<Integer> random1 = new ArrayList<Integer>();
        for (int i = 0; i < 9; i++)
        {
            random1.add(i + 1);
        }
        Collections.shuffle(random1);

        return random1.toArray(new Integer[9]);
    }


    /**
     * Returns the generated 2-D array
     * @return the 2-d array
     */
    public int[][] getGenerated()
    {
        return generated;
    }

    /**
     * Returns the board 2-D array
     * @return the 2-d array
     */
    public int[][] board()
    {
        return board;
    }

    /**
     * Gets cell values in generated
     * @param x the x coordinate
     * @param y the y coordinate
     * @return the value of the cell
     */
    public int getCell(int x, int y)
    {
        return generated[y][x];
    }

    /**
     * Gets cell values in board
     * @param x the x coordinate
     * @param y the y coordinate
     * @return the value of the cell
     */
    public int getBoard(int x, int y)
    {
        return board[y][x];
    }

    /**
     * Generates a board
     * @param row the row to start on
     * @return true if a board is generated, false otherwise
     */
    public boolean coverCells(int row)
    {

        int ranNum = 0;

        if (row > 8 )
        {
            return coverCells(0);
        }

        if (numZeroTotal() > 30)
        {
            return true;
        }

        else
        {
            for (int r = 0; r < 1000; r++)
            {
                ranNum = random.nextInt(9);

                if (generated[row][ranNum] != 0 && numZeroRow(row) < 4
                    && numColumnZero(ranNum) < 4)
                {
                    int numberToBeReplaced = generated[row][ranNum];
                    board[row][ranNum] = 0;
                    boardBackwards[row][ranNum] = 0;

                    if (isUnique())
                    {
                        generated[row][ranNum] = 0;

                        for (int ro = 0; ro < 9; ro++)
                        {
                            for (int column = 0; column < 9; column++)
                            {
                                boardBackwards[ro][column] =
                                    generated[ro][column];
                                board[ro][column] = generated[ro][column];
                            }
                        }
                        coverCells(row + 1);
                    }
                    else
                    {
                        board[row][ranNum] = numberToBeReplaced;
                        boardBackwards[row][ranNum] = numberToBeReplaced;
                        generated[row][ranNum] = numberToBeReplaced;
                    }
                }
            }
        }
        return false;
    }


    /**
     * Gets the number of zeroes in a row
     * @param row the row to start on
     * @return the number of zeroes
     */
    public int numZeroRow(int row)
    {
        int numZero = 0;

        for (int column = 0; column < 9; column++)
        {
            if (generated[row][column] == 0)
            {
                numZero++;
            }
        }
        return numZero++;
    }


    /**
     * Gets the number of zeroes in a column
     * @param column the row to start on
     * @return the number of zeroes
     */
    public int numColumnZero(int column)
    {
        int numZero = 0;

        for (int row = 0; row < 9; row++)
        {
            if (generated[row][column] == 0)
            {
                numZero++;
            }
        }
        return numZero++;
    }


    /**
     * The total number of zeroes
     * @return the number of zeroes
     */
    public int numZeroTotal()
    {
        int numZero = 0;

        for (int row = 0; row < 9; row++)
        {
            for (int column = 0; column < 9; column++)
            {
                if (generated[row][column] == 0)
                {
                    numZero++;
                }
            }
        }
        return numZero;
    }
}
