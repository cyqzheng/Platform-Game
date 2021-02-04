/*
Name: Michael Li
Date: December 16, 2017
Class code: ICS 2O3 - 02
Program name: Item
An Object that represents a generic item that the protagonist uses.
*/

import java.awt.*;
import java.math.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;
import hsa.*;

public abstract class Item
{
    private Graphics g1; // The Graphics that will draw stuff

    private Image img; // The image of the item

    private final int HEIGHT = 30; // The height of each item
    private final int WIDTH = 30; // The fatness of each item

    public Item (Graphics g, String imgName)  // Constructs a custom item
    {
	g1 = g;

	// Load image
	try
	{
	    img = ImageIO.read (new File (imgName));
	}
	catch (IOException ex)
	{
	}
    }


    public void draw (int x, int y)  // Helper method to draw the item at the point (x, y)
    {
	// Make sure that the image will actually appear on the screen--otherwise, drawing it will waste time and cause lag
	if (x <= 640 && x + WIDTH >= 0) // Check x-coordinates
	{
	    if (y <= 500 && y + HEIGHT >= 0) // Check y-coordinates
	    {
		// Image is in range so we draw it
		try
		{
		    g1.drawImage (img, x, y, null);
		}

		catch (NullPointerException ex)
		{
		    // Draw black rectangle as placeholder
		    g1.setColor (Color.black);
		    g1.fillRect (x, y, 10, 10);
		}
	    }
	}
    } // draw method
} // Item class
