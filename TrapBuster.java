/*
Name: Michael Li
Date: January 15, 2018
Class code: ICS 2O3 - 02
Program name: TrapBuster
An Item that destroys traps around the player.
*/

import java.awt.*;
import java.math.*;
import java.util.*;
import hsa.*;

public class TrapBuster extends Item
{
    private Graphics g1; // The Graphics that will draw stuff

    public TrapBuster (Graphics g)  // Constructs a custom TrapBuster
    {
	super (g, "TrapBuster.png");
    }
} // TrapBuster class
