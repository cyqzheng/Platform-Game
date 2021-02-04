/*
Name: Michael Li
Date: December 16, 2017
Class code: ICS 2O3 - 02
Program name: HealTrap
A painful trap item that resembles a mushroom.
*/

import java.awt.*;
import java.math.*;
import java.util.*;
import hsa.*;

public class HealTrap extends Healer
{
    private Console c; // The output console

    public HealTrap (Graphics g1, int pow)  // Constructs a custom HealTrap
    {
	super (g1, "Mushroom.png", -pow);
    }
} // HealTrap class
