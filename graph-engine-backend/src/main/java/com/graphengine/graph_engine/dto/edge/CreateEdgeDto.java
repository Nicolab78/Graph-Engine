package com.graphengine.graph_engine.dto.edge;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEdgeDto {
    private Long id;
    private Long targetId;
    private Double weight;
}
