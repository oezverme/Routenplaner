package com.oezverme.routenplaner.backend;

public class Dijkstra {
    // distance from startNode to node i as target
    static int[] distance;
    //
    static boolean[] visited;

















    ///////////////////////////////////////////////

    //KnotenID als Index, gibt aktuelle DIstanz zum Startknoten an
    static int[] distance;
    //Gibt an ob ein Knoten bereits besucht wurde
    static boolean[] visited;
    //KnotenID als Index, wert = -1, wenn der Knoten bereits aus der Queue einmal entfernt wurde
    static int[] removed;
    static int anzahlKnoten;

    public static void distCalcOnetoAll(Graph graph, int source) {
        // Starte Zeit
        long startTime = System.currentTimeMillis();
        anzahlKnoten = graph.getAnzahlKnoten();
        // erstelle Array mit den Distanzen zu allen Knoten von StartKnoten
        distance = new int[graph.getAnzahlKnoten()];
        // setze Distanz zu allen Knoten auf unendlich
        for (int i = 0; i < distance.length; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        removed = new int[graph.getAnzahlKnoten()];
        // setze Distanz von Startknoten zu sich selber auf 0
        distance[source] = 0;
        Queue.add(source);

        while (!Queue.pq.isEmpty()) {
            int u = Queue.extractMin();
            /*
             * Überprüft ob der Knoten bereits einmal bei extractMin entfernt wurde, da durch Lazy Insert Knoten
             * mehrmals in der Prioqueue verkommen.
             */
            if (removed[u] == -1) {
                continue;
            } else {
                removed[u] = -1;
            }
            int[] kantenVonU = graph.getEdgesOfNode(u);
            for (int i = 0; i < kantenVonU.length; i += 2) {
                int neighbourNode = kantenVonU[i];
                if (removed[neighbourNode] != -1) {
                    int newDistance = distance[u] + kantenVonU[i + 1];
                    if (newDistance < distance[neighbourNode]) {
                        distance[neighbourNode] = newDistance;
                        Queue.add(neighbourNode);
                    }
                }
            }
        }

    }
}