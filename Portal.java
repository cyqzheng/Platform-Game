/*
Name: Michael Li
Date: January 21, 2018
Class code: ICS 2O3 - 02
Program name: Portal
An Object that represents a portal that the player travels through.
*/

import java.awt.*;
import java.math.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;
import hsa.*;

public class Portal
{
    private Graphics g1; // The Graphics that will draw stuff
    private Image img; // The image of the portal
    private int x; // The x-coordinate of the top left corner of the portal
    private int y; // The y-coordinate of the top leyt corner of the portal

    private final int RADIUS = 50; // The radius of the circular portal

    public Portal (Graphics g, int x1, int y1)  // Constructs a custom Portal
    {
	// Load image
	try
	{
	    img = ImageIO.read (new File ("Portal.png"));
	}
	catch (IOException ex)
	{
	}

	// Assign variables
	g1 = g;
	x = x1;
	y = y1;
    }


    public void draw (int adjust)  // Helper method to draw the portal
    {
	try
	{
	    g1.drawImage (img, (int) x - adjust, (int) y, null);
	}
	catch (NullPointerException ex)
	{
	    // Draw purple circle as placeholder
	    g1.setColor (new Color (255, 0, 255));
	    g1.drawOval ((int) x - adjust, (int) y, RADIUS * 2, RADIUS * 2);
	}
    } // draw method


    public boolean isTouching (Player p)  // Helper method to determine whether or not the portal is touching the player
    {
	// Calculate coordinates of the centre of the portal
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
	if (dist <= RADIUS) // If the portal is touching the top left corner
	{
	    return true;
	}

	// Check top right corner
	checkX = p.getX () + p.getWidth ();
	checkY = p.getY ();
	deltaX = centreX - checkX;
	deltaY = centreY - checkY;
	dist = Math.sqrt (deltaX * deltaX + deltaY * deltaY);
	if (dist <= RADIUS) // If the portal is touching the top right corner
	{
	    return true;
	}

	// Check bottom left corner
	checkX = p.getX ();
	checkY = p.getY () + p.getHeight ();
	deltaX = centreX - checkX;
	deltaY = centreY - checkY;
	dist = Math.sqrt (deltaX * deltaX + deltaY * deltaY);
	if (dist <= RADIUS) // If the portal is touching the bottom left corner
	{
	    return true;
	}

	// Check bottom right corner
	checkX = p.getX () + p.getWidth ();
	checkY = p.getY () + p.getHeight ();
	deltaX = centreX - checkX;
	deltaY = centreY - checkY;
	dist = Math.sqrt (deltaX * deltaX + deltaY * deltaY);
	if (dist <= RADIUS) // If the portal is touching the bottom right corner
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

	// Everything is checked, the portal is not touching the player
	return false;
    } // isTouching method
} // Portal class
