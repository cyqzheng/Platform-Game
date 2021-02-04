/*
Name: Michael Li
Date: December 29, 2017
Class code: ICS 2O3 - 02
Program name: TutorialEnemy1
A very weak enemy that appears in the tutorial.
*/

import java.awt.*;
import java.math.*;
import java.util.*;
import hsa.*;

public class TutorialEnemy1 extends Enemy
{
    private Graphics g1; // The Grapics that will draw stuff
    private int scrollNumber; // The number of the instructions scroll that the player will receive

    public TutorialEnemy1 (Graphics g, int x1, int y1, int n1)  // Constructs a custom TutorialEnemy1
    {
	super (g, "TutorialEnemy1.png", null, x1, y1, 1, 10, 0);

	// Assign variables
	scrollNumber = n1;
	g1 = g;
    }


    public ArrayList die ()  // Helper method that activates when the enemy dies
    {
	// Declare variables
	ArrayList drop = new ArrayList ();
	String msg = "";

	// Select scroll message corresponding to the scroll number
	switch (scrollNumber)
	{
	    case 0:
		msg = "Good job! You killed an enemy.";
		break;
	    case 1:
		msg = "Well done! You killed another enemy.";
		break;
	    case 2:
		msg = "Press X to close a scroll after reading it.";
		break;
	    case 3:
		msg = "Press I to open your inventory.";
		msg += "\nYour inventory should be empty right now.";
		msg += "\nAfter you check your inventory, press X to exit.";
		break;
	    case 4:
		msg = "Most enemies drop money after you kill them.";
		msg += "\nMoney can be used to buy items later.";

		// Add money to tutorial to show the user what money is
		drop.add (new Money (g1, (int) (8 + 4 * Math.random ())));
		break;
	    case 5:
		msg = "Some enemies also drop useful items.";
		msg += "\nYou can use them by pressing <Enter> in your inventory.";
		msg += "\nThis enemy dropped a Mushroom.";
		msg += "\nA Mushroom will heal 100 HP.";

		// Add a Mushroom to tutorial to demonstrate what a Mushroom is
		drop.add (new Mushroom (g1));
		break;
	    case 6:
		msg = "This enemy dropped a shuriken.";
		msg += "\nYou can throw a shuriken in front of you.";
		msg += "\nThe shuriken will harm the first enemy that it hits.";
		msg += "\nThe shuriken will deal exactly one damage.";
		msg += "\nHowever, shurikens are too weak to damage bosses.";

		// Add a shuriken to demonstrate shurikens
		drop.add (new Shuriken (g1, false));
		break;
	    case 7:
		msg = "This enemy dropped some wheat.";
		msg += "\nCollect all the wheat to get an achievement at the end.";
		msg += "\nYou can also throw wheat in front of you.";
		msg += "\nYou have a 0.01% chance of dealing exactly one damage.";

		// Add some wheat to demonstrate wheat
		drop.add (new Wheat (g1, false));
		break;
	    case 8:
		msg = "WARNING: Some items dropped by enemies are traps.";
		msg += "\nFake Mushrooms will deal damage instead of healing you.";
		msg += "\nVoodoo Shurikens will harm you instead of the enemy.";
		msg += "\nCursed Wheat will harm you until you use it.";
		msg += "\nCursed Wheat does not count towards the achievement.";

		// Add a Heal Trap to demonstrate what an item trap is
		drop.add (new HealTrap (g1, 200));
		break;
	    case 9:
		msg = "There are also more advanced items.";
		msg += "\nAdvanced items are not usually dropped by enemies.";
		msg += "\nYou have to find them in stores or treasure chests.";
		msg += "\nThis enemy dropped a Calcium.";
		msg += "\nA Calcium will heal 500 HP.";

		// Add a Calcium to demonstrate Calcium
		drop.add (new Calcium (g1));
		break;
	    case 10:
		msg = "This enemy dropped a Max Health Increaser.";
		msg += "\nThis item will increase your maximum health by 500 HP.";

		// Add a Max Health Increaser to demonstrate Max Health Increaser
		drop.add (new MaxHealthIncreaser (g1));
		break;
	    case 11:
		msg = "This enemy dropped a Bridge Potion.";
		msg += "\nA Bridge Potion will heal 1430 HP.";

		// Add a Bridge Potion to demonstrate Bridge Potions
		drop.add (new BridgePotion (g1));
		break;
	    case 12:
		msg = "This enemy dropped a Death Spell.";
		msg += "\nYou can aim the Death Spell in front of you.";
		msg += "\nA Death Spell will kill the closest enemy.";
		msg += "\nIf you hit a boss, you will only deal one damage.";

		// Add a Death Spell to demonstrate Death Spells
		drop.add (new DeathSpell (g1));
		break;
	    case 13:
		msg = "This enemy dropped a Vampire Spell.";
		msg += "\nA Vampire Spell is very similar to a Death Spell.";
		msg += "\nHowever, a Vampire Spell will also heal you.";
		msg += "\nYou will gain 100 HP for every HP that the enemy has.";

		// Add a Vampire Spell to demonstrate Vampire Spells
		drop.add (new VampireSpell (g1));
		break;
	    case 14:
		msg = "Here is a store.";
		msg += "\nStores sell basic items and some advanced items.";
		msg += "\nYou need to collect money to buy the items.";
		msg += "\nMore advanced items cost more money.";
		msg += "\nPress <Enter> to enter the store, and press X to exit.";
		break;
	    case 15:
		msg = "You will need to kill more enemies to earn more money.";

		// Add money to demonstrate how to collect money
		drop.add (new Money (g1, (int) (8 + 4 * Math.random ())));
		break;
	    case 16:
		msg = "You will need to kill many enemies to get rich enough.";

		// Add money to demonstrate how to collect money
		drop.add (new Money (g1, (int) (8 + 4 * Math.random ())));
		break;
	    case 17:
		msg = "Good job! You killed an enemy.";

		// Add money to demonstrate how to collect money
		drop.add (new Money (g1, (int) (8 + 4 * Math.random ())));
		break;
	    case 18:
		msg = "Well done! You killed another enemy.";

		// Add money to demonstrate how to collect money
		drop.add (new Money (g1, (int) (8 + 4 * Math.random ())));
		break;
	    case 19:
		msg = "Now, you can run back to the store and buy something.";

		// Add money to demonstrate how to collect money
		drop.add (new Money (g1, (int) (8 + 4 * Math.random ())));
		break;
	    case 20:
		msg = "Here is a treasure chest.";
		msg += "\nTreasure chests contain advanced items.";
		msg += "\nPress <Enter> to open the treasure chest.";
		msg += "\nYou will have to answer a question to win the item.";
		msg += "\nIf your answer is incorrect, the item will disappear!";
		break;
	    case 21:
		msg = "Here is a high-level treasure chest.";
		msg += "\nHigh-level treasure chests contain the best items.";
		msg += "\nYou will have to answer a much harder question.";
		msg += "\nGood luck!";
		break;
	    case 22:
		msg = "This enemy dropped a key.";
		msg += "\nThe key can open a high-level treasure chest.";
		msg += "\nUnfortunately, the key dies after being used.";
		msg += "\nUse the key iff you suck at math.";

		// Add key to demonstrate keys
		drop.add (new ItemKey (g1));
		break;
	    case 23:
		msg = "There is a trap below this enemy.";
		msg += "\nTraps light up when you get near them.";
		msg += "\nThis trap is a spike pit. It uses spikes to damage you.";
		msg += "\nStep on it to prove that you are not a wimp.";
		break;
	    case 24:
		msg = "The trap below this enemy is an Anna Trap.";
		msg += "\nAnna is a terrible girl who hates a game called Bridge.";
		msg += "\nShe created this trap to break all your Bridge Potions!";
		msg += "\nStep on it to prove that you are not a wimp.";

		// Add Bridge Potion to demonstrate Anna Trap
		drop.add (new BridgePotion (g1));
		break;
	    case 25:
		msg = "The trap below this enemy is a Spawner Trap.";
		msg += "\nThis trap summons mysterious Spirits.";
		msg += "\nThese Spirits harm you in mysterious ways.";
		msg += "\nStep on it to prove that you are not a wimp.";
		break;
	    case 26:
		msg = "The trap below this enemy is an Ex-K Seedy Trap.";
		msg += "\nThis trap summons a guy with a black hat.";
		msg += "\nHe will kill you if you don't run away fast enough!";
		msg += "\nStep on it to prove that you are not a wimp.";
		break;
	    case 27:
		msg = "The trap below this enemy is a Love Trap.";
		msg += "\nOnce upon a time, the character really liked a girl.";
		msg += "\nShe rejected him and ran away with history nerds.";
		msg += "\nLove Traps cause him to freeze and think about her.";
		msg += "\nStep on it to prove to her that you are not a wimp.";
		break;
	    case 28:
		msg = "This enemy dropped a Trap Buster.";
		msg += "\nTrap Busters help you avoid traps by destroying them.";
		msg += "\nHowever, item traps will be unaffected.";
		msg += "\nThe Trap Buster works within a 3000-pixel radius.";
		msg += "\nUse it to prove that you are a wimp.";

		// Add Trap Buster to demonstrate Trap Busters
		drop.add (new TrapBuster (g1));
		break;
	    case 29:
		msg = "This enemy dropped a coupon.";
		msg += "\nYou can use a coupon at a store by pressing C.";
		msg += "\nThe coupon will halve the price of everything.";
		msg += "\nUsually, coupons can only be found in treasure chests.";

		// Add coupon to demonstrate coupons
		drop.add (new Coupon (g1));
		break;
	    case 30:
		msg = "You can use multiple coupons at the same store.";
		msg += "\nWhen you do this, every item's price is halved again.";
		msg += "\nYou can do this whenever you have enough coupons.";

		// Add coupon to demonstrate coupons
		drop.add (new Coupon (g1));
		break;
	    case 31:
		msg = "This enemy dropped a Bitcoin.";
		msg += "\nYou can use a Bitcoin at a store by pressing B.";
		msg += "\nAfter you pay your Bitcoin, all items will be for free.";
		msg += "\nUsually, Bitcoins can only be found in treasure chests.";
		msg += "\nLimit one Bitcoin per customer per store.";

		// Add Bitcoin to demonstrate Bitcoins
		drop.add (new Bitcoin (g1));
		break;
	    case 32:
		msg = "The next enemy is a tougher enemy.";
		msg += "\nThe next enemy has multiple health.";
		msg += "\nAfter you damage it, it will shield itself for a while.";
		msg += "\nIt will also deal more damage.";
		break;
	    case 33:
		msg = "There is a portal below this enemy.";
		msg += "\nThe portal will bring you to the next level.";
		msg += "\nYour progress will be saved and you will be healed.";
		msg += "\nPress <Enter> to go into the portal.";
		msg += "\nYour tutorial progress will NOT be saved.";
		break;
	}

	// Create a scroll with the selected message
	drop.add (new Scroll (g1, msg));

	// Drop the items
	return drop;
    }
} // TutorialEnemy1 class
