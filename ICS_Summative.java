/*
Name: Michael Li/ Crystal Yan
Date: December 26, 2017
Class code: ICS 2O3 - 02
Program name: ICS_Summative
A game where a character jumps on enemies, collects items, and defeats Mr. Brossard.
*/

import java.awt.*;
import java.awt.image.*;
import java.math.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;
import hsa.*;

public class ICS_Summative
{
    static Console c; // The output console
    static Image deadImg; // The image that shows up when the player dies
    static Image endImg; // The image that shows up when the player finishes the game

    public static boolean leftPressed = false; // Whether or not the left arrow key is being pressed
    public static boolean downPressed = false; // Whether or not the down arrow key is being pressed
    public static boolean rightPressed = false; // Whether or not the right arrow key is being pressed
    public static boolean upPressed = false; // Whether or not the up arrow key is being pressed
    public static boolean xPressed = false; // Whether or not the x key is being pressed
    public static boolean iPressed = false; // Whether or not the i key is being pressed
    public static boolean enterPressed = false; // Whether or not <Enter> is being pressed
    public static boolean bustTrap = false; // Whether or not the player is busting traps
    public static boolean bossKill = false; // Whether or not the player recently killed the boss
    public static boolean portalVal = true; // Whether or not the portal is valid
    public static boolean wheatAchieve = true; // Whether or not the player is eligible for the wheat achievement

    public static Player p; // The player that the user controls
    public static ArrayList plats; // The list of platforms that the player will jump on
    public static ArrayList enemies; // The list of enemies that the player will defeat
    public static ArrayList traps; // The list of traps that the player will avoid
    public static ArrayList spirits; // The list of Spirits that the player will be afraid of
    public static ArrayList chests; // The list of treasure chests that the player will open
    public static ArrayList stores; // The list of stores that the player will buy from
    public static ArrayList weapons; // The list of weapons that the player is using
    public static Portal por; // The portal that the player will travel through
    public static Player saved; // The saved version of the player

    public static long time1; // The variable to help measure time between each frame

    public static void resetTime ()  // Helper method to reset the time counter
    {
	time1 = System.currentTimeMillis ();
    } // resetTime method


    public static void useWeapon (Weapon we)  // Helper method to use a weapon
    {
	// Add the weapon to the ArrayList
	weapons.add (we);
    } // useWeapon method


