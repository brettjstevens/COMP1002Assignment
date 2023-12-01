/*  Author: Brett Stevens
File name: SocialSim.java 
Purpose: 
Last modified: 
*/
import java.util.*;
import java.io.*;
import java.text.*;
public class SocialSim
{
	public static void main(String[] args)
	{
		try
		{
			if(args.length == 0)
			{
				System.out.println("To go to interactive mode type: java Social Sim -i");
			}
			else if(args[0].equals("-i"))
			{
				UI ui = new UI();
				
				ui.mainMenu();
			}
			else if((args.length == 5) && (args[0].equals("-s")))
			{
				simulation sim = new simulation();
				String netfile = args[1];
				String eventfile = args[2];
				double prob_like = Double.parseDouble(args[3]);
				double prob_foll = Double.parseDouble(args[4]);
				
				sim.simulator(netfile, eventfile, prob_like, prob_foll);
			}
		}
		catch (Exception e)
		{
			System.out.println("Error: " + e);
		}
		finally
		{
			System.out.println("Bye.");
		}
	}
}	
