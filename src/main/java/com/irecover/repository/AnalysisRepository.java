package com.irecover.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.irecover.entity.DataAnalyze;


@Repository
public interface AnalysisRepository extends CrudRepository<DataAnalyze, Long> {

}
