package undirected;

public class ConnectedComponents {
	private boolean[] marked;   // marked[v] = has vertex v been marked?
	private int[] id;           // id[v] = id of connected component containing v
	private int[] size;         // size[id] = number of vertices in given component
	private int count;          // number of connected components
	
	public ConnectedComponents(Graph G) {
		marked = new boolean[G.V()];
		id = new int[G.V()];
		size = new int[G.V()];
		for(int i = 0; i < G.V(); i++) {
			if(!marked[i]) {
				dfs(G, i);
				count++;
			}
		}
	}
	
	// depth-first search
	public void dfs(Graph G, int v) {
		marked[v] = true;
		id[v] = count;
		size[count]++;
		for(int w : G.adj(v)) {
			if(!marked[w]) {
				dfs(G, w);
			}
		}
	}
	
	public int id(int v) {
		return id[v];
	}
	
	public int size(int v) {
		return size[id[v]];
	}
	
	public int count() {
		return count;
	}
	
	public boolean connected(int v, int w) {
		return id(v) == id(w);
	}
}