    public static void setUpTutorial (Graphics g1, BufferedImage buffer)  // Helper method to set up the tutorial level
    {
	// Set up player
	p = saved.getClone ();

	// Set up platforms
	plats = new ArrayList ();
	plats.add (new Platform (g1, 0, 270, 9100)); // The platform you spawn on
	// Loop to add platforms containing enemies waiting to be killed
	for (int i = 0 ; i < 36 ; i++)
	{
	    plats.add (new Platform (g1, 250 * (i + 1), 330, 100));
	}
	p.setPlatforms (plats);
	// Add platforms to place traps on
	plats.add (new Platform (g1, 6000, 390, 100));
	plats.add (new Platform (g1, 6250, 390, 100));
	plats.add (new Platform (g1, 6500, 390, 100));
	plats.add (new Platform (g1, 6750, 390, 100));
	plats.add (new Platform (g1, 7000, 390, 100));
	// Add platform to place portal on
	plats.add (new Platform (g1, 9000, 500, 100));

	// Set up enemies
	enemies = new ArrayList ();
	enemies.add (new TutorialEnemy1 (g1, 250, 280, 0)); // Beginning of tutorial
	enemies.add (new TutorialEnemy1 (g1, 500, 280, 1));
	enemies.add (new TutorialEnemy1 (g1, 750, 280, 2)); // Demonstrate closing scrolls
	enemies.add (new TutorialEnemy1 (g1, 1000, 280, 3)); // Demonstrate inventory
	enemies.add (new TutorialEnemy1 (g1, 1250, 280, 4)); // Demonstrate money
	enemies.add (new TutorialEnemy1 (g1, 1500, 280, 5)); // Demonstrate Mushroom
	enemies.add (new TutorialEnemy1 (g1, 1750, 280, 6)); // Demonstrate shuriken
	enemies.add (new TutorialEnemy1 (g1, 2000, 280, 7)); // Demonstrate wheat
	enemies.add (new TutorialEnemy1 (g1, 2250, 280, 8)); // Demonstrate item traps
	enemies.add (new TutorialEnemy1 (g1, 2500, 280, 9)); // Demonstrate Calcium
	enemies.add (new TutorialEnemy1 (g1, 2750, 280, 10)); // Demonstrate Max Health Increaser
	enemies.add (new TutorialEnemy1 (g1, 3000, 280, 11)); // Demonstrate Bridge Potion
	enemies.add (new TutorialEnemy1 (g1, 3250, 280, 12)); // Demonstrate Death Spell
	enemies.add (new TutorialEnemy1 (g1, 3500, 280, 13)); // Demonstrate Vampire Spell
	enemies.add (new TutorialEnemy1 (g1, 3750, 280, 14)); // Demonstrate store
	enemies.add (new TutorialEnemy1 (g1, 4000, 280, 15)); // Demonstrate slowness of collecting money
	enemies.add (new TutorialEnemy1 (g1, 4250, 280, 16)); // Demonstrate slowness of collecting money
	enemies.add (new TutorialEnemy1 (g1, 4500, 280, 17)); // Demonstrate slowness of collecting money
	enemies.add (new TutorialEnemy1 (g1, 4750, 280, 18)); // Demonstrate slowness of collecting money
	enemies.add (new TutorialEnemy1 (g1, 5000, 280, 19)); // Demonstrate slowness of collecting money
	enemies.add (new TutorialEnemy1 (g1, 5250, 280, 20)); // Demonstrate treasure chest
	enemies.add (new TutorialEnemy1 (g1, 5500, 280, 21)); // Demonstrate high-level treasure chest
	enemies.add (new TutorialEnemy1 (g1, 5750, 280, 22)); // Demonstrate key
	enemies.add (new TutorialEnemy1 (g1, 6000, 280, 23)); // Demonstrate Spike Pit
	enemies.add (new TutorialEnemy1 (g1, 6250, 280, 24)); // Demonstrate Anna Trap
	enemies.add (new TutorialEnemy1 (g1, 6500, 280, 25)); // Demonstrate Spawner Trap
	enemies.add (new TutorialEnemy1 (g1, 6750, 280, 26)); // Demonstrate Ex-K Seedy Trap
	enemies.add (new TutorialEnemy1 (g1, 7000, 280, 27)); // Demonstrate Love Trap
	enemies.add (new TutorialEnemy1 (g1, 7250, 280, 28)); // Demonstrate Trap Buster
	enemies.add (new TutorialEnemy1 (g1, 7750, 280, 29)); // Demonstrate coupon
	enemies.add (new TutorialEnemy1 (g1, 8000, 280, 30)); // Demonstrate more coupons
	enemies.add (new TutorialEnemy1 (g1, 8250, 280, 31)); // Demonstrate Bitcoin
	enemies.add (new TutorialEnemy1 (g1, 8500, 280, 32)); // Demonstrate tougher enemies
	enemies.add (new TutorialEnemy2 (g1, 8750, 280, 0)); // Demonstrate tougher enemies
	enemies.add (new TutorialEnemy1 (g1, 9000, 280, 33)); // Demonstrate portal
	for (int i = 0 ; i < enemies.size () ; i++)
	{
	    Enemy temp = (Enemy) enemies.get (i);
	    temp.setPlatforms (plats);
	    temp.setPlayer (p);
	}

	// Set up traps
	traps = new ArrayList ();
	// Add various traps to demonstrate them
	traps.add (new SpikePit (g1, 6025, 390));
	traps.add (new AnnaTrap (g1, 6275, 390));
	traps.add (new SpawnerTrap (g1, 6525, 390));
	traps.add (new ExKSeedyTrap (g1, 6775, 390));
	traps.add (new LoveTrap (g1, 7025, 390));
	// Add traps to demonstrate Trap Buster
	// Love Traps freeze the player while Ex-K Seedy Traps kill him
	// So the player is forced to use a Trap Buster
	// Or is he?...
	traps.add (new LoveTrap (g1, 7450, 270));
	traps.add (new ExKSeedyTrap (g1, 7450, 270));
	traps.add (new LoveTrap (g1, 7500, 270));
	traps.add (new ExKSeedyTrap (g1, 7500, 270));
	traps.add (new LoveTrap (g1, 7550, 270));
	traps.add (new ExKSeedyTrap (g1, 7550, 270));
	traps.add (new LoveTrap (g1, 7600, 270));
	traps.add (new ExKSeedyTrap (g1, 7600, 270));
	for (int i = 0 ; i < traps.size () ; i++)
	{
	    Trap tr = (Trap) traps.get (i);
	    tr.setPlayer (p);
	}

	// Set up treasure chests
	chests = new ArrayList ();
	chests.add (new Chest (c, g1, 5275, 220, false));
	chests.add (new Chest (c, g1, 5525, 220, true));
	chests.add (new Chest (c, g1, 5775, 220, true));

	// Set up stores
	stores = new ArrayList ();
	stores.add (new Store (c, g1, 3775, 220, buffer));
	stores.add (new Store (c, g1, 7775, 220, buffer));

	// Set up Spirits
	spirits = new ArrayList ();

	// Set up weapons
	weapons = new ArrayList ();

	// Set up portal
	por = new Portal (g1, 9000, 400);

	// Set up time counter
	resetTime ();
    } // setUpTutorial method


    public static void setUpRegular (Graphics g1, BufferedImage buffer)  // Helper method to set up the level that this program usually has
    {
	// Set up player
	p = saved.getClone ();

	// Set up platforms
	plats = new ArrayList ();
	plats.add (new Platform (g1, 0, 270, 500));
	plats.add (new Platform (g1, 0, 369, 1000));
	plats.add (new Platform (g1, 750, 270, 200));
	plats.add (new Platform (g1, 1250, 369, 500));
	plats.add (new Platform (g1, 2000, 369, 500));
	plats.add (new Platform (g1, 2750, 369, 500));
	plats.add (new Platform (g1, 2750, 320, 50));
	plats.add (new Platform (g1, 3200, 320, 50));
	p.setPlatforms (plats);

	// Set up enemies
	enemies = new ArrayList ();
	enemies.add (new EasyEnemy (g1, 100, 220));
	enemies.add (new NormalEnemy (g1, 250, 220));
	enemies.add (new HardEnemy (g1, 800, 200));
	enemies.add (new NormalEnemy (g1, 1300, 319));
	enemies.add (new NormalEnemy (g1, 1600, 319));
	enemies.add (new HardEnemy (g1, 2000, 299));
	enemies.add (new HardEnemy (g1, 2000, 299));
	enemies.add (new Boss (g1, 2750, 269));
	for (int i = 0 ; i < enemies.size () ; i++)
	{
	    Enemy temp = (Enemy) enemies.get (i);
	    temp.setPlatforms (plats);
	    temp.setPlayer (p);
	}

	// Set up traps
	traps = new ArrayList ();
	traps.add (new SpawnerTrap (g1, 800, 369));
	traps.add (new ExKSeedyTrap (g1, 100, 369));
	traps.add (new SpawnerTrap (g1, 3000, 369));
	for (int i = 0 ; i < traps.size () ; i++)
	{
	    Trap tr = (Trap) traps.get (i);
	    tr.setPlayer (p);
	}

	// Set up treasure chests
	chests = new ArrayList ();
	chests.add (new Chest (c, g1, 900, 319, false));
	chests.add (new Chest (c, g1, 1650, 319, true));

	// Set up stores
	stores = new ArrayList ();
	stores.add (new Store (c, g1, 20, 319, buffer));
	stores.add (new Store (c, g1, 2400, 319, buffer));

	// Set up Spirits
	spirits = new ArrayList ();

	// Set up time counter
	resetTime ();
    } // setUpRegular method


