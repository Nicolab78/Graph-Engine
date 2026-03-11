package com.graphengine.graph_engine.repository;

import com.graphengine.graph_engine.model.Graph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GraphRepository extends JpaRepository<Graph,Long> {

}
