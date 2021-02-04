/*
Name: Michael Li
Date: December 30, 2017
Class code: ICS 2O3 - 02
Program name: Player
An Object that represents the player in the game.
*/

import java.awt.*;
import java.awt.image.*;
import java.math.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;
import hsa.*;

public class Player
{
    private Graphics g1; // The Graphics that will draw stuff
    private Image img; // The standing image of the player
    private Image imgLeft1; // The first image of the player when it runs to the left
    private Image imgLeft2; // The second image of the player when it runs to the left
    private Image imgLeft3; // The third image of the player when it runs to the left
    private Image imgRight1; // The first image of the player when it runs to the right
    private Image imgRight2; // The second image of the player when it runs to the right
    private Image imgRight3; // The third image of the player when it runs to the right
    private Image inventImg; // The image of the inventory screen
    private Image tearImg; // The image of the tears summoned by the Spirit of Tears
    private ArrayList items; // The player's inventory
    private ArrayList pickItems; // Items that can be picked up
    private ArrayList plats; // The list of platforms in the game
    private int money; // The amount of money that the player has
    private int bitcoins; // The number of Bitcoins that the player has
    private int coupons; // The number of coupons that the player has
    private int keys; // The number of keys that the player has
    private double x; // The x-coordinate of the top left corner of the player
    private double y; // The y-coordinate of the top left corner of the player
    private double vel; // The player's downward velocity
    private int moveTime; // The amount of time that the player has been moving in the same direction
    private boolean moveLeft; // Whether or not the player is moving to the left
    private boolean moveRight; // Whether or not the player is moving to the right
    private boolean onPlatform; // Whether or not the player is on a platform
    private int health; // The player's health
    private int max; // The player's maximum health
    private int invulTime; // The remaining time that the player is invulnerable
    private int slowedTime; // The remaining time that the player is slowed down
    private int freezeTime; // The remaining time that the player is frozen
    private int tearTime; // The remaining time that tears appear
    private Scroll curScroll; // The scroll that is currently being read
    private boolean cursed; // Whether or not the player is cursed

    private final double ACC = 0.4; // The player's gravitational acceleration
    private final double LAUNCH = 8.74; // The player's initial speed when jumping
    private final double SPEED = 4.68; // The player's horizontal speed
    private final int HEIGHT = 70; // The player's height
    private final int WIDTH = 50; // The player's fatness
    private final int ITEM_HEIGHT = 30; // The height of each item
    private final int ITEM_WIDTH = 30; // The fatness of each item
    private final int MAX_ITEMS = 20; // The size limit of the player's inventory

    public Player (Graphics g)  // Constructs a custom Player
    {
	// Load images
	try
	{
	    img = ImageIO.read (new File ("Player.png"));
	}
	catch (IOException ex)
	{
	}
	try
	{
	    imgLeft1 = ImageIO.read (new File ("PlayerLeft1.png"));
	}
	catch (IOException ex)
	{
	}
	try
	{
	    imgLeft2 = ImageIO.read (new File ("PlayerLeft2.png"));
	}
	catch (IOException ex)
	{
	}
	try
	{
	    imgLeft3 = ImageIO.read (new File ("PlayerLeft3.png"));
	}
	catch (IOException ex)
	{
	}
	try
	{
	    imgRight1 = ImageIO.read (new File ("PlayerRight1.png"));
	}
	catch (IOException ex)
	{
	}
	try
	{
	    imgRight2 = ImageIO.read (new File ("PlayerRight2.png"));
	}
	catch (IOException ex)
	{
	}
	try
	{
	    imgRight3 = ImageIO.read (new File ("PlayerRight3.png"));
	}
	catch (IOException ex)
	{
	}
	try
	{
	    inventImg = ImageIO.read (new File ("Inventory.png"));
	}
	catch (IOException ex)
	{
	}
	try
	{
	    tearImg = ImageIO.read (new File ("Tears.png"));
	}
	catch (IOException ex)
	{
	}

	// Declare variables
	g1 = g;
	items = new ArrayList ();
	pickItems = new ArrayList ();
	plats = new ArrayList ();
	money = 0;
	bitcoins = 0;
	coupons = 0;
	x = 300;
	y = 200;
	vel = 0;
	moveTime = 0;
	moveLeft = moveRight = false;
	onPlatform = true;
	health = max = 1000;
	invulTime = slowedTime = freezeTime = tearTime = 0;
    }


