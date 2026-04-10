package com.graphengine.graph_engine.dto.algorithm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TraversalResultDto {
    private List<Long> visitedNodeIds;
    private List<String> visitedNodeLabels;
    private long executionTimeMs;
}