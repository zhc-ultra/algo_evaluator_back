package com.zhc.aeoj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhc.aeoj.common.BaseResponse;
import com.zhc.aeoj.common.ErrorCode;
import com.zhc.aeoj.common.ResultUtils;
import com.zhc.aeoj.exception.BusinessException;
import com.zhc.aeoj.model.dto.questionsubmit.QuestionSubnmitAddRequest;
import com.zhc.aeoj.model.dto.questionsubmit.QuestionSubnmitQuertRequest;
import com.zhc.aeoj.model.entity.QuestionSubmit;
import com.zhc.aeoj.model.entity.User;
import com.zhc.aeoj.model.vo.QuestionSubmitVO;
import com.zhc.aeoj.service.QuestionSubmitService;
import com.zhc.aeoj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 帖子点赞接口
 */
@RestController
@RequestMapping("/question_submit")
@Slf4j
public class QuestionSubmitController {

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private UserService userService;

    /**
     * 提交题目
     * @return 题目提交的id
     */
    @PostMapping("/")
    public BaseResponse<Long> doQuestionSubmit(@RequestBody QuestionSubnmitAddRequest questionSubmitAddRequest,
                                               HttpServletRequest request) {
        if (questionSubmitAddRequest == null || questionSubmitAddRequest.getQuestionId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        final User loginUser = userService.getLoginUser(request);
        long questionSubmitId = questionSubmitService.doQuestionSubmit(questionSubmitAddRequest, loginUser);
        return ResultUtils.success(questionSubmitId);
    }

    /**
     * 分页获取题目提交（仅管理员和用户自己能获取）
     */
    @PostMapping("/list/page")
    public BaseResponse<Page<QuestionSubmitVO>> listQuestionByPage(@RequestBody QuestionSubnmitQuertRequest questionSubnmitQuertRequest,
                                                                   HttpServletRequest request) {
        long current = questionSubnmitQuertRequest.getCurrent();
        long size = questionSubnmitQuertRequest.getPageSize();
        Page<QuestionSubmit> questionSubmitPage = questionSubmitService.page(new Page<>(current, size),
                questionSubmitService.getQueryWrapper(questionSubnmitQuertRequest));
        return ResultUtils.success(questionSubmitService.getQuestionSubmitVOPage(questionSubmitPage, userService.getLoginUser(request)));
    }
}
