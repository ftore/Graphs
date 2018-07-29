package v2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;

public class DijkstraShortestPath {
    private DirectedEdge[] edgeTo;
    private double[] distTo;

    private PriorityQueue<Distance> priorityQueue;

    public DijkstraShortestPath(EdgeWeightedDigraph G, int source) {
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];

        priorityQueue = new PriorityQueue<>();

        for(int i = 0; i < G.V(); i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }

        distTo[source] = 0.0;
        Distance distance = new Distance(source, distTo[source]);

        priorityQueue.add(distance);

        while(!priorityQueue.isEmpty()) {
            relax(G, priorityQueue.poll());
        }
    }

    private void relax(EdgeWeightedDigraph G, Distance distance) {
        for(DirectedEdge d : G.adj(distance.getSource())) {
            int w = d.to();
            if(distTo[w] > distTo[distance.getSource()] + d.weight()) {
                distTo[w] = distTo[distance.getSource()] + d.weight();
                Distance currDistance = new Distance(w, distTo[w]);
                if(priorityQueue.contains(currDistance)) {
                    priorityQueue.remove(currDistance);
                }
                priorityQueue.add(currDistance);
            }
        }
    }

    public double distTo(int v) {
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }


    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(new File("tinyEWD.txt"));
            int V = in.nextInt();
            int E = in.nextInt();
            EdgeWeightedDigraph weightedDigraph = new EdgeWeightedDigraph(V);

            for(int i = 0; i < E; i++) {
                int v = in.nextInt();
                int w = in.nextInt();
                int weight = in.nextInt();
                DirectedEdge directedEdge = new DirectedEdge(v, w, weight);
                weightedDigraph.addEdge(directedEdge);
            }

            int s = 0;

            DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath(weightedDigraph, s);

            // print shortest path
            for (int t = 0; t < weightedDigraph.V(); t++) {
                if (dijkstraShortestPath.hasPathTo(t)) {
                    System.out.printf("%d to %d (%.2f)  ", s, t, dijkstraShortestPath.distTo(t));
                    for (DirectedEdge e : dijkstraShortestPath.pathTo(t)) {
                        System.out.print(e + "   ");
                    }
                    System.out.println();
                }
                else {
                    System.out.printf("%d to %d         no path\n", s, t);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
