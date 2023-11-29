/*Author: Brett Stevens
File name: UnitTestDSAGraph.java
Purpose:
Last modified:
*/
import java.util.*;
import java.io.*;
import java.text.*;
public class UnitTestDSAGraph
{
	public static void main(String[] args)
    {
        try
        {
			//create graph
			DSAGraph graph = new DSAGraph();
			
			System.out.println("Add edge: A - B");
			graph.addEdge("A", "B");
			System.out.println("Edge added.");
			
			System.out.println("Add edge: A - C");
			graph.addEdge("A", "C");
			System.out.println("Edge added.");
			
			System.out.println("Add edge: B - C");
			graph.addEdge("B", "C");
			System.out.println("Edge added.");
			
			System.out.println("ADJ LISTS : ");
			Iterator verticesIterator = graph.vertices.iterator();
			while(verticesIterator.hasNext())
			{
				System.out.println(verticesIterator.next().toString());
			}
			
			System.out.println("Vertex count: " + graph.getVertexCount());
			System.out.println("Edge count: " + graph.getEdgeCount());
		}
		catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
		}
	}
}