package com.graphengine.graph_engine.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "graphs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Graph {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String name;

    @Column(columnDefinition= "TEXT")
    private String description;

    @Column(nullable = false)
    @Builder.Default
    private boolean directed = false;
    
    @Column(nullable = false)
    @Builder.Default
    private boolean weighted = false;

    @OneToMany(mappedBy= "graph", cascade= CascadeType.ALL, orphanRemoval= true)
    @Builder.Default
    private List<Node> nodes = new ArrayList<>();

    @OneToMany(mappedBy= "graph", cascade= CascadeType.ALL, orphanRemoval=true)
    @Builder.Default
    private List<Edge> edges = new ArrayList<>();

    @Column(nullable=false, updatable=false)
    private LocalDateTime createdAt;

    @Column(nullable=false)
    private LocalDateTime updatedAt;


    
}
