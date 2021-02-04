/*
Name: Michael Li
Date: December 29, 2017
Class code: ICS 2O3 - 02
Program name: Enemy
An Object that represents an enemy that damages the player.
*/

import java.awt.*;
import java.math.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;
import hsa.*;

public abstract class Enemy
{
    private Graphics g1; // The Graphics that will draw stuff
    private Image imgNonShieldLeft; // The regular image of the enemy facing to the left
    private Image imgNonShieldRight; // The regular image of the enemy facing to the right
    private Image imgShieldLeft; // The image when enemy is shielded facing to the left
    private Image imgShieldRight; // The image when enemy is shielded facing to the right
    private int x; // The x-coordinate of the top left corner of the enemy
    private int y; // The y-coordinate of the top left corner of the enemy
    private int health; // The number of hits it takes to defeat enemy
    private int damage; // The amount of damage the enemy deals
    private int luck; // More luck means more items and $$$
    private boolean shield; // Whether or not the shield is activated
    private int shieldTime; // The amount of time remaining for the enemy's shield
    private boolean moveLeft; // Whether or not the enemy is moving to the left
    private boolean moveRight; // Whether or not the enemy is moving to the right
    private int moveMode; // Determines a hard enemy's current movement pattern
    private Platform plat; // The platform that the enemy moves on
    private Player p; // The player that the enemy wants to damage

    private final int HEIGHT = 50; // The height of the enemy
    private final int WIDTH = 50; // The fatness of the enemy
    private final int SPEED = 2; // The horizontal speed of the enemy
    private final int HEIGHT2 = 70; // The height of hard enemies
    private final int WIDTH2 = 70; // The fatness of hard enemies
    private final int SPEED2 = 4; // The horizontal speed of hard enemies and the boss
    private final int HEIGHT3 = 100; // The height of the boss
    private final int WIDTH3 = 100; // The fatness of the boss
    private final int SHIELD_TIME = 120; // The amount of time that the shield is on

    public Enemy (Graphics g, String imgName1, String imgName2, int x1, int y1, int h1, int d1, int l1)  // Constructs a custom Enemy
    {
	// Load images
	try
	{
	    imgNonShieldLeft = ImageIO.read (new File ("Left" + imgName1));
	}
	catch (IOException ex)
	{
	}
	try
	{
	    imgNonShieldRight = ImageIO.read (new File ("Right" + imgName1));
	}
	catch (IOException ex)
	{
	}
	try
	{
	    imgShieldLeft = ImageIO.read (new File ("Left" + imgName2));
	}
	catch (IOException ex)
	{
	}
	catch (NullPointerException ex)
	{
	}
	try
	{
	    imgShieldRight = ImageIO.read (new File ("Right" + imgName2));
	}
	catch (IOException ex)
	{
	}
	catch (NullPointerException ex)
	{
	}

	// Declare variables
	g1 = g;
	x = x1;
	y = y1;
	health = h1;
	damage = d1;
	luck = l1;
	shield = false;
	shieldTime = 0;
	moveLeft = true;
	moveRight = false;
	moveMode = 0;
    }


    public int getLeft ()  // Helper method to determine the x-coordinate of the left edge of the enemy
    {
	return x;
    } // getLeft method


    public int getRight ()  // Helper method te determine the x-coordinate of the right edge of the enemy
    {
	return getLeft () + getWidth ();
    } // getRight method


    public int getWidth ()  // Helper method to determine the fatness of the enemy
    {
	if (getClass ().equals (HardEnemy.class))
	{
	    return WIDTH2;
	}
	else if (getClass ().equals (Boss.class))
	{
	    return WIDTH3;
	}
	else
	{
	    return WIDTH;
	}
    } // getWidth method


    public int getHeight ()  // Helper method to determine the height of the enemy
    {
	if (getClass ().equals (HardEnemy.class))
	{
	    return HEIGHT2;
	}
	else if (getClass ().equals (Boss.class))
	{
	    return HEIGHT3;
	}
	else
	{
	    return HEIGHT;
	}
    } // getHeight method


