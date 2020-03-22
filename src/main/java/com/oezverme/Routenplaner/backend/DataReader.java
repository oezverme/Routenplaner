package com.oezverme.Routenplaner.backend;

import java.io.BufferedReader;
import java.io.IOException;

public class DataReader {

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

    private static BufferedReader br;

    /**
     * reads and stores all relevant data for the graph
     * get data from: 'https://fmi.uni-stuttgart.de/alg/research/stuff/'
     * @param filePath path to data
     * @return graph
     */
    public static Graph readFile(String filePath) {
        System.out.println("Graph wird gelesen. Bitte warten..");
        try {
            br = new BufferedReader(new java.io.FileReader(filePath));
            //skip first 5 lines
            for (int i = 0; i < 5; i++) {
                br.readLine();
            }
            amountOfNodes = Integer.parseInt(br.readLine());
            amountOfEdges = Integer.parseInt(br.readLine());
            readNodes(br);
            readEdges(br);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Graph(nodes, edges, costs, offset, amountOfNodes, amountOfEdges);
    }

    /**
     *
     * @param br
     * @throws IOException
     */
    private static void readNodes(BufferedReader br) throws IOException {
        nodes = new double[amountOfNodes * 2];
        for (int i = 0; i < nodes.length; i += 2) {
            String[] splittedLine = br.readLine().split(" ");
            nodes[i] = Double.parseDouble(splittedLine[2]);
            nodes[i + 1] = Double.parseDouble(splittedLine[3]);
        }
    }

    /**
     *
     * @param br
     * @throws IOException
     */
    private static void readEdges(BufferedReader br) throws IOException {
        int tempNode = 0;
        int srcNode = 0;
        int offsetCounter = 0;
        edges = new int[amountOfEdges];
        costs = new int[amountOfEdges];
        offset = new int[amountOfNodes];
        for (int i = 0; i < edges.length; i++) {
            String[] splittedLine = br.readLine().split(" ");
            srcNode = Integer.parseInt(splittedLine[0]);
            edges[i] = Integer.parseInt(splittedLine[1]);
            costs[i] = Integer.parseInt(splittedLine[2]);

            if (srcNode > tempNode)
                for (int j = tempNode + 1; j <= srcNode; j++) {
                    offset[j] = offsetCounter;
                }

            edges[i] = Integer.parseInt(splittedLine[1]);
            costs[i] = Integer.parseInt(splittedLine[2]);
            offsetCounter++;
            tempNode = srcNode;
        }
    }
}
