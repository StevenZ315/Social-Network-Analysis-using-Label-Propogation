package graph;

import util.GraphLoader;
import java.lang.System;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CapGraphTester 
{	
	public static void main(String[] args) {
		//String filename = "data/football.txt";
		String filename = "data/facebook_2000.txt";
		CapGraph testGraph = new CapGraph();
		
		
		long start = System.nanoTime();
		GraphLoader.loadGraph(testGraph, filename);
		
		/*
		System.out.println(testGraph);
		System.out.println("Total time: " + total + " seconds");
		System.out.println("Total vertices: " + testGraph.getNumVertex() + 
						". Total edges: " + testGraph.getNumEdge());
		System.out.println("===================================");
		*/
		
		/*
		Graph egonet = testGraph.getEgonet(1);
		System.out.println(egonet);
		System.out.println("===================================");
		
		
		List<Graph> stronglyConnectedComponents = testGraph.getSCCs();
		int limit = 2;
		int count = 0;
		for (Graph scc : stronglyConnectedComponents) {
			System.out.println(scc);
			System.out.println("Total vertices: " + scc.getNumVertex() + 
					". Total edges: " + scc.getNumEdge());
			System.out.println("-----------------");
			count ++;
			if (count >= limit) {
				break;
			}
		}
		System.out.println("===================================");
		*/
		
		GroupClassifier classifier = new GroupClassifier();
		
		//classifier.identify(testGraph);
		classifier.aggregateIdentify(testGraph, 4);
		
		HashMap<String, ArrayList<Integer>> label = testGraph.getLabels();
		
		int threshold = testGraph.getNumVertex() / 100;
		System.out.println("Dataset used: " + filename);
		System.out.println("Total communities detected: " + label.keySet().size());
		//System.out.println("------------------------------------------------");
		
		int count = 0;
		for (String l : label.keySet()) {
			int size = label.get(l).size();
			if (size > threshold) {
				count ++;
				//System.out.println("Label: "+ l + "\tTotal member: " + label.get(l).size());
				//System.out.println("Label: "+ l + "\tTotal member: " + label.get(l));
			}			
		}
		//ystem.out.println("Communities with more than 1% of total users: " + count);
		//System.out.println("------------------------------------------------");
		long end = System.nanoTime();
		long total = (end - start) / 1000000000;
		System.out.println("Total runtime: " + total + " seconds");
		System.out.println("------------------------------------------------");
	}
	

}
