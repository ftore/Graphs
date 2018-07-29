package v2;

public class ConnectedComponents {
	private boolean[] visited;   // visited[v] = has vertex v been visited?
	private int[] id;           // id[v] = id of connected component containing v
	private int count;          // number of connected components
	
	public ConnectedComponents(UndirectedGraph G) {
		visited = new boolean[G.V()];
		id = new int[G.V()];
		for(int i = 0; i < G.V(); i++) {
			if(!visited[i]) {
				dfs(G, i);
				count++;
			}
		}
	}
	
	// depth-first search
	public void dfs(UndirectedGraph G, int v) {
		visited[v] = true;
		id[v] = count;
		for(int w : G.adj(v)) {
			if(!visited[w]) {
				dfs(G, w);
			}
		}
	}
	
	public int id(int v) {
		return id[v];
	}
	
	public int count() {
		return count;
	}
	
	public boolean connected(int v, int w) {
		return id(v) == id(w);
	}
}
