package com.yundingshuyuan.website.repository;

import com.yundingshuyuan.website.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author Mr.h
 */
public interface AssignmentRepository extends JpaRepository<Assignment,Integer>, PagingAndSortingRepository<Assignment, Integer> {

    /**
     * 首页习作展示
     * @param status
     * @return
     */
    @Modifying
    @Query(value = "select * from t_assignment t where t.status = ?1", nativeQuery = true)
    List<Assignment> findAssignmentsByStatus(Integer status);
}
