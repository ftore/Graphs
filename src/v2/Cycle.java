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

    public static void main(String[] args) {
        UndirectedGraph g = new UndirectedGraph(7);

        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 3);
        g.addEdge(4, 5);
        g.addEdge(5, 6);
        //g.addEdge(6, 4);

        Cycle cycle = new Cycle(g);
        System.out.println(cycle.hasCycle());
    }
}
