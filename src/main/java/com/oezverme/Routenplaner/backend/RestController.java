package com.oezverme.Routenplaner.backend;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    Graph graph;



    @GetMapping("readGraph")
    public void readGraph(String pfad) {
        graph = DataReader.readFile(pfad);

    }


    @GetMapping("getCoords/{start}/{ziel}")
    public ResponseEntity getCoords(@PathVariable int start, @PathVariable int ziel) {
        double[] coords = new double[4];
        coords[0] = graph.getNodes()[start * 2];
        coords[1] = graph.getNodes()[start * 2 + 1];
        coords[2] = graph.getNodes()[ziel * 2];
        coords[3] = graph.getNodes()[ziel * 2 + 1];
        return ResponseEntity.ok().body(coords);
    }


    @GetMapping("nextNode/{lat}/{lng}")
    public ResponseEntity nextNode(@PathVariable double lat, @PathVariable double lng) {

        System.out.println(lat + "---" + lng);


        int nearest = NearestNode.findNearestNode(lat, lng, graph.getNodes());
        double[] nearestNode = new double[3];
        nearestNode[0] = nearest;
        nearestNode[1] = graph.getNodes()[nearest * 2];
        nearestNode[2] = graph.getNodes()[nearest * 2 + 1];


        return ResponseEntity.ok().body(nearestNode);

    }

    @GetMapping("createRoute/{start}/{ziel}")
    public ResponseEntity createRoute(@PathVariable int start, @PathVariable int ziel) {

        int[] nodePrev = Dijkstra.distCalcAtoBVorgaenger(graph, start, ziel);
        int currentNode = ziel;

        ArrayList route = new ArrayList();

        while (currentNode != start) {
            double[] coordinates = new double[2];
            coordinates[0] = graph.getNodes()[nodePrev[currentNode] * 2];
            coordinates[1] = graph.getNodes()[nodePrev[currentNode] * 2 + 1];
            route.add(coordinates);
            currentNode = nodePrev[currentNode];
        }

        return ResponseEntity.ok().body(route);
    }


}
