package com.zhc.aeoj.judge;

import com.zhc.aeoj.judge.strategy.DefaultJudgeStrategy;
import com.zhc.aeoj.judge.strategy.JavaLanguageJudgeStrategy;
import com.zhc.aeoj.judge.strategy.JudgeContext;
import com.zhc.aeoj.judge.strategy.JudgeStrategy;
import com.zhc.aeoj.judge.codesandbox.model.JudgeInfo;
import com.zhc.aeoj.model.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

/**
 * 判题管理（简化调用）
 */
@Service
public class JudgeManager {


    /**
     * 执行判题
     *
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext) {
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if ("java".equals(language)) {
            judgeStrategy = new JavaLanguageJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }

}
