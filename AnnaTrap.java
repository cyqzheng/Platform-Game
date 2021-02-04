/*
Name: Michael Li
Date: January 12, 2018
Class code: ICS 2O3 - 02
Program name: AnnaTrap
A cruel anti-bridge trap that destroys all of the player's bridge potions.
*/

import java.awt.*;
import java.math.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;
import hsa.*;

public class AnnaTrap extends Trap
{
    private Graphics g1; // The Graphics that will draw stuff
    private Image sign; // A sign that states "NO BRIDGE POTIONS"
    private int lifetime; // The amount of time remaining until the trap despawns

    private final int MAX_LIFE = 40; // The lifetime of the trap after being activated

    public AnnaTrap (Graphics g, int x1, int y1)  // Constructs a custom Anna trap
    {
	super (g, x1, y1);

	// Load image
	try
	{
	    sign = ImageIO.read (new File ("AnnaSign.png"));
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

	// Draw anti-bridge sign if the trap is activated
	if (lifetime > 0)
	{
	    try
	    {
		g1.drawImage (sign, getX () - adjust, getY () - 60, null);
	    }
	    catch (NullPointerException ex)
	    {
		// Draw black rectangle as placeholder
		g1.setColor (Color.black);
		g1.fillRect (getX () - adjust, getY () - 60, 10, 10);
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
    }


    public void harm () throws DeathException // Helper method to harm the player
    {
	super.harm ();

	// Remove the bridge potions
	getPlayer ().annaTrap ();

	// Start counting down lifetime if necessary
	if (lifetime == -1)
	{
	    lifetime = MAX_LIFE;
	}
    } // harm method
} // AnnaTrap class
