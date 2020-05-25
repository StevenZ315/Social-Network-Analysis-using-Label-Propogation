package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class GroupClassifier {
	Random random;
	
	public GroupClassifier() {
		// TODO Auto-generated constructor stub
		random = new Random();
	}

	public GroupClassifier(long seed) {
		random = new Random(seed);
	}
	
	/* Separate vertices into different label groups.
	 * Modify the graph labels inplace.
	 */

	public void identify(CapGraph G) {
		boolean stop = true;
		ArrayList<Integer> vertices = new ArrayList<Integer>(G.exportGraph().keySet());
		int iteration = 0;
		
		do {
			iteration ++;
			stop = true;
			Collections.shuffle(vertices, random);
			
			// Update label based on the most used label of neighbors.
			for (int v : vertices) {
				String newLabel = nextLabel(G, v);
				//System.out.println("Node: " + v + "\tOld label: " + G.getLabel(v) + "\tNew label: " + newLabel);
				
				// Update and set flag to false if there's a difference.
				if (!newLabel.equals(G.getLabel(v))) {
					stop = false;
					G.setLabel(v, newLabel);
				}
			}

		} while (stop == false);
		
		//System.out.println("Iteration number: " + iteration);
	}
	
	private String nextLabel(CapGraph G, int num) {
		HashMap<String, Integer> neighborLabels = new HashMap<String, Integer>();
		int maxCount = 0;
		String currLabel = G.getLabel(num);
		
		// Count labels of neighbors
		for (int neighbor : G.getNeighbors(num)) {
			String label = G.getLabel(neighbor);
			if (!neighborLabels.containsKey(label)) {
				neighborLabels.put(label, 0);
			}
			
			int count = neighborLabels.get(label) + 1;
			if (count > maxCount) {
				maxCount = count;
			}
			neighborLabels.put(label, count);
		}
		
		// Find the labels with maximum count.
		ArrayList<String> topLabels = new ArrayList<String>();
		for (String label : neighborLabels.keySet()) {
			if (neighborLabels.get(label) == maxCount) {
				topLabels.add(label);
			}
		}
		
		// If current label is in top label list. Do not update.
		if (topLabels.contains(currLabel)) {
			return currLabel;
		}
		
		// If multiple labels exists, randomly choose one.
		return topLabels.get(random.nextInt(topLabels.size()));
		
	}
	
	/* Identify community groups based on aggregation of multiple graphs.
	 * @param G
	 * @param num: number of additional graphs to aggregate.
	 */
	
	public void aggregateIdentify(CapGraph G, int num) {
		
		// Generate identified graphs.
		ArrayList<CapGraph> graphList = new ArrayList<CapGraph>();
		for (int k = 0; k < num; k++) {
			CapGraph tempGraph = G.getCopy();
			identify(tempGraph);
			graphList.add(tempGraph);
		}
		
		// Initial classification on the main graph.
		identify(G);
		
		// Aggregation.
		for (CapGraph other : graphList) {
			//System.out.print("check");
			aggregate(G, other);
		}
	}
	
	/* Helper function to aggregate additional graph on the main graph.
	 * 
	 */
	private void aggregate(CapGraph main, CapGraph other) {
		// Iterate through all the vertices and combine the labels.
		for (int vertex : main.exportGraph().keySet()) {
			String mainLabel = main.getLabel(vertex);
			String otherLabel = other.getLabel(vertex);
			String newLabel = mainLabel + "-" + otherLabel;
			//System.out.println("Node: " + vertex + "\tOld label: " + mainLabel + "\tNew label: " + newLabel);
			main.setLabel(vertex, newLabel);
		}
		identify(main);
	}


}
