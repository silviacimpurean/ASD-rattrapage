package ads;

import ads.graph.*;

/**
 * A class for the Dijkstra algorithm
 */
public class Dijkstra {

    // the weighted graph
    private WeightedDiGraph G;

    // the special heap
    private SpecialHeapForDijkstra heap;

    // the cost for the vertices
    private double[] cost;

    // the previous vertex in the
    // shortest path for each vertex
    private int[] previous;

    /**
     * Build a Dijkstra object for the graph G
     */
    public Dijkstra(WeightedDiGraph G) {
        this.G = G;
        this.previous = new int[G.nbVertices()];
        this.cost = new double[G.nbVertices()];;
        this.heap = new SpecialHeapForDijkstra(G.nbVertices(),cost);
    }

    /**
     * Compute the shortest paths from the source
     * vertex to all other vertices
     */
    public void shortestPath(int source) {
        previous[source] = -1;
        for (int i = 0; i < cost.length; i++)
            cost[i] = Double.MAX_VALUE;
        cost[source] = 0;
        fillHeap();

        while(!heap.isEmpty()) {
            int node = heap.deleteExtreme();
            for(int adj : G.adjacents(node))
                if(cost(node) + G.weight(node, adj) < cost(adj)) {
                    cost[adj] = cost(node) + G.weight(node, adj);
                    heap.decreaseCost(adj);
                    previous[adj] = node;
                }
        }
    }

    /**
     * Return the cost of the shortest path
     * from the source to the vertex v
     */
    public double cost(int v) {
        return cost[v];
    }

    /**
     * Display the shortest path from
     * the source to the vertex v
     */
    public void displayShortestPath(int v) {
        if(previous[v] == -1)
            System.out.println(v);
        else {
            if(previous[v] != 0) {
                displayShortestPath(previous[v]);
                System.out.println(" " + v);
            }
        }
    }

    // fill the special heap with all the vertices
    private void fillHeap() {
        for(int i = 0; i < cost.length; i++) {
            heap.add(i);
        }
    }

    /**
     * For testing: the graph G is the one from
     * part 1 of lab #11
     */
    public static void main(String[] args) {
        WeightedDiGraph G = new WeightedDiGraph(12);

        G.addEdge(0, 1, 2.0);
        G.addEdge(0,4,13.0);
        G.addEdge(0,11,10.0);
        G.addEdge(0,10,7.0);
        G.addEdge(1,6,1.0);
        G.addEdge(2,4,1.0);
        G.addEdge(2,7,1.0);
        G.addEdge(2,9,2.0);
        G.addEdge(3,5,8.0);
        G.addEdge(3,7,4.0);
        G.addEdge(4,9,5.0);
        G.addEdge(6,3,5.0);
        G.addEdge(6,7,2.0);
        G.addEdge(6,11,3.0);
        G.addEdge(7,5,10.0);
        G.addEdge(7,8,4.0);
        G.addEdge(7,9,7.0);
        G.addEdge(8,5,5.0);
        G.addEdge(9,5,1.0);
        G.addEdge(9,8,9.0);
        G.addEdge(10,2,3.0);
        G.addEdge(10,4,8.0);
        G.addEdge(10,11,5.0);
        G.addEdge(11,7,12.0);

        Dijkstra d = new Dijkstra(G);
        d.shortestPath(3);



        for ( int v = 0; v < G.nbVertices(); v++ ) {
            System.out.print("shortest path to " + v + " (cost: " + d.cost(v) + "): ");
            d.displayShortestPath(v);
            System.out.println();
        }
    }
}
// Expected output:
//
// shortest path to 0 (cost: 0.0):
//	 0
//	shortest path to 1 (cost: 2.0):
//	 0 1
//	shortest path to 2 (cost: 10.0):
//	 0 10 2
//	shortest path to 3 (cost: 8.0):
//	 0 1 6 3
// shortest path to 4 (cost: 11.0):
//   0 10 2 4
//	shortest path to 5 (cost: 13.0):
//	 0 1 6 7 9 5
//	shortest path to 6 (cost: 3.0):
//	 0 1 6
//	shortest path to 7 (cost: 5.0):
//	 0 1 6 7
//	shortest path to 8 (cost: 9.0):
//	 0 1 6 7 8
//	shortest path to 9 (cost: 12.0):
//	 0 1 6 7 9
//	shortest path to 10 (cost: 7.0):
//	 0 10
//	shortest path to 11 (cost: 6.0):
//	 0 1 6 11


