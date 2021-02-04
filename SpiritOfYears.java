/*
Name: Michael Li
Date: January 2, 2018
Class code: ICS 2O3 - 02
Program name: SpiritOfYears
A Spirit that freezes the player.
*/

import java.awt.*;
import java.math.*;
import java.util.*;
import hsa.*;

public class SpiritOfYears extends Spirit
{
    public final int TIME = 80;
    public SpiritOfYears (Graphics g, int x1, int y1)  // Constructs a custom Spirit of Years
    {
	super (g, "Spirit Of Years.png", x1, y1);
    }


    public void harm (Player p)  // Helper method to freeze the player
    {
	p.freeze (TIME);
	die (); // Spirit should despawn after harming the player
    } // harm method
} // SpiritOfYears class
