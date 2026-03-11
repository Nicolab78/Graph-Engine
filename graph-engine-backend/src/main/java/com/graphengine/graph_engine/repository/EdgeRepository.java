package com.graphengine.graph_engine.repository;

import com.graphengine.graph_engine.model.Edge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EdgeRepository extends JpaRepository<Edge,Long> {
    List<Edge> findByGraphId(Long graphId);
}
