package cycle;

import java.util.*;

public class DijkstraShortestPath {
    private static class GraphNode implements Comparable<GraphNode> {
        private int val;
        private int weight;

        public GraphNode(int v, int w) {
            this.val = v;
            this.weight = w;
        }

        public int getVal() {
            return this.val;
        }

        public int getWeight() {
            return this.weight;
        }

        public int compareTo(GraphNode other) {
            return this.weight - other.weight;
        }
    }

    private static class Graph {
        private int V;
        private Map<Integer, List<GraphNode>> adjList;

        public Graph(int v) {
            this.V = v;

            adjList = new HashMap<>();
            for(int i = 0; i < this.V; i++) {
                adjList.put(i, new ArrayList<>());
            }
        }

        public void addEdge(int v, int w, int weight) {
            GraphNode g = new GraphNode(w, weight);
            adjList.get(v).add(g);
        }

        public int V() {
            return this.V;
        }

        public List<GraphNode> adjList(int v) {
            return adjList.get(v);
        }
    }

    // Dijkstra's single source shortest path algorithm
    public static void shortestPath(Graph graph, int source) {
        // save all the distances
        int[] dist = new int[graph.V()];
        Set<Integer> visited = new HashSet<>();
        PriorityQueue<GraphNode> pq = new PriorityQueue<>();

        // init all the distances to infinate
        for (int i = 0; i < graph.V(); i++)
            dist[i] = Integer.MAX_VALUE;

        // Add source node to the priority queue
        pq.add(new GraphNode(source, 0));
        dist[source] = 0;

        while(visited.size() != graph.V()) {
            // remove the minimum distance node
            // from the priority queue
            int u = pq.remove().getVal();

            // adding the node whose distance is
            // finalized
            visited.add(u);

            processNeighbours(graph, u, visited, dist, pq);
        }

        // Print the shortest path to all the nodes
        // from the source node
        System.out.println("The shorted path from node :");
        for (int i = 0; i < dist.length; i++)
            System.out.println(source + " to " + i + " is "
                    + dist[i]);
    }

    private static void processNeighbours(Graph graph, int source, Set<Integer> visited, int[] dist,
                                          PriorityQueue<GraphNode> pq) {
        int edgeDistance = -1;
        int newDistance = -1;

        // All the neighbors of source vertex
        List<GraphNode> adjList = graph.adjList(source);
        for(int i = 0; i < adjList.size(); i++) {
            GraphNode curr = adjList.get(i);

            // If current node hasn't already been processed
            if(!visited.contains(curr.getVal())) {
                visited.add(curr.getVal());

                edgeDistance = curr.getWeight();
                newDistance = dist[source] + edgeDistance;

                // If new distance is cheaper in cost
                if(newDistance < dist[curr.getVal()]) {
                    dist[curr.getVal()] = newDistance;
                }

                // Add the current node to the queue
                pq.add(new GraphNode(curr.getVal(), dist[curr.getVal()]));
            }
        }
    }

    public static void main(String[] args) {
        int V = 5;
        int source = 0;

        Graph graph = new Graph(V);
        graph.addEdge(0, 1, 9);
        graph.addEdge(0, 2, 6);
        graph.addEdge(0, 3, 5);
        graph.addEdge(0, 4, 3);
        graph.addEdge(2, 1, 2);
        graph.addEdge(2, 3, 4);


        shortestPath(graph, 0);
    }
}
