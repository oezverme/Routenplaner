package com.oezverme.routenplaner.backend;

public class Graph {
    // i is the nodeID; i*2: latitude of the node i; i*2+1: longitude of the node i;
    private static double[] nodes;
    // i: target nodeID of edge i;
    private static int[] edges;
    // i: cost of edge i
    private static int[] costs;
    // i: offset of the edges for node i
    private static int[] offset;

    private static int amountOfNodes;
    private static int amountOfEdges;

    public Graph(double[] nodes, int[] edges, int[] costs, int[] offset, int amountOfNodes, int amountOfEdges) {
        this.nodes = nodes;
        this.edges = edges;
        this.costs = costs;
        this.offset = offset;
        this.amountOfNodes = amountOfNodes;
        this.amountOfEdges = amountOfEdges;
    }

    /**
     * Returns the offset of Node
     * @param node ID of node
     * @return offset of node
     */
    public int getOffset(int node) {
        return this.offset[node];
    }

    /**
     * Returns the cost to use an edge
     * @param edge ID of edge
     * @return cost of an egde
     */
    public int getCost(int edge) {
        return this.costs[edge];
    }

    /**
     *
     * @param node ID of node
     * @return number of connected edges of node
     */
    public int getNumbOfEdges(int node) {
        if (node == amountOfNodes - 1)
            return amountOfEdges - offset[node];
        return offset[node + 1] - offset[node];
    }

    /**
     *
     * @param nodeA source
     * @return all ID's of connected node targets with nodeA
     */
    public int[] getEdges(int nodeA) {
        if (getNumbOfEdges(nodeA) > 0) {
            int[] nodeB = new int[getNumbOfEdges(nodeA)];
            for (int i = 0; i < nodeB.length; i++) {
                nodeB[i] = edges[offset[nodeA] + i];
            }
            return nodeB;
        } else {
            return null;
        }
    }
}