/*Author: Brett Stevens
File name: HeapTestHarness.java
Purpose:
Last modified:
Reference: reused test harness from prac 7
*/
import java.util.*;
import java.io.*;
import java.text.*;
public class UnitTestHeap
{
	public static void main(String[] args)
    {
        try
        {
			DSAHeap heap = new DSAHeap(10);
			
			System.out.println("\nTESTING ADD AND REMOVE : ");
			System.out.println("Count: " + heap.getCount());
			heap.add(5, 1);
			heap.add(9, 2);
			heap.add(3, 3);
			heap.add(11, 8);
			heap.add(4, 5);
			System.out.println("Five entrys added.\nCount: " + heap.getCount());
			System.out.println("Remove root; should be 8: value = " + heap.remove());
			System.out.println("Count: " + heap.getCount());
			
			System.out.println("\nTESTING HEAP SORT : ");
			heap.fileIo("RandomNames7000.csv");
			System.out.println("RandomNames7000.csv ==sorted==> SortedFile.txt\n");
			
		}
		catch(IllegalArgumentException e)
        {
            System.out.println(e);
		}
	}
}