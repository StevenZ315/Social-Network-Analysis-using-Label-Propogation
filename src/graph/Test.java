package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

public class Test {
	public static void main(String args[]) {
	      // HashSet declaration
	      HashSet<String> hset = 
	               new HashSet<String>();

	      // Adding elements to the HashSet
	      hset.add("Apple");
	      hset.add("Mango");
	      hset.add("Grapes");
	      hset.add("Orange");
	      hset.add("Fig");
	      //Addition of duplicate elements
	      hset.add("Apple");
	      hset.add("Mango");
	      //Addition of null values
	      hset.add(null);
	      hset.add(null);

	      //Displaying HashSet elements
	      ArrayList<String> hlist = new ArrayList<String>(hset);
	      for (int i = 0; i < 10; i++) {
	    	  Collections.shuffle(hlist);
		      Iterator<String> iter = hlist.iterator();
		      while (iter.hasNext()) {
		    	  System.out.print(iter.next() + " ");
		      }
		      System.out.println("=====");
	      }
	      
	    }
	
}
