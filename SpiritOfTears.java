/*
Name: Michael Li
Date: January 2, 2018
Class code: ICS 2O3 - 02
Program name: SpiritOfTears
A Spirit that summons tears all over the screen.
*/

import java.awt.*;
import java.math.*;
import java.util.*;
import hsa.*;

public class SpiritOfTears extends Spirit
{
    private final int TIME = 80;
    public SpiritOfTears (Graphics g, int x1, int y1)  // Constructs a custom Spirit of Tears
    {
	super (g, "Spirit Of Tears.png", x1, y1);
    }


    public void harm (Player p)  // Helper method to summon tears
    {
	p.tears (TIME);
    } // harm method
} // SpiritOfTears class
