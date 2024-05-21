package com.zhc.aeoj.service;

import com.zhc.aeoj.model.dto.questionsubmit.QuestionSubnmitAddRequest;
import com.zhc.aeoj.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhc.aeoj.model.entity.User;

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
    long doQuestionSubmit(QuestionSubnmitAddRequest questionSubmitAddRequest , User loginUser);

}
