/*
Name: Michael Li
Date: December 26, 2017
Class code: ICS 2O3 - 02
Program name: Tester
A program to test the code and make sure it works properly.
*/

import java.awt.*;
import java.awt.image.*;
import java.math.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;
import hsa.*;
import hsa.Console;

public class Tester
{
    private static Console c; // The output console
    private static long time1; // The variable to help measure time between each frame
    private static Image deadImg; // The image that shows up when the player dies
    private static Image endImg; // The image that shows up when the player finishes the game

    public static void resetTime ()  // Helper method to reset the time counter
    {
	time1 = System.currentTimeMillis ();
    } // resetTime method


    public static void main (String[] args) throws InterruptedException
    {
	// Prepare graphics
	if (c == null)
	{
	    c = new Console ();
	}
	BufferedImage buffer = new BufferedImage (c.getWidth (), c.getHeight (), BufferedImage.TYPE_INT_ARGB);
	Graphics g1 = buffer.getGraphics ();

	// Load images
	try
	{
	    deadImg = ImageIO.read (new File ("DeadScreen.png"));
	}
	catch (IOException ex)
	{
	}
	try
	{
	    endImg = ImageIO.read (new File ("EndScreen.png"));
	}
	catch (IOException ex)
	{
	}

	// Prepare to monitor keyboard
	KeyboardFocusManager keyTemp = KeyboardFocusManager.getCurrentKeyboardFocusManager ();
	keyTemp.addKeyEventDispatcher (new KeyHelper ());

	// Declare and assign variables
	Player p = new Player (g1);
	ArrayList plats = new ArrayList ();
	ArrayList enemies = new ArrayList ();
	ArrayList traps = new ArrayList ();
	ArrayList spirits = new ArrayList ();
	ArrayList chests = new ArrayList ();
	ArrayList stores = new ArrayList ();

	// Add platforms to ArrayList
	plats.add (new Platform (g1, 0, 270, 500));
	plats.add (new Platform (g1, 0, 369, 1000));
	plats.add (new Platform (g1, 750, 270, 200));

	// Add enemies to ArrayList
	//enemies.add (new EasyEnemy (g1, 100, 220));
	//enemies.add (new NormalEnemy (g1, 100, 220));
	//enemies.add (new HardEnemy (g1, 400, 200));
	enemies.add (new Boss (g1, 400, 269));

	// Add traps to ArrayList
	traps.add (new SpikePit (g1, 100, 270));

	// Add chests to ArrayList
	chests.add (new Chest (c, g1, 400, 220, false));
	chests.add (new Chest (c, g1, 400, 319, true));

	// Add stores to ArrayList
	stores.add (new Store (c, g1, 800, 220, buffer));

	// Set up the game
	p.setPlatforms (plats);
	for (int i = 0 ; i < enemies.size () ; i++)
	{
	    Enemy temp = (Enemy) enemies.get (i);
	    temp.setPlatforms (plats);
	    temp.setPlayer (p);
	}
	for (int i = 0 ; i < traps.size () ; i++)
	{
	    Trap temp = (Trap) traps.get (i);
	    temp.setPlayer (p);
	}

	// Keep track of time
	resetTime ();

	try
	{
	    p.takeDamage (999);
	    p.receive (new Money (g1, 120));
	    while (true)
	    {
		time1 += 25; // Each frame takes 25 milliseconds

		// Reset graphics for drawing
		g1.setColor (Color.white);
		g1.fillRect (0, 0, 640, 500);

		// Display the situation
		p.draw ();
		for (int i = 0 ; i < enemies.size () ; i++)
		{
		    Enemy temp = (Enemy) enemies.get (i);
		    temp.draw (p.getX () - 300);
		}
		for (int i = 0 ; i < traps.size () ; i++)
		{
		    Trap temp = (Trap) traps.get (i);
		    temp.draw (p.getX () - 300);
		}
		for (int i = 0 ; i < spirits.size () ; i++)
		{
		    Spirit temp = (Spirit) spirits.get (i);
		    temp.draw (p.getX () - 300);
		}
		for (int i = 0 ; i < chests.size () ; i++)
		{
		    Chest temp = (Chest) chests.get (i);
		    temp.draw (p.getX () - 300);
		}
		for (int i = 0 ; i < stores.size () ; i++)
		{
		    Store temp = (Store) stores.get (i);
		    temp.draw (p.getX () - 300);
		}
		c.drawImage (buffer, 0, 0, null);

		// Move the situation
		p.move ();
		if (ICS_Summative.iPressed)
		{
		    p.openInventory (c, buffer);
		    resetTime (); // Reset the time counter because the program was paused
		}
		if (ICS_Summative.bustTrap)
		{
		    // Loop to bust all traps in an area of radius 3000
		    for (int i = 0 ; i < traps.size () ; i++)
		    {
			Trap tr = (Trap) traps.get (i);

			// Calculate distance from player
			int deltaX = p.getX () - tr.getX ();
			int deltaY = p.getY () - tr.getY ();
			int dist = (int) Math.sqrt (deltaX * deltaX + deltaY * deltaY);

			// Make decision
			if (dist <= 3000)
			{
			    traps.remove (i);
			    i--;
			}
		    }
		    ICS_Summative.bustTrap = false;
		}
		if (ICS_Summative.bossKill)
		{
		    // Destroy all bad Spirits
		    spirits = new ArrayList ();

		    // Summon good Spirits
		    spirits.add (new SpiritOfCheers (g1, p.getX (), p.getY ()));
		    spirits.add (new SpiritOfClears (g1, p.getX (), p.getY ()));

		    // Boss not killed anymore
		    ICS_Summative.bossKill = false;
		}
		for (int i = 0 ; i < enemies.size () ; i++)
		{
		    Enemy temp = (Enemy) enemies.get (i);
		    temp.move ();
		    temp.harm ();
		    p.harm (temp);
		    if (temp.isDead ())
		    {
			// Enemy gets removed if it dies
			enemies.remove (i);
			i--;
		    }
		    // Check special types of enemies
		    if (temp.getClass ().equals (Boss.class))
		    {
			// Potentially spawn in a Spirit
			spirits.add (((Boss) temp).spawned ());
		    }
		}
		for (int i = 0 ; i < traps.size () ; i++)
		{
		    Trap temp = (Trap) traps.get (i);

		    // Check to see if the player is activating the trap
		    if (temp.isTouching ())
		    {
			// The trap gets activated
			temp.harm ();
		    }

		    // Check to see if the trap has despawned
		    if (temp.isGone ())
		    {
			traps.remove (i);
			i--;
		    }

		    // Check special types of traps
		    if (temp.getClass ().equals (SpawnerTrap.class))
		    {
			// Potentially spawn in a Spirit
			spirits.add (((SpawnerTrap) temp).spawned ());
		    }
		}
		for (int i = 0 ; i < spirits.size () ; i++)
		{
		    Spirit temp = (Spirit) spirits.get (i);
		    try
		    {
			temp.move (p);
		    }
		    catch (NullPointerException ex)  // Delete Spirit if any exceptions thrown
		    {
			spirits.remove (i);
			i--;
			continue;
		    }
		    catch (DeathException ex)
		    {
			// Check to see if the Spirit is from a dead boss
			if (temp.getClass ().equals (SpiritOfCheers.class) || temp.getClass ().equals (SpiritOfClears.class))
			{
			    throw new EndException (); // The player finished the game
			}
			spirits.remove (i);
			i--;
			continue;
		    }
		    if (temp.isTouching (p))
		    {
			temp.harm (p);
		    }
		}
		for (int i = 0 ; i < chests.size () ; i++)
		{
		    Chest temp = (Chest) chests.get (i);

		    // Check to see if the player wants to open the treasure chest
		    if (ICS_Summative.enterPressed && temp.isTouching (p))
		    {
			// Ask the question and reward smart players
			temp.ask (p);

			// Reset time counter because the program was paused
			resetTime ();

			// Remove the treasure chest
			chests.remove (i);
			i--;
		    }
		}
		for (int i = 0 ; i < stores.size () ; i++)
		{
		    Store temp = (Store) stores.get (i);

		    // Check to see if the player wants to open the store
		    if (ICS_Summative.enterPressed && temp.isTouching (p))
		    {
			// Open the store and sell items
			temp.open (p);

			// Reset time counter because the program was paused
			resetTime ();
		    }
		}
		c.setCursor (25, 1);
		//c.print (System.currentTimeMillis () - time1);

		// Loop until it is time to show the next frame
		while (System.currentTimeMillis () - time1 < 0)
		{
		}
		if (ICS_Summative.xPressed)
		{
		    //p.use (0);
		}
	    }
	}
	catch (DeathException ex)
	{
	    // Display death image
	    c.clear ();
	    g1.drawImage (deadImg, 0, 0, null);
	    c.drawImage (buffer, 0, 0, null);
	    while (c.isCharAvail ())
	    {
		c.getChar ();
	    }

	    if (c.getChar () == '\n')
	    {
		main (null); // Run through the program again
	    }
	}
	catch (EndException ex)  // The player killed the boss and finished the game
	{
	    // Display end image
	    c.clear ();
	    g1.drawImage (endImg, 0, 0, null);
	    c.drawImage (buffer, 0, 0, null);

	    // Clean the console input
	    while (c.isCharAvail ())
	    {
		// Remove available character
		c.getChar ();
	    }

	    // Pause program until a key is pressed
	    c.getChar ();
	}


	// Display termination message
	c.clear ();
	c.println ("The program has terminated.");

    } // main program
} // Tester class
