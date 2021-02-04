/*
Name: Michael Li
Date: December 29, 2017
Class code: ICS 2O3 - 02
Program name: Money
An Item that represents an amount of money.
*/

import java.awt.*;
import java.math.*;
import java.util.*;
import hsa.*;

public class Money extends Item
{
    private int amount;

    public Money (Graphics g, int a1)  // Constructs some money
    {
	super (g, "Money.png");
	amount = a1;
    }


    public int getAmount ()  // Helper method to return the amount of money
    {
	return amount;
    } // getAmount method
} // Money class