    public static void setUpLevel1 (Graphics g1, BufferedImage buffer)  // Helper method to set up the first level
    {
	// Set up player
	p = saved.getClone ();

	// Set up platforms
	plats = new ArrayList ();
	plats.add (new Platform (g1, 0, 270, 500));
	plats.add (new Platform (g1, 600, 220, 1100));
	plats.add (new Platform (g1, 750, 200, 200));
	plats.add (new Platform (g1, 1200, 400, 500));
	plats.add (new Platform (g1, 1700, 301, 500));
	plats.add (new Platform (g1, 2000, 400, 1000));
	plats.add (new Platform (g1, 2900, 301, 1150));
	plats.add (new Platform (g1, 3300, 400, 200));
	plats.add (new Platform (g1, 3800, 480, 200));
	p.setPlatforms (plats);

	// Set up enemies
	enemies = new ArrayList ();
	enemies.add (new EasyEnemy (g1, 100, 220));
	enemies.add (new EasyEnemy (g1, 250, 220));
	enemies.add (new EasyEnemy (g1, 800, 150));
	enemies.add (new EasyEnemy (g1, 1000, 170));
	enemies.add (new EasyEnemy (g1, 1300, 350));
	enemies.add (new EasyEnemy (g1, 1600, 350));
	enemies.add (new EasyEnemy (g1, 2000, 350));
	enemies.add (new EasyEnemy (g1, 2700, 350));
	enemies.add (new EasyEnemy (g1, 3000, 251));
	enemies.add (new EasyEnemy (g1, 3800, 251));
	enemies.add (new EasyEnemy (g1, 3850, 430));
	for (int i = 0 ; i < enemies.size () ; i++)
	{
	    Enemy temp = (Enemy) enemies.get (i);
	    temp.setPlatforms (plats);
	    temp.setPlayer (p);
	}

	// Set up traps
	traps = new ArrayList ();
	traps.add (new SpikePit (g1, 880, 200));
	traps.add (new SpikePit (g1, 1200, 400));
	traps.add (new LoveTrap (g1, 3000, 301));
	for (int i = 0 ; i < traps.size () ; i++)
	{
	    Trap tr = (Trap) traps.get (i);
	    tr.setPlayer (p);
	}

	// Set up treasure chests
	chests = new ArrayList ();
	chests.add (new Chest (c, g1, 1400, 350, false));
	chests.add (new Chest (c, g1, 2000, 350, false));
	chests.add (new Chest (c, g1, 2900, 350, false));
	chests.add (new Chest (c, g1, 3350, 350, true));


	// Set up stores
	stores = new ArrayList ();
	stores.add (new Store (c, g1, 2100, 251, buffer));

	// Set up Spirits
	spirits = new ArrayList ();

	// Set up weapons
	weapons = new ArrayList ();

	// Set up portal
	por = new Portal (g1, 3850, 380);

	// Set up time counter
	resetTime ();
    } // setUpLevel1 method


    public static void setUpBoss1 (Graphics g1, BufferedImage buffer)  // Helper method to set up the first boss room
    {
	// Set up player
	p = saved.getClone ();

	// Set up platforms
	plats = new ArrayList ();
	plats.add (new Platform (g1, 300, 280, 500));
	plats.add (new Platform (g1, 0, 300, 2000));
	plats.add (new Platform (g1, 750, 280, 500));
	plats.add (new Platform (g1, 1250, 329, 800));
	plats.add (new Platform (g1, 2000, 329, 500));
	p.setPlatforms (plats);

	// Set up enemies
	enemies = new ArrayList ();
	enemies.add (new EasyEnemy (g1, 150, 250));
	enemies.add (new NormalEnemy (g1, 100, 250));
	enemies.add (new NormalEnemy (g1, 860, 230));
	enemies.add (new NormalEnemy (g1, 1300, 279));
	enemies.add (new NormalEnemy (g1, 1500, 279));
	enemies.add (new NormalEnemy (g1, 1000, 230));
	enemies.add (new NormalEnemy (g1, 1700, 279));
	for (int i = 0 ; i < enemies.size () ; i++)
	{
	    Enemy temp = (Enemy) enemies.get (i);
	    temp.setPlatforms (plats);
	    temp.setPlayer (p);
	}

	// Set up traps
	traps = new ArrayList ();
	traps.add (new SpawnerTrap (g1, 800, 280));
	traps.add (new ExKSeedyTrap (g1, 100, 300));
	traps.add (new SpawnerTrap (g1, 1200, 280));
	traps.add (new LoveTrap (g1, 1600, 329));
	traps.add (new SpikePit (g1, 2000, 329));
	for (int i = 0 ; i < traps.size () ; i++)
	{
	    Trap tr = (Trap) traps.get (i);
	    tr.setPlayer (p);
	}

	// Set up chests
	chests = new ArrayList ();

	// Set up stores
	stores = new ArrayList ();

	// Set up Spirits
	spirits = new ArrayList ();

	// Set up weapons
	weapons = new ArrayList ();

	// Set up portal
	por = new Portal (g1, 2400, 269);

	// Set up time counter
	resetTime ();
    } // setUpBoss1 method


