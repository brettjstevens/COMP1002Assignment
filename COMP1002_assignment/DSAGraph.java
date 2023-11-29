/*Author: Brett Stevens
File name: DSAGraph.java
Purpose:
Last modified:
Reference: 
*/
import java.util.*;
import java.text.*;

public class DSAGraph
{
	//class fields
    protected DSALinkedList vertices;
	protected DSALinkedList posts;
	private int V, E;	//no. verts and edges
	private int postCount;
	
	//default constructor
	public DSAGraph()
	{
		vertices = new DSALinkedList();
	}
	
	//MUTATORS 
	public Post convertObjtoPost(Object obj)
	{
		return (Post)obj;
	}
	
	public DSAGraphVertex convertObjtoVertex(Object obj)
	{
		return (DSAGraphVertex)obj;
	}
	
	public void addVertex(String label)
	{
		DSAGraphVertex newVertex = new DSAGraphVertex(label);
		
		if(!hasVertex(label))
		{
			vertices.insertLast(newVertex);
			V++;
		}
	}
	
	public void addEdge(String label1, String label2)
	{
		if(!hasVertex(label1))
		{
			addVertex(label1);
		}
		
		if(!hasVertex(label2))
		{
			addVertex(label2);
		}
		
		getVertex(label1).addAdj(getVertex(label2));
		E++;
	}
	
	public void removeEdge(String label1, String label2)
	{
		System.out.println("yet to be implemented");
	}
	
	public void addPost(String person, String text, int CBF)
	{
		Post post = new Post(person, text);
		post.setCbFactor(CBF);
		
		posts.insertLast(post);
		postCount++;
	}
	
	public void remove(String person)
	{
		System.out.println("yet to be implemented");
	}
	
	//ACCESSORS
	public boolean hasVertex(String label)
	{
		Iterator verticesIterator = vertices.iterator();
		boolean yay = false;
		
		while((verticesIterator.hasNext()) && (yay != true))
		{
			DSAGraphVertex nextVert = (DSAGraphVertex)verticesIterator.next();
			
			if((nextVert.getLabel()).equals(label))
			{
				yay = true;
			}
		}
		
		return yay;
	}
	
	public int getVertexCount()
	{
		return V;
	}
	
	public int getEdgeCount()
	{
		return E;
	}
	
	public int getPostCount()
	{
		return postCount;
	}
	
	public String getVertLabel(DSAGraphVertex vert)
	{
		return vert.getLabel();
	}
	
	public boolean hasFollowers(Object obj)
	{
		DSAGraphVertex vert = (DSAGraphVertex) obj;
		
		return vert.hasFollowers();
	}
	
	public DSAGraphVertex getVertex(String label)
	{
		DSAGraphVertex nextVert = new DSAGraphVertex();
		Iterator verticesIterator = vertices.iterator();
		boolean yay = false;
		
		while((verticesIterator.hasNext()) && (yay != true))
		{
			nextVert = (DSAGraphVertex)verticesIterator.next();
			
			if((nextVert.getLabel()).equals(label))
			{
				yay = true;
			}
		}
		
		return nextVert;
	}
	
	public DSALinkedList getFollowers(String label)
	{
		DSALinkedList newList = new DSALinkedList();
		Iterator verticesIterator = vertices.iterator();
		boolean yay = false;
		
		while((verticesIterator.hasNext()) && (yay != true))
		{
			DSAGraphVertex nextVert = (DSAGraphVertex)verticesIterator.next();
			
			if((nextVert.getLabel()).equals(label))
			{
				newList = getVertex(label).links;
				
				yay = true;
			}
		}
		
		return newList;
	}
	
	public Post getPost(String label)
	{
		Post nextPost = new Post();
		Iterator postIterator = posts.iterator();
		boolean yay = false;
		
		while((postIterator.hasNext()) && (yay != true))
		{
			nextPost = (Post)postIterator.next();
			
			if((nextPost.getOwner()).equals(label))
			{
				yay = true;
			}
		}
		
		return nextPost;
	}
	
	public DSALinkedList getPosts()
	{
		return posts;
	}
	
	public int getFollowerCount(DSAGraphVertex vert)
	{
		return vert.getFollowerCount();
	}
	
