/*
Name: Michael Li
Date: January 12, 2018
Class code: ICS 2O3 - 02
Program name: Trap
An Object that represents a generic trap that harms the protagonist.
*/

import java.awt.*;
import java.math.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;
import hsa.*;

public abstract class Trap
{
    private Graphics g1; // The Graphics that will draw stuff
    private int x; // The x-coordinate of the top right corner of the trap
    private int y; // The y-coordinate of the top right corner of the trap
    private Player p; // The player that the trap will harm
    private boolean gone; // Whether or not the trap is gone

    private final int WIDTH = 50; // The fatness of the trap
    private final int SHOW_DIST = 80; // The maximum distance at which the trap is visible


    public Trap (Graphics g, int x1, int y1)  // Constructs a custom trap
    {
	// Assign variables
	g1 = g;
	x = x1;
	y = y1;
	gone = false;
    }


    public void draw (int adjust) throws DeathException // Helper method to draw the trap on the floor
    {
	// Will be visible only if player is near the trap
	// Determined by x-coordinates of objects
	if (p.getX () <= x + WIDTH + SHOW_DIST && p.getX () + p.getWidth () >= x - SHOW_DIST)
	{
	    g1.setColor (new Color (180, 50, 0));
	    g1.fillRect (x - adjust, y, WIDTH, 5);
	}
    } // draw method


    public void setPlayer (Player p1)  // Helper method to receive the player that will be harmed
    {
	p = p1;
    } // setPlayer method


    public int getX ()  // Helper method to access the x-coordinate of the trap
    {
	return x;
    } // getX method


    public int getY ()  // Helper method to access the y-coordinate of the trap
    {
	return y;
    } // getY method


    public int getWidth ()  // Helper method to access the fatness of the trap
    {
	return WIDTH;
    } // getWidth method


    public Player getPlayer ()  // Helper method to access the player assigned to the trap
    {
	return p;
    } // getPlayer method


    public boolean isTouching ()  // Helper method to check whether or not the trap is touching its player
    {
	// Check x-coordinate
	if (p.getX () <= x + WIDTH && p.getX () + p.getWidth () >= x)
	{
	    // Check y-coordinate
	    if (p.getY () + p.getHeight () == y)
	    {
		// The trap is touching its player
		return true;
	    }
	}

	// The trap is not touching its player
	return false;
    } // isTouching method


    public void harm () throws DeathException // Helper method to harm the player
    {
    } // harm method


    public void despawn ()  // Helper method to make the trap despawn
    {
	gone = true;
    } // despawn method


    public boolean isGone ()  // Helper method to check whether or not the trap has despawned
    {
	return gone;
    } // isGone method
} // Trap class
