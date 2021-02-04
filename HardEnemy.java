/*
Name: Michael Li
Date: December 29, 2017
Class code: ICS 2O3 - 02
Program name: HardEnemy
An hard enemy that appears in Level 3.
*/

import java.awt.*;
import java.math.*;
import java.util.*;
import hsa.*;

public class HardEnemy extends Enemy
{
    public HardEnemy (Graphics g, int x1, int y1)  // Constructs a custom HardEnemy
    {
	super (g, "HardEnemy1.png", "HardEnemy2.png", x1, y1, 3, 400, 30);
    }
} // HardEnemy class