	//-------------------------------------------------
	private class DSAGraphVertex
	{
		//class fields
		private String label;
		private DSALinkedList links; 
		private int followerCount = 0;
		private boolean visited;
		
		//default constructor
		public DSAGraphVertex()
		{
			label = null;
			links = null;
			visited = false;
		}
		
		//alternate constructor
		public DSAGraphVertex(String inLabel)
		{
			label = inLabel;
			links = new DSALinkedList();
			visited = false;
		}
		
		//ACCESSORS
		public boolean hasFollowers()
		{
			boolean yay = false;
			
			if(!(links.isEmpty()))
			{
				yay = true;
			}
			
			return yay;
		}
		
		public String getLabel()
		{
			return label;
		}
		
		public int getFollowerCount()
		{
			return followerCount;
		}
		
		public boolean getVisited()
		{
			System.out.println("Not yet implemented");
			return true;
		}
		
		public String toString()
		{
			String st = label + " |";
			int first = 0;
		
			Iterator listIterator = links.iterator();
			while(listIterator.hasNext())
			{
				DSAGraphVertex next = (DSAGraphVertex)listIterator.next();
				
				if(first == 0)
				{
					st += " " + next.getLabel();
					first++;
				}
				else
				{
					st += "; " + next.getLabel();
				}
			}
			
			return st;
		}
		
		//MUTATORS
		public void addAdj(DSAGraphVertex vertex)
		{
			boolean notPresent = false;
			Iterator linksIterator = links.iterator();
			
			if(links.isEmpty())
			{
				notPresent = true;
			}
			
			while(linksIterator.hasNext())
			{
				DSAGraphVertex nextLink = (DSAGraphVertex)linksIterator.next();
				
				if(!(vertex.equals(nextLink)))
				{
					notPresent = true;
				}
			}
			
			if(notPresent)
			{
				links.insertLast(vertex);
				followerCount++;
			}
		}
		
		public boolean equals(DSAGraphVertex inVertex)
		{
			boolean yay = false;
			
			if(inVertex.getLabel().equals(label))
			{
				yay = true;
			}
			
			return yay;
		}
		
		public void setVisited()
		{
			visited = true;
		}
		
		public void clearVisited()
		{
			visited = false;
		}
		
	}
	
	//-------------------------------------------------------------------------------------------
	private class Post
	{
		//class fields
		private String owner;
		private String text;
		private int likes;
		private DSALinkedList likers;
		private int cbFactor;				//click bait factor (multiplier)
		
		//default constructor
		public Post()
		{
			owner = null;
			text = null;
			likes = 0;
			likers = null;
			cbFactor = 1;
		}
		
		//alt. constructor
		public Post(String inOwner, String inText)
		{
			owner = inOwner;
			text = inText;
			likes = 0;
			likers = new DSALinkedList();
			cbFactor = 1;
		}
		
		/*public String toString()
		{
			String st = "Owner: " + owner + "\nPost: " + text + 
		}*/
		
		//ACCESSORS
		public String getOwner()
		{
			return owner;
		}
		
		public String getText()
		{
			return text;
		}
		
		public int getCbFactor()
		{
			return cbFactor;
		}
		
		public int getLikes()
		{
			return likes;
		}
		
		public DSALinkedList getLikers()
		{
			return likers;
		}
		
		//MUTATORS
		public void setOwner(String inOwner)
		{
			owner = inOwner;
		}
		
		public void setText(String inText)
		{
			text = inText;
		}
		
		public void setCbFactor(int inCBF)
		{
			cbFactor = inCBF;
		}
		
		public void addLike()
		{
			likes++;
		}
		
		public void addToLikers(String label)
		{
			likers.insertLast(label);
		}
	}
	
	/*//-------------------------------------------------
	private class DSAGraphEdge
	{
		//class fields
		private String label;
		private Object value;
		
		//alternate constructor
		public DSAGraphEdge(String inLabel, Object inValue)
		{
			label = inLabel;
			value = inValue;
		}
		
		//ACCESSORS
		public String getLabel()
		{
			return label;
		}
		
		public Object getValue()
		{
			return value;
		}
		
		public DSAGraphVertex getFrom()
		{
			
		}
		
		public DSAGraphVertex getTo()
		{
			
		}
		
		public boolean isDirected()
		{
			
		}
		
		public String toString()
		{
			
		}		
	} */
}