    public int getY ()  // Helper method to determine the y-coordinate of the upper edge of the enemy
    {
	return y;
    } // getY method


    public void draw (int adjust)  // Helper method to draw the enemy
    {
	// Make sure that the image will actually appear on the screen--otherwise, drawing it would waste time and cause lag
	if (getLeft () - adjust <= 640 && getRight () - adjust >= 0) // Check x-coordinates
	{
	    if (y <= 500 && y + getHeight () >= 0) // Check y-coordinates
	    {
		// Image is in range so we draw it
		try
		{
		    if (shield)
		    {
			// Draw enemy with shield on
			if (moveLeft)
			{
			    // Draw enemy facing to the left
			    g1.drawImage (imgShieldLeft, x - adjust, y, null);
			}
			else
			{
			    // Draw enemy facing to the right
			    g1.drawImage (imgShieldRight, x - adjust, y, null);
			}
		    }
		    else
		    {
			// Draw enemy with shield off
			if (moveLeft)
			{
			    // Draw enemy facing to the left
			    g1.drawImage (imgNonShieldLeft, x - adjust, y, null);
			}
			else
			{
			    // Draw enemy facing to the right
			    g1.drawImage (imgNonShieldRight, x - adjust, y, null);
			}
		    }
		}
		catch (NullPointerException ex)
		{
		    // Draw black rectangle as placeholder
		    g1.setColor (Color.black);
		    g1.fillRect (x - adjust, y, 10, 10);
		}
	    }
	}
    } // draw method


    public void move ()  // Helper method to make the enemy move
    {
	// Count down time for shield
	shieldTime--;
	if (shieldTime == 0)
	{
	    shield = false; // Turn shield off
	}
	// Check the type of enemy
	if (getClass ().equals (HardEnemy.class) || getClass ().equals (Boss.class))
	{
	    // Use a different move method for hard enemies and the boss
	    moveHard ();
	    return;
	}
	// Check whether or not the enemy is at an edge of its platform
	if (getLeft () <= plat.getLeft ()) // If the enemy is at the left edge
	{
	    moveLeft = false; // The enemy should move to the right
	}
	else if (getRight () >= plat.getRight ()) // If the enemy is at the right edge
	{
	    moveLeft = true; // The enemy should move to the left
	}

	// Move in the correct direction
	if (moveLeft)
	{
	    x -= SPEED;
	}
	else
	{
	    x += SPEED;
	}
    } // move method


    public void moveHard ()  // Helper method to make a hard enemy move
    {
	// Choose movement strategy based on moveMode variable
	if (moveMode == 0)
	{
	    // The enemy moves back and forth between the edges of its platform
	    // Check whether or not the enemy is currently staying still
	    if (moveLeft == false && moveRight == false)
	    {
		moveLeft = true; // Make the enemy move to the left by default
	    }
	    // Check whether or not the enemy is at an edge of its platform
	    if (getLeft () <= plat.getLeft ()) // If the enemy is at the left edge
	    {
		moveLeft = false;
		moveRight = true; // The enemy should move to the right
	    }
	    else if (getRight () >= plat.getRight ()) // If the enemy is at the right edge
	    {
		moveRight = false;
		moveLeft = true; // The enemy should move to the left
	    }
	}
	else if (moveMode == 1)
	{
	    // The enemy stays still
	    moveLeft = moveRight = false;
	}
	else if (moveMode == 2)
	{
	    // The enemy targets the player
	    if (x >= p.getX ()) // If the enemy is to the right of the player
	    {
		moveLeft = true; // The enemy should move to the left
		moveRight = false;
	    }
	    else
	    {
		moveRight = true; // The enemy should move to the right
		moveLeft = false;
	    }
	    // Check whether or not the enemy is at an edge of its platform
	    if (getLeft () <= plat.getLeft ()) // If the enemy is at the left edge
	    {
		moveLeft = false; // The enemy should not move to the left
	    }
	    else if (getRight () >= plat.getRight ()) // If the enemy is at the right edge
	    {
		moveRight = false; // The enemy should not move to the right
	    }
	}

	// Move in the correct direction
	if (moveLeft)
	{
	    x -= SPEED2;
	}
	else if (moveRight)
	{
	    x += SPEED2;
	}

	// Randomly switch movement strategy
	if (Math.random () <= 0.01) // 1% chance of switching strategy
	{
	    moveMode = (int) (Math.random () * 3); // Randomly choose moveMode from 0 to 2
	}
    } // moveHard method


