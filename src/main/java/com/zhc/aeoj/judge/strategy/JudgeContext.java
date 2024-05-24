package com.zhc.aeoj.judge.strategy;

import com.zhc.aeoj.model.dto.question.JudgeCase;
import com.zhc.aeoj.judge.codesandbox.model.JudgeInfo;
import com.zhc.aeoj.model.entity.Question;
import com.zhc.aeoj.model.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;

/**
 * @author zhc
 * @description 策略传递的参数
 * @date 2024/5/24 11:43
 **/
@Data
public class JudgeContext {

    private JudgeInfo judgeInfo;

    private List<String> inputList;

    private List<String> outputList;

    private List<JudgeCase> judgeCaseList;

    private Question question;

    private QuestionSubmit questionSubmit;

}
