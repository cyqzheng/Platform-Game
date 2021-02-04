/*
Name: Michael Li
Date: January 17, 2018
Class code: ICS 2O3 - 02
Program name: Store
An Object that represents a store that sells items.
*/

import java.awt.*;
import java.awt.image.*;
import java.math.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;
import hsa.*;

public class Store
{
    private Graphics g1; // The Graphics that will draw stuff
    private Console c; // The input/output console
    private BufferedImage buffer; // The image that will be displayed
    private Image img; // The image of the store
    private Image inventImg; // The image of the inventory of the store
    private Bitcoin btc; // The Bitcoin that will be drawn
    private Coupon cpn; // The coupon that will be drawn
    private ArrayList items; // The items sold by the store
    private ArrayList prices; // The prices of the items
    private int x; // The x-coordinate of the top left corner of the store
    private int y; // The y-coordinate of the top left corner of the store
    private boolean bitcoin; // Whether or not the player used a bitcoin
    private String errorMsg; // The message that shows if there is an error

    private final int VALUE1 = 50; // The approximate value of low-level items
    private final int VALUE2 = 200; // The approximate value of medium-level items
    private final int HEIGHT = 50; // The height of the store
    private final int WIDTH = 50; // The fatness of the store

    public Store (Console c1, Graphics g, int x1, int y1, BufferedImage b1)  // Constructs a random store
    {
	// Load images
	try
	{
	    img = ImageIO.read (new File ("Store.png"));
	}
	catch (IOException ex)
	{
	}
	try
	{
	    inventImg = ImageIO.read (new File ("StoreInventory.png"));
	}
	catch (IOException ex)
	{
	}

	// Assign variables
	c = c1;
	g1 = g;
	x = x1;
	y = y1;
	bitcoin = false;
	btc = new Bitcoin (g1);
	cpn = new Coupon (g1);
	buffer = b1;
	items = new ArrayList ();
	prices = new ArrayList ();
	errorMsg = "";

	// Add items to be sold
	items.add (new Mushroom (g1)); // Add mushrooms
	prices.add (new Integer ((int) (VALUE1 * (0.8 + 0.4 * Math.random ())))); // Select random prices
	items.add (new Mushroom (g1));
	prices.add (new Integer ((int) (VALUE1 * (0.8 + 0.4 * Math.random ()))));
	items.add (new Wheat (g1, false)); // Add wheat
	prices.add (new Integer ((int) (VALUE1 * (0.8 + 0.4 * Math.random ()))));
	items.add (new Wheat (g1, false));
	prices.add (new Integer ((int) (VALUE1 * (0.8 + 0.4 * Math.random ()))));
	items.add (new Shuriken (g1, false)); // Add shuriken
	prices.add (new Integer ((int) (VALUE1 * (0.8 + 0.4 * Math.random ()))));
	items.add (new Shuriken (g1, false));
	prices.add (new Integer ((int) (VALUE1 * (0.8 + 0.4 * Math.random ()))));
	items.add (new Calcium (g1)); // Add Calcium
	prices.add (new Integer ((int) (VALUE2 * (0.8 + 0.4 * Math.random ()))));
	items.add (new Calcium (g1));
	prices.add (new Integer ((int) (VALUE2 * (0.8 + 0.4 * Math.random ()))));
	items.add (new DeathSpell (g1)); // Add Death Spell
	prices.add (new Integer ((int) (VALUE2 * (0.8 + 0.4 * Math.random ()))));
	items.add (new DeathSpell (g1));
	prices.add (new Integer ((int) (VALUE2 * (0.8 + 0.4 * Math.random ()))));
	items.add (new ItemKey (g1)); // Add keys
	prices.add (new Integer ((int) (VALUE2 * (0.8 + 0.4 * Math.random ()))));
	items.add (new ItemKey (g1));
	prices.add (new Integer ((int) (VALUE2 * (0.8 + 0.4 * Math.random ()))));
	items.add (new TrapBuster (g1)); // Add Trap Busters
	prices.add (new Integer ((int) (VALUE2 * (0.8 + 0.4 * Math.random ()))));
	items.add (new TrapBuster (g1));
	prices.add (new Integer ((int) (VALUE2 * (0.8 + 0.4 * Math.random ()))));
    }


