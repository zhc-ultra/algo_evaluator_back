package com.zhc.aeoj.judge.strategy;

import cn.hutool.json.JSONUtil;
import com.zhc.aeoj.model.dto.question.JudgeCase;
import com.zhc.aeoj.model.dto.question.JudgeConfig;
import com.zhc.aeoj.judge.codesandbox.model.JudgeInfo;
import com.zhc.aeoj.model.entity.Question;
import com.zhc.aeoj.model.enums.JudgeInfoMessageEnum;

import java.util.List;

/**
 * @author zhc
 * @description 默认判题策略
 * @date 2024/5/24 11:44
 **/
public class DefaultJudgeStrategy implements JudgeStrategy {

    @Override
    public JudgeInfo doJudge(JudgeContext judgeContext) {
        JudgeInfo judgeInfo = judgeContext.getJudgeInfo();
        List<String> inputList = judgeContext.getInputList();
        List<String> outputList = judgeContext.getOutputList();
        List<JudgeCase> judgeCaseList = judgeContext.getJudgeCaseList();
        Question question = judgeContext.getQuestion();
        Long memory = judgeInfo.getMemory();
        Long time = judgeInfo.getTime();

        JudgeInfo judgeInfoRes = new JudgeInfo();
        judgeInfoRes.setMemory(memory);
        judgeInfoRes.setTime(time);


        // 根据沙箱的执行结果，更新题目提交状态
        JudgeInfoMessageEnum judgeInfoMessageEnum = JudgeInfoMessageEnum.ACCEPTED;
        if (outputList.size() != inputList.size()) {
            judgeInfoMessageEnum = JudgeInfoMessageEnum.WRONG_ANSWER;
            judgeInfoRes.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoRes;
        }

        // 依次对比每一个输入输出用例
        for (int i = 0; i < judgeCaseList.size(); ++i) {
            JudgeCase jc = judgeCaseList.get(i);
            if (!jc.getOutput().equals(outputList.get(i))) {
                judgeInfoMessageEnum = JudgeInfoMessageEnum.WRONG_ANSWER;
                judgeInfoRes.setMessage(judgeInfoMessageEnum.getValue());
                return judgeInfoRes;
            }
        }

        // 判断题目限制

        String judgeCaseConfigStr = question.getJudgeConfig();
        JudgeConfig judgeConfig = JSONUtil.toBean(judgeCaseConfigStr, JudgeConfig.class);
        Long expectTime = judgeConfig.getTimeLimit();
        Long expectMemory = judgeConfig.getMemoryLimit();
        if (time > expectTime) {
            judgeInfoMessageEnum = JudgeInfoMessageEnum.TIME_LIMIT_EXCEEDED;
            judgeInfoRes.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoRes;
        }
        if (memory > expectMemory) {
            judgeInfoMessageEnum = JudgeInfoMessageEnum.MEMORY_LIMIT_EXCEEDED;
            judgeInfoRes.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoRes;
        }
        // 默认值 AC
        judgeInfoRes.setMessage(judgeInfoMessageEnum.getValue());
        return judgeInfoRes;
    }
}
