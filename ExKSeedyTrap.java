/*
Name: Michael Li
Date: January 12, 2018
Class code: ICS 2O3 - 02
Program name: ExKSeedyTrap
A trap that summons a guy with a black hat who tries to kill the player.
*/

import java.awt.*;
import java.math.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;
import hsa.*;

public class ExKSeedyTrap extends Trap
{
    private Graphics g1; // The Graphics that will draw stuff
    private Image killer1; // A guy with a black hat who tries to kill the player
    private Image killer2; // The same guy dealing lethal damage around him
    private int lifetime; // The amount of time remaining until the trap despawns

    private final int MAX_LIFE = 20; // The lifetime of the trap after being activated

    public ExKSeedyTrap (Graphics g, int x1, int y1)  // Constructs a custom Ex-K Seedy trap
    {
	super (g, x1, y1);

	// Load images
	try
	{
	    killer1 = ImageIO.read (new File ("BlackHat1.png"));
	}
	catch (IOException ex)
	{
	}
	try
	{
	    killer2 = ImageIO.read (new File ("BlackHat2.png"));
	}
	catch (IOException ex)
	{
	}

	// Declare variables
	g1 = g;
	lifetime = -1; // Do not start lifetime count until the trap gets activated
    }


    public void draw (int adjust) throws DeathException // Helper method to draw the trap
    {
	super.draw (adjust);

	// Draw the guy with the black hat if the trap is activated
	if (lifetime > 0)
	{
	    try
	    {
		if (lifetime > 10) // Draw first image earlier
		{
		    g1.drawImage (killer1, getX () - adjust, getY () - 100, null);
		}
		else // Draw second image later
		{
		    g1.drawImage (killer2, getX () - adjust, getY () - 100, null);
		}
	    }
	    catch (NullPointerException ex)
	    {
		// Draw black rectangle as placeholder
		g1.setColor (Color.black);
		g1.fillRect (getX () - adjust, getY () - 100, 10, 10);
	    }
	}

	// Count down lifetime if trap is activated
	if (lifetime != -1)
	{
	    lifetime--;
	    if (lifetime == 0)
	    {
		despawn ();
	    }
	}
	// Kill the player if he is nearby and time is up
	if (getX () + getWidth () >= getPlayer ().getX () && getX () <= getPlayer ().getX () + getPlayer ().getWidth () && lifetime == 0)
	{
	    getPlayer ().takeDamage (2000000000);
	}
    }


    public void harm () throws DeathException // Helper method to harm the player
    {
	super.harm ();

	// Start counting down lifetime if necessary
	if (lifetime == -1)
	{
	    lifetime = MAX_LIFE;
	}

	// Kill the player if time is up
	if (lifetime == 0)
	{
	    getPlayer ().takeDamage (2000000000);
	}
    } // harm method
} // ExKSeedyTrap class


