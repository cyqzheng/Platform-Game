/*
Name: Michael Li
Date: January 2, 2018
Class code: ICS 2O3 - 02
Program name: SpiritOfCheers
A Spirit that cheers for the player.
*/

import java.awt.*;
import java.math.*;
import java.util.*;
import java.io.*;
import sun.audio.*;
import hsa.*;

public class SpiritOfCheers extends Spirit
{
    private InputStream in; // The InputStream that reads the sound file
    private AudioStream as; // The AudioStream that produces noise
    public boolean isCheering; // Whether or not the spirit is currently producing noise

    public SpiritOfCheers (Graphics g, int x1, int y1)  // Constructs a custom Spirit of Cheers
    {
	super (g, "Spirit Of Cheers.png", x1, y1);

	// Load noises
	try
	{
	    in = new FileInputStream (new File ("Spirit of Cheers.wav"));
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


    public void harm (Player p)  // Helper method to cheer for the player
    {
	if (!isCheering)
	{
	    // Start cheering for the player
	    AudioPlayer.player.start (as);
	    isCheering = true;
	}
    } // harm method


    public void unharm (Player p)  // Helper method to stop producing noise
    {
	if (isCheering)
	{
	    // Stop cheering for the player
	    AudioPlayer.player.stop (as);
	    isCheering = false;
	}
    } // unharm method
} // SpiritOfCheers class