    public int getX ()  // Helper method to access the x-coordinate of the top left corner of the player
    {
	return (int) x;
    } // getX method


    public int getY ()  // Helper method to access the y-coordinate of the top left corner of the player
    {
	return (int) y;
    } // getY method


    public int getHeight ()  // Helper method to access the height of the player
    {
	return HEIGHT;
    } // getHeight method


    public int getWidth ()  // Helper method to access the fatness of the player
    {
	return WIDTH;
    } // getWidth method


    public int getMoney ()  // Helper method to access the richness of the player
    {
	return money;
    } // getMoney method


    public Player getClone ()  // Helper method to make a copy of the player
    {
	Player p1 = new Player (g1);

	// Copy variables onto the new player
	for (int i = 0 ; i < items.size () ; i++)
	{
	    p1.items.add (items.get (i));
	}
	for (int i = 0 ; i < pickItems.size () ; i++)
	{
	    p1.pickItems.add (pickItems.get (i));
	}
	for (int i = 0 ; i < plats.size () ; i++)
	{
	    p1.plats.add (plats.get (i));
	}
	p1.money = this.money;
	p1.bitcoins = this.bitcoins;
	p1.coupons = this.coupons;
	p1.keys = this.keys;
	p1.health = this.health;
	p1.max = this.max;
	p1.invulTime = this.invulTime;
	p1.slowedTime = this.slowedTime;
	p1.freezeTime = this.freezeTime;
	p1.tearTime = this.tearTime;
	p1.curScroll = this.curScroll;
	p1.cursed = this.cursed;
	return p1;
    } // getClone method


    public void setPlatforms (ArrayList plat1)  // Helper method to store the platforms in the game
    {
	plats = plat1;
    } // setPlatforms method


