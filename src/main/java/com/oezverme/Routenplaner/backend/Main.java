package com.oezverme.Routenplaner.backend;

public class Main {
    public static void main(String[] args) {
        Graph graph = DataReader.readFile("C:\\Users\\muham\\Documents\\Routenplaner\\toy.fmi.txt");
        int[] result = Dijkstra.distCalcAtoBVorgaenger(graph, 0, 3);

        for (int i: result) {
            System.out.println(i + " cost: " );
        };
    }
}