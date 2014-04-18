package SudokuScreen.src.cs2114.sudoku;

import android.widget.CheckBox;
import java.util.Arrays;
import android.widget.TextView;
import sofia.graphics.Color;
import sofia.app.ShapeScreen;

/**
 * The sudoku screen that displays the sudoku board and other GUI elements.
 *
 * @author Kyle Bentley (ttkyle)
 * @version 2013.12.07
 */
public class SudokuScreen
    extends ShapeScreen
{
    private Tiles       tile;
    private Tiles[][]   grid;
    private int         mode;
    private SudokuModel model;
    private TextView    infoLabel;
    private CheckBox    easy;


    /**
     * Initialize the GUI with a default 8x8 maze.
     */
    public void initialize()
    {

        grid = new Tiles[9][9];

        float dimension = Math.min(this.getHeight(), this.getWidth()) / 9;

        for (int i = 0; i < 9; i++)
        {
            for (int n = 0; n < 9; n++)
            {
                tile =
                    new Tiles(
                        dimension * i,
                        dimension * n,
                        dimension * (i + 1),
                        dimension * (n + 1));

                tile.setColor(Color.black);
                tile.setFillColor(Color.white);

                grid[i][n] = tile;
                add(grid[i][n]);
            }
        }

        model = new SudokuModel();
        generate();
    }


    /**
     * Puts the GUI in mode to put ones down
     */
    public void buttonOneClicked()
    {
        mode = 1;
    }


    /**
     * Puts the GUI in mode to put twos down
     */
    public void buttonTwoClicked()
    {
        mode = 2;
    }


    /**
     * Puts the GUI in mode to put threes down
     */
    public void buttonThreeClicked()
    {
        mode = 3;
    }


    /**
     * Puts the GUI in mode to put fours down
     */
    public void buttonFourClicked()
    {
        mode = 4;
    }


    /**
     * Puts the GUI in mode to put fives down
     */
    public void buttonFiveClicked()
    {
        mode = 5;
    }


    /**
     * Puts the GUI in mode to put sixes down
     */
    public void buttonSixClicked()
    {
        mode = 6;
    }


    /**
     * Puts the GUI in mode to put sevens down
     */
    public void buttonSevenClicked()
    {
        mode = 7;
    }


    /**
     * Puts the GUI in mode to put eights down
     */
    public void buttonEightClicked()
    {
        mode = 8;
    }


    /**
     * Puts the GUI in mode to put nines down
     */
    public void buttonNineClicked()
    {
        mode = 9;
    }


    /**
     * Takes a look at the 2-D array in the model that holds a valid sudoku
     * board and sets the image = to the number in the array. Also locks the
     * cell that it cannot be overwritten by the user.
     */
    public void generate()
    {
        float dimension = Math.min(this.getHeight(), this.getWidth()) / 9;
        infoLabel.setText("");

        for (int i = 0; i < 9; i++)
        {
            for (int n = 0; n < 9; n++)
            {

                tile =
                    new Tiles(
                        dimension * i,
                        dimension * n,
                        dimension * (i + 1),
                        dimension * (n + 1));

                grid[i][n] = tile;

                if (model.getCell(i, n) == 1)
                {
                    tile.setImage("one");
                    tile.setLocked(true);
                }
                else if (model.getCell(i, n) == 2)
                {
                    tile.setImage("two");
                    tile.setLocked(true);
                }
                else if (model.getCell(i, n) == 3)
                {
                    tile.setImage("three");
                    tile.setLocked(true);
                }
                else if (model.getCell(i, n) == 4)
                {
                    tile.setImage("four");
                    tile.setLocked(true);
                }
                else if (model.getCell(i, n) == 5)
                {
                    tile.setImage("five");
                    tile.setLocked(true);
                }
                else if (model.getCell(i, n) == 6)
                {
                    tile.setImage("six");
                    tile.setLocked(true);
                }
                else if (model.getCell(i, n) == 7)
                {
                    tile.setImage("seven");
                    tile.setLocked(true);
                }
                else if (model.getCell(i, n) == 8)
                {
                    tile.setImage("eight");
                    tile.setLocked(true);
                }

                else if (model.getCell(i, n) == 9)
                {
                    tile.setImage("nine");
                    tile.setLocked(true);
                }

                add(grid[i][n]);
            }
        }
    }


    /**
     * When the create button is clicked it makes a new model and displays the
     * new board.
     */
    public void createClicked()
    {
        model = new SudokuModel();
        generate();
    }


    /**
     * Tells the user if they solved the board correctly.
     */
    public void correctClicked()
    {
        try
        {
            model.solve(0, 0, model.board());
        }
        catch (Exception e)
        {
            //
        }

        if (Arrays.deepEquals(model.board(), model.getGenerated()))
        {
            infoLabel.setText("Correct");
        }
        else
        {
            infoLabel.setText("Not correct");
        }
    }


    /**
     * When solve is clicked it the method looks at what has not been filled in
     * and fills it in with blue numbers and updates all the arrays it needs to.
     */
    public void solveClicked()
    {
        infoLabel.setText("");

        for (int i = 0; i < 9; i++)
        {
            for (int n = 0; n < 9; n++)
            {
                if (model.getBoard(i, n) == 1 && !grid[i][n].getLocked())
                {
                    grid[i][n].setLocked(true);
                    grid[i][n].setImage("oneblue");
                    model.setGenerated(i, n, 1);
                }
                else if (model.getBoard(i, n) == 2 && !grid[i][n].getLocked())
                {
                    grid[i][n].setLocked(true);
                    grid[i][n].setImage("twoblue");
                    model.setGenerated(i, n, 2);
                }
                else if (model.getBoard(i, n) == 3 && !grid[i][n].getLocked())
                {
                    grid[i][n].setLocked(true);
                    grid[i][n].setImage("threeblue");
                    model.setGenerated(i, n, 3);
                }
                else if (model.getBoard(i, n) == 4 && !grid[i][n].getLocked())
                {
                    grid[i][n].setLocked(true);
                    grid[i][n].setImage("fourblue");
                    model.setGenerated(i, n, 4);
                }
                else if (model.getBoard(i, n) == 5 && !grid[i][n].getLocked())
                {
                    grid[i][n].setLocked(true);
                    grid[i][n].setImage("fiveblue");
                    model.setGenerated(i, n, 5);
                }
                else if (model.getBoard(i, n) == 6 && !grid[i][n].getLocked())
                {
                    grid[i][n].setLocked(true);
                    grid[i][n].setImage("sixblue");
                    model.setGenerated(i, n, 6);
                }
                else if (model.getBoard(i, n) == 7 && !grid[i][n].getLocked())
                {
                    grid[i][n].setLocked(true);
                    grid[i][n].setImage("sevenblue");
                    model.setGenerated(i, n, 7);
                }
                else if (model.getBoard(i, n) == 8 && !grid[i][n].getLocked())
                {
                    grid[i][n].setLocked(true);
                    grid[i][n].setImage("eightblue");
                    model.setGenerated(i, n, 8);
                }

                else if (model.getBoard(i, n) == 9 && !grid[i][n].getLocked())
                {
                    grid[i][n].setLocked(true);
                    grid[i][n].setImage("nineblue");
                    model.setGenerated(i, n, 9);
                }
            }
        }
    }


    /**
     * When the user touches the screen on a cell the method checks the locked
     * status and the mode of the GUI to know which number tile to put down.
     * Also updates the 2-D array in the model.
     *
     * @param x
     *            the x coordinate of the touch
     * @param y
     *            the y coordinate of the touch
     */
    public void onTouchDown(float x, float y)
    {
        float dimension = Math.min(this.getHeight(), this.getWidth()) / 9;
        int newX = (int)((int)(y) / dimension);
        int newY = (int)((int)(x) / dimension);

        if (mode == 1 && !grid[newY][newX].getLocked())
        {


            if (!model.check3x3(newY, newX, mode, model.getGenerated())
                && easy.isChecked()
                && !model.checkColumn(newX, mode, model.getGenerated())
                && !model.checkRow(newY, mode, model.getGenerated()))
            {
                grid[newY][newX].setImage("onered");
            }

            grid[newY][newX].setImage("oneblue");
            model.setGenerated(newY, newX, 1);
        }

        if (mode == 2 && !grid[newY][newX].getLocked())
        {
            grid[newY][newX].setImage("twoblue");
            model.setGenerated(newY, newX, 2);
        }

        if (mode == 3 && !grid[newY][newX].getLocked())
        {
            grid[newY][newX].setImage("threeblue");
            model.setGenerated(newY, newX, 3);
        }

        if (mode == 4 && !grid[newY][newX].getLocked())
        {
            grid[newY][newX].setImage("fourblue");
            model.setGenerated(newY, newX, 4);
        }

        if (mode == 5 && !grid[newY][newX].getLocked())
        {
            grid[newY][newX].setImage("fiveblue");
            model.setGenerated(newY, newX, 5);
        }

        if (mode == 6 && !grid[newY][newX].getLocked())
        {
            grid[newY][newX].setImage("sixblue");
            model.setGenerated(newY, newX, 6);
        }

        if (mode == 7 && !grid[newY][newX].getLocked())
        {
            grid[newY][newX].setImage("sevenblue");
            model.setGenerated(newY, newX, 7);
        }

        if (mode == 8 && !grid[newY][newX].getLocked())
        {
            grid[newY][newX].setImage("eightblue");
            model.setGenerated(newY, newX, 8);
        }

        if (mode == 9 && !grid[newY][newX].getLocked())
        {
            grid[newY][newX].setImage("nineblue");
            model.setGenerated(newY, newX, 9);
        }
    }
}
