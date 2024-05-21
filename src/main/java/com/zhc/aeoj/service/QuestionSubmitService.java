package com.zhc.aeoj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhc.aeoj.model.dto.questionsubmit.QuestionSubnmitAddRequest;
import com.zhc.aeoj.model.dto.questionsubmit.QuestionSubnmitQuertRequest;
import com.zhc.aeoj.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhc.aeoj.model.entity.User;
import com.zhc.aeoj.model.vo.QuestionSubmitVO;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yinger
 * @description 针对表【question_submit(题目提交)】的数据库操作Service
 * @createDate 2024-05-21 09:29:37
 */
public interface QuestionSubmitService extends IService<QuestionSubmit> {
    /**
     * 题目提交
     *
     * @param questionSubmitAddRequest
     * @param loginUser
     * @return
     */
    long doQuestionSubmit(QuestionSubnmitAddRequest questionSubmitAddRequest, User loginUser);

    /**
     * 获取查询条件
     *
     * @param questionSubnmitQuertRequest
     * @return
     */
    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubnmitQuertRequest questionSubnmitQuertRequest);


    /**
     * 获取题目封装
     *
     * @param questionSubmit
     * @param loginUser
     * @return
     */
    QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser);

    /**
     * 分页获取题目封装
     *
     * @param questionPage
     * @param loginUser
     * @return
     */
    Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionPage, User loginUser);
}
