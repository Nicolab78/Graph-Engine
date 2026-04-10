package com.graphengine.graph_engine.controller;

import com.graphengine.graph_engine.dto.algorithm.TraversalResultDto;
import com.graphengine.graph_engine.dto.edge.CreateEdgeDto;
import com.graphengine.graph_engine.dto.edge.EdgeDto;
import com.graphengine.graph_engine.dto.graph.CreateGraphDto;
import com.graphengine.graph_engine.dto.graph.GraphDetailDto;
import com.graphengine.graph_engine.dto.graph.GraphDto;
import com.graphengine.graph_engine.dto.graph.UpdateGraphDto;
import com.graphengine.graph_engine.dto.node.CreateNodeDto;
import com.graphengine.graph_engine.dto.node.NodeDto;
import com.graphengine.graph_engine.service.impl.GraphService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/graphs")
@RequiredArgsConstructor
public class GraphController {

    private final GraphService graphService;

    @PostMapping("/create")
    public ResponseEntity<?> createGraph(@Valid @RequestBody CreateGraphDto createGraphDto) {
        try{
            GraphDto createGraph = graphService.createGraph(createGraphDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createGraph);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGraphById(@PathVariable Long id) {
        try{
            GraphDto graph = graphService.getGraph(id);
            return ResponseEntity.ok(graph);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/detail")
    public ResponseEntity<?> getGraphDetailById(@PathVariable Long id) {
        try{
            GraphDetailDto graph = graphService.getGraphDetail(id);
            return ResponseEntity.ok(graph);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllGraphs() {
        try{
            List<GraphDto> graphs = graphService.getAllGraphs();
            return ResponseEntity.ok(graphs);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<?> updateGraph(@PathVariable Long id, @Valid @RequestBody UpdateGraphDto updateGraphDto) {
        try{
            GraphDto updatedGraph = graphService.updateGraph(id, updateGraphDto);
            return ResponseEntity.ok(updatedGraph);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGraph(@PathVariable Long id) {
        try{
            graphService.deleteGraph(id);
            return ResponseEntity.ok("Graph deleted successfully");
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{graphId}/nodes")
    public ResponseEntity<?> addNode(@PathVariable Long graphId, @Valid @RequestBody CreateNodeDto createNodeDto) {
        try{
            NodeDto node = graphService.addNode(graphId, createNodeDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(node);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/nodes/{nodeId}")
    public ResponseEntity<?> deleteNode(@PathVariable Long nodeId) {
        try{
            graphService.deleteNode(nodeId);
            return ResponseEntity.ok("Node deleted successfully");
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{graphId}/edges")
    public ResponseEntity<?> addEdge(@PathVariable Long graphId, @Valid @RequestBody CreateEdgeDto createEdgeDto) {
        try {
            EdgeDto edge = graphService.addEdge(graphId, createEdgeDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(edge);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/edges/{edgeId}")
    public ResponseEntity<?> deleteEdge(@PathVariable Long edgeId) {
        try{
            graphService.deleteEdge(edgeId);
            return ResponseEntity.ok("Edge deleted successfully");
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{graphId}/bfs/{startNodeId}")
    public ResponseEntity<?> bfs(@PathVariable Long graphId, @PathVariable Long startNodeId) {
        try {
            TraversalResultDto result = graphService.bfs(graphId, startNodeId);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{graphId}/dfs/{startNodeId}")
    public ResponseEntity<?> dfs(@PathVariable Long graphId, @PathVariable Long startNodeId) {
        try {
            TraversalResultDto result = graphService.dfs(graphId, startNodeId);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }











}






















