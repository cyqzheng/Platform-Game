/*
Name: Michael Li
Date: January 12, 2018
Class code: ICS 2O3 - 02
Program name: SpawnerTrap
An trap that spawns several Spirits that will harm the player.
*/

import java.awt.*;
import java.math.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;
import hsa.*;

public class SpawnerTrap extends Trap
{
    private Graphics g1; // The Graphics that will draw stuff
    private Image portal; // The portal that spawns in Spirits
    private int lifetime; // The amount of time remaining until the trap despawns
    private boolean spawn; // Whether or not the trap is currently spawning a Spirit

    private final int MAX_LIFE = 200; // The lifetime of the trap after being activated

    public SpawnerTrap (Graphics g, int x1, int y1)  // Constructs a custom spawner trap
    {
	super (g, x1, y1);

	// Load image
	try
	{
	    portal = ImageIO.read (new File ("SpawnPortal.png"));
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

	// Draw portal if the trap is activated
	if (lifetime > 0)
	{
	    try
	    {
		g1.drawImage (portal, getX () - adjust, getY () - 100, null);
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
	// Determine whether or not the trap will spawn a Spirit
	if (lifetime != -1 && lifetime % 20 == 0) // Will spawn every 20 frames
	{
	    spawn = true;
	}
	else
	{
	    spawn = false;
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
    } // harm method


    public Spirit spawned ()  // Helper method to summon a Spirit
    {
	// Return nothing if not spawning
	if (spawn == false)
	{
	    return null;
	}

	// Randomly switch between types of Spirits
	int r = (int) (Math.random () * 5);
	switch (r)
	{
	    case 0:
		return new SpiritOfSpears (g1, getX (), getY () - 100);
	    case 1:
		return new SpiritOfFears (g1, getX (), getY () - 100);
	    case 2:
		return new SpiritOfYears (g1, getX (), getY () - 100);
	    case 3:
		return new SpiritOfEars (g1, getX (), getY () - 100);
	    case 4:
		return new SpiritOfTears (g1, getX (), getY () - 100);
	}
	return null;
    } // spawned method
} // SpawnerTrap class
