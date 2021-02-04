/*
Name: Michael Li
Date: December 29, 2017
Class code: ICS 2O3 - 02
Program name: TutorialEnemy2
A weak enemy that appears in the tutorial and can shield itself.
*/

import java.awt.*;
import java.math.*;
import java.util.*;
import hsa.*;

public class TutorialEnemy2 extends Enemy
{
    private Graphics g1; // The Graphics that will draw stuff
    private int scrollNumber; // The number of the instructions scroll that the player will receive
    public TutorialEnemy2 (Graphics g, int x1, int y1, int n1)  // Constructs a custom TutorialEnemy2
    {
	super (g, "TutorialEnemy2.png", "TutorialEnemy3.png", x1, y1, 3, 50, 0);

	// Assign variables
	g1 = g;
	scrollNumber = n1;
    }


    public ArrayList die ()  // Helper method that activates when the enemy dies
    {
	// Declare variables
	ArrayList drop = new ArrayList ();
	String msg = "";

	// Select scroll message corresponding to the scroll number
	switch (scrollNumber)
	{
	    case 0:
		msg = "Tougher enemies drop more money than weaker enemies.";
		msg += "\nThey also have a higher chance of dropping items.";

		// Add items to demonstrate drops from tougher enemies
		drop.add (new Money (g1, 200));
		drop.add (new Mushroom (g1));
		break;
	}

	// Create a scroll with the selected message
	drop.add (new Scroll (g1, msg));

	// Drop the items
	return drop;
    }
} // TutorialEnemy2 class
