/*  Author: Brett Stevens
File name: UI.java 
Purpose: 
Last modified: 
*/
import java.util.*;
import java.io.*;
import java.text.*;
public class UI
{
	//class fields
	private simulation sim;
	
	//constructor
	public UI()
	{
		sim = new simulation();
	}
	
	/* Function: mainMenu
	Purpose: handles user input to select appropriate functions/path
	*/
	public void mainMenu()
	{
		Scanner sc = new Scanner(System.in);
		int selection = 0;
		
		System.out.println("	INTERACTIVE MODE:");
		do
		{
			try
			{
				System.out.println("		Menu:\n" +
									"1. Load networks\n" +
									//"2. Set probabilities\n" +
									"2. Node operations (find, insert, delete)\n" +
									"3. Edge operations (follow - add, remove)\n" +
									//"5. New post\n" +
									"4. Display network\n" +
									/* "7. Display statistics\n" +
									"8. Update (run a timestep)\n" +
									"9. Save network\n" + */
									"5. Exit\n");
				selection = sc.nextInt();
				
				
				switch (selection)
				{
					case 1:
						inputNetfile();
						break;
						
					/*case 2:	
						inputProbs();
						break;*/
					
					case 2:
						nodeOp();
						break;
					
					case 3:
						edgeOp();
						break;
					
					/*case 5:
						addPost();
						break;*/
					
					case 4:
						sim.displayNetwork();
						break;
					
					/*case 7:
						sim.displayStats();
						break;
					
					case 8:
						System.out.println("Not implemented");
						break;
					
					case 9:
						System.out.println("Not implemented");
						break;*/
						
					case 5:
						System.out.println("Exiting program...");
						break;
				}
				
			}
			catch(Exception e)
			{
				System.out.println("ERROR: " + e + "\nPlease enter a valid input.\n");
				sc.nextLine();
			}
		
		}while(selection != 5);
		
	}
	
	/* Function: addPost
	Purpose: 
	*/
	public void addPost()
	{
		Scanner sc = new Scanner(System.in);
		String name, text;
		int cbf;
		
		System.out.println("Whos making the post: ");
		name = sc.nextLine();
		
		sim.addNode(name);
		
		System.out.println("What does the post say: ");
		text = sc.nextLine();
		
		System.out.println("Whats the posts clickbait factor: ");
		cbf = sc.nextInt();
		
		sim.addPost(name, text, cbf);
		System.out.println("Post added..");
	}
	
	/* Function: inputNetfile
	Purpose: 
	*/
	public void inputNetfile()
	{
		Scanner sc = new Scanner(System.in);
		String filename;
		
		System.out.println("Input network file: ");
		filename = sc.nextLine();
		
		sim.loadNetwork(filename);
	}
	
	/* Function: inputProbs
	Purpose: 
	*/
	public void inputProbs()
	{
		Scanner sc = new Scanner(System.in);
		double prob_like, prob_foll;
		
		System.out.println("Input probability of a like: ");
		prob_like = sc.nextDouble();
		
		System.out.println("Input probability to follow: ");
		prob_foll = sc.nextDouble();
		
		sim.setLikeProb(prob_like);
		sim.setFollProb(prob_foll);
	}
	
	/* Function: nodeOp
	Purpose: handles user input to select appropriate module
	*/
	public void nodeOp()
	{
		Scanner sc = new Scanner(System.in);
		int operation;
		
		try
		{
			do
			{
				
				System.out.println("What node operation would you like to make:\n" +
							"1: Add\n" +
							"2: Delete\n" +
							"3. Exit\n");
				operation = sc.nextInt();
			
				switch(operation)
				{
					case 1:
						addNode();
						break;
						
					case 2:
						System.out.println("not yet implemented\n");
						break;
						
					case 3:
						System.out.println("Exiting to main menu...\n");
						break;
				}
			
			}while(operation > 3 || operation < 1);
		}
		catch(Exception e)
		{
			System.out.println("Invalid input: " + e + "\n");
			sc.nextLine();
		}
	}
	
	/* Function: addNode
	Purpose: 
	*/
	public void addNode()
	{
		Scanner sc = new Scanner(System.in);
		String name;
		
		System.out.println("Enter name: ");
		name = sc.nextLine();
		sim.addNode(name);
		System.out.println("\n" + name + " added..");
	}
	
	/* Function: edgeOp
	Purpose: handles user input to select appropriate module
	*/
	public void edgeOp()
	{
		Scanner sc = new Scanner(System.in);
		int operation;
		
		try
		{
			do
			{
				
				System.out.println("What type of edge operation:\n" +
							"1: Follow\n" +
							"2: Unfollow\n" +
							"3. Exit\n");
				operation = sc.nextInt();
			
				switch(operation)
				{
					case 1:
						addEdge();
						break;
						
					case 2:
						System.out.println("not yet implemented");
						break;
						
					case 3:
						System.out.println("Exiting to main menu...\n");
						break;
				}
			
			}while(operation > 3 || operation < 1);
		}
		catch(Exception e)
		{
			System.out.println("Invalid input: " + e + "\n");
			sc.nextLine();
		}
	}
	
	/* Function: addEdge
	Purpose: 
	*/
	public void addEdge()
	{
		Scanner sc = new Scanner(System.in);
		String follower, followee;
		
		System.out.println("Enter followee: ");
		followee = sc.nextLine();
		System.out.println("Enter follower: ");
		follower = sc.nextLine();
		
		sim.addEdge(followee, follower);
	}
}