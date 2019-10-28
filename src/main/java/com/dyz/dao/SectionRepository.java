package com.dyz.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


import com.dyz.entity.Section;


public interface SectionRepository extends JpaRepository<Section, Integer>,JpaSpecificationExecutor<Section> {

}
