package com.yundingshuyuan.website.service.Impl;

import com.yundingshuyuan.website.entity.Assignment;
import com.yundingshuyuan.website.enums.ErrorCodeEnum;
import com.yundingshuyuan.website.exception.SysException;
import com.yundingshuyuan.website.repository.AssignmentRepository;
import com.yundingshuyuan.website.service.AssignmentService;
import com.yundingshuyuan.website.service.support.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mr.h
 */
@Service
public class AssignmentServiceImpl extends BaseServiceImpl<Assignment, Integer> implements AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;


    /**
     * 首页展示文章
     * @param assignment
     * @return
     */
    @Override
    public List<Assignment> selectAssignment(Assignment assignment) {
        List<Assignment> assignments = assignmentRepository.findAssignmentsByStatus(assignment.getStatus());

        if (assignments.isEmpty()){
            throw new SysException(ErrorCodeEnum.PARAM_ERROR);
        }

        return assignments;
    }
}
