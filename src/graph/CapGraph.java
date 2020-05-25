/**
 * 
 */
package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * @author Your name here.
 * 
 * For the warm up assignment, you must implement your Graph in a class
 * named CapGraph.  Here is the stub file.
 *
 */
public class CapGraph implements Graph, Comparable<CapGraph>
{
	private HashMap<Integer, HashSet<Integer>> graph; 
	private HashMap<Integer, String> label;
	private int numVertex;
	private int numEdge;
	
	public CapGraph() {
		graph = new HashMap<Integer, HashSet<Integer>>();
		label = new HashMap<Integer, String>();
		numVertex = 0;
		numEdge = 0;
	}
	/* (non-Javadoc)
	 * @see graph.Graph#addVertex(int)
	 */
	@Override
	public void addVertex(int num) {
		// TODO Auto-generated method stub
		if (graph.containsKey(num)) {
			// System.out.println("Vertex already exists in the graph: " + num);
			return;
		} else {
			graph.put(num, new HashSet<Integer>());
			label.put(num, String.valueOf(num));
			numVertex ++;
		}

	}

	/* (non-Javadoc)
	 * @see graph.Graph#addEdge(int, int)
	 */
	@Override
	public void addEdge(int from, int to) {
		// TODO Auto-generated method stub
		if (!graph.containsKey(from) || !graph.containsKey(to)) {
			System.out.println("One or more of the Vertices of Edge does not exist in the graph: " + from + " " + to);
		} else if (graph.get(from).contains(to)) {
			// System.out.println("Edge already exists in the graph: " + from + " " + to);
			return;
		} else {
			graph.get(from).add(to);
			graph.get(to).add(from);
			numEdge ++;
		}
	}

	/* (non-Javadoc)
	 * @see graph.Graph#getEgonet(int)
	 */
	@Override
	public Graph getEgonet(int center) {
		// TODO Auto-generated method stub
		Graph egonet = new CapGraph();
		
		if (this.graph.containsKey(center)) {
			egonet.addVertex(center);
			
			// add friend vertex		
			for (int vertex : this.graph.get(center)) {
				egonet.addVertex(vertex);
			}
			
			// add edges between vertices in egonet
			Set<Integer> vertices = egonet.exportGraph().keySet();
			for (int from : vertices) {
				for (int to : vertices) {
					if (this.graph.get(from).contains(to)) {
						egonet.addEdge(from, to);
					}
				}
			}			
		}
		return egonet;
	}

	/* (non-Javadoc)
	 * @see graph.Graph#getSCCs()
	 */
	@Override
	public List<Graph> getSCCs() {
		// TODO Auto-generated method stub
		Stack<Integer> vertices = this.getVertexStack();
		CapGraph transpose = this.getTranspose();
		
		// first pass
		Stack<Integer> finished = DFS.dfs(this, vertices);
		
		// second pass
		List <Graph> stronglyConnectedComponents = DFS.constructSCC(transpose, finished);
		Collections.sort(stronglyConnectedComponents, Collections.reverseOrder());
		return stronglyConnectedComponents;
	}

	/* (non-Javadoc)
	 * @see graph.Graph#exportGraph()
	 */
	@Override
	public HashMap<Integer, HashSet<Integer>> exportGraph() {
		// TODO Auto-generated method stub
		return graph;
	}
	
	public HashSet<Integer> getNeighbor(int from) {
		if (!this.graph.containsKey(from)) {
			System.out.println("Vertex does not exist in the graph.");
			return new HashSet<Integer>();
		}
		return this.graph.get(from);
	}
	
	public int getNumVertex() {
		return numVertex;
	}
	
	public int getNumEdge() {
		return numEdge;
	}
	
	public Set<Integer> getNeighbors(int num) {
		if (!this.graph.containsKey(num)) {
			System.out.println("Vertex does not exist in the graph.");
			return new HashSet<Integer>();
		} else {
			return this.graph.get(num);
		}
	}
	
	
	public String getLabel(int num) {
		if (!label.containsKey(num)) {
			System.out.println("Key not exist in the graph: " + num);
			return "";
		}
		return label.get(num);
	}
	
	public void setLabel(int num, String text) {
		if (!this.label.containsKey(num)) {
			System.out.println("Key not exist in the graph: " + num);
			return;
		}
		this.label.put(num, text);
	}
	
	private Stack<Integer> getVertexStack() {
		Stack<Integer> stack = new Stack<Integer>();
		for (int vertex : graph.keySet()) {
			stack.push(vertex);
		}
		return stack;
	}
	
	// return a copy of the current graph.
	public CapGraph getCopy() {
		CapGraph copy = new CapGraph();
		for (int vertex : graph.keySet()) {
			copy.addVertex(vertex);
		}
		for (int from : graph.keySet()) {
			for (int to : graph.get(from)) {
				copy.addEdge(from, to);
			}
		}
		return copy;
	}
	
	// return a transpose of the current graph.
	public CapGraph getTranspose() {
		CapGraph transpose = new CapGraph();
		for (int vertex : graph.keySet()) {
			transpose.addVertex(vertex);
		}
		for (int from : graph.keySet()) {
			for (int to : graph.get(from)) {
				transpose.addEdge(to, from);
			}
		}
		return transpose;
	}
	
	@Override
	public int compareTo(CapGraph other) {
		// TODO Auto-generated method stub
		return Integer.compare(this.numVertex+ this.numEdge, other.numVertex + other.numEdge);
	}
	
	// return existing labels with its vertices.
	public HashMap<String, ArrayList<Integer>> getLabels() {
		HashMap<String, ArrayList<Integer>> ret = new HashMap<String, ArrayList<Integer>>();
		for (int vertex : graph.keySet()) {
			String label = this.getLabel(vertex);
			if (!ret.containsKey(label)) {
				ret.put(label, new ArrayList<Integer>());
			}
			ret.get(label).add(vertex);
		}
		return ret;
	}
	
	public String toString() {
		String ret = "";
		int numLineToPrint = 100;
		int count = 0;
		
		for (int vertex : graph.keySet()) {
			ret += vertex + " : " + graph.get(vertex) + "\n";
			count ++;
			if (count >= numLineToPrint) {
				ret += "...Only print the first 100 vertices.";
				break;
			}
		}
		return ret;
	}


}
