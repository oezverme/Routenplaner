package com.oezverme.Routenplaner.backend;

public class Dijkstra {
    static int[] distance;
    static boolean[] visited;

    /**
     *
     * @param graph
     * @param source
     * @return
     */
    public static int[] distCalcOnetoAll(Graph graph, int source) {
        // Starte Zeit
        long startTime = System.currentTimeMillis();

        // erstelle Array mit den Distanzen zu allen Knoten von StartKnoten
        distance = new int[graph.getNumbOfNodes()];

        // speichert ob Knoten schon besucht und in den minheap eingefügt wurde
        visited = new boolean[graph.getNumbOfNodes()];

        // setze Distanz zu allen Knoten auf unendlich
        for (int i = 0; i < distance.length; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        // setze Distanz von Startknoten zu sich selber auf 0
        distance[source] = 0;

        // erstelle Minheap
        MinHeap minheap = new MinHeap(graph.getNumbOfNodes());

        minheap.add(source, distance[source]);
        visited[source] = true;

        while (!minheap.isEmpty()) {

            int u = minheap.extractMin();

            int[] kantenVonU = graph.getEdgesOfNode(u);
            if (kantenVonU == null) {
            } else {
                for (int i = 0; i < kantenVonU.length; i++) {
                    int neighbourNode = kantenVonU[i];

                    if (!minheap.getCheckIfRemoved(neighbourNode)) {
                        int newDistance = distance[u] + graph.getCostOfEdge(u, i);

                        if (newDistance < distance[neighbourNode]) {
                            distance[neighbourNode] = newDistance;

                            if (visited[neighbourNode] == true) {

                                minheap.decreaseKey(neighbourNode, distance[neighbourNode]);
                            } else {
                                minheap.add(neighbourNode, distance[neighbourNode]);
                                visited[neighbourNode] = true;
                            }
                        }
                    }
                }
            }
        }
        long finishTime = System.currentTimeMillis();
        float durationInSeconds = (finishTime - startTime) / 1000f;

        System.out.println("Dijkstra Dauer: " + durationInSeconds + " Sekunden");
        return distance;
    }

    /**
     *
     * @param graph
     * @param source
     * @return
     */
    public static int[] distCalcAtoB(Graph graph, int source, int target) {
        // Starte Zeit
        long startTime = System.currentTimeMillis();

        // erstelle Array mit den Distanzen zu allen Knoten von StartKnoten
        distance = new int[graph.getNumbOfNodes()];

        // speichert ob Knoten schon besucht und in den minheap eingefügt wurde
        visited = new boolean[graph.getNumbOfNodes()];

        // setze Distanz zu allen Knoten auf unendlich
        for (int i = 0; i < distance.length; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        // setze Distanz von Startknoten zu sich selber auf 0
        distance[source] = 0;

        // erstelle Minheap
        MinHeap minheap = new MinHeap(graph.getNumbOfNodes());

        minheap.add(source, distance[source]);
        visited[source] = true;

        while (!minheap.isEmpty()) {

            int u = minheap.extractMin();
            if(u == target)
                break;
            int[] kantenVonU = graph.getEdgesOfNode(u);
            if (kantenVonU == null) {
            } else {
                for (int i = 0; i < kantenVonU.length; i++) {
                    int neighbourNode = kantenVonU[i];

                    if (!minheap.getCheckIfRemoved(neighbourNode)) {
                        int newDistance = distance[u] + graph.getCostOfEdge(u, i);

                        if (newDistance < distance[neighbourNode]) {
                            distance[neighbourNode] = newDistance;

                            if (visited[neighbourNode] == true) {

                                minheap.decreaseKey(neighbourNode, distance[neighbourNode]);
                            } else {
                                minheap.add(neighbourNode, distance[neighbourNode]);
                                visited[neighbourNode] = true;
                            }
                        }
                    }
                }
            }

        }
        long finishTime = System.currentTimeMillis();
        float durationInSeconds = (finishTime - startTime) / 1000f;

        System.out.println("Dijkstra Dauer: " + durationInSeconds + " Sekunden");
        return distance;
    }


    public static int[] distCalcAtoBVorgaenger(Graph graph, int source, int target) {
        // Starte Zeit
        long startTime = System.currentTimeMillis();

        // erstelle Array mit den Distanzen zu allen Knoten von StartKnoten
        distance = new int[graph.getNumbOfNodes()];

        // speichert ob Knoten schon besucht und in den minheap eingefügt wurde
        visited = new boolean[graph.getNumbOfNodes()];

        int[] prev = new int[graph.getNumbOfNodes()];

        // setze Distanz zu allen Knoten auf unendlich
        for (int i = 0; i < distance.length; i++) {
            distance[i] = Integer.MAX_VALUE;
            prev[i] = -1;
        }

        // setze Distanz von Startknoten zu sich selber auf 0
        distance[source] = 0;
        prev[source] = source;

        // erstelle Minheap
        MinHeap minheap = new MinHeap(graph.getNumbOfNodes());

        minheap.add(source, distance[source]);
        visited[source] = true;

        while (!minheap.isEmpty()) {

            int u = minheap.extractMin();
            if(u == target)
                break;
            int[] kantenVonU = graph.getEdgesOfNode(u);
            if (kantenVonU == null) {
            } else {
                for (int i = 0; i < kantenVonU.length; i++) {
                    int neighbourNode = kantenVonU[i];

                    if (!minheap.getCheckIfRemoved(neighbourNode)) {
                        int newDistance = distance[u] + graph.getCostOfEdge(u, i);

                        if (newDistance < distance[neighbourNode]) {
                            distance[neighbourNode] = newDistance;
                            prev[neighbourNode] = u;

                            if (visited[neighbourNode] == true) {

                                minheap.decreaseKey(neighbourNode, distance[neighbourNode]);
                            } else {
                                minheap.add(neighbourNode, distance[neighbourNode]);
                                visited[neighbourNode] = true;
                            }
                        }
                    }
                }
            }

        }
        long finishTime = System.currentTimeMillis();
        float durationInSeconds = (finishTime - startTime) / 1000f;

        System.out.println("Dijkstra Dauer: " + durationInSeconds + " Sekunden");
        return prev;
    }



}