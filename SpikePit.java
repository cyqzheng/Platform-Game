/*
Name: Michael Li
Date: January 12, 2018
Class code: ICS 2O3 - 02
Program name: SpikePit
An trap that deals damage with spikes.
*/

import java.awt.*;
import java.math.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;
import hsa.*;

public class SpikePit extends Trap
{
    private Graphics g1; // The Graphics that will draw stuff
    private Image spikes; // The image of the spikes
    private int lifetime; // The amount of time remaining until the trap despawns

    private final int MAX_LIFE = 80; // The lifetime of the trap after being activated

    public SpikePit (Graphics g, int x1, int y1)  // Constructs a custom spike pit
    {
	super (g, x1, y1);

	// Load image
	try
	{
	    spikes = ImageIO.read (new File ("Spikes.png"));
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

	// Draw spikes if the trap is activated
	if (lifetime > 0)
	{
	    try
	    {
		g1.drawImage (spikes, getX () - adjust, getY () - 20, null);
	    }
	    catch (NullPointerException ex)
	    {
		// Draw black rectangle as placeholder
		g1.setColor (Color.black);
		g1.fillRect (getX () - adjust, getY () - 20, 10, 10);
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
	getPlayer ().takeDamage (300);

	// Start counting down lifetime if necessary
	if (lifetime == -1)
	{
	    lifetime = MAX_LIFE;
	}
    } // harm method
} // SpikePit class
