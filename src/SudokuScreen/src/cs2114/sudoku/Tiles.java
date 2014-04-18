package SudokuScreen.src.cs2114.sudoku;

import sofia.graphics.Color;
import sofia.graphics.RectangleShape;


/**
 *  The tile class used to set the background to white with black grid-lines.
 *  Also lets cells be locked via the locked boolean.
 *
 *  @author Kyle Bentley (ttkyle)
 *  @version 2013.12.07
 */
public class Tiles extends RectangleShape
{

    private boolean locked = false;
    /**
     * Create a new CoverTile object.
     * @param left left coordinate
     * @param top top coordinate
     * @param right right coordinate
     * @param bottom bottom coordinate
     */
    public Tiles(float left, float top, float right, float bottom)
    {
        super(left, top, right, bottom);
        this.setColor(Color.black);
        this.setFillColor(Color.white);
    }

    /**
     * Lets the program to get if the cell is locked or not
     * @return true if locked, false otherwise
     */
    public boolean getLocked()
    {
        return locked;
    }

    /**
     * Lets the program lock initial cells generated by the model
     * @param value either true to lock or false to unlock cell
     */
    public void setLocked(Boolean value)
    {
        locked = value;
    }
}