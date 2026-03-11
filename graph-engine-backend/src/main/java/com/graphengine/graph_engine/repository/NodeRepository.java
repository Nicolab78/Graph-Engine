package com.graphengine.graph_engine.repository;

import com.graphengine.graph_engine.model.Node;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NodeRepository extends JpaRepository<Node,Long> {
    List<Node> findByGraphId(Long graphId);

}
