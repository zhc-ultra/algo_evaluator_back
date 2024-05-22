package com.zhc.aeoj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhc.aeoj.model.dto.question.QuestionQueryRequest;
import com.zhc.aeoj.model.entity.Question;
import com.zhc.aeoj.model.entity.Question;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhc.aeoj.model.vo.QuestionVO;

import javax.servlet.http.HttpServletRequest;

/**
* @author yinger
* @description 针对表【question(题目)】的数据库操作Service
* @createDate 2024-05-21 09:20:22
*/
public interface QuestionService extends IService<Question> {
    /**
     * 校验
     */
    void validQuestion(Question question, boolean add);

    /**
     * 获取查询条件
     */
    QueryWrapper<Question> getQueryWrapper(QuestionQueryRequest questionQueryRequest);


    /**
     * 获取题目封装
     */
    QuestionVO getQuestionVO(Question question, HttpServletRequest request);

    /**
     * 分页获取题目封装
     */
    Page<QuestionVO> getQuestionVOPage(Page<Question> questionPage, HttpServletRequest request);
}
