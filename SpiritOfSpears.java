/*
Name: Michael Li
Date: January 1, 2018
Class code: ICS 2O3 - 02
Program name: SpiritOfSpears
A Spirit that damages the player.
*/

import java.awt.*;
import java.math.*;
import java.util.*;
import hsa.*;

public class SpiritOfSpears extends Spirit
{
    private final int DAMAGE = 50;
    public SpiritOfSpears (Graphics g, int x1, int y1)  // Constructs a custom Spirit of Spears
    {
	super (g, "Spirit Of Spears.png", x1, y1);
    }


    public void harm (Player p) throws DeathException // Helper method to deal damage to the player
    {
	p.takeDamage (DAMAGE);
    } // harm method
} // SpiritOfSpears class
