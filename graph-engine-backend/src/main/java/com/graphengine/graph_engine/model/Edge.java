package com.graphengine.graph_engine.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "edges")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Edge {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "source_id", nullable=false)
    private Node source;

    @ManyToOne
    @JoinColumn(name = "target_id", nullable=false)
    private Node target;

    @ManyToOne
    @JoinColumn(name = "graph_id", nullable=false)
    private Graph graph;

    private Double weight;

}
