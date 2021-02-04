/*
Name: Michael Li
Date: January 2, 2018
Class code: ICS 2O3 - 02
Program name: SpiritOfEars
A Spirit that produces annoyingly loud noises.
*/

import java.awt.*;
import java.math.*;
import java.util.*;
import java.io.*;
import sun.audio.*;
import hsa.*;

public class SpiritOfEars extends Spirit
{
    private InputStream in; // The InputStream that reads the sound file
    private AudioStream as; // The AudioStream that produces noise
    public boolean isHarming; // Whether or not the spirit is currently producing noise

    public SpiritOfEars (Graphics g, int x1, int y1)  // Constructs a custom Spirit of Ears
    {
	super (g, "Spirit Of Ears.png", x1, y1);

	// Load noises
	try
	{
	    in = new FileInputStream (new File ("Spirit of Ears.wav"));
	}
	catch (FileNotFoundException ex)
	{
	}
	try
	{
	    as = new AudioStream (in);
	}
	catch (IOException ex)
	{
	}
	catch (NullPointerException ex)
	{
	}
    }


    public void harm (Player p)  // Helper method to produce annoyingly loud noises
    {
	if (!isHarming)
	{
	    // Start harming the player
	    AudioPlayer.player.start (as);
	    isHarming = true;
	}
    } // harm method


    public void unharm (Player p)  // Helper method to stop producing noise
    {
	if (isHarming)
	{
	    // Stop harming the player
	    AudioPlayer.player.stop (as);
	    isHarming = false;
	}
    } // unharm method
} // SpiritOfEars class
