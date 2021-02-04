/*
Name: Michael Li
Date: December 29, 2017
Class code: ICS 2O3 - 02
Program name: NormalEnemy
A normal enemy that appears in Levels 2 and 3.
*/

import java.awt.*;
import java.math.*;
import java.util.*;
import hsa.*;

public class NormalEnemy extends Enemy
{
    public NormalEnemy (Graphics g, int x1, int y1)  // Constructs a custom NormalEnemy
    {
	super (g, "NormalEnemy1.png", "NormalEnemy2.png", x1, y1, 2, 200, 20);
    }
} // NormalEnemy class
