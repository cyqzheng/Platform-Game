/*
Name: Michael Li
Date: January 2, 2018
Class code: ICS 2O3 - 02
Program name: SpiritOfFears
A Spirit that intimidates and slows down the player.
*/

import java.awt.*;
import java.math.*;
import java.util.*;
import hsa.*;

public class SpiritOfFears extends Spirit
{
    private final int TIME = 80;
    public SpiritOfFears (Graphics g, int x1, int y1)  // Constructs a custom Spirit of Fears
    {
	super (g, "Spirit Of Fears.png", x1, y1);
    }


    public void harm (Player p)  // Helper method to slow down the player
    {
	p.slow (TIME);
    } // harm method
} // SpiritOfFears class
