package com.oezverme.Routenplaner.backend;

public class Graph {
    // i is the nodeID; i*2: latitude of the node i; i*2+1: longitude of the node i;
    private static double[] nodes;
    // i: target nodeID of edge i;
    private static int[] edges;
    // i: cost of edge i
    private static int[] costs;
    // i: offset of the edges for node i
    private static int[] offset;

    private static int numbOfNodes;
    private static int numbOfEdges;

    public Graph(double[] nodes, int[] edges, int[] costs, int[] offset, int numbOfNodes, int numbOfEdges) {
        this.nodes = nodes;
        this.edges = edges;
        this.costs = costs;
        this.offset = offset;
        this.numbOfNodes = numbOfNodes;
        this.numbOfEdges = numbOfEdges;
    }

    public int getNumbOfNodes(){
        return numbOfNodes;
    }

    public int[] getCosts(){
        return costs;
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
     *
     * @param nodeA startpoint of the edge
     * @param i number of the edge of nodeA
     * @return
     */
    public int getCostOfEdge(int nodeA, int i) {
        return this.costs[getOffset(nodeA) + i];
    }

    /**
     *
     * @param node ID of node
     * @return number of connected edges of node
     */
    public int getNumbOfEdgesOfNode(int node) {
        if (node == numbOfNodes - 1)
            return numbOfEdges - offset[node];
        return offset[node + 1] - offset[node];
    }

    /**
     *
     * @param nodeA source
     * @return all ID's of connected node targets with nodeA
     */
    public int[] getEdgesOfNode(int nodeA) {
        if (getNumbOfEdgesOfNode(nodeA) > 0) {
            int[] nodeB = new int[getNumbOfEdgesOfNode(nodeA)];
            for (int i = 0; i < nodeB.length; i++) {
                nodeB[i] = edges[offset[nodeA] + i];
            }
            return nodeB;
        } else {
            return null;
        }
    }
}