/*
Name: Michael Li
Date: January 15, 2018
Class code: ICS 2O3 - 02
Program name: Chest
An Object that represents a treasure chest that contains an item.
*/

import java.awt.*;
import java.math.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;
import hsa.*;

public class Chest
{
    private Graphics g1; // The Graphics that will draw stuff
    private Console c; // The input/output console
    private Question quest; // The question asked by the treasure chest
    private Image img; // The image of the treasure chest
    private Image keyImg; // The image of the keys that the player uses
    private Item item; // The item given by the treasure chest
    private int x; // The x-coordinate of the top left corner of the chest
    private int y; // The y-coordinate of the top left corner of the chest
    private boolean diff; // Whether or not the question is hard

    private final int NUM_EASY = 5; // The number of medium-quality items
    private final int NUM_HARD = 4; // The number of high-quality items
    private final int HEIGHT = 50; // The height of the treasure chest
    private final int WIDTH = 50; // The fatness of the treasure chest

    public Chest (Console c1, Graphics g, int x1, int y1, boolean hard)  // Constructs a treasure chest
    {
	// Load images
	try
	{
	    // Select chest based on difficulty of question
	    if (hard)
	    {
		img = ImageIO.read (new File ("HardChest.png"));
	    }
	    else
	    {
		img = ImageIO.read (new File ("EasyChest.png"));
	    }
	}
	catch (IOException ex)
	{
	}
	try
	{
	    keyImg = ImageIO.read (new File ("Key.png"));
	}
	catch (IOException ex)
	{
	}

	// Assign variables
	c = c1;
	g1 = g;
	x = x1;
	y = y1;
	diff = hard;
	quest = new Question (c, g1, hard);

	// Select item based on difficulty of question
	if (hard)
	{
	    int r = (int) (Math.random () * NUM_HARD);
	    switch (r)
	    {
		case 0:
		    item = new BridgePotion (g1);
		    break;
		case 1:
		    item = new MaxHealthIncreaser (g1);
		    break;
		case 2:
		    item = new Bitcoin (g1);
		    break;
		case 3:
		    item = new VampireSpell (g1);
		    break;
	    }
	}
	else
	{
	    int r = (int) (Math.random () * NUM_EASY);
	    switch (r)
	    {
		case 0:
		    item = new Calcium (g1);
		    break;
		case 1:
		    item = new TrapBuster (g1);
		    break;
		case 2:
		    item = new Coupon (g1);
		    break;
		case 3:
		    item = new ItemKey (g1);
		    break;
		case 4:
		    item = new DeathSpell (g1);
		    //break;
	    }
	}
    }


    public void draw (int adjust)  // Helper method to display the treasure chest on the ground
    {
	// Display treasure chest image
	try
	{
	    g1.drawImage (img, x - adjust, y, null);
	}
	catch (NullPointerException ex)
	{
	    // Display black rectangle as placeholder
	    g1.setColor (Color.black);
	    g1.fillRect (x - adjust, y, 10, 10);
	}
    } // draw method


    public boolean isTouching (Player p)  // Helper method to determine whether or not the chest is touching the player
    {
	// Check x-coordinates
	if (x <= p.getX () + p.getWidth () && x + WIDTH >= p.getX ())
	{
	    // Check y-coordinates
	    if (y <= p.getY () + p.getHeight () && y + HEIGHT >= p.getY ())
	    {
		// The treasure chest is touching the player
		return true;
	    }
	}

	// The treasure chest is not touching the player
	return false;
    } // isTouching method


    public void ask (Player p)  // Helper method to ask the question and reward smart players
    {
	// Cleas the console input and output
	c.clear ();
	while (c.isCharAvail ())
	{
	    c.getChar ();
	}

	if (p.hasKey () && diff == true) // If the player can use a key
	{
	    // Prompt user and receive input
	    c.println ("You have a key:");
	    c.println (); // Leave a blank area for the key
	    c.println ();
	    c.println ();
	    c.println ();
	    try
	    {
		c.drawImage (keyImg, 0, 20, null); // Display the key
	    }
	    catch (NullPointerException ex)
	    {
		// Display black rectangle as placeholder
		c.setColor (Color.black);
		c.fillRect (0, 20, 10, 10);
	    }
	    c.println ("Would you like to use the key? Enter Yes or No.");
	    String in = c.readString ();

	    // Make decisions
	    if (in.equalsIgnoreCase ("Yes")) // If the user wants to use the key
	    {
		c.println ("You used the key and received a free item!");
		p.receive (item);

		// Display the type of item
		if (item.getClass ().equals (BridgePotion.class))
		{
		    c.println ("Congratulations! You won a Bridge Potion!");
		}
		else if (item.getClass ().equals (MaxHealthIncreaser.class))
		{
		    c.println ("Congratulations! You won a Max Health Increaser!");
		}
		else if (item.getClass ().equals (Bitcoin.class))
		{
		    c.println ("Congratulations! You won a Bitcoin!");
		}
		else if (item.getClass ().equals (VampireSpell.class))
		{
		    c.println ("Congratulations! You won a Vampire Spell!");
		}

		// Pause the program
		c.println ("Press any key to continue...");
		c.getChar ();
		return;
	    }
	    else if (in.equalsIgnoreCase ("No")) // If the user doesn't want to use the key
	    {
		c.clear ();
	    }
	    else // If the user is dumb
	    {
		c.clear ();
		c.println ("We did not understand your input, so you do not get to use your key.");
	    }
	}
	if (quest.ask ())
	{
	    // Reward the player if his answer is right
	    p.receive (item);

	    // Display the type of item received
	    c.println ();
	    if (item.getClass ().equals (Calcium.class))
	    {
		c.println ("Congratulations! You won a Calcium!");
	    }
	    else if (item.getClass ().equals (TrapBuster.class))
	    {
		c.println ("Congratulations! You won a Trap Buster!");
	    }
	    else if (item.getClass ().equals (Coupon.class))
	    {
		c.println ("Congratulations! You won a coupon!");
	    }
	    else if (item.getClass ().equals (ItemKey.class))
	    {
		c.println ("Congratulations! You won a key!");
	    }
	    else if (item.getClass ().equals (DeathSpell.class))
	    {
		c.println ("Congratulations! You won a Death Spell!");
	    }
	    else if (item.getClass ().equals (BridgePotion.class))
	    {
		c.println ("Congratulations! You won a Bridge Potion!");
	    }
	    else if (item.getClass ().equals (MaxHealthIncreaser.class))
	    {
		c.println ("Congratulations! You won a Max Health Increaser!");
	    }
	    else if (item.getClass ().equals (Bitcoin.class))
	    {
		c.println ("Congratulations! You won a Bitcoin!");
	    }
	    else if (item.getClass ().equals (VampireSpell.class))
	    {
		c.println ("Congratulations! You won a Vampire Spell!");
	    }
	}

	// Pause the program
	c.println ("Press any key to continue...");
	c.getChar ();
    } // ask method
} // Chest class
