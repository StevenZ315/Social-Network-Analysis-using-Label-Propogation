package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;


public class DFS {

    // Depth-first search algorithm
    public static Stack <Integer> dfs(Graph G, Stack <Integer> vertices) {
        HashSet <Integer> visited = new HashSet <>();
        Stack <Integer> finished = new Stack <>();

        while (!vertices.isEmpty()) {
            int temp = vertices.pop();
            if (!visited.contains(temp)) {
                DFSvisit(G, temp, visited, finished);
            }
        }
        return finished;
    }

    private static void DFSvisit(Graph G, Integer v, HashSet <Integer> visited, Stack <Integer> finished) {
        visited.add(v);
        for (int neighbor : G.exportGraph().get(v)) {
            if (!visited.contains(neighbor)) {
                DFSvisit(G, neighbor, visited, finished);
            }
        }
        finished.push(v);
    }

    /**
     * @param G
     * @param vertices
     * @return List of Strongly Connected Components as Graphs
     */
    public static List <Graph> constructSCC(Graph G, Stack <Integer> vertices) {
        HashSet <Integer> visited = new HashSet <>();
        Stack <Integer> finished = new Stack <>();
        List <Graph> graphList = new ArrayList <>();

        while (!vertices.isEmpty()) {
            int temp = vertices.pop();
            if (!visited.contains(temp)) {
                DFS.DFSvisit(G, temp, visited, finished);
                if (finished.size() > 0) {
                    Graph tempGraph = new CapGraph();
                    // add vertices.
                    for (int num : finished) {
                        tempGraph.addVertex(num);
                    }
                    // add edges.
                    for (int from : tempGraph.exportGraph().keySet()) {
                    	for (int to : tempGraph.exportGraph().keySet()) {
                    		if (G.exportGraph().get(from).contains(to)) {
                    			tempGraph.addEdge(from, to);
                    		}
                    	}
                    }
                    finished = new Stack <>();
                    graphList.add(tempGraph);
                }
            }
        }
        return graphList;
    }
}