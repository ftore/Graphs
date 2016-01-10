package directed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Digraph {
	private final int V;
	private int E;
	private int[] indegree;
	private Map<Integer, List<Integer>> adjList;
	
	public Digraph(int v) {
		V = v;
		E = 0;
		adjList = new HashMap<>();
		for(int i = 0; i < v; i++) {
			adjList.put(i, new ArrayList<>());
		}
	}
	
	public int V() {
		return V;
	}
	
	public int E() {
		return E;
	}
	
	private void validateVertex(int v) {
		if (v < 0 || v >= V)
			throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (V - 1));
	}
	
	public void addEdge(int v, int w) {
		validateVertex(v);
		validateVertex(w);
		adjList.get(v).add(w);
		E++;
		indegree[w]++;
	}
	
	public Iterable<Integer> adj(int v) {
		validateVertex(v);
		return adjList.get(v);
	}
	
	public int outdegree(int v) {
		validateVertex(v);
		return adjList.get(v).size();
	}
	
	public int indegree(int v) {
		validateVertex(v);
		return indegree[v];
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(V + " vertices, " + E + " edges \n");
		for (int v = 0; v < V; v++) {
			s.append(v + ": ");
			for (int w : adjList.get(v)) {
				s.append(w + " ");
			}
			s.append("\n");
		}
		return s.toString();
	}
}
