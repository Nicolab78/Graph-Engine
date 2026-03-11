package com.graphengine.graph_engine.dto.graph;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GraphDto {
    private String name;
    private String description;
    private boolean directed;
    private boolean weighted;
    private int nodeCount;
    private int edgeCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
