package com.graphengine.graph_engine.core.algorithm.traversal;


import com.graphengine.graph_engine.model.Edge;
import com.graphengine.graph_engine.model.Graph;
import com.graphengine.graph_engine.model.Node;

import java.util.*;

public class DFS {

    public static List<Long> traverse(Graph graph, Long startNodeId) {

        List<Long> visitedOrder = new ArrayList<>();

        Set<Long> visited = new HashSet<>();

        Stack<Long> stack = new Stack<>();

        Node startNode = graph.getNodes().stream()
                .filter(n -> n.getId().equals(startNodeId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Start node not found"));

        stack.push(startNodeId);
        visited.add(startNodeId);

        while (!stack.isEmpty()) {
            Long currentNodeId = stack.pop();
            visitedOrder.add(currentNodeId);

            List<Long> neighbors = getNeighbors(graph, currentNodeId);

            for (Long neighborId : neighbors) {
                if (!visited.contains(neighborId)) {
                    visited.add(neighborId);
                    stack.push(neighborId);
                }
            }
        }

        return visitedOrder;
    }

    private static List<Long> getNeighbors(Graph graph, Long nodeId) {
        List<Long> neighbors = new ArrayList<>();

        for (Edge edge : graph.getEdges()) {
            if (edge.getSource().getId().equals(nodeId)) {
                neighbors.add(edge.getTarget().getId());
            }

            if (!graph.isDirected() && edge.getTarget().getId().equals(nodeId)) {
                neighbors.add(edge.getSource().getId());
            }
        }

        return neighbors;
    }
}
