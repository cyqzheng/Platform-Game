/*
Name: Michael Li
Date: December 16, 2017
Class code: ICS 2O3 - 02
Program name: Wheat
A weapon that represents some wheat that has a 0.01% chance of dealing exactly one damage.
*/

import java.awt.*;
import java.math.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;
import hsa.*;

public class Wheat extends Weapon
{
    private Graphics g1; // The Graphics that draws stuff
    private int amount; // The number of wheat that the player has
    private int cursed; // The number of wheat that is cursed
    private Image img; // The image of regular wheat
    private Image curseImg; // The image of cursed wheat

    public Wheat (Graphics g, boolean curse)  // Constructs a custom Wheat
    {
	super (g, "Wheat.png");

	// Load images
	try
	{
	    img = ImageIO.read (new File ("Wheat.png"));
	}
	catch (IOException ex)
	{
	}
	try
	{
	    curseImg = ImageIO.read (new File ("WheatTrap.png"));
	}
	catch (IOException ex)
	{
	}

	// Assign variables
	g1 = g;
	amount = 1;
	if (curse)
	{
	    cursed = 1;
	}
	else
	{
	    cursed = 0;
	}
    }


    public void draw (int x, int y)  // Helper method to display the wheat
    {
	// Make sure that the image will actually appear on the screen--otherwise, drawing it will waste time and cause lag
	if (x <= 640 && x + 30 >= 0) // Check x-coordinates
	{
	    if (y <= 500 && y + 30 >= 0) // Check y-coordinates
	    {
		// Image is in range so we draw it
		try
		{
		    // Select image based on whether or not the wheat is cursed
		    if (cursed > 0)
		    {
			g1.drawImage (curseImg, x, y, null);
		    }
		    else
		    {
			g1.drawImage (img, x, y, null);
		    }
		}

		catch (NullPointerException ex)
		{
		    // Draw black rectangle as placeholder
		    g1.setColor (Color.black);
		    g1.fillRect (x, y, 10, 10);
		}

		// Display the amount of wheat if stacked
		if (amount > 1)
		{
		    g1.setColor (Color.black);
		    g1.setFont (new Font ("Courier New", Font.PLAIN, 20));
		    g1.drawString ("" + amount, x + 40, y + 40);
		}
	    }
	}
    } // draw method


    public boolean isCursed ()  // Helper method to determine whether or not the player has cursed wheat
    {
	return cursed > 0;
    } // isCursed method


    public boolean exists ()  // Helper method to determine whether or not the player has wheat
    {
	return amount > 0;
    } // exists method


    public void collect (boolean curse)  // Helper method to collect wheat
    {
	// Increment accumulators
	amount++;
	if (curse) // Oh noes! The wheat is cursed
	{
	    cursed++;
	}
    } // collect method


    public void use ()  // Helper method to use a wheat
    {
	amount--;
	if (cursed > 0)
	{
	    // Player will use cursed wheat first
	    cursed--;
	}
    }
} // Wheat class