    public static void setUpLevel2 (Graphics g1, BufferedImage buffer)  // Helper method to set up the second level
    {
	// Set up player
	p = saved.getClone ();

	// Set up platforms
	plats = new ArrayList ();
	plats.add (new Platform (g1, 0, 270, 500));
	plats.add (new Platform (g1, 600, 220, 1100));
	plats.add (new Platform (g1, 750, 200, 200));
	plats.add (new Platform (g1, 1200, 400, 500));
	plats.add (new Platform (g1, 1700, 301, 500));
	plats.add (new Platform (g1, 2000, 400, 1000));
	plats.add (new Platform (g1, 2900, 301, 1150));
	plats.add (new Platform (g1, 3300, 400, 200));
	plats.add (new Platform (g1, 3800, 480, 200));
	p.setPlatforms (plats);

	// Set up enemies
	enemies = new ArrayList ();
	enemies.add (new EasyEnemy (g1, 100, 220));
	enemies.add (new EasyEnemy (g1, 250, 220));
	enemies.add (new NormalEnemy (g1, 800, 150));
	enemies.add (new EasyEnemy (g1, 1000, 170));
	enemies.add (new NormalEnemy (g1, 1300, 350));
	enemies.add (new EasyEnemy (g1, 1600, 350));
	enemies.add (new NormalEnemy (g1, 2000, 350));
	enemies.add (new NormalEnemy (g1, 2700, 350));
	enemies.add (new NormalEnemy (g1, 3000, 251));
	enemies.add (new NormalEnemy (g1, 3500, 251));
	enemies.add (new EasyEnemy (g1, 3800, 251));
	enemies.add (new NormalEnemy (g1, 3850, 430));
	for (int i = 0 ; i < enemies.size () ; i++)
	{
	    Enemy temp = (Enemy) enemies.get (i);
	    temp.setPlatforms (plats);
	    temp.setPlayer (p);
	}

	// Set up traps
	traps = new ArrayList ();
	traps.add (new SpikePit (g1, 880, 200));
	traps.add (new SpawnerTrap (g1, 1200, 400));
	traps.add (new LoveTrap (g1, 3000, 301));
	traps.add (new ExKSeedyTrap (g1, 3500, 301));
	for (int i = 0 ; i < traps.size () ; i++)
	{
	    Trap tr = (Trap) traps.get (i);
	    tr.setPlayer (p);
	}

	// Set up treasure chests
	chests = new ArrayList ();
	chests.add (new Chest (c, g1, 1400, 350, false));
	chests.add (new Chest (c, g1, 2000, 350, true));
	chests.add (new Chest (c, g1, 2100, 251, false));
	chests.add (new Chest (c, g1, 2900, 350, true));


	// Set up stores
	stores = new ArrayList ();
	stores.add (new Store (c, g1, 3350, 350, buffer));

	// Set up Spirits
	spirits = new ArrayList ();

	// Set up weapons
	weapons = new ArrayList ();

	// Set up portal
	por = new Portal (g1, 3850, 380);

	// Set up time counter
	resetTime ();
    } // setUpLevel2 method


    public static void setUpBoss2 (Graphics g1, BufferedImage buffer)  // Helper method to set up the second boss room
    {
	// Set up player
	p = saved.getClone ();

	// Set up platforms
	plats = new ArrayList ();
	plats.add (new Platform (g1, 300, 280, 500));
	plats.add (new Platform (g1, 0, 300, 2000));
	plats.add (new Platform (g1, 750, 280, 500));
	plats.add (new Platform (g1, 1250, 369, 800));
	plats.add (new Platform (g1, 2000, 369, 500));
	p.setPlatforms (plats);

	// Set up enemies
	enemies = new ArrayList ();
	enemies.add (new EasyEnemy (g1, 150, 250));
	enemies.add (new NormalEnemy (g1, 100, 250));
	enemies.add (new HardEnemy (g1, 860, 210));
	enemies.add (new NormalEnemy (g1, 1300, 319));
	enemies.add (new NormalEnemy (g1, 1500, 319));
	enemies.add (new HardEnemy (g1, 1000, 210));
	enemies.add (new HardEnemy (g1, 1700, 299));
	for (int i = 0 ; i < enemies.size () ; i++)
	{
	    Enemy temp = (Enemy) enemies.get (i);
	    temp.setPlatforms (plats);
	    temp.setPlayer (p);
	}

	// Set up traps
	traps = new ArrayList ();
	traps.add (new SpawnerTrap (g1, 800, 280));
	traps.add (new ExKSeedyTrap (g1, 100, 300));
	traps.add (new SpawnerTrap (g1, 1200, 280));
	traps.add (new LoveTrap (g1, 1600, 369));
	for (int i = 0 ; i < traps.size () ; i++)
	{
	    Trap tr = (Trap) traps.get (i);
	    tr.setPlayer (p);
	}

	// Set up chests
	chests = new ArrayList ();

	// Set up stores
	stores = new ArrayList ();

	// Set up Spirits
	spirits = new ArrayList ();

	// Set up weapons
	weapons = new ArrayList ();

	// Set up portal
	por = new Portal (g1, 2400, 269);

	// Set up time counter
	resetTime ();
    } // setUpBoss2 method


