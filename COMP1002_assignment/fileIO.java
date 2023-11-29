/*  Author: Brett Stevens
File name: fileIO.java 
Purpose: 
Last modified: 
*/
import java.util.*;
import java.io.*;
import java.text.*;
public class fileIO
{
	/* Function: loadNetwork
	Purpose: to load network in from file
	*/
	public DSAGraph loadNetwork(DSAGraph network, String netfile)
	{
		Scanner sc = new Scanner(System.in);
        FileInputStream fileStrm = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
		
		int lineNum;
		String line;
		
		try 
        {
            fileStrm = new FileInputStream(netfile);
            rdr = new InputStreamReader(fileStrm);	 
            bufRdr = new BufferedReader(rdr);

            lineNum = 0;
            line = bufRdr.readLine();
            
			while(line != null)
            {
                network = processNetworkLine(network, line);
				lineNum++;
                line = bufRdr.readLine();
            }
            
			fileStrm.close();
        }
        catch (IOException e)
        {   
            if(fileStrm != null)
            {
                try
				{
					fileStrm.close(); 
				}catch(IOException ex2){ }
            }
            System.out.println("Error in file processing: " + e.getMessage());
        }
		
		return network;
	}
	
	public DSAGraph processNetworkLine(DSAGraph network, String line)
	{
        String person = "", follower = "";
		String[] tokens = line.split(":");
        
		try
        {
            if(tokens.length == 2)
			{
				person = tokens[0];
				follower = tokens[1];
				
				network.addEdge(person, follower);
			}
        }
        catch (Exception e)
        {
            throw new IllegalStateException("csv row had invalid format");
        }
		
		return network;
	}
	
	public DSAGraph loadEventfile(DSAGraph network, String eventfile)
	{
		Scanner sc = new Scanner(System.in);
        FileInputStream fileStrm = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
		
		int lineNum;
		String line;
		
		try 
        {
            fileStrm = new FileInputStream(eventfile);
            rdr = new InputStreamReader(fileStrm);	 
            bufRdr = new BufferedReader(rdr);

            lineNum = 0;
            line = bufRdr.readLine();
			
			while(line != null)
            {
                network = processEventLine(network, line);
				lineNum++;
                line = bufRdr.readLine();
            }
            
			fileStrm.close();
        }
        catch (IOException e)
        {   
            if(fileStrm != null)
            {
                try
				{
					fileStrm.close(); 
				}catch(IOException ex2){ }
            }
            System.out.println("Error in file processing: " + e.getMessage());
        }
		
		return network;
	}
	
	public DSAGraph processEventLine(DSAGraph network, String line)
	{
		String[] tokens = line.split(":");
        
		try
        {
			if(tokens[0].equals("P"))
			{
				if(tokens.length == 4)
				{
					network.addPost(tokens[1], tokens[2], Integer.parseInt(tokens[3]));
				}
				else
				{
					network.addPost(tokens[1], tokens[2], 1);
				}
			}
			else if((tokens[0].equals("F")) && (tokens.length == 3))
			{
				network.addEdge(tokens[1], tokens[2]);
			}
			else if((tokens[0].equals("U")) && (tokens.length == 3))
			{
				network.removeEdge(tokens[1], tokens[2]);
			}
			else if((tokens[0].equals("R")) && (tokens.length == 2))
			{
				network.remove(tokens[1]);
			}

        }
        catch (Exception e)
        {
            throw new IllegalStateException("csv row had invalid format");
        }
		
		return network;
	}
	
	public void exportEvents(DSAGraph network)
	{
		FileOutputStream fileStrm = null;
		PrintWriter pw;
		String filename = "", timestamp;
		
		try 
		{
			timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			filename = timestamp + ".txt";
			
			fileStrm = new FileOutputStream(filename);
			pw = new PrintWriter(fileStrm);
			
			//while likes still happen
			
			Iterator verticesIterator = network.vertices.iterator();
			pw.println("\n\tNetwork State:\n<person> | <followers>");
			while(verticesIterator.hasNext())
			{
				pw.println(verticesIterator.next().toString());
			}
			
			/*Iterator postsIterator = network.posts.iterator();
			pw.println("\n\tPosts:");
			while(verticesIterator.hasNext())
			{
				pw.println(postsIterator.next().toString());
			}*/
			
			//endwhile
			pw.close();
		}
		catch (IOException e) 
		{
			if (fileStrm != null) 
			{
				try { fileStrm.close(); } catch (IOException ex2) { }
			
			}
			System.out.println("Error in writing to file: " + e.getMessage());
		}
		
		System.out.println("\nEvents exported as " + filename);
	}
}