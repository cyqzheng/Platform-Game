/*
Name: Michael Li
Date: December 16, 2017
Class code: ICS 2O3 - 02
Program name: Scroll
An Item that represents a scroll with mysterious text.
*/

import java.awt.*;
import java.math.*;
import java.util.*;
import hsa.*;

public class Scroll extends Item
{
    private String text; // The mysterious text
    private Graphics g1; // The Graphics that will draw stuff
    private boolean cursed; // Whether or not the scroll is cursed

    public Scroll (Graphics g, String message)  // Constructs a custom scroll
    {
	super (g, "Scroll.png");
	
	// Declare variables
	text = message;
	g1 = g;
	cursed = false;
    }


    public void read ()
    {
	// Display rectangle for text
	g1.setColor (new Color (255, 255, 128));
	g1.fillRect (120, 100, 400, 100);

	// Prepare to display text
	g1.setColor (Color.black);
	g1.setFont (new Font ("Courier New", Font.ITALIC, 12));

	// Declare helper variables
	String temp = text;
	int num;
	int y = 120;

	// We will display chunks of text separated by '\n' characters
	do
	{
	    num = temp.indexOf ('\n');
	    if (num == -1)
	    {
		num = temp.length ();
	    }
	    g1.drawString (temp.substring (0, num), 130, y);
	    y += 15;
	    try
	    {
		temp = temp.substring (num + 1);
	    }
	    catch (StringIndexOutOfBoundsException ex)
	    {
		temp = "";
	    }
	}
	while (temp.length () > 0);
    } // read method


    public void curse ()  // Helper method to curse the scroll
    {
	cursed = true;
    } // curse method


    public boolean isCursed () // Helper method to determine whether or not the scroll is cursed
    {
	return cursed;
    } // isCursed method
} // Scroll class
