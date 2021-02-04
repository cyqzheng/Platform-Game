/*
Name: Michael Li
Date: January 4, 2018
Class code: ICS 2O3 - 02
Program name: KeyHelper
A helper class to monitor the keyboard.
*/

import java.awt.*;
import java.awt.event.KeyEvent;
import java.math.*;
import java.util.*;
import hsa.*;

public class KeyHelper implements KeyEventDispatcher
{
    public KeyHelper ()  // Constructs a new KeyHelper
    {
    }


    public boolean dispatchKeyEvent (KeyEvent ke)
    {
	boolean press; // Variable to signify whether a key was pressed or released

	// Make decisions and store results
	switch (ke.getID ())
	{
	    case KeyEvent.KEY_PRESSED:
		press = true;
		break;
	    case KeyEvent.KEY_RELEASED:
		press = false;
		break;
	    default:
		return false; // Do not allow program to continue
	}
	switch (ke.getKeyCode ())
	{
	    case KeyEvent.VK_LEFT:
		ICS_Summative.leftPressed = press;
		break;
	    case KeyEvent.VK_DOWN:
		ICS_Summative.downPressed = press;
		break;
	    case KeyEvent.VK_RIGHT:
		ICS_Summative.rightPressed = press;
		break;
	    case KeyEvent.VK_UP:
		ICS_Summative.upPressed = press;
		break;
	    case KeyEvent.VK_X:
		ICS_Summative.xPressed = press;
		break;
	    case KeyEvent.VK_I:
		ICS_Summative.iPressed = press;
		break;
	    case KeyEvent.VK_ENTER:
		ICS_Summative.enterPressed = press;
		break;
	}
	return false;
    } // DispatchKeyEvent method
} // KeyHelper class
