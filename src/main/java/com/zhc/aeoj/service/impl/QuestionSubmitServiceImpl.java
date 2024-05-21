package com.zhc.aeoj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhc.aeoj.common.ErrorCode;
import com.zhc.aeoj.exception.BusinessException;
import com.zhc.aeoj.model.dto.questionsubmit.QuestionSubnmitAddRequest;
import com.zhc.aeoj.model.entity.Question;
import com.zhc.aeoj.model.entity.QuestionSubmit;
import com.zhc.aeoj.model.entity.User;
import com.zhc.aeoj.model.enums.QuestionSubmitLanguageEnum;
import com.zhc.aeoj.model.enums.QuestionSubmitStateEnum;
import com.zhc.aeoj.service.QuestionService;
import com.zhc.aeoj.service.QuestionSubmitService;
import com.zhc.aeoj.mapper.QuestionSubmitMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yinger
 * @description 针对表【question_submit(题目提交)】的数据库操作Service实现
 * @createDate 2024-05-21 09:29:37
 */
@Service
public class QuestionSubmitServiceImpl extends ServiceImpl<QuestionSubmitMapper, QuestionSubmit>
        implements QuestionSubmitService {

    @Resource
    private QuestionService questionService;

    /**
     * 题目提交
     *
     * @param questionSubnmitAddRequest
     * @param loginUser
     * @return 提交记录的id
     */
    @Override
    public long doQuestionSubmit(QuestionSubnmitAddRequest questionSubnmitAddRequest, User loginUser) {
        long questionId = questionSubnmitAddRequest.getQuestionId();

        String language = questionSubnmitAddRequest.getLanguage();
        QuestionSubmitLanguageEnum languageEnum = QuestionSubmitLanguageEnum.getEnumByValue(language);
        if (languageEnum == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "编程语言错误");
        }


        Question question = questionService.getById(questionSubnmitAddRequest);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 是否已题目提交
        long userId = loginUser.getId();

        QuestionSubmit questionSubmit = new QuestionSubmit();
        questionSubmit.setUserId(userId);
        questionSubmit.setQuestionId(questionId);
        questionSubmit.setCode(questionSubnmitAddRequest.getCode());
        questionSubmit.setLanguage(language);
        // 设置初始状态 TODO 加入枚举值
        questionSubmit.setStatus(QuestionSubmitStateEnum.WAITING.getValue());

        questionSubmit.setJudgeInfo("{}");
        boolean result = this.save(questionSubmit);
        if (!result) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "数据插入异常");
        }

        return questionSubmit.getId();
    }

}




