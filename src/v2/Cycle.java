package v2;

public class Cycle {
    private boolean[] visited;
    private boolean hasCycle;

    public Cycle(UndirectedGraph G) {
        visited = new boolean[G.V()];

        for(int i = 0; i < G.V(); i++) {
            if(!visited[i]) {
                dfs(G, i, i);
            }
        }
    }

    private void dfs(UndirectedGraph G, int v, int w) {
        visited[v] = true;

        for (Integer n : G.adj(v)) {
            if(!visited[n]) {
                dfs(G, n, v);
            } else if(n != w) hasCycle = true;
        }
    }

    public boolean hasCycle() {
        return hasCycle;
    }
}
