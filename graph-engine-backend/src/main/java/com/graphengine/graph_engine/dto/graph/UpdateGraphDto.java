package com.graphengine.graph_engine.dto.graph;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateGraphDto {
    private String name;
    private String description;
    private Boolean directed;
    private Boolean weighted;
}
