package com.graphengine.graph_engine.model;

import jakarta.persistence.Column;
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
@Table(name = "nodes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Node {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable=false)
    private String label;

    @ManyToOne
    @JoinColumn(name = "graph_id", nullable=false)
    private Graph graph;

    private Double x;
    private Double y;


}
