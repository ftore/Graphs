package v2;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class BreadthFirstSearch {
    private boolean[] visited;
    private int[] pathTo;
    private int source;

    public BreadthFirstSearch(UndirectedGraph G, int source) {
        visited = new boolean[G.V()];
        pathTo = new int[G.V()];
        this.source = source;

        bsf(G, source);
    }

    private void bsf(UndirectedGraph G, int source) {
        Queue<Integer> toExplore = new LinkedList<>();
        toExplore.add(source);

        visited[source] = true;

        while (!toExplore.isEmpty()) {
            int curr = toExplore.remove();

            for(int w : G.adj(curr)) {
                if(!visited[w]) {
                    pathTo[w] = curr;
                    toExplore.add(w);
                    visited[w] = true;
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        return visited[v];
    }

    public List<Integer> pathTo(int v) {
        if(!hasPathTo(v)) return null;

        Stack<Integer> path = new Stack<>();

        for(int x = v; x != source; x = pathTo[x]) {
            path.push(x);
        }
        path.push(source);
        return path;
    }
}
