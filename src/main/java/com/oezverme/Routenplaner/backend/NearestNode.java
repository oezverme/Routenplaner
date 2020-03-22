package com.oezverme.Routenplaner.backend;

public class NearestNode {
    public static int findNearestNode(double xCoord, double yCoord, double[] nodes) {
        double currentDistance = Integer.MAX_VALUE;
        int nearestNode = -1;

        for (int i = 0; i < nodes.length; i += 2) {
            int currentNode = i / 2;
            double currentX = nodes[i];
            double currentY = nodes[i + 1];

            double tempDistance = Math.sqrt(Math.pow(xCoord - currentX, 2) + Math.pow(yCoord - currentY, 2));
            if (tempDistance <= currentDistance) {
                nearestNode = currentNode;
                currentDistance = tempDistance;
            }

        }
        return nearestNode;
    }
}
