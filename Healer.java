/*
Name: Michael Li
Date: December 16, 2017
Class code: ICS 2O3 - 02
Program name: Healer
An item that represents a generic healing item that the protagonist uses.
*/

import java.awt.*;
import java.math.*;
import java.util.*;
import hsa.*;

public abstract class Healer extends Item
{
    private int power; // Amount of health the protagonist regenerates

    public Healer (Graphics g1, String imgName, int pow)  // Constructs a custom Healer
    {
	super (g1, imgName);
	power = pow;
    }


    public int getPower () // Helper method to access value of power
    {
	return power;
    } // getPower method
    
} // Healer class
