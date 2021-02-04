/*
Name: Michael Li
Date: January 2, 2018
Class code: ICS 2O3 - 02
Program name: SpiritOfClears
A Spirit that heals the player.
*/

import java.awt.*;
import java.math.*;
import java.util.*;
import hsa.*;

public class SpiritOfClears extends Spirit
{
    private final int POWER = 5;
    public SpiritOfClears (Graphics g, int x1, int y1)  // Constructs a custom Spirit of Clears
    {
	super (g, "Spirit Of Clears.png", x1, y1);
    }


    public void harm (Player p)  // Helper method to heal the player
    {
	p.heal (POWER);
    } // harm method
} // SpiritOfClears class