    public static void setUpLevel3 (Graphics g1, BufferedImage buffer)  // Helper method to set up the third level
    {
	// Set up player
	p = saved.getClone ();

	// Set up platforms
	plats = new ArrayList ();
	plats.add (new Platform (g1, 0, 400, 500));
	plats.add (new Platform (g1, 600, 330, 1100));
	plats.add (new Platform (g1, 750, 200, 200));
	plats.add (new Platform (g1, 1200, 400, 500));
	plats.add (new Platform (g1, 1700, 301, 500));
	plats.add (new Platform (g1, 2000, 400, 1000));
	plats.add (new Platform (g1, 2900, 301, 1150));
	plats.add (new Platform (g1, 3800, 480, 200));
	p.setPlatforms (plats);

	// Set up enemies
	enemies = new ArrayList ();
	enemies.add (new EasyEnemy (g1, 100, 350));
	enemies.add (new NormalEnemy (g1, 250, 350));
	enemies.add (new HardEnemy (g1, 800, 130));
	enemies.add (new NormalEnemy (g1, 1000, 280));
	enemies.add (new NormalEnemy (g1, 1300, 350));
	enemies.add (new NormalEnemy (g1, 1600, 350));
	enemies.add (new HardEnemy (g1, 2000, 330));
	enemies.add (new HardEnemy (g1, 2100, 330));
	enemies.add (new HardEnemy (g1, 3000, 231));
	enemies.add (new NormalEnemy (g1, 3200, 251));
	enemies.add (new HardEnemy (g1, 3850, 410));
	for (int i = 0 ; i < enemies.size () ; i++)
	{
	    Enemy temp = (Enemy) enemies.get (i);
	    temp.setPlatforms (plats);
	    temp.setPlayer (p);
	}

	// Set up traps
	traps = new ArrayList ();
	traps.add (new SpawnerTrap (g1, 400, 400));
	traps.add (new ExKSeedyTrap (g1, 910, 200));
	traps.add (new SpawnerTrap (g1, 1200, 400));
	traps.add (new ExKSeedyTrap (g1, 170, 400));
	traps.add (new SpawnerTrap (g1, 3000, 301));
	traps.add (new LoveTrap (g1, 3000, 301));
	traps.add (new AnnaTrap (g1, 3500, 301));
	for (int i = 0 ; i < traps.size () ; i++)
	{
	    Trap tr = (Trap) traps.get (i);
	    tr.setPlayer (p);
	}

	// Set up treasure chests
	chests = new ArrayList ();
	chests.add (new Chest (c, g1, 1400, 350, false));
	chests.add (new Chest (c, g1, 1650, 350, true));
	chests.add (new Chest (c, g1, 150, 350, true));

	// Set up stores
	stores = new ArrayList ();
	stores.add (new Store (c, g1, 200, 350, buffer));
	stores.add (new Store (c, g1, 3300, 251, buffer));

	// Set up Spirits
	spirits = new ArrayList ();

	// Set up weapons
	weapons = new ArrayList ();

	// Set up portal
	por = new Portal (g1, 3850, 380);

	// Set up time counter
	resetTime ();
    } // setUpLevel3 method


    public static void setUpBoss3 (Graphics g1, BufferedImage buffer)  // Helper method to set up the third boss room
    {
	// Set up player
	p = saved.getClone ();

	// Set up platforms
	plats = new ArrayList ();
	plats.add (new Platform (g1, 300, 380, 400));
	plats.add (new Platform (g1, 0, 450, 2000));
	plats.add (new Platform (g1, 750, 380, 500));
	plats.add (new Platform (g1, 1200, 369, 800));
	plats.add (new Platform (g1, 2000, 369, 500));
	plats.add (new Platform (g1, 2500, 450, 300));
	p.setPlatforms (plats);

	// Set up enemies
	enemies = new ArrayList ();
	enemies.add (new EasyEnemy (g1, 150, 400));
	enemies.add (new NormalEnemy (g1, 100, 400));
	enemies.add (new HardEnemy (g1, 460, 310));
	enemies.add (new NormalEnemy (g1, 800, 330));
	enemies.add (new NormalEnemy (g1, 1000, 330));
	enemies.add (new HardEnemy (g1, 1100, 310));
	enemies.add (new HardEnemy (g1, 1300, 299));
	enemies.add (new HardEnemy (g1, 1400, 299));
	enemies.add (new HardEnemy (g1, 1510, 299));
	enemies.add (new HardEnemy (g1, 1700, 299));
	enemies.add (new Boss (g1, 2560, 350));
	for (int i = 0 ; i < enemies.size () ; i++)
	{
	    Enemy temp = (Enemy) enemies.get (i);
	    temp.setPlatforms (plats);
	    temp.setPlayer (p);
	}

	// Set up traps
	traps = new ArrayList ();
	traps.add (new SpawnerTrap (g1, 800, 380));
	traps.add (new ExKSeedyTrap (g1, 1250, 369));
	for (int i = 0 ; i < traps.size () ; i++)
	{
	    Trap tr = (Trap) traps.get (i);
	    tr.setPlayer (p);
	}

	// Set up chests
	chests = new ArrayList ();

	// Set up stores
	stores = new ArrayList ();

	// Set up Spirits
	spirits = new ArrayList ();

	// Set up weapons
	weapons = new ArrayList ();

	// Set up portal
	por = new Portal (g1, -1000, -1000);

	// Set up time counter
	resetTime ();
    } // setUpBoss3 method


