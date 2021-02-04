/*
Name: Michael Li
Date: January 12, 2018
Class code: ICS 2O3 - 02
Program name: LoveTrap
An trap that causes the player to freeze as he thinks and thinks about his special lover.
*/

import java.awt.*;
import java.math.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;
import hsa.*;

public class LoveTrap extends Trap
{
    private Graphics g1; // The Graphics that will draw stuff
    private Image thought; // The thought bubble containing the player's special lover
    private int lifetime; // The amount of time remaining until the trap despawns

    private final int MAX_LIFE = 100; // The lifetime of the trap after being activated

    public LoveTrap (Graphics g, int x1, int y1)  // Constructs a custom love trap
    {
	super (g, x1, y1);

	// Load image
	try
	{
	    thought = ImageIO.read (new File ("LoveThought.png"));
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

	// Draw thought bubble if the trap is activated
	if (lifetime > 0)
	{
	    try
	    {
		g1.drawImage (thought, getX () - adjust, getY () - 200, null);
	    }
	    catch (NullPointerException ex)
	    {
		// Draw black rectangle as placeholder
		g1.setColor (Color.black);
		g1.fillRect (getX () - adjust, getY () - 200, 10, 10);
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

	// Freeze the player
	getPlayer ().freeze (2);

	// Start counting down lifetime if necessary
	if (lifetime == -1)
	{
	    lifetime = MAX_LIFE;
	}
    } // harm method
} // LoveTrap class