    public void draw ()  // Helper method to draw the player
    {
	// Display scroll message, if applicable
	try
	{
	    curScroll.read ();
	}
	catch (NullPointerException ex)  // There might be no current scroll
	{
	}

	// Display platforms behind the player
	for (int i = 0 ; i < plats.size () ; i++)
	{
	    Platform pl = (Platform) plats.get (i);

	    // Make sure that the image will actually appear on the screen--otherwise, drawing it will watse time and cause lag
	    if (pl.getLeft () - x + 300 <= 640 && pl.getRight () - x + 300 >= 0) // Check x-coordinates
	    {
		if (pl.getY () <= 500) // Check y-coordinates
		{
		    // Image is in range so we draw it
		    pl.draw ((int) x - 300); // Platform image will be adjusted based on player's x-coordinate
		}
	    }
	}

	// Display dropped items that need to be picked up
	for (int i = 0 ; i < pickItems.size () ; i++)
	{
	    ArrayList temp = (ArrayList) pickItems.get (i);
	    Item itm = (Item) temp.get (0);
	    int x1 = ((Integer) temp.get (1)).intValue ();
	    int y1 = ((Integer) temp.get (2)).intValue ();
	    itm.draw (300 - (int) x + x1, y1);
	}

	// Draw player in the middle of the screen
	try
	{
	    // Make decisions based on the player's movement
	    if (onPlatform == false) // If the player is in the air
	    {
		if (moveLeft == true) // If the player is jumping to the left
		{
		    g1.drawImage (imgLeft1, 300, (int) y, null);
		}
		else // If the player is jumping to the right or straight up
		{
		    g1.drawImage (imgRight1, 300, (int) y, null);
		}
	    }
	    else // If the player is on a platform
	    {
		if (moveLeft == true) // If the player is running to the left
		{
		    // Oscillate between the three images
		    switch (moveTime % 20)
		    {
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			    g1.drawImage (imgLeft1, 300, (int) y, null);
			    break;
			case 10:
			case 11:
			case 12:
			case 13:
			case 14:
			case 15:
			    g1.drawImage (imgLeft3, 300, (int) y, null);
			    break;
			default:
			    g1.drawImage (imgLeft2, 300, (int) y, null);
		    }
		}
		else if (moveRight == true) // If the player is running to the right
		{
		    // Oscillate between the three images
		    switch (moveTime % 20)
		    {
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			    g1.drawImage (imgRight1, 300, (int) y, null);
			    break;
			case 10:
			case 11:
			case 12:
			case 13:
			case 14:
			case 15:
			    g1.drawImage (imgRight3, 300, (int) y, null);
			    break;
			default:
			    g1.drawImage (imgRight2, 300, (int) y, null);
		    }
		}
		else // If the player is standing still
		{
		    g1.drawImage (img, 300, (int) y, null);
		}
	    }
	}
	catch (NullPointerException ex)  // If the image is not found
	{
	    // Draw black rectangle as placeholder
	    g1.setColor (Color.black);
	    g1.fillRect (300, (int) y, 50, 70);
	}

	// Draw health bar in top left corner of the screen
	g1.setColor (Color.black);
	g1.drawRect (18, 18, max * 3 / 20 + 4, 24);
	g1.drawRect (19, 19, max * 3 / 20 + 2, 22);
	g1.setColor (Color.red);
	g1.fillRect (20, 20, health * 3 / 20, 20);

	// Display the player's health
	g1.setColor (Color.black);
	g1.setFont (new Font ("Courier New", Font.BOLD, 15));
	g1.drawString ("HP: " + health + "/" + max, 20 + max * 3 / 20 + 10, 30);

	// Display tears, if applicable
	if (tearTime > 0)
	{
	    try
	    {
		g1.drawImage (tearImg, 0, 0, null);
	    }
	    catch (NullPointerException ex)
	    {
		// Draw blue circles as placeholders
		g1.setColor (Color.blue);
		g1.fillOval (20, 20, 50, 50);
		g1.fillOval (30, 110, 130, 130);
		g1.fillOval (400, 200, 90, 90);
		g1.fillOval (200, 300, 80, 80);
		g1.fillOval (280, 180, 60, 60);
		g1.fillOval (470, 360, 100, 100);
		g1.fillOval (450, 30, 120, 120);
	    }
	}
    } // draw method


    public void move () throws DeathException  // Helper method to move the player
    {
	// Count time
	moveTime++;
	invulTime--;
	slowedTime--;
	freezeTime--;
	tearTime--;

	// Check status conditions
	if (freezeTime > 0)
	{
	    // Player is frozen and cannot move
	    moveRight = false;
	    moveLeft = false;
	    onPlatform = true;
	    return;
	}
	if (cursed)
	{
	    takeDamage (50);
	}

	// Vertical movement
	if (onPlatform && ICS_Summative.upPressed)
	{
	    vel = -LAUNCH; // Jump up
	    // The velocity is measured downward so it is negative when the player moves up.
	}
	else if (onPlatform && ICS_Summative.downPressed)
	{
	    y += 0.01; // Drop down from platform
	}
	onPlatform = false; // Default assumption that player is not on a platform
	double y1 = y + HEIGHT; // The bottom of the player
	double y2 = y1 + vel; // The potental new bottom of the player
	// Check if the player will be stopped by a platform
	for (int i = 0 ; i < plats.size () ; i++)
	{
	    Platform p = (Platform) plats.get (i);
	    // Check x-coordinate range
	    if (x < p.getRight () && x + WIDTH > p.getLeft ())
	    {
		// Check y-coordinate range
		if (y2 >= p.getY () && y1 <= p.getY ())
		{
		    // The player will be stopped at the platform
		    y2 = p.getY ();
		    vel = 0;

		    // The player will be on a platform
		    onPlatform = true;
		}
	    }
	}
	y = y2 - HEIGHT;
	vel += ACC;
	// Player will die if he falls below the screen
	if (y > 500)
	{
	    takeDamage (health);
	}

	// Horizontal movement
	if (ICS_Summative.rightPressed)
	{
	    if (slowedTime > 0)
	    {
		x += SPEED / 2; // The player is slowed down
	    }
	    else
	    {
		x += SPEED;
	    }
	    // If the player has switched direction to move to the right
	    if (moveRight == false)
	    {
		moveRight = true;
		moveTime = 0;
	    }
	}
	else if (moveRight == true)
	{
	    // The player has switched direction to not move to the right
	    moveRight = false;
	    moveTime = 0;
	}
	if (ICS_Summative.leftPressed)
	{
	    if (slowedTime > 0)
	    {
		x -= SPEED / 2; // The player is slowed down
	    }
	    else
	    {
		x -= SPEED;
	    }
	    // If the player has switch direction to move to the left
	    if (moveLeft == false)
	    {
		moveLeft = true;
		moveTime = 0;
	    }
	}
	else if (moveLeft == true)
	{
	    // The player has switched direction to not move to the left
	    moveLeft = false;
	    moveTime = 0;
	}

	// Loop through items on the ground and pick them up if player touches item
	for (int i = 0 ; i < pickItems.size () ; i++)
	{
	    // Access the coordinates of the item
	    ArrayList temp = (ArrayList) pickItems.get (i);
	    int xItem = ((Integer) temp.get (1)).intValue ();
	    int yItem = ((Integer) temp.get (2)).intValue ();

	    // Check x-coordinates
	    if (getX () <= xItem + ITEM_WIDTH && getX () + getWidth () >= xItem)
	    {
		// Check y-coordinates
		if (getY () <= yItem + ITEM_HEIGHT && getY () + getHeight () >= yItem)
		{
		    // Pick up item
		    receive ((Item) temp.get (0));

		    // Remove item from ground
		    pickItems.remove (i);
		    i--;
		}
	    }
	}

	// Check the scroll
	if (ICS_Summative.xPressed)
	{
	    // Close the scroll
	    curScroll = null;
	    uncurse ();
	}
    } // move method


