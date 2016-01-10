package undirected;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Graph {
	private final int V;
	private int E;
	
	private Map<Integer, List<Integer>> adjList;
	
	public Graph(int v) {
		if(v < 0) {
			throw new IllegalArgumentException("Number of vertices must be nonnegative");
		}
		V = v;
		E = 0;
		
		adjList = new HashMap<Integer, List<Integer>>();
		
		for(int i = 0; i < V; i++) {
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
	
	/**
	* Adds the undirected edge v-w to this graph.
	*
	* @param  v one vertex in the edge
	* @param  w the other vertex in the edge
	* @throws IndexOutOfBoundsException unless both 0 <= v < V and 0 <= w < V
	*/
	public void addEdge(int v, int w) {
		validateVertex(v);
		validateVertex(w);
		E++;
		adjList.get(v).add(w);
		adjList.get(w).add(v);
	}
	
	public Iterable<Integer> adj(int v) {
		validateVertex(v);
		return adjList.get(v);
	}
	
	/**
	* Returns the degree of vertex <tt>v</tt>.
	*
	* @param  v the vertex
	* @return the degree of vertex <tt>v</tt>
	* @throws IndexOutOfBoundsException unless 0 <= v < V
	*/
	public int degree(int v) {
		validateVertex(v);
		return adjList.get(v).size();
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
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int V = in.nextInt();
		int E = in.nextInt();
		
		Graph graph = new Graph(V);
		
		for(int i = 0; i < E; i++) {
			int v = in.nextInt();
			int w = in.nextInt();
			graph.addEdge(v, w);
		}
		
		System.out.println(graph);
		
		if(GraphSearch.dfs(0, 7, graph)) {
			System.out.println("There is a path from 0 to 7");
		} else {
			System.out.println("There is no path");
		}
		
		if(GraphSearch.bfs(0, 7, graph)) {
			System.out.println("There is a path from 0 to 7");
		} else {
			System.out.println("There is no path");
		}
	}
}
