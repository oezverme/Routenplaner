package com.oezverme.Routenplaner.backend;

public class MinHeap {
    public int[] prioQueue;
    public int[] nodePos;
    public int prioQueueLength;
    public int nodesExtracted;

    public MinHeap(int numOfNodes) {
        prioQueue = new int[numOfNodes * 2];
        nodePos = new int[numOfNodes];
        prioQueueLength = 0;
        nodesExtracted = 0;
    }

    public int getLeft(int pos) {
        return 2 * pos + 1;
    }

    public int getRight(int pos) {
        return 2 * pos + 2;
    }

    public int getParent(int pos) {
        return (pos - 1) / 2;
    }

    public int getValueOf(int pos) {
        return prioQueue[prioQueue.length / 2 + pos];
    }

    public void heapifyDown(int pos) {
        int l = getLeft(pos);
        int r = getRight(pos);
        int smallest = pos;

        if (l < prioQueueLength && getValueOf(l) < getValueOf(smallest)) {
            smallest = l;
        }
        if (r < prioQueueLength && getValueOf(r) < getValueOf(smallest)) {
            smallest = r;
        }

        if (smallest != pos) {
            swap(pos, smallest);
            heapifyDown(smallest);
        }
    }

    public void heapifyUp(int pos) {
        if (pos > 0) {
            int p = getParent(pos);
            if (getValueOf(p) > getValueOf(pos)) {
                swap(p, pos);
                heapifyUp(p);
            }
        }
    }

    public void swap(int pos1, int pos2) {
        int tempNode1 = prioQueue[pos1];
        int tempValue1 = getValueOf(pos1);
        int tempNode2 = prioQueue[pos2];

        nodePos[tempNode1] = pos2;
        nodePos[tempNode2] = pos1;

        prioQueue[pos1] = tempNode2;
        prioQueue[pos2] = tempNode1;

        prioQueue[prioQueue.length / 2 + pos1] = getValueOf(pos2);
        prioQueue[prioQueue.length / 2 + pos2] = tempValue1;

    }

    public void decreaseKey(int node, int newValue) {
        int pos = nodePos[node];
        if (getValueOf(node) > newValue) {
            prioQueue[(prioQueue.length / 2) + pos] = newValue;
            heapifyUp(pos);
        }
    }

    public void add(int node, int value) {
        prioQueue[prioQueueLength] = node;
        prioQueue[(prioQueue.length / 2) + prioQueueLength] = value;
        nodePos[node] = prioQueueLength;
        prioQueueLength++;
        heapifyUp(prioQueueLength - 1);
    }

    public int extractMin() {
        int minNode = prioQueue[0];
        swap(0, (prioQueue.length / 2) - nodesExtracted - 1);
        swap(0, prioQueueLength - 1);
        nodePos[minNode] = -1;
        prioQueueLength--;
        nodesExtracted++;
        heapifyDown(0);
        return minNode;
    }

    public boolean getCheckIfRemoved(int node) {
        return nodePos[node] == -1;
    }

    public boolean isEmpty() {
        return prioQueueLength == 0;
    }

}