    public void receive (Item item)  // Helper method to receive an item
    {
	if (item.getClass ().equals (Money.class)) // If the player receives money
	{
	    money += ((Money) item).getAmount ();
	}
	else if (item.getClass ().equals (Scroll.class)) // If the player picks up and reads a scroll
	{
	    curScroll = (Scroll) item;
	}
	else if (item.getClass ().equals (Bitcoin.class)) // If the player receives a Bitcoin
	{
	    bitcoins++;
	}
	else if (item.getClass ().equals (Coupon.class)) // If the player receives a coupon
	{
	    coupons++;
	}
	else if (item.getClass ().equals (ItemKey.class)) // If the player receives a key
	{
	    keys++;
	}
	else if (item.getClass ().equals (Wheat.class)) // If the player receives wheat
	{
	    Wheat wh = (Wheat) item;
	    // Check to see if the wheat is cursed
	    if (wh.isCursed ())
	    {
		curse (); // The player is cursed
	    }

	    // Loop through inventory to see if there is already wheat in the inventory
	    for (int i = 0 ; i < items.size () ; i++)
	    {
		if (items.get (i).getClass ().equals (Wheat.class))
		{
		    // Stack the wheat
		    ((Wheat) items.get (i)).collect (wh.isCursed ());
		    return;
		}
	    }

	    // The inventory does not have wheat so we add it
	    if (items.size () < MAX_ITEMS) // Make sure the inventory has room
	    {
		items.add (wh);
	    }
	}
	else if (items.size () < MAX_ITEMS) // Make sure the inventory has room
	{
	    items.add (item);
	}
    } // receive method


