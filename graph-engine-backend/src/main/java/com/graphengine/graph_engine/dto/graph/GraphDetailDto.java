package com.graphengine.graph_engine.dto.graph;

import com.graphengine.graph_engine.dto.edge.EdgeDto;
import com.graphengine.graph_engine.dto.node.NodeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GraphDetailDto {
    private Long id;
    private String name;
    private String description;
    private boolean directed;
    private boolean weighted;
    private List<NodeDto> nodes;
    private List<EdgeDto> edges;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
