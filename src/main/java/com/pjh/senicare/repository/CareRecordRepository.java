package com.pjh.senicare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pjh.senicare.entity.CareRecordEntity;

@Repository
public interface CareRecordRepository extends JpaRepository<CareRecordEntity, Integer> {
    
    

}
