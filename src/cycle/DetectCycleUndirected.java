package cycle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetectCycleUndirected {
    private static class UndirectedGraph {
        private int V;
        private Map<Integer, List<Integer>> adjList;

        public UndirectedGraph(int v) {
            this.V = v;
            adjList = new HashMap<>();

            for(int i = 0; i < this.V; i++) {
                adjList.put(i, new ArrayList<>());
            }
        }

        public void addEdge(int v, int w) {
            adjList.get(v).add(w);
            adjList.get(w).add(v);
        }

        public int V() {
            return V;
        }

        public List<Integer> adjList(int v) {
            return adjList.get(v);
        }
    }

    public static boolean hasCycle(UndirectedGraph graph) {
        boolean[] visited = new boolean[graph.V()];

        for(int i = 0; i < graph.V(); i++) {
            if(!visited[i]) {
                if (dfs(graph, visited, i, -1)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean dfs(UndirectedGraph graph, boolean[] visited, int v, int parent) {
        // Mark the current node as visited
        visited[v] = true;

        // Recur for all the vertices adjacent to this vertex
        for(int i : graph.adjList(v)) {

            // If an adjacent vertex is not visited,
            // then recur for that adjacent vertex
            if(!visited[i]) {
                if(dfs(graph, visited, i, v)) {
                    return true;
                }
            }

            // If an adjacent vertex has already been visited
            // and not parent of the current vertex, then there is a cycle.
            else if(i != parent) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        UndirectedGraph g1 = new UndirectedGraph(5);
        g1.addEdge(1, 0);
        g1.addEdge(0, 2);
        g1.addEdge(2, 0);
        g1.addEdge(0, 3);
        g1.addEdge(3, 4);

        System.out.println(hasCycle(g1));

        UndirectedGraph g2 = new UndirectedGraph(3);
        g2.addEdge(0, 1);
        g2.addEdge(1, 2);

        System.out.println(hasCycle(g2));
    }
}
