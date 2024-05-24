package com.zhc.aeoj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhc.aeoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.zhc.aeoj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.zhc.aeoj.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhc.aeoj.model.entity.User;
import com.zhc.aeoj.model.vo.QuestionSubmitVO;

/**
 * @author yinger
 * @description 针对表【question_submit(题目提交)】的数据库操作Service
 * @createDate 2024-05-21 09:29:37
 */
public interface QuestionSubmitService extends IService<QuestionSubmit> {
    /**
     * 题目提交
     */
    long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);

    /**
     * 获取查询条件
     */
    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubnmitQuertRequest);


    /**
     * 获取题目封装
     */
    QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser);

    /**
     * 分页获取题目封装
     */
    Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionPage, User loginUser);
}
