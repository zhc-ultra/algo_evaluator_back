package com.zhc.aeoj.judge;

import com.zhc.aeoj.model.entity.QuestionSubmit;

/**
 * @author zhc
 * @description 判题服务
 * @date 2024/5/24 11:03
 **/
public interface JudgeService {
    /**
     * 判题
     */
    QuestionSubmit doJudge(long questionSubmitId);
}
