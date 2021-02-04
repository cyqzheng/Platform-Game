/*
Name: Michael Li
Date: January 22, 2018
Class code: ICS 2O3 - 02
Program name: Weapon
An item that represents a generic weapon that the protagonist uses.
*/

import java.awt.*;
import java.math.*;
import java.util.*;
import hsa.*;

public abstract class Weapon extends Item
{
    private int x; // The x-coordinate of the top left corner of the weapon
    private int y; // The y-coordinate of the top left corner of the weapon
    private boolean left; // Whether or not the weapon is thrown to the left

    private final int SPEED = 10; // The speed of wheat and shurikens
    private final int RADIUS = 15; // The radius of wheat and shurikens

    public Weapon (Graphics g1, String imgName)  // Constructs a custom Weapon
    {
	super (g1, imgName);
    }


    public void set (int x1, int y1, boolean left1)  // Helper method to set the weapon's information before using
    {
	// Assign variables
	x = x1;
	y = y1;
	left = left1;
    }


    public int getX ()  // Helper method to determine the x-coordinate of the weapon
    {
	return x;
    } // getX method


    public int getY ()  // Helper method to determine the y-coordinate of the weapon
    {
	return y;
    } // getY method


    public boolean getLeft ()  // Helper method to determine whether or not the weapon moves to the left
    {
	return left;
    } // getLeft method


    public boolean isTouching (Enemy e)  // Helper method that determines whether or not the weapon is touching the enemy
    {
	// Assume a circular weapon and a rectangular enemy.
	// Calculate coordinates of the centre of the weapon
	double centreX = x + RADIUS;
	double centreY = y + RADIUS;

	// Declare other required variables
	double dist, checkX, checkY, deltaX, deltaY;

	// Check top left corner
	checkX = e.getLeft ();
	checkY = e.getY ();
	deltaX = centreX - checkX;
	deltaY = centreY - checkY;
	dist = Math.sqrt (deltaX * deltaX + deltaY * deltaY);
	if (dist <= RADIUS) // If the weapon is touching the top left corner
	{
	    return true;
	}

	// Check top right corner
	checkX = e.getRight ();
	checkY = e.getY ();
	deltaX = centreX - checkX;
	deltaY = centreY - checkY;
	dist = Math.sqrt (deltaX * deltaX + deltaY * deltaY);
	if (dist <= RADIUS) // If the weapon is touching the top right corner
	{
	    return true;
	}

	// Check bottom left corner
	checkX = e.getLeft ();
	checkY = e.getY () + e.getHeight ();
	deltaX = centreX - checkX;
	deltaY = centreY - checkY;
	dist = Math.sqrt (deltaX * deltaX + deltaY * deltaY);
	if (dist <= RADIUS) // If the weapon is touching the bottom left corner
	{
	    return true;
	}

	// Check bottom right corner
	checkX = e.getRight ();
	checkY = e.getY () + e.getHeight ();
	deltaX = centreX - checkX;
	deltaY = centreY - checkY;
	dist = Math.sqrt (deltaX * deltaX + deltaY * deltaY);
	if (dist <= RADIUS) // If the weapon is touching the bottom right corner
	{
	    return true;
	}

	// Check left side
	checkX = e.getLeft ();
	if (centreX + RADIUS >= checkX && centreX - RADIUS <= checkX)
	{
	    if (centreY >= e.getY () && centreY <= e.getY () + e.getHeight ())
	    {
		return true;
	    }
	}

	// Check upper side
	checkY = e.getY ();
	if (centreY + RADIUS >= checkY && centreY - RADIUS <= checkY)
	{
	    if (centreX >= e.getLeft () && centreX <= e.getLeft () + e.getHeight ())
	    {
		return true;
	    }
	}

	// Check right side
	checkX = e.getRight ();
	if (centreX + RADIUS >= checkX && centreX - RADIUS <= checkX)
	{
	    if (centreY >= e.getY () && centreY <= e.getY () + e.getHeight ())
	    {
		return true;
	    }
	}

	// Check lower side
	checkY = e.getY () + e.getHeight ();
	if (centreY + RADIUS >= checkY && centreY - RADIUS <= checkY)
	{
	    if (centreX >= e.getLeft () && centreX <= e.getLeft () + e.getHeight ())
	    {
		return true;
	    }
	}

	// Check interior
	if (centreX >= e.getLeft ())
	{
	    if (centreY >= e.getY ())
	    {
		if (centreX <= e.getRight ())
		{
		    if (centreY <= e.getY () + e.getHeight ())
		    {
			// The centre of the weapon is inside the enemy, so return true
			return true;
		    }
		}
	    }
	}

	// Everything is checked, the weapon is not touching the enemy
	return false;
    } // isTouching method


    public void move ()  // Helper method to move the weapon
    {
	// Make decisions based on the direction of the weapon
	if (left)
	{
	    x -= SPEED;
	}
	else
	{
	    x += SPEED;
	}
    } // move method
} // Weapon class
