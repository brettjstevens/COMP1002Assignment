/* Author: Brett Stevens
File name: DSALinkedList.java
Purpose:
Last modified:
*/
import java.util.*;
import java.io.*;

public class DSALinkedList implements Iterable, Serializable
{
	//class fields
	private DSAListNode head, tail;

	/*defualt constructor */
	public DSALinkedList()
	{
		head = null;
		tail = null;
	}

	/* ACCESSORS */
	public boolean isEmpty()
	{
		return(head == null);
	}

	public Object peekFirst()
	{
		Object nodeVal;

		if(isEmpty())
		{
			throw new IllegalArgumentException("Cannot peek first: Node is empty.");
		}
		else
		{
			nodeVal = head.getValue();
		}

		return nodeVal;
	}

	public Object peekLast()
	{
		Object nodeVal;

		if(isEmpty())
		{
			throw new IllegalArgumentException("Cannot peek last: Node is empty.");
		}
		else
		{
			nodeVal = tail.getValue();
		}
		
		return nodeVal;
	}
	
	/* MUTATORS */
	public void insertFirst(Object newVal)
	{
		DSAListNode newNd = new DSAListNode(newVal);

		if(isEmpty())
		{
			tail = newNd;
		}
		else if(head.getNext() == null)
		{
			tail.setPrevious(newNd);
			newNd.setNext(tail);
		}
		else
		{
			head.setPrevious(newNd);
			newNd.setNext(head);
		}
		
		head = newNd;
	}
	

	public void insertLast(Object newVal)
	{
		DSAListNode newNd = new DSAListNode(newVal);

		if(isEmpty())
		{
			head = newNd;
		}
		else if(head.getNext() == null)
		{
			head.setNext(newNd);
			newNd.setPrevious(tail);
		}
		else
		{
			newNd.setPrevious(tail);
			tail.setNext(newNd);
		}
		
		tail = newNd;
	}

	public Object removeFirst()
	{
		Object nodeVal;
		
		if(isEmpty())
		{
			throw new IllegalArgumentException("Cannot remove first: Node is empty.");
		}
		else if(head.getNext() == null)
		{
			nodeVal = head.getValue();
			head = null;
			tail = null;
		}
		else
		{
			nodeVal = head.getValue();
			head = head.getNext();
			head.setPrevious(null);
		}
		
		
		return nodeVal;
	}
	
	public Object removeLast()
	{
		Object nodeVal;
		
		if(isEmpty())
		{
			throw new IllegalArgumentException("Cannot remove last: Node is empty.");
		}
		else if(head.getNext() == null)
		{
			nodeVal = head.getValue();
			head = null;
			tail = null;
		}
		else
		{
			nodeVal = tail.getValue();
			tail = tail.getPrevious();
			tail.setNext(null);
		}
		
		return nodeVal;
	}
	
//-------------------------------------------------------------------------------------

	private class DSAListNode
	{
		//class fields
		private Object value;
		private DSAListNode next, previous;

		/* alternate constructor */
		public DSAListNode(Object inVal)
		{
			value = inVal;
			next = null;
			previous = null;
		}

		/* ACCESSORS */
		public Object getValue()
		{
			return value;
		}

		public DSAListNode getNext()
		{
			return next;
		}
		
		public DSAListNode getPrevious()
		{
			return previous;
		}
		
		/* MUTATORS */
		public void setValue(Object inVal)
		{
			value = inVal;
		}

		public void setNext(DSAListNode newNext)
		{
			next = newNext;
		}
		
		public void setPrevious(DSAListNode newPrevious)
		{
			previous = newPrevious;
		}
	}
	
	public Iterator iterator()
	{
		return new DSALinkedListIterator(this);
	}
	
	private class DSALinkedListIterator implements Iterator
	{
		private DSAListNode iterNext;
		
		public DSALinkedListIterator(DSALinkedList theList)
		{
			iterNext = theList.head;
		}
		
		//Iterator interface implementation
		public boolean hasNext()
		{
			return(iterNext != null);
		}
		
		public Object next()
		{
			Object value;
			
			if(iterNext == null)
			{
				value = null;
			}
			else
			{
				value = iterNext.getValue();
				iterNext = iterNext.getNext();
			}
			
			return value;
		}
		
		public void remove()
		{
			throw new UnsupportedOperationException("Not supported.");
		}
	}
}