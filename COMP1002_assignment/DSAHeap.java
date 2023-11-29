/*Author: Brett Stevens
File name: DSAHeap.java
Purpose:
Last modified:
Reference: reused heapsort class from prac 7
*/
import java.io.*;
import java.util.*;
import java.text.*;

public class DSAHeap
{
    //class fields
    protected DSAHeapEntry[] heap;
    private int count;

    //constructor
    public DSAHeap(int maxSize)
    {
		heap = new DSAHeapEntry[maxSize];
		count = 0;
    }

    //MUTATORS
    public void add(int priority, Object value)
    {
		DSAHeapEntry newEntry = new DSAHeapEntry(priority, value);
		
		heap[count] = newEntry;
		trickleUp(count);
		count++;
    }

    public Object remove()
    {
		DSAHeapEntry root;
		
		if(heap[0] == null)
		{
			throw new IllegalArgumentException("Heap is empty.");
		}
		else
		{
			root = heap[0];
			heap[0] = heap[count - 1];
			trickleDown(0, count);
			count--;
		}
		
		return root.getValue();
    }

    public DSAHeapEntry[] heapSort(DSAHeapEntry[] array, int numItems)
    {
		heapify(array, numItems);
		
		for(int i = numItems - 1; i >= 1; i--)
		{
			swap(0, i);
			trickleDown(0, i);
		}
		
		return array;
    }
	
	public void heapify(DSAHeapEntry[] heapArray, int numItems)
	{
		for(int i = (numItems / 2) - 1; i >= 0; i--)
		{
			trickleDown(i, numItems);
		}
	}
	
	private void swap(int firstIdx, int secondIdx)
	{
		DSAHeapEntry temp;
		
		temp = heap[secondIdx];
		heap[secondIdx] = heap[firstIdx];
		heap[firstIdx] = temp;
	}

    private void trickleUp(int currIdx)
    {
        int parentIdx = (currIdx - 1) / 2;
		int temp;
		
		if(currIdx > 0)
		{
			if(heap[currIdx].getPriority() > heap[parentIdx].getPriority())
			{
				swap(currIdx, parentIdx);
				trickleUp(parentIdx);
			}
		}
    }

    private void trickleDown(int currIdx, int numItems)
    {
        int leftIdx = currIdx * 2 + 1;
		int rightIdx = leftIdx + 1;
		int largeIdx;
		
		if(leftIdx < numItems)
		{
			largeIdx = leftIdx;
			
			if(rightIdx < numItems)
			{
				if(heap[leftIdx].getPriority() < heap[rightIdx].getPriority())
				{
					largeIdx = rightIdx;
				}
			}
			
			if(heap[largeIdx].getPriority() > heap[currIdx].getPriority())
			{
				swap(largeIdx, currIdx);
				trickleDown(largeIdx, numItems);
			}
		}
    }

    //ACCESSORS
    public int getCount()
    {
        return count;
    }
	
	public int getPriority(DSAHeapEntry entry)
	{
		return entry.getPriority();
	}
	
	 public Object getValue(DSAHeapEntry entry)
	{
		return entry.getValue();
	}

    //------------------------------------------
    private class DSAHeapEntry
    {
        //class fields
        private int priority;
        private Object value;

        //construstor
        public DSAHeapEntry(int inPriority, Object inValue)
        {
            priority = inPriority;
			value = inValue;
        }

        //MUTATORS
        public void setPriority(int inPriority)
        {
            priority = inPriority;
        }

        public void setValue(Object inValue)
        {
            value = inValue;
        }

        //ACCESSORS
        public int getPriority()
        {
            return priority;
        }

        public Object getValue()
        {
            return value;
        }
    }
	
	//------------------------------------------
	public void fileIo(String filename)
	{
		heap = new DSAHeapEntry[7000];
		Scanner sc = new Scanner(System.in);
        FileInputStream fileStrm = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        int lineNum;
        String csvRow;
		
		try 
        {
            fileStrm = new FileInputStream(filename);
            rdr = new InputStreamReader(fileStrm);	 
            bufRdr = new BufferedReader(rdr);

            lineNum = 0;
            csvRow = bufRdr.readLine();
            
			while (csvRow != null)
            {
                heap[lineNum] = processLine(csvRow);
				lineNum++;
                csvRow = bufRdr.readLine();
            }   
            
			fileStrm.close();
        }
        catch (IOException e)
        {   
            if(fileStrm != null)
            {
                try{ fileStrm.close(); } catch(IOException ex2) { }
            }
            System.out.println("Error in file processing: " + e.getMessage());
        }
		
		SortNPrint(heap);
	}
	
	public DSAHeapEntry processLine(String csvRow)
	{
		DSAHeapEntry newEntry;
		
		String[] tokens = csvRow.split(",");
        try
        {
            newEntry = new DSAHeapEntry(Integer.parseInt(tokens[0]), tokens[1]);
			
        }
        catch (Exception e)
        {
            throw new IllegalStateException("csv row had invalid format");
        }
		
		return newEntry;
	}
	
	public void SortNPrint(DSAHeapEntry[] array)
	{
		DSAHeapEntry[] sortedArray = new DSAHeapEntry[7000];
		
		sortedArray = heapSort(array, 7000);
		
		FileOutputStream fileStrm = null;
		PrintWriter pw;
		
		try 
		{
			fileStrm = new FileOutputStream("SortedFile.txt");
			pw = new PrintWriter(fileStrm);
			
			for(int i = 0; i < 7000; i++)
			{
				pw.println(sortedArray[i].getPriority() + ", " + sortedArray[i].getValue() + "\n");
			}
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
	}
}
