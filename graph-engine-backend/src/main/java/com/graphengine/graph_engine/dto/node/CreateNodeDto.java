package com.graphengine.graph_engine.dto.node;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateNodeDto {
    private String label;
    private Double x;
    private Double y;
}
