package com.yundingshuyuan.website.service;

import com.yundingshuyuan.website.entity.Assignment;
import com.yundingshuyuan.website.service.support.IBaseService;

import java.util.List;

/**
 * @author Mr.h
 */
public interface AssignmentService extends IBaseService<Assignment, Integer> {

    /**
     * 首页展示习作
     * @param assignment
     * @return
     */
    List<Assignment> selectAssignment(Assignment assignment);
}