    public void draw (int adjust)  // Helper method to display the store on the ground
    {
	// Display store image
	try
	{
	    g1.drawImage (img, x - adjust, y, null);
	}
	catch (NullPointerException ex)
	{
	    // Display black rectangle as placeholder
	    g1.setColor (Color.black);
	    g1.fillRect (x - adjust, y, 10, 10);
	}
    } // draw method


    public boolean isTouching (Player p)  // Helper method to determine whether or not the store is touching the player
    {
	// Check x-coordinates
	if (x <= p.getX () + p.getWidth () && x + WIDTH >= p.getX ())
	{
	    // Check y-coordinates
	    if (y <= p.getY () + p.getHeight () && y + HEIGHT >= p.getY ())
	    {
		// The store is touching the player
		return true;
	    }
	}

	// The store is not touching the player
	return false;
    } // isTouching method


    public void open (Player p)  // Helper method to open the store
    {
	// Clear the console input
	while (c.isCharAvail ())
	{
	    c.getChar ();
	}

	// Declare variables
	boolean leftPressed = false; // Whether or not the left arrow key is being pressed
	boolean downPressed = false; // Whether or not the down arrow key is being pressed
	boolean rightPressed = false; // Whether or not the right arrow key is being pressed
	boolean upPressed = false; // Whether or not the up arrow key is being pressed
	int x1 = 0; // The x-coordinate of the item that the player wants to access
	int y1 = 0; // The y-coordinate of the item that the player wants to access
	char ch; // The character that the user entered

	// Loop until the player presses the X key
	while (!ICS_Summative.xPressed)
	{
	    // Reset graphics for drawing
	    g1.setColor (Color.white);
	    g1.fillRect (0, 0, 640, 500);

	    // Display the situation
	    // Draw the inventory grid
	    try
	    {
		g1.drawImage (inventImg, 0, 0, null);
	    }
	    catch (NullPointerException ex)
	    {
		g1.setColor (Color.blue);
		g1.fillRect (0, 0, 640, 500);
	    }
	    // Draw Bitcoin and coupon in their slots
	    btc.draw (95, 95);
	    cpn.draw (435, 50);
	    g1.setColor (Color.white);
	    g1.setFont (new Font ("Courier New", Font.PLAIN, 12));
	    g1.drawString ("[B]", 115, 155);
	    g1.drawString ("[C]", 500, 155);
	    // Draw border around selected item
	    g1.setColor (Color.white);
	    g1.drawRect (90 + x1 * 118, 180 + y1 * 77, 102, 58);
	    g1.drawRect (89 + x1 * 118, 179 + y1 * 77, 104, 60); // Make border thicker
	    g1.drawRect (91 + x1 * 118, 181 + y1 * 77, 100, 56);
	    g1.drawRect (92 + x1 * 118, 182 + y1 * 77, 98, 54);
	    // Display amount of money
	    g1.setFont (new Font ("Courier New", Font.PLAIN, 20));
	    g1.setColor (Color.black);
	    g1.drawString ("Money: $" + p.getMoney (), 260, 150);
	    // Loop through items to draw them
	    for (int i = 0 ; i < items.size () ; i++)
	    {
		Item it = (Item) items.get (i);
		it.draw (115 + i % 4 * 118, 180 + i / 4 * 77);
		g1.drawString ("$" + prices.get (i), 120 + i % 4 * 118, 250 + i / 4 * 77);
	    }
	    // Display error message
	    g1.setColor (Color.red);
	    g1.drawString (errorMsg, 150, 490);
	    // Display final image
	    c.drawImage (buffer, 0, 0, null);

	    // Check arrow keys
	    if (ICS_Summative.leftPressed)
	    {
		if (!leftPressed) // Should only change coordinates once
		{
		    leftPressed = true;
		    if (x1 > 0)
		    {
			x1--;
		    }
		}
	    }
	    else
	    {
		leftPressed = false;
	    }
	    if (ICS_Summative.downPressed)
	    {
		if (!downPressed) // Should only change coordinates once
		{
		    downPressed = true;
		    if (y1 < 3)
		    {
			y1++;
		    }
		}
	    }
	    else
	    {
		downPressed = false;
	    }
	    if (ICS_Summative.rightPressed)
	    {
		if (!rightPressed) // Should only change coordinates once
		{
		    rightPressed = true;
		    if (x1 < 3)
		    {
			x1++;
		    }
		}
	    }
	    else
	    {
		rightPressed = false;
	    }
	    if (ICS_Summative.upPressed)
	    {
		if (!upPressed) // Should only change coordinates once
		{
		    upPressed = true;
		    if (y1 > 0)
		    {
			y1--;
		    }
		}
	    }
	    else
	    {
		upPressed = false;
	    }

	    // Check console input
	    if (c.isCharAvail ())
	    {
		ch = c.getChar ();
	    }
	    else
	    {
		ch = '\0';
	    }

	    // Check <Enter> key
	    if (ch == '\n')
	    {
		// The user has selected an item to buy
		// Declare variables
		int index = x1 + 4 * y1;


		// Make sure the index is valid
		if (index < items.size ())
		{
		    // Declare more variables
		    Item item = (Item) items.get (index);
		    int price = ((Integer) prices.get (index)).intValue ();

		    // Check to make sure the user has enough money
		    if (p.getMoney () >= price)
		    {
			buy (index, p);
		    }
		    else
		    {
			errorMsg = "ERROR: NOT ENOUGH MONEY";
		    }
		}
	    }

	    // Check B key
	    if (ch == 'b' || ch == 'B')
	    {
		// The user has decided to use a Bitcoin
		// Make sure store isn't already Bitcoined
		if (!bitcoin)
		{
		    // Check to make sure the user has a Bitcoin
		    if (p.hasBitcoin ())
		    {
			bitcoin = true;
			// Loop through prices to make everything free
			for (int i = 0 ; i < prices.size () ; i++)
			{
			    prices.set (i, new Integer (0));
			}
			p.pay (-2);
		    }
		    else
		    {
			errorMsg = "ERROR: YOU HAVE NO BITCOINS";
		    }
		}
		else
		{
		    errorMsg = "ERROR: YOU ALREADY USED A BITCOIN";
		}
	    }

	    // Check C key
	    if (ch == 'c' || ch == 'C')
	    {
		// The user has decided to use a coupon
		// Make sure the store isn't Bitcoined
		if (!bitcoin)
		{
		    // Check to make sure the user has a coupon
		    if (p.hasCoupon ())
		    {
			// Loop through prices to make everything half price
			for (int i = 0 ; i < prices.size () ; i++)
			{
			    int price = ((Integer) prices.get (i)).intValue ();
			    prices.set (i, new Integer (price / 2));
			}
			p.pay (-1);
		    }
		    else
		    {
			errorMsg = "ERROR: YOU HAVE NO COUPONS";
		    }
		}
		else
		{
		    errorMsg = "ERROR: YOU ALREADY USED A BITCOIN";
		}
	    }
	}

	// Reset error message
	errorMsg = "";
    } // open method


    public void buy (int index, Player p)  // Helper method to buy an item
    {
	// Declare variables
	Item item = (Item) items.get (index);
	int price = ((Integer) prices.get (index)).intValue ();

	// Make transaction
	p.pay (price);
	p.receive (item);

	// Remove item from store
	items.remove (index);
	prices.remove (index);
    } // buy method


    public void checkWheat ()  // Helper method to check the store for wheat
    {
	// Loop through list of items to find wheat
	for (int i = 0 ; i < items.size () ; i++)
	{
	    if (items.get (i).getClass ().equals (Wheat.class))
	    {
		// The player missed some wheat at the store so cannot earn wheat achievement
		ICS_Summative.wheatAchieve = false;
	    }
	}
    } // checkWheat method
} // Store class
