/*
Name: Michael Li
Date: December 29, 2017
Class code: ICS 2O3 - 02
Program name: EasyEnemy
An easy enemy that appears in Levels 1, 2, and 3.
*/

import java.awt.*;
import java.math.*;
import java.util.*;
import hsa.*;

public class EasyEnemy extends Enemy
{
    public EasyEnemy (Graphics g, int x1, int y1)  // Constructs a custom EasyEnemy
    {
	super (g, "EasyEnemy.png", null, x1, y1, 1, 100, 10);
    }
} // EasyEnemy class
