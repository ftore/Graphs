package v2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EdgeWeightedDigraph {
    private final int V;
    private int E;

    private Map<Integer, List<DirectedEdge>> adj;

    public EdgeWeightedDigraph(int v) {
        this.V = v;
        E = 0;

        adj = new HashMap<>();

        for(int i = 0; i < v; i++) {
            adj.put(i, new ArrayList<>());
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(DirectedEdge edge) {
        adj.get(edge.from()).add(edge);
        E++;
    }

    public List<DirectedEdge> adj(int v) {
        return adj.get(v);
    }

    public List<DirectedEdge> edges() {
        List<DirectedEdge> edgeList = new ArrayList<>();

        for(int i = 0; i < this.V; i++) {
            for(DirectedEdge e : adj.get(i)) {
                edgeList.add(e);
            }
        }

        return edgeList;
    }
}