    public ArrayList takeDamage ()  // Helper method to make the enemy take damage
    {
	// Make sure shield is off before taking damage
	if (shield == false)
	{
	    health--;
	    if (health == 0)
	    {
		return die ();
	    }
	    // Turn on shield
	    shield = true;
	    shieldTime = SHIELD_TIME;
	}

	return new ArrayList ();
    } // takeDamage method


    public ArrayList die ()  // Helper method that activates when the enemy dies
    {
	// Declare variables
	ArrayList drop = new ArrayList (); // The items that the enemy drops

	// Decide whether the player is lucky enough to receive an item
	if (Math.random () * 100 <= luck)
	{
	    // Decide whether the player is lucky enough to receive money as well
	    if (Math.random () * 100 <= luck)
	    {
		drop.add (new Money (g1, (int) (luck * (0.8 + 0.4 * Math.random ()))));
	    }

	    // Randomly choose a basic item
	    double r = Math.random ();
	    if (r <= 1.0 / 3.0) // 1/3 chance of receiving a mushroom
	    {
		if (r <= 0.05) // How unlucky! You receive a trap
		{
		    drop.add (new HealTrap (g1, 200));
		}
		else
		{
		    drop.add (new Mushroom (g1));
		}
	    }
	    else if (r <= 2.0 / 3.0) // 1/3 chance of receiving wheat
	    {
		if (r <= 0.05 + 1.0 / 3.0) // How unlucky! You receive a trap
		{
		    drop.add (new Wheat (g1, true));
		}
		else
		{
		    drop.add (new Wheat (g1, false));
		}
	    }
	    else // 1/3 chance of receiving a shuriken
	    {
		if (r <= 0.05 + 2.0 / 3.0) // How unlucky! You receive a trap
		{
		    drop.add (new Shuriken (g1, true));
		}
		else
		{
		    drop.add (new Shuriken (g1, false));
		}
	    }
	    // Some items need to be implemented
	}
	else
	{
	    // Reward the player with some money
	    drop.add (new Money (g1, (int) (luck * (0.8 + 0.4 * Math.random ())))); // Amount of money depends on luck
	}

	// Deplete the enemy's health, if necessary
	health = 0;

	// Drop the items
	return drop;
    } // die method


    public boolean isDead ()  // Helper method to determine whether or not the enemy is dead
    {
	// Returns true if and only if health is at most 0
	return health <= 0;
    } // isDead method


    public void setPlatforms (ArrayList plats)  // Helper method to receive a platform
    {
	// Helper method to determine the right platform for the enemy
	for (int i = 0 ; i < plats.size () ; i++)
	{
	    Platform p1 = (Platform) plats.get (i);
	    // Test y-coordinate
	    if (y + getHeight () == p1.getY ())
	    {
		// Test x-coordinate
		if (getLeft () >= p1.getLeft () && getRight () <= p1.getRight ())
		{
		    // Assign platform to enemy
		    plat = p1;
		}
	    }
	}
    } // setPlatforms method


    public void setPlayer (Player p1)  // Helper method to receive a player
    {
	p = p1;
    } // setPlayer method


    public void harm () throws DeathException // Helper method to damage the player if touching the player
    {
	// Check x-coordinates
	if (p.getX () <= getRight () && p.getX () + p.getWidth () >= getLeft ())
	{
	    // Check y-coordinates
	    if (p.getY () <= getY () + getHeight () && p.getY () + p.getHeight () >= getY ())
	    {
		p.takeDamage (damage);
	    }
	}
    } // harm method


    public void unshield ()  // Helper method to remove the shield from the enemy
    {
	shield = false;
	shieldTime = 0;
    } // unshield method
} // Enemy class