    public static void playLevel (Graphics g1, BufferedImage buffer, int n1)  // Helper method to play the selected level
	throws DeathException
    {
	// Set up level corresponding to level number
	switch (n1)
	{
	    case 0:
		setUpTutorial (g1, buffer);
		break;
	    case 1:
		// Reset wheat achievement because player doesn't have to collect wheat in tutorial
		wheatAchieve = true;
		setUpLevel1 (g1, buffer);
		break;
	    case 2:
		setUpBoss1 (g1, buffer);
		portalVal = false; // Cannot use the portal to escape
		break;
	    case 3:
		setUpLevel2 (g1, buffer);
		break;
	    case 4:
		setUpBoss2 (g1, buffer);
		portalVal = false; // Cannot use the portal to escape
		break;
	    case 5:
		setUpLevel3 (g1, buffer);
		break;
	    case 6:
		setUpBoss3 (g1, buffer);
		portalVal = false; // Cannot use the portal to escape
		break;
	}

	// Loop until player dies or finishes the game
	try
	{
	    while (true)
	    {
		time1 += 25; // Each frame takes 25 milliseconds

		// Reset graphics for drawing
		g1.setColor (Color.white);
		g1.fillRect (0, 0, 640, 500);

		// Display the situation
		// Loop through list of enemies to draw them
		for (int i = 0 ; i < enemies.size () ; i++)
		{
		    Enemy en = (Enemy) enemies.get (i);
		    en.draw (p.getX () - 300);
		}
		// Loop through list of spirits to draw them
		for (int i = 0 ; i < spirits.size () ; i++)
		{
		    Spirit sp = (Spirit) spirits.get (i);
		    sp.draw (p.getX () - 300);
		}
		// Loop through list of treasure chests to draw them
		for (int i = 0 ; i < chests.size () ; i++)
		{
		    Chest ch = (Chest) chests.get (i);
		    ch.draw (p.getX () - 300);
		}
		// Loop through list of stores to draw them
		for (int i = 0 ; i < stores.size () ; i++)
		{
		    Store st = (Store) stores.get (i);
		    st.draw (p.getX () - 300);
		}
		// Draw the portal
		if (portalVal)
		{
		    por.draw (p.getX () - 300);
		}
		// Draw the player
		p.draw ();
		// Loop through list of traps to draw them
		for (int i = 0 ; i < traps.size () ; i++)
		{
		    Trap tr = (Trap) traps.get (i);
		    tr.draw (p.getX () - 300);
		}
		// Loop through list of weapons to draw them
		for (int i = 0 ; i < weapons.size () ; i++)
		{
		    Weapon we = (Weapon) weapons.get (i);
		    c.setCursor (25, 1);
		    if (we.getClass ().equals (Wheat.class))
		    {
			// Wheat has a different drawing method
			((Wheat) we).draw (we.getX () - p.getX () + 300, we.getY ());
		    }
		    else
		    {
			we.draw (we.getX () - p.getX () + 300, we.getY ());
		    }
		}
		// Draw the final image
		c.drawImage (buffer, 0, 0, null);


		// Move the situation and make calculations
		// Move the player
		p.move ();
		if (iPressed) // Pressing the I key opens the inventory
		{
		    p.openInventory (c, buffer);
		    resetTime (); // Reset the time counter because the program was paused
		}
		if (bustTrap)
		{
		    // Loop to bust all traps in an area of radius 3000
		    for (int i = 0 ; i < traps.size () ; i++)
		    {
			Trap tr = (Trap) traps.get (i);

			// Calculate distance from player
			int deltaX = p.getX () - tr.getX ();
			int deltaY = p.getY () - tr.getY ();
			int dist = (int) Math.sqrt (deltaX * deltaX + deltaY * deltaY);

			// Make decision
			if (dist <= 3000)
			{
			    traps.remove (i);
			    i--;
			}
		    }
		    bustTrap = false;
		}

		if (ICS_Summative.bossKill) // If the boss was recently killed
		{
		    // Destroy all bad Spirits
		    spirits = new ArrayList ();

		    // Summon good Spirits
		    spirits.add (new SpiritOfCheers (g1, p.getX (), p.getY ()));
		    spirits.add (new SpiritOfClears (g1, p.getX (), p.getY ()));

		    // Boss not killed anymore
		    ICS_Summative.bossKill = false;
		}
		// Loop through lest of traps to check them
		for (int i = 0 ; i < traps.size () ; i++)
		{
		    Trap tr = (Trap) traps.get (i);

		    // Check to see if the player is activating the trap
		    if (tr.isTouching ())
		    {
			// The trap gets activated
			tr.harm ();
		    }

		    // Check to see if the trap has despawned
		    if (tr.isGone ())
		    {
			traps.remove (i);
			i--;
		    }

		    // Check special types of traps
		    if (tr.getClass ().equals (SpawnerTrap.class))
		    {
			// Potentially spawn in a Spirit
			spirits.add (((SpawnerTrap) tr).spawned ());
		    }
		}
		// Loop through list of enemies to move them
		for (int i = 0 ; i < enemies.size () ; i++)
		{
		    Enemy en = (Enemy) enemies.get (i);
		    en.move (); // Basic movement
		    en.harm (); // The enemy potentially harms the player
		    p.harm (en); // The player potentially harms the enemy
		    if (en.isDead ())
		    {
			// Enemy gets removed if it dies
			enemies.remove (i);
			i--;
		    }
		    // Check special types of enemies
		    if (en.getClass ().equals (Boss.class))
		    {
			// Potentially spawn in a Spirit
			spirits.add (((Boss) en).spawned ());
		    }
		}
		// Loop through list of Spirits to move them
		for (int i = 0 ; i < spirits.size () ; i++)
		{
		    Spirit sp = (Spirit) spirits.get (i);
		    try
		    {
			sp.move (p);
		    }
		    catch (NullPointerException ex)  // Delete Spirit if any exceptions thrown
		    {
			spirits.remove (i);
			i--;
			continue;
		    }
		    catch (DeathException ex)
		    {
			// Check to see if the Spirit is from a dead boss
			if (sp.getClass ().equals (SpiritOfCheers.class) || sp.getClass ().equals (SpiritOfClears.class))
			{
			    throw new EndException (); // The player finished the game
			}
			spirits.remove (i);
			i--;
			continue;
		    }
		    if (sp.isTouching (p))
		    {
			sp.harm (p);
		    }
		}
		// Loop through list of treasure chests to check them
		for (int i = 0 ; i < chests.size () ; i++)
		{
		    Chest ch = (Chest) chests.get (i);

		    // Check to see if the player wants to open the treasure chest
		    if (enterPressed && ch.isTouching (p))
		    {
			// Ask the question and reward smart players
			ch.ask (p);

			// Reset time counter because the program was paused
			resetTime ();

			// Remove the treasure chest
			chests.remove (i);
			i--;
		    }
		}
		// Loop through list of stores to check them
		for (int i = 0 ; i < stores.size () ; i++)
		{
		    Store temp = (Store) stores.get (i);

		    // Check to see if the player wants to open the store
		    if (ICS_Summative.enterPressed && temp.isTouching (p))
		    {
			// Open the store and sell items
			temp.open (p);

			// Reset time counter because the program was paused
			resetTime ();
		    }
		}
		// Loop through list of weapons to move them
		for (int i = 0 ; i < weapons.size () ; i++)
		{
		    Weapon we = (Weapon) weapons.get (i);

		    // Check the type of weapon
		    if (we.getClass ().equals (Wheat.class))
		    {
			we.move ();
			// Loop through list of enemies to check them
			for (int j = 0 ; j < enemies.size () ; j++)
			{
			    Enemy en = (Enemy) enemies.get (j);

			    // Check to see if the enemy gets harmed
			    if (we.isTouching (en))
			    {
				if (Math.random () <= 0.0001 && !en.getClass ().equals (Boss.class)) // 0.01% chance of dealing exactly one damage to non-boss enemies
				{
				    en.unshield ();
				    p.process (en.takeDamage (), en);
				}

				// Unregister the weapon
				weapons.remove (i);
				i--;
				break;
			    }
			}
		    }
		    else if (we.getClass ().equals (Shuriken.class))
		    {
			we.move ();
			// Loop through list of enemies to check them
			for (int j = 0 ; j < enemies.size () ; j++)
			{
			    Enemy en = (Enemy) enemies.get (j);
			    // Check to see if the enemy gets harmed
			    if (we.isTouching (en))
			    {
				if (((Shuriken) we).isVoodoo ()) // Oh noes! You used a Voodoo Shuriken
				{
				    p.takeDamage (100);
				}
				else if (!en.getClass ().equals (Boss.class)) // Shurikens are too weak to harm bosses
				{
				    en.unshield ();
				    p.process (en.takeDamage (), en);
				}

				// Unregister the weapon
				weapons.remove (i);
				i--;
				break;
			    }
			}
		    }
		    else if (we.getClass ().equals (DeathSpell.class))
		    {
			// Declare variables
			Enemy close = null;

			// Loop through list of enemies to find the closest one
			for (int j = 0 ; j < enemies.size () ; j++)
			{
			    Enemy en = (Enemy) enemies.get (j);
			    // Make sure the enemy is on the correct side of the weapon
			    if (en.getLeft () < we.getX () == we.getLeft ()) // The enemy should be to the left of the weapon iff the weapon targets to the left
			    {
				try
				{
				    // Compare to find the closest enemy
				    if (Math.abs (en.getLeft () - we.getX ()) < Math.abs (close.getLeft () - we.getX ()))
				    {
					close = en;
				    }
				}
				catch (NullPointerException ex)  // If there is currently no enemy being targeted
				{
				    close = en;
				}
			    }
			}

			// Kill the targeted enemy
			try
			{
			    if (close.getClass ().equals (Boss.class)) // Death Spell is too weak to kill bosses
			    {
				close.unshield ();
				p.process (close.takeDamage (), close);
			    }
			    else
			    {
				while (!close.isDead ()) // Damage the enemy until it dies
				{
				    close.unshield ();
				    p.process (close.takeDamage (), close);
				}
			    }
			}
			catch (NullPointerException ex)  // Oh noes! No enemy is targeted
			{
			    // Do nothing
			}

			// Unregister the weapon
			weapons.remove (i);
			i--;
		    }
		    else if (we.getClass ().equals (VampireSpell.class))
		    {
			// Declare variables
			Enemy close = null;

			// Loop through list of enemies to find the closest one
			for (int j = 0 ; j < enemies.size () ; j++)
			{
			    Enemy en = (Enemy) enemies.get (j);
			    // Make sure the enemy is on the correct side of the weapon
			    if (en.getLeft () < we.getX () == we.getLeft ()) // The enemy should be to the left of the weapon iff the weapon targets to the left
			    {
				try
				{
				    // Compare to find the closest enemy
				    if (Math.abs (en.getLeft () - we.getX ()) < Math.abs (close.getLeft () - we.getX ()))
				    {
					close = en;
				    }
				}
				catch (NullPointerException ex)  // If there is currently no enemy being targeted
				{
				    close = en;
				}
			    }
			}

			// Kill the targeted enemy
			try
			{
			    if (close.getClass ().equals (Boss.class)) // Vampire Spell is too weak to kill bosses
			    {
				close.unshield ();
				p.process (close.takeDamage (), close);
				p.heal (100); // Steal the enemy's health
			    }
			    else
			    {
				while (!close.isDead ()) // Damage the enemy until it dies
				{
				    close.unshield ();
				    p.process (close.takeDamage (), close);
				    p.heal (100); // Steal the enemy's health
				}
			    }
			}
			catch (NullPointerException ex)  // Oh noes! No enemy is targeted
			{
			    // Do nothing
			}

			// Unregister the weapon
			weapons.remove (i);
			i--;
		    }
		}
		// Check the portal
		if (enemies.size () == 0) // If there are no enemies left
		{
		    portalVal = true; // Portal appears after all enemies are killed
		}
		if (por.isTouching (p) && enterPressed == true && portalVal == true) // If the player uses the portal
		{
		    // The player finished the level
		    // Check the wheat achievement
		    if (enemies.size () > 0)
		    {
			// The player missed an enemy with wheat so cannot earn the achievement
			wheatAchieve = false;
		    }
		    // Loop through list of stores to check for wheat
		    for (int i = 0 ; i < stores.size () ; i++)
		    {
			((Store) stores.get (i)).checkWheat ();
		    }
		    p.checkWheat ();

		    // Prepare for next level
		    if (n1 > 0) // If the level is not the tutorial
		    {
			p.heal (); // Heal the player before playing next level
			saved = p.getClone (); // Save progress
		    }
		    return;
		}

		// Loop until it is time to show the next frame
		while (System.currentTimeMillis () - time1 < 0)
		{
		}
	    }
	}
	catch (DeathException ex)  // Oh noes! The player died
	{
	    // Display death image
	    c.clear ();
	    g1.drawImage (deadImg, 0, 0, null);
	    c.drawImage (buffer, 0, 0, null);

	    // Clean the console input
	    while (c.isCharAvail ())
	    {
		// Remove available character
		c.getChar ();
	    }

	    // Restart if and only if the next pressed key is <Enter>
	    if (c.getChar () == '\n')
	    {
		try
		{
		    playLevel (g1, buffer, n1); // Play the level again
		}
		catch (OutOfMemoryError oome)  // The memory messes up sometimes
		{
		    playLevel (g1, buffer, n1); // Play the level again
		}
	    }
	    else
	    {
		throw new DeathException ();
	    }
	}
	catch (EndException ex)  // The player killed the boss and finished the game
	{
	    // Check wheat achievement
	    if (enemies.size () > 0)
	    {
		// The player missed an enemy with wheat so cannot earn the achievement
		wheatAchieve = false;
	    }
	    p.checkWheat ();

	    // Display end image
	    c.clear ();
	    g1.drawImage (endImg, 0, 0, null);
	    c.drawImage (buffer, 0, 0, null);

	    // Clean the console input
	    while (c.isCharAvail ())
	    {
		// Remove available character
		c.getChar ();
	    }

	    // Pause program until a key is pressed
	    c.getChar ();
	}
    }


