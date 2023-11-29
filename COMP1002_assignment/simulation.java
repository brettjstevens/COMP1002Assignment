/*  Author: Brett Stevens
File name: simulation.java 
Purpose: 
Last modified: 
*/
import java.util.*;
import java.io.*;
import java.text.*;
public class simulation
{
	//class fields
	private DSAGraph network;
	private double likeProb, follProb;
	
	//constructor
	public simulation()
	{
		network = new DSAGraph();
		likeProb = 0;
		follProb = 0;
	}
	
	//MUTATORS
	public void setLikeProb(double prob_like)
	{
		likeProb = prob_like;
	}
	
	public void setFollProb(double prob_foll)
	{
		follProb = prob_foll;
	}
	
	public void addNode(String label)
	{
		network.addVertex(label);
	}
	
	public void addEdge(String label1, String label2)
	{
		network.addEdge(label1, label2);
	}
	
	public void addPost(String name, String text, int cbf)
	{
		network.addPost(name, text, cbf);
	}
	
	/* Function: simulator
	Purpose:
	*/
	public void simulator(String netfile, String eventfile, double prob_like, double prob_foll)
	{
		
	}
	
	/* Function: loadNetwork
	Purpose: to call file io loadNetwork()
	*/
	public void loadNetwork(String netfile)
	{
		fileIO io = new fileIO();
		
		network = io.loadNetwork(network, netfile);
	}
	
	/* Function: displayNetwork
	Purpose: to display adjacency list of network
	*/
	public void displayNetwork()
	{
		Iterator verticesIterator = network.vertices.iterator();
		
		System.out.println("\n\tNetwork:\n<person> | <followers>");
		
		while(verticesIterator.hasNext())
		{
			System.out.println(verticesIterator.next().toString());
		}
	}
	
	/* Function: displayStats
	Purpose: to display stats of network
	*/
	public void displayStats() //????
	{	
		DSAHeap Heap = new DSAHeap(network.getVertexCount());
		
		Iterator vertexIterator = network.vertices.iterator();
		while(vertexIterator.hasNext())
		{
			Heap.add(network.getFollowerCount(network.convertObjtoVertex(vertexIterator.next())), 
												network.getVertLabel(network.convertObjtoVertex(vertexIterator.next())));
		}
		
		System.out.println("People in order of popularity: \n<followers>: <person>");
		for(int i = 0; i < network.getVertexCount(); i++)
		{
			System.out.println(Heap.getPriority(Heap.heap[i]) + ": " + Heap.getValue(Heap.heap[i]));
		}
	}
	
	/* Function: export
	Purpose: to export timesteps to file
	*/
	public void export()
	{
		fileIO io = new fileIO();
		
		io.exportEvents(network);
	}
	
	public void timeStep(int prob_like, int prob_foll)
	{
		Random rand = new Random();
		int randNum = rand.nextInt(100);
		
		Iterator postsIterator = network.posts.iterator();
		while(postsIterator.hasNext())
		{
			//String owner = network.convertPost(postsIterator.next()).getOwner();
			
			//DSALinkedList followers = network.getFollowers(owner);
			
			//network.getPost(owner).getText();
		}
	}
}