    public void use (int index) throws DeathException // Helper method to use an item in the inventory
    {
	if (index >= 0 && index < items.size ()) // Make sure that index is valid
	{
	    Item item = (Item) items.get (index);

	    // Make decisions based on type of item
	    if (Healer.class.isAssignableFrom (item.getClass ()))
	    {
		// Use healing item
		Healer he = (Healer) item;
		health += he.getPower ();
		if (health > max)
		{
		    // Player's health should not exceed maximum health
		    health = max;
		}
		if (health <= 0) // If the item was a trap and the player dies
		{
		    throw new DeathException ();
		}
	    }
	    else if (Weapon.class.isAssignableFrom (item.getClass ()))
	    {
		// Check special types of weapons
		if (item.getClass ().equals (Wheat.class))
		{
		    // Create new wheat that will be thrown
		    Wheat temp = new Wheat (g1, ((Wheat) item).isCursed ());

		    // Set information about weapon
		    temp.set (getX (), getY () + 20, moveLeft);

		    // Use the weapon
		    ICS_Summative.useWeapon (temp);

		    // Decrement amount of wheat
		    Wheat wh = (Wheat) item;
		    wh.use ();
		    if (!wh.exists ()) // If there is no wheat left
		    {
			// Remove the wheat from the inventory
			items.remove (index);
		    }
		    if (!wh.isCursed ()) // If there is no longer any cursed wheat
		    {
			// Un-curse the player
			uncurse ();
		    }

		    // Check to see if the thrown wheat is cursed
		    if (!temp.isCursed ())
		    {
			ICS_Summative.wheatAchieve = false; // The player used non-cursed wheat and cannot get the achievement
		    }
		    return; // End the method so that the wheat doesn't do non-wheat stuff
		}

		// Set information about weapon
		((Weapon) item).set (getX (), getY () + 20, moveLeft);

		// Use weapon
		ICS_Summative.useWeapon ((Weapon) item);
	    }
	    else if (item.getClass ().equals (TrapBuster.class))
	    {
		// Use the Trap Buster
		ICS_Summative.bustTrap = true;
	    }
	    else if (item.getClass ().equals (MaxHealthIncreaser.class))
	    {
		// Use Max Health Increaser
		MaxHealthIncreaser mhi = (MaxHealthIncreaser) item;
		// Increase both the max health and the current health
		health += mhi.getPower ();
		max += mhi.getPower ();
	    }

	    // Remove item from inventory
	    items.remove (index);
	}
    } // use method


