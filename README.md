# Social-Network-Analysis-using-Label-Propogation
My Capstone project for OOP: Data Structures &amp; Beyond Specialization offered by UC San Diego ---- Community identification using Label Propogation Algorithm

This course is offered at <a href="https://www.coursera.org">Coursera</a> and is part of the <a href="https://www.coursera.org/specializations/java-object-oriented">Object Oriented Java Programming: Data Structure and Beyond Specializaiton</a>.

Here's my <a href = "">certificate</a> on completing the course.

## Questions
- Develop the label propagation algorithm to generate one feasible solution for community detection.
- Aggregate based on one set of feasible solutions to generate the community structure containing the most useful information.
- Optimize the algorithm to find overlapping communities in networks.

## Data Structures
Main Data Structure: The network has been laid out as a classic graph using an adjacency list. Each individual in the graph is a vertex and an edge between vertices represents a friendship.
An additional HashMap is used to store the vertex-label pair information.

## Algorithm Description and Complexity Analysis:
```
1. Generate the graph from input data. --- O(V+E)
2. Initialize different labels for all vertices. --- O(V)
3. For each iteration: --- O(N) (N: number of iterations)
Update the label for each vertex based on neighbor vertices. --- O(V+E)
Return if nothing to update.
4. Generate multiple graphs based on the above algorithm O(N*(V+E)) and combine the labels for same node in diifferent results to generate the aggregated graph. --- O(V+E).
```

The estimated running time is O(N*(V+E)), and it has been proven in the reference paper that irrespective of N, 95% of the nodes or more are classified correctly by the end of iteration 5. Thus this is a near-linear algorithm with estimation of O(V+E).

## Limitations and Risks
There is a random factor in label propagation process, thus the result for each run is different. It's hard to validate the correctness of the result. However, with aggregation, the final result is proven to be relatively stable with five graphs aggregated.

## Testing and Validation
```
Dataset used: data/football.txt
Total communities detected: 14
Total runtime: 0 seconds
------------------------------------------------
Dataset used: data/facebook_1000.txt
Total communities detected: 187
Total runtime: 0 seconds
------------------------------------------------
Dataset used: data/facebook_2000.txt
Total communities detected: 259
Total runtime: 0 seconds
------------------------------------------------
Dataset used: data/facebook_ucsd.txt
Total communities detected: 25
Total runtime: 11 seconds
------------------------------------------------
```
