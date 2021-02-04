/*
Name: Michael Li
Date: January 1, 2018
Class code: ICS 2O3 - 02
Program name: Spirit
An Object that represents one of Mr. Brossard's spirits.
*/

import java.awt.*;
import java.math.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;
import hsa.*;

public abstract class Spirit
{
    private Graphics g1; // The Graphics that will draw stuff
    private Image img; // The image of the spirit
    private double x; // The x-coordinate of the spirit
    private double y; // The y-coordinate of the spirit
    private double time; // The amount of time that the spirit has existed

    private final double SPEED = 1; // The speed of the spirit
    private final int RADIUS = 20; // The radius of the circular spirit
    private final int LIFETIME = 400; // The lifetime of the spirit

    public Spirit (Graphics g, String imgName, int x1, int y1)  // Constructs a custom Spirit
    {
	// Load image
	try
	{
	    img = ImageIO.read (new File (imgName));
	}
	catch (IOException ex)
	{
	}

	// Assign variables
	g1 = g;
	x = x1;
	y = y1;
	time = 0;
    }


    public void draw (int adjust)  // Helper method to draw the spirit
    {
	try
	{
	    g1.drawImage (img, (int) x - adjust, (int) y, null);
	}
	catch (NullPointerException ex)
	{
	    // Draw black circle as placeholder
	    g1.setColor (Color.black);
	    g1.drawOval ((int) x - adjust, (int) y, 40, 40);
	}
    } // draw method


    public void move (Player p) throws DeathException // Helper method to move the spirit towards the player
    {
	// Increment time
	time++;

	// Despawn if necessary
	if (time > LIFETIME)
	{
	    throw new DeathException ();
	}

	// Determine coordinates of centers of spirit and player
	double playerX = p.getX () + p.getWidth () / 2.0;
	double playerY = p.getY () + p.getHeight () / 2.0;
	double spiritX = x + RADIUS;
	double spiritY = y + RADIUS;

	// Calculate vector from spirit to player
	double deltaX = playerX - spiritX;
	double deltaY = playerY - spiritY;

	// Calculate distance from spirit to player
	double dist = Math.sqrt (deltaX * deltaX + deltaY * deltaY);

	// Calculate ratio of lengths of displacement vector and velocity vector
	double ratio = SPEED / dist;

	// Calculate velocity vector
	double velX = deltaX * ratio;
	double velY = deltaY * ratio;

	// Move the spirit
	x += velX;
	y += velY;
    } // move method


    public boolean isTouching (Player p)  // Helper method to determine whether or not the spirit is touching the player
    {
	// Calculate coordinates of the centre of the spirit
	double centreX = x + RADIUS;
	double centreY = y + RADIUS;

	// Declare other required variables
	double dist, checkX, checkY, deltaX, deltaY;

	// Check top left corner
	checkX = p.getX ();
	checkY = p.getY ();
	deltaX = centreX - checkX;
	deltaY = centreY - checkY;
	dist = Math.sqrt (deltaX * deltaX + deltaY * deltaY);
	if (dist <= RADIUS) // If the spirit is touching the top left corner
	{
	    return true;
	}

	// Check top right corner
	checkX = p.getX () + p.getWidth ();
	checkY = p.getY ();
	deltaX = centreX - checkX;
	deltaY = centreY - checkY;
	dist = Math.sqrt (deltaX * deltaX + deltaY * deltaY);
	if (dist <= RADIUS) // If the spirit is touching the top right corner
	{
	    return true;
	}

	// Check bottom left corner
	checkX = p.getX ();
	checkY = p.getY () + p.getHeight ();
	deltaX = centreX - checkX;
	deltaY = centreY - checkY;
	dist = Math.sqrt (deltaX * deltaX + deltaY * deltaY);
	if (dist <= RADIUS) // If the spirit is touching the bottom left corner
	{
	    return true;
	}

	// Check bottom right corner
	checkX = p.getX () + p.getWidth ();
	checkY = p.getY () + p.getHeight ();
	deltaX = centreX - checkX;
	deltaY = centreY - checkY;
	dist = Math.sqrt (deltaX * deltaX + deltaY * deltaY);
	if (dist <= RADIUS) // If the spirit is touching the bottom right corner
	{
	    return true;
	}

	// Check left side
	checkX = p.getX ();
	if (centreX + RADIUS >= checkX && centreX - RADIUS <= checkX)
	{
	    if (centreY >= p.getY () && centreY <= p.getY () + p.getHeight ())
	    {
		return true;
	    }
	}

	// Check upper side
	checkY = p.getY ();
	if (centreY + RADIUS >= checkY && centreY - RADIUS <= checkY)
	{
	    if (centreX >= p.getX () && centreX <= p.getX () + p.getHeight ())
	    {
		return true;
	    }
	}

	// Check right side
	checkX = p.getX () + p.getWidth ();
	if (centreX + RADIUS >= checkX && centreX - RADIUS <= checkX)
	{
	    if (centreY >= p.getY () && centreY <= p.getY () + p.getHeight ())
	    {
		return true;
	    }
	}

	// Check lower side
	checkY = p.getY () + p.getHeight ();
	if (centreY + RADIUS >= checkY && centreY - RADIUS <= checkY)
	{
	    if (centreX >= p.getX () && centreX <= p.getX () + p.getHeight ())
	    {
		return true;
	    }
	}

	// Check interior
	if (centreX >= p.getX ())
	{
	    if (centreY >= p.getY ())
	    {
		if (centreX <= p.getX () + p.getWidth ())
		{
		    if (centreY <= p.getY () + p.getHeight ())
		    {
			// The centre of the spirit is inside the player, so return true
			return true;
		    }
		}
	    }
	}

	// Everything is checked, the spirit is not touching the player
	return false;
    } // isTouching method


    public void harm (Player p) throws DeathException // Helper method to make the spirit harm the player
    {
	// This method does nothing here but is implemented in the subclasses.
    } // harm method


    public void die ()  // Helper method to despawn the player
    {
	time = LIFETIME;
    } // die method
} // Spirit class
