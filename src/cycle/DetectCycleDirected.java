package cycle;

import java.util.*;

public class DetectCycleDirected {
    private static class Graph {
        private int V;
        private Map<Integer, List<Integer>> adjList;

        public Graph(int v) {
            V = v;

            adjList = new HashMap<>();
            for(int i = 0; i < V; i++) {
                adjList.put(i, new ArrayList<>());
            }
        }

        public void addEdge(int from, int to) {
            adjList.get(from).add(to);
        }

        public int V() {
            return V;
        }

        public List<Integer> adjList(int v) {
            return adjList.get(v);
        }
    }

    // DFS
    // keep track of visited node and whether this node in recursion stack
    // or not. If the node is already visited and is currently in recursion
    // stack, then there is a cycle
    public static boolean hasCycle(Graph graph) {
        boolean[] visited = new boolean[graph.V()];
        boolean[] recursionStack = new boolean[graph.V()];

        for(int i = 0; i < graph.V(); i++) {
            if(dfs(graph, visited, recursionStack, i)) {
                return true;
            }

        }

        return false;
    }

    private static boolean dfs(Graph graph, boolean[] visited, boolean[] recursionStack, int v) {

        // if current vertex is still in recursion stack
        // there is a cycle
        if(recursionStack[v]) {
            return true;
        }

        // if current vertex is not in recursion stack
        // but visited in the past, it means, it might inbound edge
        if(visited[v]) {
            return false;
        }

        // mark current vertex as visited
        // and part of recursion stack
        visited[v] = true;
        recursionStack[v] = true;

        // for each vertex in adj list of v
        // recurse
        for(Integer i : graph.adjList(v)) {
            if(dfs(graph, visited, recursionStack, i)) {
                return true;
            }
        }

        // remove current vertex from recursion stack
        // since at this point v is out of recursion stack
        // and we didn't detect any cycle
        recursionStack[v] = false;

        return false;
    }

    public static void main(String[] args) {
        Graph g = new Graph(7);

        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 3);
        g.addEdge(4, 5);
        g.addEdge(5, 6);
        g.addEdge(6, 4);

        System.out.println(hasCycle(g));
    }
}
