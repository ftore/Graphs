package v2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UndirectedGraph {
    private final int V;
    private int E;

    private Map<Integer, List<Integer>> adjList;

    public UndirectedGraph(int v) {
        this.V = v;
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

    public void addEdge(int v, int w) {
        adjList.get(v).add(w);
        adjList.get(w).add(v);
        E++;
    }

    public List<Integer> adj(int v) {
        return adjList.get(v);
    }
}
