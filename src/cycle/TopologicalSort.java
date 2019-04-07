package cycle;

import java.util.*;

public class TopologicalSort {
    private static class DirectedGraph {
        private int V;
        private Map<Integer, List<Integer>> adjList;

        public DirectedGraph(int v) {
            this.V = v;
            adjList = new HashMap<>();

            for(int i = 0; i < this.V; i++) {
                adjList.put(i, new ArrayList<>());
            }
        }

        public void addEdge(int v, int w) {
            adjList.get(v).add(w);
        }

        public int V() {
            return V;
        }

        public List<Integer> adjList(int v) {
            return adjList.get(v);
        }
    }

    public void topologicalSort(DirectedGraph graph) {
        boolean[] visited = new boolean[graph.V()];
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < graph.V(); i++) {
            if(!visited[i]) {
                dfs(graph, visited, stack, i);
            }
        }

        // Print contents of stack
        while(!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    private void dfs(DirectedGraph graph, boolean[] visited, Stack<Integer> stack, int v) {
        // Mark the current node as visited.
        visited[v] = true;

        for(int i : graph.adjList(v)) {
            if(!visited[i]) {
                dfs(graph, visited, stack, i);
            }
        }

        // Push current vertex to stack which stores result
        stack.add(v);
    }
}
