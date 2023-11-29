/*Author: Brett Stevens
File name: UnitTestsimulation.java
Purpose:
Last modified:
*/
import java.util.*;
import java.io.*;
import java.text.*;
public class UnitTestsimulation
{
	public static void main(String[] args)
    {
        try
        {
			simulation sim = new simulation();
			
			sim.loadNetwork("netfile.txt");
			System.out.println("netfile.txt successfully loaded.\nDisplaying adj. list of network:");
			sim.displayNetwork();
			sim.export();
		}
		catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
		}
	}
}