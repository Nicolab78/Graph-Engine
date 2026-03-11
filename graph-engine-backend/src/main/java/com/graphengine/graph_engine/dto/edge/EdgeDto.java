package com.graphengine.graph_engine.dto.edge;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EdgeDto {
    private Long id;
    private Long sourceId;
    private Long targetId;
    private String sourceLabel;
    private String targetLabel;
    private Double weight;

}
