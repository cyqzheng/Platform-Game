/*
Name: Michael Li
Date: December 26, 2017
Class code: ICS 2O3 - 02
Program name: Platform
An Object that represents a platform for the character to jump on.
*/

import java.awt.*;
import java.math.*;
import java.util.*;
import hsa.*;

public class Platform
{
    private Graphics g1; // The Graphics that will draw stuff
    private int x; // x-coordinate of top left corner
    private int y; // y-coordinate of top left corner
    private int width; // width of platform

    public Platform (Graphics g, int x1, int y1, int width1)  // Constructs a custom platform
    {
	g1 = g;
	x = x1;
	y = y1;
	width = width1;
    }


    public void draw (int adjust)  // Helper method to draw the platform
    {
	// Draw the main platform
	g1.setColor (new Color (100, 50, 0));
	g1.fillRect (x - adjust, y, width, 5);

	// Draw pillars supporting the platform
	g1.setColor (new Color (200, 100, 0));
	g1.fillRect (x + 5 - adjust, y + 5, 10, 600);
	g1.fillRect (x + width - 15 - adjust, y + 5, 10, 600);

    } // draw method


    public int getLeft ()  // Returns the leftmost point of the platform.
    {
	return x;
    }


    public int getRight ()  // Returns the rightmost point of the platform.
    {
	return x + width;
    }


    public int getY ()  // Returns the y-ccordinate of the top of the platform.
    {
	return y;
    }
} // Platform class
