/*Janet Chen
December 2017
CJ_RocketLanding
Rocket blasting off and then landing
*/
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import hsa.Console;
import sun.audio.*;
import javax.imageio.*;
import java.io.*;
import java.util.Random;

public class BackgroundGraphics
{
    static Console c;           // The output console
    static Graphics2D graphics;
    static Random rand = new Random ();

//*******************************Image Loading Method****************************
    public static Image loadBackground (String name) throws Exception
    {
    
 Image img = ImageIO.read (new File (name));
 return img;
 
    }
}
