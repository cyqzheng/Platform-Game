/*
Name: Michael Li
Date: January 15, 2018
Class code: ICS 2O3 - 02
Program name: MaxHealthIncreaser
An Item that increases the player's maximum health.
*/

import java.awt.*;
import java.math.*;
import java.util.*;
import hsa.*;

public class MaxHealthIncreaser extends Item
{
    private final int POWER = 500; // The amount that the player's maximum health will increase by

    public MaxHealthIncreaser (Graphics g)  // Constructs a custom MaxHealthIncreaser
    {
	super (g, "MaxHealthIncrease.png");
    }


    public int getPower ()  // Helper method to access the power of the item
    {
	return POWER;
    } // getPower method
} // MaxHealthIncreaser class
