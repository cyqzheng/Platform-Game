/*
Name: Michael Li
Date: January 19, 2018
Class code: ICS 2O3 - 02
Program name: Boss
The boss that appears at the end of Level 3 and spawns Spirits.
*/

import java.awt.*;
import java.math.*;
import java.util.*;
import hsa.*;

public class Boss extends Enemy
{
    private static Graphics g1;
    public Boss (Graphics g, int x1, int y1)  // Constructs a Boss
    {
	super (g, "Boss1.png", "Boss2.png", x1, y1, 10, 400, 0);

	// Assign variables
	g1 = g;
    }


    public Spirit spawned ()  // Helper method to spawn a random Spirit
    {
	if (Math.random () < 0.005) // 0.5% chance of spawning a Spirit
	{
	    // Select a random Spirit to spawn
	    int r = (int) (Math.random () * 5);
	    switch (r)
	    {
		case 0:
		    return new SpiritOfSpears (g1, getLeft () + 30, getY () + 30);
		case 1:
		    return new SpiritOfFears (g1, getLeft () + 30, getY () + 30);
		case 2:
		    return new SpiritOfYears (g1, getLeft () + 30, getY () + 30);
		case 3:
		    return new SpiritOfEars (g1, getLeft () + 30, getY () + 30);
		case 4:
		    return new SpiritOfTears (g1, getLeft () + 30, getY () + 30);
	    }
	}
	// Return nothing if not spawning
	return null;
    } // spawn method


    public ArrayList die ()  // Helper method that activates when the boss dies
    {
	// Declare variables
	ArrayList drop = new ArrayList ();

	// Add a Bridge Potion to tell the program that the dead enemy is a boss
	drop.add (new BridgePotion (g1));

	// "Drop" the Bridge Potion
	return drop;
    } // die method
} // Boss class
