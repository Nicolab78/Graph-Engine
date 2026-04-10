package com.graphengine.graph_engine.service.impl;

import com.graphengine.graph_engine.core.algorithm.traversal.BFS;
import com.graphengine.graph_engine.core.algorithm.traversal.DFS;
import com.graphengine.graph_engine.dto.algorithm.TraversalResultDto;
import com.graphengine.graph_engine.dto.edge.CreateEdgeDto;
import com.graphengine.graph_engine.dto.edge.EdgeDto;
import com.graphengine.graph_engine.dto.graph.CreateGraphDto;
import com.graphengine.graph_engine.dto.graph.GraphDetailDto;
import com.graphengine.graph_engine.dto.graph.GraphDto;
import com.graphengine.graph_engine.dto.graph.UpdateGraphDto;
import com.graphengine.graph_engine.dto.node.CreateNodeDto;
import com.graphengine.graph_engine.dto.node.NodeDto;
import com.graphengine.graph_engine.model.Edge;
import com.graphengine.graph_engine.model.Graph;
import com.graphengine.graph_engine.model.Node;
import com.graphengine.graph_engine.repository.EdgeRepository;
import com.graphengine.graph_engine.repository.GraphRepository;
import com.graphengine.graph_engine.repository.NodeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class GraphService {

    private final GraphRepository graphRepository;
    private final NodeRepository nodeRepository;
    private final EdgeRepository edgeRepository;

    public GraphDto createGraph(CreateGraphDto createGraphDto) {
        Graph graph = Graph.builder()
                .name(createGraphDto.getName())
                .description(createGraphDto.getDescription())
                .directed(createGraphDto.isDirected())
                .weighted(createGraphDto.isWeighted())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Graph savedGraph = graphRepository.save(graph);
        return mapToGraphDto(savedGraph);
    }

    public GraphDto getGraph(Long id) {
        Graph graph = graphRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Graph not found with id: " + id));
        return mapToGraphDto(graph);
    }

    public GraphDetailDto getGraphDetail(Long id) {
        Graph graph = graphRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Graph not found with id: " + id));
        return mapToGraphDetailDto(graph);
    }

    public List<GraphDto> getAllGraphs() {
        return graphRepository.findAll().stream()
                .map(this::mapToGraphDto)
                .toList();
    }

    public GraphDto updateGraph(Long id, UpdateGraphDto updateGraphDto) {
        Graph graph = graphRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Graph not found with id: " + id));

        if (updateGraphDto.getName() != null) {
            graph.setName(updateGraphDto.getName());
        }
        if (updateGraphDto.getDescription() != null) {
            graph.setDescription(updateGraphDto.getDescription());
        }
        if (updateGraphDto.getDirected() != null) {
            graph.setDirected(updateGraphDto.getDirected());
        }
        if (updateGraphDto.getWeighted() != null) {
            graph.setWeighted(updateGraphDto.getWeighted());
        }

        Graph updatedGraph = graphRepository.save(graph);
        return mapToGraphDto(updatedGraph);
    }

    public void deleteGraph(Long id) {
        if(!graphRepository.existsById(id)) {
            throw new RuntimeException("Graph not found with id: " + id);
        }
        graphRepository.deleteById(id);
    }

    public NodeDto addNode(Long graphId, CreateNodeDto createNodeDto) {
        Graph graph = graphRepository.findById(graphId)
                .orElseThrow(() -> new RuntimeException("Graph not found with id: " + graphId));

        Node node = Node.builder()
                .label(createNodeDto.getLabel())
                .graph(graph)
                .x(createNodeDto.getX())
                .y(createNodeDto.getY())
                .build();

        Node savedNode = nodeRepository.save(node);

        return mapToNodeDto(savedNode);

    }

    public void deleteNode(Long nodeId) {
        Node node = nodeRepository.findById(nodeId)
                .orElseThrow(() -> new RuntimeException("Graph not found with id: " + nodeId));

        nodeRepository.deleteById(nodeId);
    }

    public EdgeDto addEdge(Long graphId, CreateEdgeDto createEdgeDto) {
        Graph graph = graphRepository.findById(graphId)
                .orElseThrow(() -> new RuntimeException("Graph not found with id: " + graphId));

        Node source = nodeRepository.findById(createEdgeDto.getSourceId())
                .orElseThrow(() -> new RuntimeException("Source node not found with id: " + createEdgeDto.getSourceId()));

        Node target = nodeRepository.findById(createEdgeDto.getTargetId())
                .orElseThrow(() -> new RuntimeException("Target node not found with id: " + createEdgeDto.getTargetId()));

        Edge edge = Edge.builder()
                .source(source)
                .target(target)
                .graph(graph)
                .weight(createEdgeDto.getWeight())
                .build();

        Edge savedEdge = edgeRepository.save(edge);

        return mapToEdgeDto(savedEdge);

    }

    public void deleteEdge(Long edgeId) {
        Edge edge = edgeRepository.findById(edgeId)
                .orElseThrow(() -> new RuntimeException("Edge not found with id: " + edgeId));

        edgeRepository.deleteById(edgeId);
    }

    public TraversalResultDto bfs(Long graphId, Long startNodeId) {
        long startTime = System.currentTimeMillis();

        Graph graph = graphRepository.findById(graphId)
                .orElseThrow(() -> new RuntimeException("Graph not found with id: " + graphId));

        List<Long> visitedIds = BFS.traverse(graph, startNodeId);

        List<String> visitedLabels = visitedIds.stream()
                .map(id -> graph.getNodes().stream()
                        .filter(n -> n.getId().equals(id))
                        .findFirst()
                        .map(Node::getLabel)
                        .orElse(""))
                .toList();

        long executionTime = System.currentTimeMillis() - startTime;

        return TraversalResultDto.builder()
                .visitedNodeIds(visitedIds)
                .visitedNodeLabels(visitedLabels)
                .executionTimeMs(executionTime)
                .build();
    }

    public TraversalResultDto dfs(Long graphId, Long startNodeId) {
        long startTime = System.currentTimeMillis();

        Graph graph = graphRepository.findById(graphId)
                .orElseThrow(() -> new RuntimeException("Graph not found with id: " + graphId));

        List<Long> visitedIds = DFS.traverse(graph, startNodeId);

        List<String> visitedLabels = visitedIds.stream()
                .map(id -> graph.getNodes().stream()
                        .filter(n -> n.getId().equals(id))
                        .findFirst()
                        .map(Node::getLabel)
                        .orElse(""))
                .toList();

        long executionTime = System.currentTimeMillis() - startTime;

        return TraversalResultDto.builder()
                .visitedNodeIds(visitedIds)
                .visitedNodeLabels(visitedLabels)
                .executionTimeMs(executionTime)
                .build();
    }


    private GraphDto mapToGraphDto(Graph graph) {
        return GraphDto.builder()
                .id(graph.getId())
                .name(graph.getName())
                .description(graph.getDescription())
                .directed(graph.isDirected())
                .weighted(graph.isWeighted())
                .nodeCount(graph.getNodes().size())
                .edgeCount(graph.getEdges().size())
                .createdAt(graph.getCreatedAt())
                .updatedAt(graph.getUpdatedAt())
                .build();

    }

    private GraphDetailDto mapToGraphDetailDto(Graph graph) {
        return GraphDetailDto.builder()
                .id(graph.getId())
                .name(graph.getName())
                .description(graph.getDescription())
                .directed(graph.isDirected())
                .weighted(graph.isWeighted())
                .nodes(graph.getNodes().stream().map(this::mapToNodeDto).toList())
                .edges(graph.getEdges().stream().map(this::mapToEdgeDto).toList())
                .createdAt(graph.getCreatedAt())
                .updatedAt(graph.getUpdatedAt())
                .build();

    }

    private NodeDto mapToNodeDto(Node node) {
        return NodeDto.builder()
                .id(node.getId())
                .label(node.getLabel())
                .x(node.getX())
                .y(node.getY())
                .build();
    }

    private EdgeDto mapToEdgeDto(Edge edge) {
        return EdgeDto.builder()
                .id(edge.getId())
                .sourceId(edge.getSource().getId())
                .targetId(edge.getTarget().getId())
                .sourceLabel(edge.getSource().getLabel())
                .targetLabel(edge.getTarget().getLabel())
                .weight(edge.getWeight())
                .build();
    }


}