    public static void main (String[] args)
    {
	// Prepare graphics
	if (c == null)
	{
	    c = new Console ();
	}
	BufferedImage buffer = new BufferedImage (c.getWidth (), c.getHeight (), BufferedImage.TYPE_INT_ARGB);
	Graphics g1 = buffer.getGraphics ();

	// Load images
	try
	{
	    deadImg = ImageIO.read (new File ("deadImg.png"));
	}
	catch (IOException ex)
	{
	}
	try
	{
	    endImg = ImageIO.read (new File ("endImg.png"));
	}
	catch (IOException ex)
	{
	}

	// Prepare to monitor keyboard
	KeyboardFocusManager keyTemp = KeyboardFocusManager.getCurrentKeyboardFocusManager ();
	keyTemp.addKeyEventDispatcher (new KeyHelper ());

	// Set up the game
	saved = new Player (g1);

	// Play the game
	try
	{
	    playLevel (g1, buffer, 0);
	    playLevel (g1, buffer, 1);
	    playLevel (g1, buffer, 2);
	    playLevel (g1, buffer, 3);
	    playLevel (g1, buffer, 4);
	    playLevel (g1, buffer, 5);
	    playLevel (g1, buffer, 6);
	}
	catch (DeathException ex)  // If the game is over
	{
	    // The player quit the game so cannot earn the wheat achievement
	    wheatAchieve = false;
	}

	// Display termination message
	c.clear ();
	if (wheatAchieve) // If the player is eligible for the wheat achievement
	{
	    c.println ("Congratulations! You collected all the wheat and earned an achievement.");
	}
	else
	{
	    c.println ("Next time, collect all the wheat to earn an achievement!");
	}
	c.println ("The program has terminated.");
    } // main program
} // ICS_Summative class
