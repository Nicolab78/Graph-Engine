package com.graphengine.graph_engine.dto.graph;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateGraphDto {
    private String name;
    private String description;
    private boolean directed = false;
    private boolean weighted = false;
}