    public void openInventory (Console c, BufferedImage buffer) throws DeathException // Helper method to open and access the inventory
    {
	// Close the current scroll
	curScroll = null;

	// Declare variables
	boolean leftPressed = false; // Whether or not the left arrow key is being pressed
	boolean downPressed = false; // Whether or not the down arrow key is being pressed
	boolean rightPressed = false; // Whether or not the right arrow key is being pressed
	boolean upPressed = false; // Whether or not the up arrow key is being pressed
	int x1 = 0; // The x-coordinate of the item that the player wants to access
	int y1 = 0; // The y-coordinate of the item that the player wants to access

	// Clean the console input
	while (c.isCharAvail ())
	{
	    c.getChar ();
	}

	// Keep inventory open until the x key is pressed
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
	    // Display amount of money
	    g1.setFont (new Font ("Courier New", Font.PLAIN, 20));
	    g1.setColor (Color.black);
	    g1.drawString ("Money: $" + money, 260, 120);
	    // Loop through items to draw them
	    for (int i = 0 ; i < items.size () ; i++)
	    {
		Item it = (Item) items.get (i);
		it.draw (105 + i % 5 * 95, 145 + i / 5 * 77);
	    }
	    // Draw border around selected item
	    g1.setColor (Color.white);
	    g1.drawRect (92 + x1 * 95, 142 + y1 * 77, 75, 55);
	    g1.drawRect (91 + x1 * 95, 141 + y1 * 77, 77, 57); // Make border thicker
	    g1.drawRect (93 + x1 * 95, 143 + y1 * 77, 73, 53);
	    g1.drawRect (94 + x1 * 95, 144 + y1 * 77, 71, 51);
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
		    if (x1 < 4)
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

	    // Check <Enter> key
	    if (c.isCharAvail () && c.getChar () == '\n')
	    {
		// The user has selected an item to use
		use (x1 + 5 * y1);
		return;
	    }
	}
    } // openInventory method


    public boolean hasBitcoin ()  // Helper method to determine whether or not the player has a Bitcoin
    {
	return bitcoins > 0;
    } // hasBitcoin method


    public boolean hasCoupon ()  // Helper method to determine whether or not the player has a coupon
    {
	return coupons > 0;
    } // hasCoupon method


    public boolean hasKey ()  // Helper method to determine whether or not the player has a key
    {
	return keys > 0;
    } // hasKey method


    public void pay (int amount)  // Helper method to pay money to buy items
    {
	// Check special values for the amount
	if (amount == -1) // Special value that means "Pay a coupon"
	{
	    coupons--;
	}
	else if (amount == -2) // Special value that means "Pay a Bitcoin"
	{
	    bitcoins--;
	}
	else
	{
	    money -= amount;
	}
    } // pay method


    public void heal ()  // Helper method to heal player after finishing level
    {
	health = max;
	invulTime = slowedTime = freezeTime = tearTime = 0;
    } // heal method


    public void heal (int amount)  // Helper method to heal player by a specified amount
    {
	health += amount;
	if (health > max)
	{
	    // Player's health should not exceed maximum health
	    health = max;
	}
    } // heal method


    public void takeDamage (int dmg) throws DeathException // Helper method to make the player take a certain amount of damage
    {
	if (invulTime <= 0) // If player is not invulnerable
	{
	    health -= dmg;
	    invulTime = 20; // Give player some invulnerability time
	}

	if (health <= 0) // If player is dead
	{
	    throw new DeathException ();
	}
    } // takeDamage method


    public void harm (Enemy e)  // Helper method to harm an enemy if jumping on it
    {
	double y1 = y + HEIGHT; // The bottom of the player
	double y2 = y1 + vel; // The potental new bottom of the player
	// Check x-coordinate range
	if (x < e.getRight () && x + WIDTH > e.getLeft ())
	{
	    // Check y-coordinate range
	    if (y2 >= e.getY () && y1 <= e.getY ())
	    {
		// The player will jump on the enemy and harm it
		vel = -LAUNCH;
		ArrayList drop = e.takeDamage (); // Temporarily store items that get dropped
		process (drop, e); // Process the dropped items
	    }
	}
    } // harm method


    public void process (ArrayList drop, Enemy e)  // Helper method to process a list of dropped items
    {
	// Loop through dropped items
	for (int i = 0 ; i < drop.size () ; i++)
	{
	    // Check for special types of items
	    if (drop.get (i).getClass ().equals (BridgePotion.class) && e.getClass ().equals (Boss.class)) // Bridge Potion only dropped by boss and not supposed to be collected
	    {
		ICS_Summative.bossKill = true; // The player killed the boss
	    }
	    else
	    {
		// Add new storage structure for new item
		ArrayList temp = new ArrayList ();
		temp.add (drop.get (i));
		// Store coordinates of dropped item
		temp.add (new Integer (e.getLeft ()));
		temp.add (new Integer (e.getY () + e.getHeight () - ITEM_HEIGHT)); // To make item the right amount above the ground

		// Register new item
		pickItems.add (temp);
	    }
	}
    }


    public void slow (int time)  // Helper method to slow down the player
    {
	slowedTime = time;
    } // slow method


    public void freeze (int time)  // Helper method to freeze the player
    {
	freezeTime = time;
    } // freeze method


    public void tears (int time)  // Helper method to summon tears
    {
	tearTime = time;
    } // tears method


    public void annaTrap ()  // Helper method to remove all bridge potions after activating an Anna trap.
    {
	for (int i = 0 ; i < items.size () ; i++)
	{
	    if (items.get (i).getClass ().equals (BridgePotion.class))
	    {
		items.remove (i);
		i--;
	    }
	}
    } // annaTrap method


    public void curse ()  // Helper method to curse the player after opening a cursed scroll
    {
	cursed = true;
    } // curse method


    public void uncurse ()  // Helper method to uncurse the player after closing a cursed scroll
    {
	cursed = false;
    } // uncurse method


    public void checkWheat ()  // Helper method to check for dropped wheat
    {
	for (int i = 0 ; i < pickItems.size () ; i++)
	{
	    Item item = (Item) pickItems.get (i);
	    if (item.getClass ().equals (Wheat.class)) // If there is wheat
	    {
		if (!((Wheat) item).isCursed ()) // If wheat is not cursed
		{
		    // The player missed some wheat and cannot earn the achievement
		    ICS_Summative.wheatAchieve = false;
		}
	    }
	}
    }
} // Player class


