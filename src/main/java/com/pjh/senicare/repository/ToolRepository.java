package com.pjh.senicare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pjh.senicare.entity.ToolEntity;

@Repository
public interface ToolRepository extends JpaRepository<ToolEntity, Integer> {
    
    List<ToolEntity> findByOrderByToolNumberDesc();

}
