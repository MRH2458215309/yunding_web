package com.yundingshuyuan.website.controller;

import com.yundingshuyuan.website.controller.support.BaseController;
import com.yundingshuyuan.website.entity.Assignment;
import com.yundingshuyuan.website.service.AssignmentService;
import com.yundingshuyuan.website.wrapper.ResultWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Mr.h
 */
@Api(tags = "习作查询")
@RequestMapping("/select")
@RestController
@Slf4j
public class AssignmentController extends BaseController {

    @Autowired
    private AssignmentService assignmentService;

    @ApiOperation("首页习作展示")
    @PostMapping("/Assignment/status")
    public ResultWrapper selectAssignment(@Valid Assignment assignment, BindingResult bindingResult){
        validateParams(bindingResult);

        List<Assignment> assignments = assignmentService.selectAssignment(assignment);

        if (assignments.isEmpty()){
            return ResultWrapper.failure();
        } else {
            return ResultWrapper.successWithData(assignments);
        }
    }
}
