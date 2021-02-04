/*
Name: Michael Li
Date: December 16, 2017
Class code: ICS 2O3 - 02
Program name: Shuriken
A weapon that represents a shuriken that deals exactly one damage and cannot harm bosses.
*/

import java.awt.*;
import java.math.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;
import hsa.*;

public class Shuriken extends Weapon
{
    private boolean voodoo; // Whether or not the shuriken is a Voodoo Shuriken

    public Shuriken (Graphics g, boolean v)  // Constructs a custom Shuriken
    {
	super (g, "Shruiken.png");

	// Assign variables
	voodoo = v;
    }


    public boolean isVoodoo ()  // Helper method to determine whether or not the shuriken is a Voodoo Shuriken
    {
	return voodoo;
    } // isVoodoo method
} // Shuriken class
