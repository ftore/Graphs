package v2;

import java.util.Stack;

public class DepthFirstSearch {
    private boolean[] visited;
    private int[] pathTo;
    private int source;

    public DepthFirstSearch(UndirectedGraph G, int source) {
        visited = new boolean[G.V()];
        pathTo = new int[G.V()];
        this.source = source;
        dfs(G, source);
    }

    private void dfs(UndirectedGraph G, int source) {
        visited[source] = true;

        for(int w : G.adj(source)) {
            if(!visited[w]) {
                pathTo[w] = source;
                dfs(G, w);
            }
        }
    }

    public boolean hasPathTo(int w) {
        return visited[w];
    }

    public Iterable<Integer> pathTo(int v) {
        if(!hasPathTo(v)) return null;

        Stack<Integer> path = new Stack<>();
        for(int x = v; x != source; x = pathTo[x]) {
            path.push(x);
        }

        path.push(source);
        return path;
    }

}
